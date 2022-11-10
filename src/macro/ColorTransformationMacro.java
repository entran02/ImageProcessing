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
    return new Pixel(
            (int) (matrix[0][0] * p.getR() + matrix[0][1] * p.getG() + matrix[0][2] * p.getB()),
            (int) (matrix[1][0] * p.getR() + matrix[1][1] * p.getG() + matrix[1][2] * p.getB()),
            (int) (matrix[2][0] * p.getR() + matrix[2][1] * p.getG() + matrix[2][2] * p.getB()));
  }
}
