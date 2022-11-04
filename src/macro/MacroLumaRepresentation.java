package macro;

import model.Image;
import model.Pixel;

/**
 * Macro to represent each pixel in terms of its Luma.
 */
public class MacroLumaRepresentation implements Macro {
  /**
   * Apply this macro to the image.
   *
   * @param img image to apply macro to
   */
  @Override
  public void apply(Image img) {
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        Pixel pixel = img.getPixels().get(i).get(j);
        int val = (int) Math.round((pixel.getR() * .2126) + (pixel.getG() * .7152)
                + (pixel.getB() * .0722));
        img.setPixel(i, j, new Pixel(val, val, val));
      }
    }
  }
}
