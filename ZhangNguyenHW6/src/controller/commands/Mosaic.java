package controller.commands;

import model.ImageModel;
import model.ImageModelImpl;
import model.ImageProcessorModel;
import model.PixelImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * Command to apply mosaic effect on an ImageModel.
 */
public class Mosaic extends AbstractCommand implements ImageCommand {
  private int numSeeds;
  private Random rand;

  /**
   * Constructor to apply mosaic effect on an Image.
   *
   * @param numSeeds    number of seeds for mosaicing an image
   * @param fileName    file name of image to mosaic
   * @param newFileName new file name of image
   */
  public Mosaic(int numSeeds, String fileName, String newFileName) {
    this(numSeeds, fileName, newFileName, new Random());
  }

  /**
   * Constructor to apply mosaic effect with given Random object.
   *
   * @param numSeeds    number of seeds for mosaicing an image
   * @param fileName    filename of image to mosaic
   * @param newFileName new file name of image
   * @param rand        random object used to generate seeds
   */
  public Mosaic(int numSeeds, String fileName, String newFileName, Random rand) {
    super(fileName, newFileName);
    if (numSeeds <= 0) {
      throw new IllegalArgumentException("Num seeds must be non-zero positive");
    }
    this.numSeeds = numSeeds;
    this.rand = Objects.requireNonNull(rand);
  }

  /**
   * Applies the respective command object.
   *
   * @param model Image Processor Model that stores the images.
   */
  @Override
  public void apply(ImageProcessorModel model) {
    if (model.hasImage(this.fileName)) {
      try {
        model.addImage(newFileName, this.mosaic(model.getImage(fileName)));
      } catch (NullPointerException npe) {
        throw new IllegalArgumentException("Not a valid operation type.");
      }
    } else {
      throw new IllegalArgumentException("Image name doesn't exist.");
    }
  }

  /**
   * Represents an X-Y coordinate pair.
   */
  private class Coord {
    private int x;
    private int y;

    /**
     * Constructor for setting x-y coordinate pair.
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public Coord(int x, int y) {
      this.x = x;
      this.y = y;
    }

    /**
     * Get x coordinate.
     *
     * @return x coordinate
     */
    public int getX() {
      return x;
    }

    /**
     * Get y coordinate.
     *
     * @return y coordinate
     */
    public int getY() {
      return y;
    }

    /**
     * Compute straight-line distance to an x-y pair.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return distance from this to given x-y coordinates
     */
    public double distance(int x, int y) {
      return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow((this.y - y), 2));
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }

      if (!(o instanceof Coord)) {
        return false;
      }
      Coord c = (Coord) o;

      return c.getX() == x && c.getY() == y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }

  /**
   * Applies mosaic effect to given image. Returns a copy of image with mosaic effect.
   *
   * @param img image to apply effect on
   * @return image with mosaic effect
   */
  private ImageModel mosaic(ImageModel img) {
    // map of coord_of_seed : list_of_pixels_belonging_to_this_cluster
    Map<Coord, ArrayList<Coord>> clusters = new HashMap<>();
    numSeeds = Math.min(numSeeds, img.getHeight() * img.getWidth());

    while (clusters.size() < numSeeds) {
      clusters.put(new Coord(rand.nextInt(img.getWidth()),
              rand.nextInt(img.getHeight())), new ArrayList<Coord>());
    }
    this.sortIntoCluster(clusters, img);

    ImageModel newimg = new ImageModelImpl(img.getHeight(), img.getWidth(), img.getMaxValue());

    for (Map.Entry<Coord, ArrayList<Coord>> c : clusters.entrySet()) {
      if (c.getValue().size() == 0) {
        continue;
      }
      int[] avg = new int[3];
      for (Coord coord : c.getValue()) {
        avg[0] += img.getPixelAt(coord.getY(), coord.getX()).getRedChannel();
        avg[1] += img.getPixelAt(coord.getY(), coord.getX()).getGreenChannel();
        avg[2] += img.getPixelAt(coord.getY(), coord.getX()).getBlueChannel();
      }
      avg[0] /= c.getValue().size();
      avg[1] /= c.getValue().size();
      avg[2] /= c.getValue().size();
      for (Coord coord : c.getValue()) {
        newimg.updateImagePixel(coord.getY(), coord.getX(), new PixelImpl(avg[0], avg[1], avg[2]));
      }
    }

    return newimg;
  }

  /**
   * Partitions the seeds into sectors, then finds the closest seed to each pixel.
   *
   * @param clusters map of seed: list of closest pixels
   * @param img      image to apply effect on
   */
  private void sortIntoCluster(Map<Coord, ArrayList<Coord>> clusters,
                               ImageModel img) {
    // sort the seeds into rectangular "sectors",
    // each pixel only looks for seeds in the 9 closest sectors
    int sectorX = img.getWidth() / 10;
    int sectorY = img.getHeight() / 10;

    // sector(xy coord of top left corner) : list_of_seeds_in_sector
    Map<Coord, ArrayList<Coord>> sectors = new HashMap<>();
    for (int i = 0; i < 11; i++) {
      for (int j = 0; j < 11; j++) {
        sectors.put(new Coord(i * sectorX, j * sectorY), new ArrayList<Coord>());
      }
    }

    for (Coord c : clusters.keySet()) {
      sectors.get(new Coord(Math.min((c.getX() / sectorX) * sectorX, sectorX * 10),
              Math.min((c.getY() / sectorY) * sectorY, sectorY * 10))).add(c);
    }

    for (int i = 0; i < img.getWidth(); i++) {
      for (int j = 0; j < img.getHeight(); j++) {
        Coord closest = null;
        double closest_distance = Double.MAX_VALUE;
        for (Coord c : this.mergeSectors(sectors, sectorX, sectorY, i, j)) {
          if (closest == null || (c.distance(i, j) < closest_distance)) {
            closest = c;
            closest_distance = closest.distance(i, j);
          }
        }
        clusters.get(closest).add(new Coord(i, j));
      }
    }
  }

  private ArrayList<Coord> mergeSectors(Map<Coord, ArrayList<Coord>> sectors,
                                        int sectorX, int sectorY, int x, int y) {
    ArrayList<Coord> seedsToCheck = new ArrayList<Coord>();
    int xStart = Math.min((x / sectorX) * sectorX, sectorX * 10) - sectorX;
    int yStart = Math.min((y / sectorY) * sectorY, sectorY * 10) - sectorY;

    for (int i = xStart; i <= xStart + 2 * sectorX; i += sectorX) {
      for (int j = yStart; j <= yStart + 2 * sectorY; j += sectorY) {
        if (sectors.containsKey(new Coord(i, j))) {
          seedsToCheck.addAll(sectors.get(new Coord(i, j)));
        }
      }
    }
    if (seedsToCheck.size() <= numSeeds / 100) {
      // if the seed count is low or in the unlikely chance there are very few seeds
      // in the nearby sectors, check with all seeds
      for (ArrayList<Coord> coords : sectors.values()) {
        seedsToCheck.addAll(coords);
      }
    }
    return seedsToCheck;
  }
}
