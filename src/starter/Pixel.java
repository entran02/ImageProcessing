package starter;

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
   * @param r the value for red
   * @param g the value for green
   * @param b the value for blue
   * @throws IllegalArgumentException if any of the values are negative or null
   */
  public Pixel(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || g < 0 || b < 0) {
      throw new IllegalArgumentException("RGB value is invalid.");
    }
    this.r = Objects.requireNonNull(r);
    this.g = Objects.requireNonNull(g);
    this.b = Objects.requireNonNull(b);
  }

  /**
   * Gets red value.
   * @returns red value
   */
  public int getR(){
    return this.r;
  }

  /**
   * Gets green value.
   * @returns green value
   */
  public int getG(){
    return this.g;
  }

  /**
   * Gets blue value.
   * @returns red value
   */
  public int getB(){
    return this.b;
  }
}
