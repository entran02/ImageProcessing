package macro;

import model.Image;
import model.Pixel;

/**
 * Macro to convert image to greyscale with the green channel.
 */
public class MacroGreenGreyscale implements Macro {
  /**
   * Apply this macro to the image.
   *
   * @param img image to apply macro to
   */
  @Override
  public void apply(Image img) {
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        int val = img.getPixels().get(i).get(j).getG();
        img.setPixel(i, j, new Pixel(val, val, val));
      }
    }
  }
}
