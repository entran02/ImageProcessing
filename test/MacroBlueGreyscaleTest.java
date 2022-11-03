import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Macros.Macro;
import Macros.MacroAdjustBrightness;
import Macros.MacroBlueGreyscale;
import model.Image;
import model.ImageImpl;
import model.Pixel;

import static org.junit.Assert.*;

public class MacroBlueGreyscaleTest {
  Pixel yellow = new Pixel(102, 102, 102);
  Pixel pink = new Pixel(204, 204, 204);
  Pixel green = new Pixel(204, 204, 204);
  Pixel blue = new Pixel(255, 255, 255);
  Pixel black = new Pixel(0, 0, 0);
  Pixel hotPink = new Pixel(127, 127, 127);

  public final List<List<Pixel>> bluePixels = new ArrayList<>(Arrays.asList(
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

  public final Image expectedBlueKirby = new ImageImpl(5, 5, 255, bluePixels);

  @Test
  public void blueGreyscaleTest() {
    Macro blueGreyscale = new MacroBlueGreyscale();
    Image blueKirby = new TestingUtil().kirby;
    blueGreyscale.apply(blueKirby);
    assertEquals(blueKirby, expectedBlueKirby);
  }
}