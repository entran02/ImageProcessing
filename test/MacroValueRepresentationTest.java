import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import macro.Macro;
import macro.MacroValueRepresentation;
import model.Image;
import model.ImageImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Value Greyscale Macro.
 */
public class MacroValueRepresentationTest {
  Pixel yellow = new Pixel(255, 255, 255);
  Pixel pink = new Pixel(255, 255, 255);
  Pixel green = new Pixel(255, 255, 255);
  Pixel blue = new Pixel(255, 255, 255);
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

  public final Image expectedValueKirby = new ImageImpl(5, 5, 255, newPixels);

  @Test
  public void valueTest() {
    Macro value = new MacroValueRepresentation();
    Image valueKirby = TestingUtil.getKirby();
    value.apply(valueKirby);
    assertEquals(valueKirby, expectedValueKirby);
  }

}