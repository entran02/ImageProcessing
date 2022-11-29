package model;

/**
 * This interface represents operations that can be used to monitor the state of an image
 * model, without changing it.
 */
public interface ImageModelState {
  /**
   * Gets the height dimension of the image.
   *
   * @return an int representing height in pixels of the image.
   */
  int getHeight();

  /**
   * Gets the width dimensions of the image.
   *
   * @return an int representing width in pixels of the image.
   */
  int getWidth();

  /**
   * Gets the maximum value of a component in an image.
   *
   * @return an int representing the maximum value of a color in an image.
   */
  int getMaxValue();

  /**
   * Returns a 3d array representing the row and col positions of pixels in an image and their
   * corresponding component values.
   *
   * @return a 3d array mapping the image information.
   */
  Pixel[][] getImagePixels();

  /**
   * Returns an array that represents the R, G, B values of a single pixel.
   *
   * @param row the row of the pixel in the image.
   * @param col the column of the pixel in the image.
   * @return an array with the R, G, V values.
   */
  Pixel getPixelAt(int row, int col);

  /**
   * Returns a single value that represents a component value, either R, G, B.
   *
   * @param row        the row of the pixel in the image.
   * @param col        the column of the pixel in the image.
   * @param compNumber the number from 0-2 representing R, G, or B respectively.
   * @return an int the value of the specified component.
   */
  int getChannelValueAt(int row, int col, int compNumber);
}
