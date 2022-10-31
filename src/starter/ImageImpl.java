package starter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ImageImpl implements Image{
  private final int width;
  private final int height;
  private final int maxVal;
  private final List<List<Pixel>> pixels;

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
   * Flips the image horizontally in-place.
   */
  @Override
  public void flipHorizontal() {
    for (List<Pixel> row : this.pixels) {
      Collections.reverse(row);
    }
  }

  /**
   * Flips the image vertically in-place.
   */
  @Override
  public void flipVertical() {
    for (int r = 0; r < height / 2; r++) {
      List<Pixel> row = this.pixels.get(r);
      this.pixels.set(r, this.pixels.get(height - r));
      this.pixels.set(height - r, row);
    }
  }
}
