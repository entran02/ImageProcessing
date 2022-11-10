package macro;

import model.Pixel;

/**
 * Macro to apply color transformation to an image.
 */
public abstract class ColorTransformationMacro extends AMacro implements Macro {
  protected double[][] matrix; // 3x3 matrix

  /**
   * Transforms one pixel by applying matrix.
   *
   * @param p pixel to transform
   * @return transformed pixel
   */
  @Override
  protected Pixel applyToPixel(Pixel p) {
    int[] vals = new int[3];
    for (int i = 0; i < 3; i ++) {
      double v = matrix[i][0] * p.getR() + matrix[i][1] * p.getG() + matrix[i][2] * p.getB();
      vals[i] = (int) Math.max(0, Math.min(img.getMaxVal(), v));
    }
    return new Pixel(vals[0], vals[1], vals[2]);
  }
}
