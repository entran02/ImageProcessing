package model;

/**
 * Interface for Macros for manipulating images.
 */
public interface Macro {
  /**
   * Apply this macro to the image.
   * @param img image to apply macro to
   */
  void apply(Image img);
}
