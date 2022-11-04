import java.util.Objects;

import macro.Macro;
import model.Image;

/**
 * Mock class to represent a macro. Logs when apply is called.
 */
public class MacroMock implements Macro {
  public Appendable log;

  public MacroMock(Appendable log) {
    this.log = Objects.requireNonNull(log);
  }

  /**
   * Log macro use.
   *
   * @param img image to apply macro to
   */
  @Override
  public void apply(Image img) {
    try {
      log.append("Macro applied\n");
    } catch (Exception e) {
      return;
    }
  }
}
