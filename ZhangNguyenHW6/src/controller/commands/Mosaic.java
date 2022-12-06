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
      return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow((this.y - y), 2));
    }
  }

  private ImageModel mosaic(ImageModel img) {
    // map of coord_of_seed : list_of_pixels_belonging_to_this_cluster
    Map<Coord, ArrayList<Coord>> clusters = new HashMap<>();
    numSeeds = Math.min(numSeeds, img.getHeight() * img.getWidth());

    while (clusters.size() < numSeeds) {
      clusters.put(new Coord(rand.nextInt(img.getHeight()),
              rand.nextInt(img.getWidth())), new ArrayList<Coord>());
    }
    long startTime = System.currentTimeMillis();
    this.sortIntoCluster(clusters, img);
    System.out.println("sorting took: " + (System.currentTimeMillis() - startTime));

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

//  private void sortIntoCluster(Map<Coord, ArrayList<Coord>> clusters,
//                               ImageModel img) {
//    // sort the seeds into "sectors", each pixel only looks for seeds in the 9 closest sectors
//    int sectorX = img.getWidth() / 10;
//    int sectorY = img.getHeight() / 10;
//
//    // sector : list_of_seeds_in_sector
//    Map<Coord, ArrayList<Coord>> sectors = new HashMap<>();
//    for (int i = 0; i < 11; i ++) {
//      for (int j = 0; j < 11; j ++) {
//        sectors.put(new Coord(i*sectorX, j*sectorY), new ArrayList<Coord>());
//      }
//      // overflow bucket due to integer math
//      sectors.put(new Coord(i*sectorX, img.getHeight()), new ArrayList<>());
//    }
//    sectors.put(new Coord(img.getWidth(), img.getHeight()), new ArrayList<>());
//
//    for (Coord c: clusters.keySet()) {
//
//    }
//
//    for (int i = 0; i < img.getHeight(); i ++) {
//      for (int j = 0; j < img.getWidth(); j ++) {
//        Coord closest = null;
//        double closest_distance = Double.MAX_VALUE;
//        for (Coord c: clusters.keySet()) {
//          if (closest == null || (c.distance(i, j) < closest_distance)) {
//            closest = c;
//            closest_distance = closest.distance(i, j);
//          }
//        }
//        ArrayList<Coord> clusterMembers = clusters.get(closest);
//        clusterMembers.add(new Coord(i, j));
//        clusters.put(closest, clusterMembers);
//      }
//    }
//  }

  private void sortIntoCluster(Map<Coord, ArrayList<Coord>> clusters,
                                                       ImageModel img) {
    for (int i = 0; i < img.getHeight(); i ++) {
      for (int j = 0; j < img.getWidth(); j ++) {
        Coord closest = null;
        double closest_distance = Double.MAX_VALUE;
        for (Coord c: clusters.keySet()) {
          if (closest == null || (c.distance(i, j) < closest_distance)) {
            closest = c;
            closest_distance = closest.distance(i, j);
          }
        }
        clusters.get(closest).add(new Coord(i, j));
      }
    }
  }
}
