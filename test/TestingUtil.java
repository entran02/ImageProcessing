import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Image;
import model.ImageImpl;
import model.Pixel;

/**
 * Utility class that stores some images used in tests.
 */
public class TestingUtil {
  public static Image getKirby() {
    return new ImageImpl(5, 5, 255,
            new ArrayList<>(Arrays.asList(
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
                            new Pixel(255, 0, 127))))));
  }

  public static Image getTwoByTwoImg() {
    return new ImageImpl(2, 2, 255, new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(
                    new Pixel(0, 0, 0),
                    new Pixel(1, 1, 1))),
            new ArrayList<>(Arrays.asList(
                    new Pixel(2, 2, 2),
                    new Pixel(3, 3, 3))))));
  }
}
