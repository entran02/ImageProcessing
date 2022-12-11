package macro;

import java.util.Objects;

import model.Image;

public class MacroMask implements Macro {
  private final Macro macro;
  private final Image mask;

  public MacroMask(Macro macro, Image mask) {
    if (macro == null | mask == null) {
      throw new IllegalArgumentException("mask or macro is null");
    }
    this.macro = macro;
    this.mask = mask;
  }

  /**
   * Apply this macro to the image.
   *
   * @param img image to apply macro to
   */
  @Override
  public void apply(Image img) {
    Objects.requireNonNull(img);
    if (mask.getHeight() != img.getHeight() || mask.getWidth() != img.getWidth()) {
      throw new IllegalArgumentException("Mask and image dimensions do not match");
    }

    Image transformedImage = img.copy();
    macro.apply(transformedImage);

    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        if (mask.getPixel(row, col).getR() + mask.getPixel(row, col).getG()
                + mask.getPixel(row, col).getB() == 0) {
          img.setPixel(row, col, transformedImage.getPixels().get(row).get(col));
        }
      }
    }
  }
}
