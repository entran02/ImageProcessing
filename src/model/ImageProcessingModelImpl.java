package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import macro.Macro;

/**
 * Implementation of ImageProcessingModel, which stores a map of images with names.
 */
public class ImageProcessingModelImpl implements ImageProcessingModel {
  private Map<String, Image> images;

  /**
   * Constructor that instantiates the empty hashmap.
   */
  public ImageProcessingModelImpl() {
    this.images = new HashMap<String, Image>();
  }

  /**
   * Get one of the stored images.
   *
   * @param name name of image
   * @return requested image
   */
  @Override
  public Image getImage(String name) throws IllegalArgumentException {
    this.imageExists(name);
    return this.images.get(name);
  }

  /**
   * Stores image in collection. If name is duplicate, overwrites original image
   *
   * @param name name of image
   * @param img  the Image
   */
  @Override
  public void add(String name, Image img) throws IllegalArgumentException {
    if (name == null || name.length() == 0 || img == null) {
      throw new IllegalArgumentException("Invalid image or image name.");
    }
    this.images.put(name, img);
  }

  /**
   * Removes image from stored list.
   *
   * @param name name of image to remove
   */
  @Override
  public void remove(String name) throws IllegalArgumentException {
    this.imageExists(name);
    images.remove(name);
  }

  /**
   * Applies given macro to desired image.
   *
   * @param name name of image
   * @param m    macro to apply to image
   */
  @Override
  public void apply(String name, Macro m) {
    m.apply(this.getImage(name));
  }

  /**
   * Copies one image with a new name.
   *
   * @param srcImage  Image to copy
   * @param destImage New image name
   */
  @Override
  public void copy(String srcImage, String destImage) {
    this.imageExists(srcImage);
    Image src = this.images.get(srcImage);
    List<List<Pixel>> pixels = new ArrayList<>(new ArrayList<>());
    for (List<Pixel> r : src.getPixels()) {
      List<Pixel> row = new ArrayList<>();
      for (Pixel p : r) {
        row.add(new Pixel(p.getR(), p.getG(), p.getB()));
      }
      pixels.add(row);
    }
    this.add(destImage,
            new ImageImpl(src.getWidth(), src.getHeight(), src.getMaxVal(), pixels));
  }

  private void imageExists(String name) throws IllegalArgumentException {
    if (name == null || name.length() == 0) {
      throw new IllegalArgumentException("Invalid image name");
    }
    if (images.get(name) == null) {
      throw new IllegalArgumentException("Image does not exist");
    }
  }
}
