import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import macro.Macro;
import macro.MacroGreyscale;
import model.Image;
import model.ImageImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Greyscale Macro.
 */
public class MacroGreyscaleTest {
  Pixel yellow = new Pixel(211, 211, 211);
  Pixel pink = new Pixel(178, 178, 178);
  Pixel green = new Pixel(251, 251, 251);
  Pixel blue = new Pixel(66, 66, 66);
  Pixel black = new Pixel(0, 0, 0);
  Pixel hotPink = new Pixel(63, 63, 63);

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

  public final Image expectedGreyscaleKirby = new ImageImpl(5, 5, 255, newPixels);

  @Test
  public void lumaTest() {
    Macro greyscale = new MacroGreyscale();
    Image greyscaleKirby = TestingUtil.getKirby();
    greyscale.apply(greyscaleKirby);
    assertEquals(greyscaleKirby, expectedGreyscaleKirby);
  }

}