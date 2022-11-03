import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Image;
import model.ImageImpl;
import model.Pixel;

public class TestingUtil {
  public static final List<List<Pixel>> kirbyPixels = new ArrayList<>(Arrays.asList(
          new ArrayList<>(Arrays.asList(
                  new Pixel(102, 255, 102),
                  new Pixel(255, 153, 204),
                  new Pixel(255, 153, 204),
                  new Pixel(255, 153, 204),
                  new Pixel(255, 255, 204))),
          new ArrayList<>(Arrays.asList(
                  new Pixel(255, 153, 204),
                  new Pixel(51, 51, 255),
                  new Pixel(255, 153, 204),
                  new Pixel(51, 51, 255),
                  new Pixel(255, 153, 204))),
          new ArrayList<>(Arrays.asList(
                  new Pixel(255, 153, 204),
                  new Pixel(0, 0, 0),
                  new Pixel(255, 153, 204),
                  new Pixel(0, 0, 0),
                  new Pixel(255, 153, 204))),
          new ArrayList<>(Arrays.asList(
                  new Pixel(102, 255, 102),
                  new Pixel(255, 153, 204),
                  new Pixel(255, 153, 204),
                  new Pixel(255, 153, 204),
                  new Pixel(255, 255, 204))),
          new ArrayList<>(Arrays.asList(
                  new Pixel(255, 0, 127),
                  new Pixel(255, 0, 127),
                  new Pixel(255, 255, 204),
                  new Pixel(255, 0, 127),
                  new Pixel(255, 0, 127)))));

  public static final Image kirby = new ImageImpl(5, 5, 255, kirbyPixels);
}
