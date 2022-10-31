package model;

import java.util.List;
import java.util.Objects;

public class ImageImpl implements Image {
  private final int width;
  private final int height;
  private final int maxVal;
  private List<List<Pixel>> pixels;

  /**
   * Constructor for an image.
   * @param width width of the image
   * @param height height of the image
   * @param maxVal maximum value of the colors of the pixels for the image
   * @param pixels a list of the pixels that make the image
   * @throws IllegalArgumentException if any of the values are invalid or null
   */
  public ImageImpl(int width, int height, int maxVal, List<List<Pixel>> pixels)
          throws IllegalArgumentException{
    if (width != pixels.get(0).size() || height != pixels.size() || maxVal < 0) {
      throw new IllegalArgumentException("Invalid height, width, or maxVal");
    }
    this.width = Objects.requireNonNull(width);
    this.height = Objects.requireNonNull(height);
    this.maxVal = Objects.requireNonNull(maxVal);
    this.pixels = Objects.requireNonNull(pixels);
  }

  /**
   * Finds the width of the image.
   *
   * @returns the width
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Finds the height of the image.
   *
   * @returns the height
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Finds the maximum value for the colors of the pixels of the image.
   *
   * @returns the maximum value.
   */
  @Override
  public int getMaxVal() {
    return this.maxVal;
  }

  /**
   * Finds the pixels that make up the image.
   *
   * @returns a list of the pixels.
   */
  @Override
  public List<List<Pixel>> getPixels() {
    return this.pixels;
  }

  /**
   * Replaces a specific pixel with given one.
   *
   * @param row row of pixel to set
   * @param col col of pixel to set
   * @param p   new Pixel value
   */
  @Override
  public void setPixel(int row, int col, Pixel p) {
    this.pixels.get(row).set(col, p);
  }

  /**
   * Replaces all pixels of this value with a new set.
   *
   * @param pixels new pixel values
   */
  @Override
  public void setPixels(List<List<Pixel>> pixels) {
    if (width != pixels.get(0).size() || height != pixels.size()) {
      throw new IllegalArgumentException("Invalid new pixel size");
    }
    this.pixels = pixels;
  }
}
