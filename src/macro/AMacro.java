package macro;

import java.util.Objects;

import model.Image;
import model.Pixel;

/**
 * Abstraction for Macros that apply a set transformation to each pixel.
 */
public abstract class AMacro implements Macro {
  protected Image img;

  /**
   * Applies pixel transformation to each pixel.
   *
   * @param img image to apply macro to
   */
  @Override
  public void apply(Image img) {
    this.img = Objects.requireNonNull(img);
    for (int row = 0; row < this.img.getHeight(); row++) {
      for (int col = 0; col < this.img.getWidth(); col++) {
        this.img.setPixel(row, col, applyToPixel(this.img.getPixels().get(row).get(col)));
      }
    }
    this.img = null;
  }

  /**
   * Transforms one pixel depending on the macro function.
   *
   * @param p pixel to transform
   * @return transformed pixel
   */
  protected abstract Pixel applyToPixel(Pixel p);
}
