package model;

import java.awt.Color;

/**
 * Represents the operations to be done on a Pixel.
 */
public interface Pixel {

  /**
   * Gets the color of the pixel.
   * @return Color of the pixel.
   */
  Color getColor();

  /**
   * Gets the channel values of a pixel.
   * @return an integer array of the pixel's 3 channel values (RGB).
   */
  int[] getChannelValues();

  /**
   * Gets the red channel value of a pixel.
   * @return the value from 0-255 of the red channel in the pixel.
   */
  int getRedChannel();

  /**
   * Gets the green channel value of a pixel.
   * @return the value from 0-255 of the green channel in the pixel.
   */
  int getGreenChannel();

  /**
   * Gets the blue channel value of a pixel.
   * @return the value from 0-255 of the blue channel in the pixel.
   */
  int getBlueChannel();

  /**
   * Changes the value of the red channel in a pixel.
   * @param red value to change the red channel to.
   */
  void updatePixelRedChannel(int red);

  /**
   * Changes the value of the red channel in a pixel.
   * @param green value to change the red channel to.
   */
  void updatePixelGreenChannel(int green);

  /**
   * Changes the value of the red channel in a pixel.
   * @param blue value to change the red channel to.
   */
  void updatePixelBlueChannel(int blue);

  /**
   * Clamps the value of the channels at a max of 255.
   */
  void clamp();


  /**
   * Overrides the equals method for comparing two objects.
   * @param pixel the Pixel Object being compared to a Pixel.
   * @return boolean if the pixel objects are equal.
   */
  boolean equals(Object pixel);

  /**
   * Overrides the hashCode method for comparing two objects.
   * @return boolean if the hashCodes are equal.
   */
  int hashCode();


  /**
   * Overrides the toString method for the pixel object.
   * @return the pixel channel values as a string as they would be seen in a PPM.
   */
  String toString();

}