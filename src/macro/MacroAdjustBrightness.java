package macro;

import java.util.Objects;

import model.Image;
import model.Pixel;

/**
 * Macro to brighten or darken an image depending on the brightness value.
 */
public class MacroAdjustBrightness implements Macro {
  private int value;

  /**
   * Constructor to set the amount to brighten/darken an image.
   *
   * @param value amount to adjust image brightness
   */
  public MacroAdjustBrightness(int value) {
    this.value = Objects.requireNonNull(value);
  }

  /**
   * Apply this macro to the image.
   *
   * @param img image to apply macro to
   */
  @Override
  public void apply(Image img) {
    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        img.setPixel(row, col, adjustPixel(img.getPixels().get(row).get(col), img.getMaxVal()));
      }
    }
  }

  /**
   * Adjust pixel value + value clamped between maxValue and 0.
   *
   * @param p        pixel
   * @param maxValue maximum value for each color channel
   * @return adjusted pixel
   */
  private Pixel adjustPixel(Pixel p, int maxValue) {
    int r = Math.max(0, Math.min(maxValue, p.getR() + value));
    int g = Math.max(0, Math.min(maxValue, p.getG() + value));
    int b = Math.max(0, Math.min(maxValue, p.getB() + value));
    return new Pixel(r, g, b);
  }
}
