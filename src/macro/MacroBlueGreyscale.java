package macro;

import model.Pixel;

/**
 * Macro to convert image to greyscale with the blue channel.
 */
public class MacroBlueGreyscale extends AMacro implements Macro {
  /**
   * Transforms one pixel to greyscale based on blue channel.
   *
   * @param p pixel to transform
   * @return transformed pixel
   */
  @Override
  protected Pixel applyToPixel(Pixel p) {
    int val = p.getB();
    return new Pixel(val, val, val);
  }

}
