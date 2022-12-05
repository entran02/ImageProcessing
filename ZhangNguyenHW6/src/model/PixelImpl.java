package model;

import java.awt.Color;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a color pixel.
 */
public class PixelImpl implements Pixel {
  private int[] channelValues;
  private int redChannel;
  private int greenChannel;
  private int blueChannel;


  /**
   * Constructor for a color pixel to make a pixel made of one color.
   *
   * @param color array of integer values representing the red, green, and blue channels.
   */
  public PixelImpl(int[] color) {
    this.channelValues = color;
    this.redChannel = channelValues[0];
    this.greenChannel = channelValues[1];
    this.blueChannel = channelValues[2];
  }

  /**
   * Constructor for a color pixel made of channel values.
   *
   * @param redChannel   the integer value for the red channel.
   * @param greenChannel the integer value for the green channel.
   * @param blueChannel  the integer value for the blue channel.
   */
  public PixelImpl(int redChannel, int greenChannel, int blueChannel) {
    this.channelValues = new int[]{redChannel, greenChannel, blueChannel};
    this.redChannel = redChannel;
    this.greenChannel = greenChannel;
    this.blueChannel = blueChannel;
  }

  @Override
  public Color getColor() {
    return new Color(redChannel, greenChannel, blueChannel);
  }

  @Override
  public int[] getChannelValues() {
    return this.channelValues;
  }

  @Override
  public int getRedChannel() {
    return this.redChannel;
  }

  @Override
  public int getGreenChannel() {
    return this.greenChannel;
  }

  @Override
  public int getBlueChannel() {
    return this.blueChannel;
  }

  @Override
  public void updatePixelRedChannel(int red) {
    this.redChannel = red;
    this.channelValues = new int[] {red, this.getGreenChannel(), this.getBlueChannel()};
  }

  @Override
  public void updatePixelGreenChannel(int green) {
    this.greenChannel = green;
    this.channelValues = new int[] {this.redChannel, green, this.getBlueChannel()};
  }

  @Override
  public void updatePixelBlueChannel(int blue) {
    this.blueChannel = blue;
    this.channelValues = new int[] {this.redChannel, this.greenChannel, blue};
  }

  @Override
  public void clamp() {
    if (redChannel > 255) {
      this.redChannel = 255;
    } else if (redChannel < 0) {
      this.redChannel = 0;
    }

    if (greenChannel > 255) {
      this.greenChannel = 255;
    } else if (greenChannel < 0) {
      this.greenChannel = 0;
    }

    if (blueChannel > 255) {
      this.blueChannel = 255;
    } else if (blueChannel < 0) {
      this.blueChannel = 0;
    }
  }

  @Override
  public boolean equals(Object pixel) {
    if (this == pixel) {
      return true;
    }

    if (!(pixel instanceof PixelImpl)) {
      return false;
    }

    PixelImpl pix = (PixelImpl) pixel;

    return Arrays.equals(this.channelValues, pix.channelValues)
            && this.redChannel == pix.redChannel
            && this.greenChannel == pix.greenChannel
            && this.blueChannel == pix.blueChannel;
  }

  @Override
  public int hashCode() {
    int channelHash = Arrays.hashCode(channelValues);
    return channelHash * Objects.hash(redChannel, greenChannel, blueChannel);
  }

  @Override
  public String toString() {
    return this.channelValues[0] + "\n" +
            this.channelValues[1] + "\n" +
            this.channelValues[2] + "\n";
  }
}
