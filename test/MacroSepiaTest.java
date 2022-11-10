import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import macro.Macro;
import model.Image;
import macro.MacroSepia;
import model.ImageImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Sepia Macro.
 */
public class MacroSepiaTest {
  Pixel yellow = new Pixel(255, 228, 177);
  Pixel pink = new Pixel(255, 228, 178);
  Pixel green = new Pixel(255, 255, 232);
  Pixel blue = new Pixel(107, 96, 75);
  Pixel black = new Pixel(0, 0, 0);
  Pixel hotPink = new Pixel(124, 110, 86);

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

  public final Image expectedSepia = new ImageImpl(5, 5, 255, newPixels);

  @Test
  public void lumaTest() {
    Macro sepia = new MacroSepia();
    Image sepiaKirby = TestingUtil.getKirby();
    sepia.apply(sepiaKirby);
    assertEquals(sepiaKirby, expectedSepia);
  }

}