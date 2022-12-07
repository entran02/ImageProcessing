package model;

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

  /**
   * Returns value of single pixel.
   *
   * @param row the row of the pixel
   * @param col the column of the pixel
   * @return the pixel
   */
  public Pixel getPixel(int row, int col);

  /**
   * Replaces a specific pixel with given one.
   * @param row row of pixel to set
   * @param col col of pixel to set
   * @param p new Pixel value
   */
  void setPixel(int row, int col, Pixel p);

  /**
   * Replaces all pixels of this value with a new set.
   * @param pixels new pixel values
   */
  void setPixels(List<List<Pixel>> pixels);

  /**
   * Equals method to check equality between two images.
   * @param other other image
   * @return if this and other are equal
   */
  boolean equals(Object other);

  /**
   * Returns a copy of this image.
   * @return copy of this image.
   */
  Image copy();
}
