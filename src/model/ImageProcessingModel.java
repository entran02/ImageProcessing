package model;

/**
 * Interface to manipulate a collection of images.
 */
public interface ImageProcessingModel {
  /**
   * Get one of the stored images.
   * @param name name of image
   * @return requested image
   */
  Image getImage(String name);

  /**
   * Stores image in collection.
   * @param name name of image
   * @param img the Image
   */
  void add(String name, Image img);

  /**
   * Removes image from stored list.
   * @param name name of image to remove
   */
  void remove(String name);

  /**
   * Applies given macro to desired image.
   * @param name name of image
   * @param m macro to apply to image
   */
  void apply(String name, Macro m);
}
