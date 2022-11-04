package macro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Image;
import model.Pixel;

/**
 * Macro to flip an image horizontally.
 */
public class MacroFlipHorizontal implements Macro {

  /**
   * Flips image horizontally.
   *
   * @param img image to apply macro to
   */
  @Override
  public void apply(Image img) {
    List<List<Pixel>> newpixels = new ArrayList<>(new ArrayList<>());
    for (List<Pixel> row : img.getPixels()) {
      Collections.reverse(row);
      newpixels.add(row);
    }
    img.setPixels(newpixels);
  }
}
