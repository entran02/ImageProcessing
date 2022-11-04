import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import macro.Macro;
import macro.MacroRedGreyscale;
import model.Image;
import model.ImageImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Red Greyscale Macro.
 */
public class MacroRedGreyscaleTest {
  Pixel yellow = new Pixel(102, 102, 102);
  Pixel pink = new Pixel(255, 255, 255);
  Pixel green = new Pixel(255, 255, 255);
  Pixel blue = new Pixel(51, 51, 51);
  Pixel black = new Pixel(0, 0, 0);
  Pixel hotPink = new Pixel(255, 255, 255);

  public final List<List<Pixel>> newPixels = new ArrayList<>(Arrays.asList(
          new ArrayList<>(Arrays.asList(
                  yellow,
                  pink,
                  pink,
                  pink,
                  green)),
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
                  yellow,
                  pink,
                  pink,
                  pink,
                  green)),
          new ArrayList<>(Arrays.asList(
                  hotPink,
                  hotPink,
                  green,
                  hotPink,
                  hotPink))));

  public final Image expectedRedKirby = new ImageImpl(5, 5, 255, newPixels);

  @Test
  public void RedGreyscaleTest() {
    Macro red = new MacroRedGreyscale();
    Image redKirby = TestingUtil.getKirby();
    red.apply(redKirby);
    assertEquals(redKirby, expectedRedKirby);
  }

}