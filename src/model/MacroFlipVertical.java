package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Macro to flip an image vertically.
 */
public class MacroFlipVertical implements Macro {

  /**
   * Flips image vertically.
   *
   * @param img image to apply macro to
   */
  @Override
  public void apply(Image img) {
    List<List<Pixel>> pixels = img.getPixels();
    for (int r = 0; r < img.getHeight() / 2; r++) {
      List<Pixel> row = pixels.get(r);
      pixels.set(r, pixels.get(img.getHeight() - r));
      pixels.set(img.getHeight() - r, row);
    }
    img.setPixels(pixels);
  }
}