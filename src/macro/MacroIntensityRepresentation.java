package macro;

import model.Pixel;

/**
 * Macro to represent each pixel in terms of its intensity.
 */
public class MacroIntensityRepresentation extends AMacro implements Macro {
  /**
   * Transforms one pixel to greyscale based on intensity.
   *
   * @param p pixel to transform
   * @return transformed pixel
   */
  @Override
  protected Pixel applyToPixel(Pixel p) {
    int val = (int) Math.round((p.getR() + p.getG() + p.getB()) / 3.0);
    return new Pixel(val, val, val);
  }
}
