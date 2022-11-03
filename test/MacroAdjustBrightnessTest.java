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

  public final List<List<Pixel>> brightPixels = new ArrayList<>(Arrays.asList(
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

  public final Image expectedBrightKirby = new ImageImpl(5, 5, 255, brightPixels);

  @Test
  public void brightenTest() {
    Macro brighten = new MacroAdjustBrightness(52);
    Image brightKirby = new TestingUtil().kirby;
    brighten.apply(brightKirby);
    assertEquals(brightKirby, expectedBrightKirby);
  }

}