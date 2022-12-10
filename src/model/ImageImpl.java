package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of Image. Stores dimensions and pixel values of a single image.
 */
public class ImageImpl implements Image {
  private int width;
  private int height;
  private int maxVal;
  private List<List<Pixel>> pixels;

  /**
   * Constructor for an image.
   *
   * @param width  width of the image
   * @param height height of the image
   * @param maxVal maximum value of the colors of the pixels for the image
   * @param pixels a list of the pixels that make the image
   * @throws IllegalArgumentException if any of the values are invalid or null
   */
  public ImageImpl(int width, int height, int maxVal, List<List<Pixel>> pixels)
          throws IllegalArgumentException {
    if (pixels.size() == 0 || width != pixels.get(0).size()
            || height != pixels.size() || maxVal < 0) {
      throw new IllegalArgumentException("Invalid height, width, or maxVal");
    }
    for (List<Pixel> r : pixels) {
      for (Pixel p : r) {
        if (p.getR() > maxVal || p.getG() > maxVal || p.getB() > maxVal) {
          throw new IllegalArgumentException("Maxvalue exceeded");
        }
      }
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
   * Returns value of single pixel.
   *
   * @param row the row of the pixel
   * @param col the column of the pixel
   * @return the pixel
   */
  public Pixel getPixel(int row, int col) {
    try {
      return this.pixels.get(row).get(col);
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("invalid row/col");
    }
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
    if (p == null) {
      throw new IllegalArgumentException("Null pixel");
    }
    try {
      this.pixels.get(row).set(col, p);
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("invalid row/col");
    }
  }

  /**
   * Replaces all pixels of this value with a new set. Cannot change height or width.
   *
   * @param pixels new pixel values
   */
  @Override
  public void setPixels(List<List<Pixel>> pixels) {
    if (pixels == null || width != pixels.get(0).size() || height != pixels.size()) {
      throw new IllegalArgumentException("Invalid new pixel size");
    }
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        setPixel(i, j, pixels.get(i).get(j));
      }
    }
  }

  /**
   * Equals method to check equality between two images.
   *
   * @param other other image
   * @return if this and other are equal
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other instanceof Image) {
      Image otherImage = (Image) other;
      if (this.width != otherImage.getWidth() || this.height != otherImage.getHeight()
              || this.maxVal != otherImage.getMaxVal()) {
        return false;
      }
      for (int i = 0; i < this.height; i++) {
        for (int j = 0; j < this.width; j++) {
          if (!this.pixels.get(i).get(j).equals(otherImage.getPixels().get(i).get(j))) {
            return false;
          }
        }
      }
      return true;
    }
    return false;
  }

  /**
   * Returns a copy of this image.
   *
   * @return copy of this image.
   */
  @Override
  public Image copy() {
    List<List<Pixel>> pix = new ArrayList<>(new ArrayList<>());
    for (List<Pixel> row : this.pixels) {
      List<Pixel> r = new ArrayList<>();
      for (Pixel p : row) {
        r.add(p.copy());
      }
      pix.add(r);
    }
    return new ImageImpl(this.width, this.height, this.maxVal, pix);
  }

  /**
   * Resizes this image to given width and height.
   *
   * @param w new width of this image
   * @param h new height of this image
   */
  @Override
  public void setSize(int w, int h) {
    if (w <= 0 || h <= 0) {
      throw new IllegalArgumentException("Width or height cannot be negative or zero.");
    }
    this.width = w;
    this.height = h;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.width, this.height, this.maxVal, this.pixels);
  }

}
