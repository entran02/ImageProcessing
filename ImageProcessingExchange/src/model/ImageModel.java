package model;

/**
 * This interface represents the operations offered by the image model. One image model represents
 * one image.
 */
public interface ImageModel extends ImageModelState {

  /**
   * Updates the components pixel at a given row and col.
   *
   * @param row      the row of the pixel in the image.
   * @param col      the column of the pixel in the image.
   * @param newValue an array representing the new components that make up the pixel.
   */
  void updateImagePixel(int row, int col, Pixel newValue);

  /**
   * Checks equivalence of two images.
   *
   * @param image the image to be checked with this image.
   * @return a boolean representing their equivalence.
   */
  boolean equals(Object image);

  /**
   * Produces a hash code representing the image.
   *
   * @return a hash code representing the image.
   */
  int hashCode();
}
