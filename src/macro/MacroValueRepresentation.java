package macro;

import model.Pixel;

/**
 * Macro to represent each pixel in terms of its pixel values.
 */
public class MacroValueRepresentation extends AMacro implements Macro {
  /**
   * Transforms one pixel to greyscale based on pixel value.
   *
   * @param p pixel to transform
   * @return transformed pixel
   */
  @Override
  protected Pixel applyToPixel(Pixel p) {
    int val = Math.max(p.getR(), Math.max(p.getG(), p.getB()));
    return new Pixel(val, val, val);
  }
}
