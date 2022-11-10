package macro;

import model.Image;
import model.Pixel;

/**
 * Abstraction for macros that involve applying a matrix filter to an image.
 */
public abstract class FilterMacro implements Macro {
  protected double[][] kernel; // odd number square matrix

  /**
   * Applies pixel transformation to each pixel.
   *
   * @param img image to apply macro to
   */
  @Override
  public void apply(Image img) {
    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        img.setPixel(row, col, applyToPixel(img, row, col));
      }
    }
  }

  /**
   * Applies matrix filter to each pixel.
   *
   * @param img image being transformed
   * @param row row of pixel
   * @param col col of pixel
   * @return transformed pixel
   */
  protected Pixel applyToPixel(Image img, int row, int col) {
    int center = kernel.length / 2;
    int vals[] = new int[3];
    for (int i = 0; i < kernel.length; i ++) {
      for (int j = 0; j < kernel[0].length; j ++) {
        int r = row + i - center;
        int c = col + j - center;
        if (r > 0 && r < img.getHeight() && c > 0 && c < img.getWidth()) {
          vals[0] += kernel[i][j] * img.getPixels().get(r).get(c).getR();
          vals[1] += kernel[i][j] * img.getPixels().get(r).get(c).getG();
          vals[2] += kernel[i][j] * img.getPixels().get(r).get(c).getB();
        }
      }
    }
    return new Pixel(vals[0], vals[1], vals[2]);
  }

}
