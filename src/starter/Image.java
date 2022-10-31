package starter;

import java.util.List;

/**
 * Represents an image.
 */
public interface Image {
  /**
   * Finds the width of the image.
   * @returns the width
   */
  int getWidth();

  /**
   * Finds the height of the image.
   * @returns the height
   */
  int getHeight();

  /**
   * Finds the maximum value for the colors of the pixels of the image.
   * @returns the maximum value.
   */
  int getMaxVal();

  /**
   * Finds the pixels that make up the image.
   * @returns a list of the pixels.
   */
  List<List<Pixel>> getPixels();
}
