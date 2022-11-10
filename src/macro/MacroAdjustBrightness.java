package macro;

import model.Pixel;

/**
 * Macro to brighten or darken an image depending on the brightness value.
 */
public class MacroAdjustBrightness extends AMacro implements Macro {
  private final int value;

  /**
   * Constructor to set the amount to brighten/darken an image.
   *
   * @param value amount to adjust image brightness
   */
  public MacroAdjustBrightness(int value) {
    this.value = value;
  }

  /**
   * Adjust pixel value + value clamped between maxValue and 0.
   *
   * @param p        pixel
   * @return adjusted pixel
   */
  @Override
  protected Pixel applyToPixel(Pixel p) {
    int maxValue = this.img.getMaxVal();
    int r = Math.max(0, Math.min(maxValue, p.getR() + value));
    int g = Math.max(0, Math.min(maxValue, p.getG() + value));
    int b = Math.max(0, Math.min(maxValue, p.getB() + value));
    return new Pixel(r, g, b);
  }
}
