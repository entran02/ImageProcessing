package controller;

/**
 * Represents the controller for an image processing application that is controlled
 * via text instructions.
 */
public interface ImageProcessingController {
  /**
   * Runs the image processing program.
   *
   * @throws IllegalStateException if unable to read input or give output
   */
  void run() throws IllegalStateException;

}
