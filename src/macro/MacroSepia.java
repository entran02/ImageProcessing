package macro;

/**
 * Applies a matrix based color transformation on image.
 */
public class MacroSepia extends ColorTransformationMacro implements Macro {
  /**
   * Default constructor for initializing greyscale matrix.
   */
  public MacroSepia() {
    this.matrix = new double[][]{
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}};
  }
}
