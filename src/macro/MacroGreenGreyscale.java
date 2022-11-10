package macro;

import model.Pixel;

/**
 * Macro to convert image to greyscale with the green channel.
 */
public class MacroGreenGreyscale extends AMacro implements Macro {
  /**
   * Transforms one pixel to greyscale based on green channel.
   *
   * @param p pixel to transform
   * @return transformed pixel
   */
  @Override
  protected Pixel applyToPixel(Pixel p) {
    int val = p.getG();
    return new Pixel(val, val, val);
  }
}
