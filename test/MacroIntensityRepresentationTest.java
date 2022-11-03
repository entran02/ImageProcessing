import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Macros.Macro;
import Macros.MacroIntensityRepresentation;
import model.Image;
import model.ImageImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Intensity Greyscale Macro.
 */
public class MacroIntensityRepresentationTest {
  Pixel yellow = new Pixel(153, 153, 153);
  Pixel pink = new Pixel(204, 204, 204);
  Pixel green = new Pixel(238, 238, 238);
  Pixel blue = new Pixel(119, 119, 119);
  Pixel black = new Pixel(0, 0, 0);
  Pixel hotPink = new Pixel(127, 127, 127);

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

  public final Image expectedIntensityKirby = new ImageImpl(5, 5, 255, newPixels);

  @Test
  public void horizontalFlipTest() {
    Macro intensity = new MacroIntensityRepresentation();
    Image intensityKirby = new TestingUtil().kirby;
    intensity.apply(intensityKirby);
    assertEquals(intensityKirby, expectedIntensityKirby);
  }

}