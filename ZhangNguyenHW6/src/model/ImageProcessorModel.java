package model;

/**
 * This interface represents the operations offered by the image processor
 * model. One processor represents a single instance of a processor.
 */
public interface ImageProcessorModel {
  /**
   * Horizontally flips the given image name and
   * designates the updated image to a given destination.
   *
   * @param fileName    the original image name.
   * @param newFileName the new image name.
   */
  void flip(String type, String fileName, String newFileName);

  /**
   * Brightens or darkens an image by adding a constant value to all the RGB values.
   *
   * @param value the incrementation value that should be added to the image's RGB values.
   *              A positive value brightens, while a negative value darkens.
   */
  void brighten(int value, String fileName, String newFileName);

  /**
   * Converts a color image to greyscale by luma, value, intensity, or specified color component.
   *
   * @param type        the type of greyscale user wants to do.
   * @param fileName    the file name they want to do it on.
   * @param newFileName the name of the image after the greyscale has been applied.
   */
  void colorTransformation(String type, String fileName, String newFileName);

  void filter(String type, String fileName, String newFileName);

  /**
   * Gets a specified image in the hash map of images currently loaded into the processor.
   *
   * @param fileName the specified image name the user is looking for.
   * @return the image corresponding to the given name.
   */
  ImageModel getImage(String fileName);

  /**
   * Adds an image to the hash map of currently loaded images.
   *
   * @param fileName the name that will correspond with the given image in the hashmap.
   * @param img      the image to be inserted into the hashmap.
   */
  void addImage(String fileName, ImageModel img);

  boolean hasImage(String imageName);

}
