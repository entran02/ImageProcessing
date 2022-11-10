import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import macro.Macro;
import macro.MacroBlur;
import model.Image;
import model.ImageImpl;
import model.Pixel;

/**
 * Tests for blur macro.
 */
public class MacroBlurTest {
  private Pixel getConstPixel(int i) {
    return new Pixel(i, i, i);
  }

  @Test
  public void testBlur() {
    List<List<Pixel>> pixels = new ArrayList<>(new ArrayList<>());
    for (int i = 0; i < 3; i++) {
      List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < 3; j++) {
        row.add(getConstPixel(16));
      }
      pixels.add(row);
    }
    Image img = new ImageImpl(3, 3, 255, pixels);
    Macro macro = new MacroBlur();
    macro.apply(img);
    assertEquals(img, new ImageImpl(3, 3, 255,
            new ArrayList<>(Arrays.asList(
                    Arrays.asList(getConstPixel(9), getConstPixel(12), getConstPixel(9)),
                    Arrays.asList(getConstPixel(12), getConstPixel(16), getConstPixel(12)),
                    Arrays.asList(getConstPixel(9), getConstPixel(12), getConstPixel(9))))));
  }
}
