package model;

import java.util.Objects;

/**
 * Represents a pixel from an image.
 */
public class Pixel {
  private final int r;
  private final int g;
  private final int b;

  /**
   * Constructor for a pixel with the red, green, and blue values.
   *
   * @param r the value for red
   * @param g the value for green
   * @param b the value for blue
   * @throws IllegalArgumentException if any of the values are negative or null
   */
  public Pixel(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || g < 0 || b < 0) {
      throw new IllegalArgumentException("RGB value is invalid.");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Gets red value.
   *
   * @returns red value
   */
  public int getR() {
    return this.r;
  }

  /**
   * Gets green value.
   *
   * @returns green value
   */
  public int getG() {
    return this.g;
  }

  /**
   * Gets blue value.
   *
   * @returns blue value
   */
  public int getB() {
    return this.b;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pixel pixel = (Pixel) o;
    return this.r == pixel.r && this.g == pixel.g && this.b == pixel.b;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.r, this.g, this.b);
  }
}
