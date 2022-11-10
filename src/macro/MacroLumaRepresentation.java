package macro;

import model.Pixel;

/**
 * Macro to represent each pixel in terms of its Luma.
 */
public class MacroLumaRepresentation extends AMacro implements Macro {
  /**
   * Transforms one pixel to greyscale based on Luma.
   *
   * @param p pixel to transform
   * @return transformed pixel
   */
  @Override
  protected Pixel applyToPixel(Pixel p) {
    int val = (int) Math.round((p.getR() * .2126) + (p.getG() * .7152)
            + (p.getB() * .0722));
    return new Pixel(val, val, val);
  }
}
