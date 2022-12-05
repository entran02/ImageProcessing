package controller.commands;

import model.ImageModel;
import model.ImageModelImpl;
import model.ImageProcessorModel;
import model.Pixel;
import model.PixelImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Mosaic extends AbstractCommand implements ImageCommand {
  private int numSeeds;
  private Random rand;
  /**
   * Abstract constructor for command objects.
   *
   * @param numSeeds number of seeds for mosaicing an image
   * @param fileName file name of image to mosaic
   * @param newFileName new file name of image
   */
  public Mosaic(int numSeeds, String fileName, String newFileName) {
    super(fileName, newFileName);
    if (numSeeds <= 0) {
      throw new IllegalArgumentException("Num seeds must be non-zero positive");
    }
    this.numSeeds = numSeeds;
    this.rand = new Random();
  }

  /**
   * Applies the respective command object.
   *
   * @param model
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

  private class Coord {
    private int x;
    private int y;

    public Coord(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    public double distance(int x, int y) {
      return Math.sqrt(((this.x - x)^2) + ((this.y - y)^2));
    }
  }

  public ImageModel mosaic(ImageModel img) {
    // map of coord_of_seed : list_of_pixels_belonging_to_this_cluster
    Map<Coord, ArrayList<Coord>> clusters = new HashMap<>();
    numSeeds = Math.min(numSeeds, img.getHeight() * img.getWidth());

    while (clusters.size() < numSeeds) {
      clusters.put(new Coord(rand.nextInt(img.getHeight()),
              rand.nextInt(img.getWidth())), new ArrayList<Coord>());
    }

    for (int i = 0; i < img.getHeight(); i ++) {
      for (int j = 0; j < img.getWidth(); j ++) {
        Coord closest = null;
        for (Coord c: clusters.keySet()) {
          if (closest == null || (c.distance(i, j) < closest.distance(i, j))) {
            closest = c;
          }
        }
        ArrayList<Coord> clusterMembers = clusters.get(closest);
        clusterMembers.add(new Coord(i, j));
        clusters.put(closest, clusterMembers);
      }
    }
    ImageModel newimg = new ImageModelImpl(img.getHeight(), img.getWidth(), img.getMaxValue());

    for (Map.Entry<Coord, ArrayList<Coord>> c: clusters.entrySet()) {
      if (c.getValue().size() == 0) {
        continue;
      }
      int[] avg = new int[3];
      for (Coord coord: c.getValue()) {
        avg[0] += img.getPixelAt(coord.getX(), coord.getY()).getRedChannel();
        avg[1] += img.getPixelAt(coord.getX(), coord.getY()).getGreenChannel();
        avg[2] += img.getPixelAt(coord.getX(), coord.getY()).getBlueChannel();
      }
      avg[0] /= c.getValue().size();
      avg[1] /= c.getValue().size();
      avg[2] /= c.getValue().size();
      for (Coord coord: c.getValue()) {
        newimg.updateImagePixel(coord.getX(), coord.getY(), new PixelImpl(avg[0], avg[1], avg[2]));
      }
    }

    return newimg;
  }
}
