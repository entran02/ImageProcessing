package controller;

/**
 * This interface represents the controller of the image model.
 * The controller communicates with the ImageModelState.
 */
public interface ImageController {

  /**
   * Runs the image processor.
   */
  void runImageProcessor();

  /**
   * Loads an image from a file, creates an image representation, and adds it to the existing map of
   * loaded images.
   *
   * @param filename    Filename of the image to be loaded as a String.
   * @param newFileName the name the user wants to refer to the image as in the future.
   */
  void load(String filename, String newFileName);

  /**
   * Saves an image to the PPM format.
   * The PPM format is a simple, text-based file format to store images.
   * It contains a dump of the RGB values of each pixel, row-wise.
   *
   * @param fileName     the file path or name of where the user wants to save the image.
   * @param newImageName the name of the file they want to save it as.
   */
  void save(String fileName, String newImageName, String fileExtension);
}
