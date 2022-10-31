package model;

import java.util.HashMap;
import java.util.Map;

import Macros.Macro;

public class ImageProcessingModelImpl implements ImageProcessingModel{
  private Map<String, Image> images;

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
  public Image getImage(String name) throws IllegalArgumentException{
    if (name == null) {
      throw new IllegalArgumentException("Invalid image name.");
    }
    return this.images.get(name);
  }

  /**
   * Stores image in collection.
   *
   * @param name name of image
   * @param img  the Image
   */
  @Override
  public void add(String name, Image img) throws IllegalArgumentException{
    if (name == null || img == null) {
      throw new IllegalArgumentException("Invalid image or image name.");
    }
  }

  /**
   * Removes image from stored list.
   *
   * @param name name of image to remove
   */
  @Override
  public void remove(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("Invalid image name");
    }
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
      m.apply(this.images.get(name));
  }
}
