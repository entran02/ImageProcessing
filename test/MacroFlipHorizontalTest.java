import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Macros.Macro;
import Macros.MacroFlipHorizontal;
import model.Image;
import model.ImageImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Horizontal Flip Macro.
 */
public class MacroFlipHorizontalTest {
  Pixel yellow = new Pixel(102, 255, 102);
  Pixel pink = new Pixel(255, 153, 204);
  Pixel green = new Pixel(255, 255, 204);
  Pixel blue = new Pixel(51, 51, 255);
  Pixel black = new Pixel(0, 0, 0);
  Pixel hotPink = new Pixel(255, 0, 127);

  public final List<List<Pixel>> newPixels = new ArrayList<>(Arrays.asList(
          new ArrayList<>(Arrays.asList(
                  green,
                  pink,
                  pink,
                  pink,
                  yellow)),
          new ArrayList<>(Arrays.asList(
                  pink,
                  blue,
                  pink,
                  blue,
                  pink)),
          new ArrayList<>(Arrays.asList(
                  pink,
                  black,
                  pink,
                  black,
                  pink)),
          new ArrayList<>(Arrays.asList(
                  green,
                  pink,
                  pink,
                  pink,
                  yellow)),
          new ArrayList<>(Arrays.asList(
                  hotPink,
                  hotPink,
                  green,
                  hotPink,
                  hotPink))));

  public final Image expectedFlipHKirby = new ImageImpl(5, 5, 255, newPixels);

  @Test
  public void horizontalFlipTest() {
    Macro flipH = new MacroFlipHorizontal();
    Image flipHKirby = TestingUtil.getKirby();
    flipH.apply(flipHKirby);
    assertEquals(flipHKirby, expectedFlipHKirby);
  }

}