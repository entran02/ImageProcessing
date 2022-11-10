package macro;

import model.Pixel;

/**
 * Macro to convert image to greyscale with the red channel.
 */
public class MacroRedGreyscale extends AMacro implements Macro {
  /**
   * Transforms one pixel to greyscale based on red channel.
   *
   * @param p pixel to transform
   * @return transformed pixel
   */
  @Override
  protected Pixel applyToPixel(Pixel p) {
    int val = p.getR();
    return new Pixel(val, val, val);
  }
}
