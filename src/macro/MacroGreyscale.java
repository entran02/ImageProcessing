package macro;

/**
 * Applies a matrix based color transformation on image.
 */
public class MacroGreyscale extends ColorTransformationMacro implements Macro {
  /**
   * Default constructor for initializing greyscale matrix.
   */
  public MacroGreyscale() {
    double[] row = new double[]{0.2126, 0.7152, 0.0722};
    this.matrix = new double[][]{row, row, row};
  }
}
