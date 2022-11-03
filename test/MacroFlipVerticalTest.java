import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Macros.Macro;
import Macros.MacroFlipVertical;
import model.Image;
import model.ImageImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Vertical Flip Macro.
 */
public class MacroFlipVerticalTest {
  Pixel yellow = new Pixel(102, 255, 102);
  Pixel pink = new Pixel(255, 153, 204);
  Pixel green = new Pixel(255, 255, 204);
  Pixel blue = new Pixel(51, 51, 255);
  Pixel black = new Pixel(0, 0, 0);
  Pixel hotPink = new Pixel(255, 0, 127);

  public final List<List<Pixel>> newPixels = new ArrayList<>(Arrays.asList(
          new ArrayList<>(Arrays.asList(
                  hotPink,
                  hotPink,
                  green,
                  hotPink,
                  hotPink)),
          new ArrayList<>(Arrays.asList(
                  yellow,
                  pink,
                  pink,
                  pink,
                  green)),
          new ArrayList<>(Arrays.asList(
                  pink,
                  black,
                  pink,
                  black,
                  pink)),
          new ArrayList<>(Arrays.asList(
                  pink,
                  blue,
                  pink,
                  blue,
                  pink)),
          new ArrayList<>(Arrays.asList(
                  yellow,
                  pink,
                  pink,
                  pink,
                  green))));

  public final Image expectedFlipVKirby = new ImageImpl(5, 5, 255, newPixels);

  @Test
  public void verticalFlipTest() {
    Macro flipV = new MacroFlipVertical();
    Image flipVKirby = TestingUtil.getKirby();
    flipV.apply(flipVKirby);
    assertEquals(flipVKirby, expectedFlipVKirby);
  }

}