package macro;

/**
 * Macro to apply gaussian blur on image.
 */
public class MacroBlur extends FilterMacro implements Macro {
  /**
   * Default constructor for gaussian blur.
   */
  public MacroBlur() {
    this.kernel = new double[][]{
            {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
            {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0},
            {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0}};
  }
}
