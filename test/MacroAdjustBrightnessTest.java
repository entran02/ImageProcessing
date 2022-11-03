import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Macros.Macro;
import Macros.MacroAdjustBrightness;
import model.Image;
import model.ImageImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;

public class MacroAdjustBrightnessTest {
  public final List<List<Pixel>> newPixels = new ArrayList<>(Arrays.asList(
          new ArrayList<>(Arrays.asList(
                  new Pixel(154, 255, 154),
                  new Pixel(255, 205, 255),
                  new Pixel(255, 205, 255),
                  new Pixel(255, 205, 255),
                  new Pixel(255, 255, 255))),
          new ArrayList<>(Arrays.asList(
                  new Pixel(255, 205, 255),
                  new Pixel(103, 103, 255),
                  new Pixel(255, 205, 255),
                  new Pixel(103, 103, 255),
                  new Pixel(255, 205, 255))),
          new ArrayList<>(Arrays.asList(
                  new Pixel(255, 205, 255),
                  new Pixel(52, 52, 52),
                  new Pixel(255, 205, 255),
                  new Pixel(52, 52, 52),
                  new Pixel(255, 205, 255))),
          new ArrayList<>(Arrays.asList(
                  new Pixel(154, 255, 154),
                  new Pixel(255, 205, 255),
                  new Pixel(255, 205, 255),
                  new Pixel(255, 205, 255),
                  new Pixel(255, 255, 255))),
          new ArrayList<>(Arrays.asList(
                  new Pixel(255, 52, 179),
                  new Pixel(255, 52, 179),
                  new Pixel(255, 255, 255),
                  new Pixel(255, 52, 179),
                  new Pixel(255, 52, 179)))));

  public static final List<List<Pixel>> darkPixels = new ArrayList<>(Arrays.asList(
          new ArrayList<>(Arrays.asList(
                  new Pixel(50, 203, 50),
                  new Pixel(203, 101, 152),
                  new Pixel(203, 101, 152),
                  new Pixel(203, 101, 152),
                  new Pixel(203, 203, 152))),
          new ArrayList<>(Arrays.asList(
                  new Pixel(203, 101, 152),
                  new Pixel(0, 0, 203),
                  new Pixel(203, 101, 152),
                  new Pixel(0, 0, 203),
                  new Pixel(203, 101, 152))),
          new ArrayList<>(Arrays.asList(
                  new Pixel(203, 101, 152),
                  new Pixel(0, 0, 0),
                  new Pixel(203, 101, 152),
                  new Pixel(0, 0, 0),
                  new Pixel(203, 101, 152))),
          new ArrayList<>(Arrays.asList(
                  new Pixel(50, 203, 50),
                  new Pixel(203, 101, 152),
                  new Pixel(203, 101, 152),
                  new Pixel(203, 101, 152),
                  new Pixel(203, 203, 152))),
          new ArrayList<>(Arrays.asList(
                  new Pixel(203, 0, 75),
                  new Pixel(203, 0, 75),
                  new Pixel(203, 203, 152),
                  new Pixel(203, 0, 75),
                  new Pixel(203, 0, 75)))));
  public final Image expectedBrightKirby = new ImageImpl(5, 5, 255, newPixels);
  public final Image expectedDarkKirby = new ImageImpl(5, 5, 255, darkPixels);

  @Test
  public void brightenTest() {
    Macro brighten = new MacroAdjustBrightness(52);
    Image brightKirby = new TestingUtil().kirby;
    brighten.apply(brightKirby);
    assertEquals(brightKirby, expectedBrightKirby);
  }

  @Test
  public void darkenTest() {
    Macro darken = new MacroAdjustBrightness(-52);
    Image darkKirby = new TestingUtil().kirby;
    darken.apply(darkKirby);
    assertEquals(darkKirby, expectedDarkKirby);
  }

}