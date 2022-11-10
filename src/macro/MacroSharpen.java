package macro;

/**
 * Macro to sharpen an image.
 */
public class MacroSharpen extends FilterMacro implements Macro {
  /**
   * Default constructor for sharpening image.
   */
  public MacroSharpen() {
    double a = -1.0 / 8.0;
    double b = 1.0 / 4.0;
    this.kernel = new double[][]{
            {a, a, a, a, a},
            {a, b, b, b, a},
            {a, b, 1.0, b, a},
            {a, b, b, b, a},
            {a, a, a, a, a},};
  }
}
