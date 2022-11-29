package model;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents an image of type PPM along with all operations and state observers.
 */
public class ImageModelImpl implements ImageModel {
  private Pixel[][] imagePixels;
  private int height;
  private int width;
  private int maxValue;

  /**
   * Constructs an instance of PPMImage Model representing a single PPM image.
   *
   * @param imagePixels the 3d array that maps the information that makes up the image.
   * @param height      the height of the image.
   * @param width       the width of the image.
   * @param maxValue    the maximum value of a component of the image.
   */
  public ImageModelImpl(Pixel[][] imagePixels, int height, int width, int maxValue) {
    this.imagePixels = imagePixels;
    this.height = height;
    this.width = width;
    this.maxValue = maxValue;
  }

  /**
   * The default constructor that creates an empty image represented by an empty 3d array.
   *
   * @param height   the height of the image.
   * @param width    the width of the image.
   * @param maxValue the maximum value of a component of the image.
   */
  public ImageModelImpl(int height, int width, int maxValue) {
    this(new Pixel[height][width], height, width, maxValue);
  }

  /**
   * Gets the height dimension of the image.
   *
   * @return an int representing height in pixels of the image.
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Gets the width dimensions of the image.
   *
   * @return an int representing width in pixels of the image.
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Gets the maximum value of a component in an image.
   *
   * @return an int representing the maximum value of a color in an image.
   */
  public int getMaxValue() {
    return this.maxValue;
  }

  /**
   * Returns a 3d array representing the row and col positions of pixels in an image and their
   * corresponding component values.
   *
   * @return a 3d array mapping the image information.
   */
  public Pixel[][] getImagePixels() {
    return this.imagePixels;
  }

  /**
   * Returns an array that represents the R, G, B values of a single pixel.
   *
   * @param row the row of the pixel in the image.
   * @param col the column of the pixel in the image.
   * @return an array with the R, G, B values.
   */
  public Pixel getPixelAt(int row, int col) {
    return this.imagePixels[row][col];
  }

  /**
   * Returns a single value that represents a component value, either R, G, B.
   *
   * @param row     the row of the pixel in the image.
   * @param col     the column of the pixel in the image.
   * @param channel the number from 0-2 representing R, G, or B respectively.
   * @return an int the value of the specified component.
   */
  public int getChannelValueAt(int row, int col, int channel) {
    if (channel == 3) {
      int red = this.imagePixels[row][col].getRedChannel();
      int green = this.imagePixels[row][col].getGreenChannel();
      int blue = this.imagePixels[row][col].getBlueChannel();
      return red + green + blue / 3;
    } else {
      int[] channelValues = this.imagePixels[row][col].getChannelValues();
      int channelValue = channelValues[channel];

      return channelValue;
    }
  }

  /**
   * Updates the image component at the specified pixel and component.
   *
   * @param row       the row of the pixel in the image.
   * @param col       the column of the pixel in the image.
   * @param compIndex the number from 0-2 representing R, G, or B respectively.
   * @param newValue  the new value to replace the current value of the component
   */
  public void updateImageComp(int row, int col, int compIndex, int[] newValue) {
    imagePixels[row][col] = new PixelImpl(newValue);
  }

  /**
   * Updates the components pixel at a given row and col.
   *
   * @param row      the row of the pixel in the image.
   * @param col      the column of the pixel in the image.
   * @param newValue an array representing the new components that make up the pixel.
   */
  public void updateImagePixel(int row, int col, Pixel newValue) {
    imagePixels[row][col] = newValue;
  }

  /**
   * Creates a string that represents all the image data.
   *
   * @return a sttring representing all the image data.
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder.append("P3\n");
    builder.append(this.width + " " + this.height + "\n");
    builder.append(this.maxValue + "\n");

    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        builder.append(this.imagePixels[row][col].toString());
      }
    }

    return builder.toString();
  }

  /**
   * Checks equivalence of two images.
   *
   * @param image the image to be checked with this image.
   * @return a boolean representing their equivalence.
   */
  @Override
  public boolean equals(Object image) {
    if (this == image) {
      return true;
    }

    if (!(image instanceof ImageModelImpl)) {
      return false;
    }

    ImageModelImpl model = (ImageModelImpl) image;

    return this.width == model.width
            && this.height == model.height
            && this.maxValue == model.maxValue;
  }

  /**
   * Produces a hash code representing the image.
   *
   * @return a hash code representing the image.
   */
  @Override
  public int hashCode() {
    int hash = Arrays.deepHashCode(imagePixels);
    return hash * Objects.hash(width, height, maxValue);
  }
}
