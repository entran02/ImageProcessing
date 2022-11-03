import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Image;
import model.ImageImpl;
import model.ImageUtil;
import model.Pixel;

import static org.junit.Assert.assertEquals;

public class TestImageUtil {

  @Test
  public void testReadPPMFile() {
    Image kirbyFromFile = ImageUtil.readPPM("res/Kirby/Kirby.ppm");
    assertEquals(TestingUtil.kirby, kirbyFromFile);
  }

  @Test
  public void testReadPPMString() {
    String kirby = "P3\n" +
            "5 5\n" +
            "255\n" +
            "102 255 102\n" +
            "255 153 204\n" +
            "255 153 204\n" +
            "255 153 204\n" +
            "255 255 204\n" +
            "255 153 204\n" +
            "51 51 255\n" +
            "255 153 204\n" +
            "51 51 255\n" +
            "255 153 204\n" +
            "255 153 204\n" +
            "0 0 0\n" +
            "255 153 204\n" +
            "0 0 0\n" +
            "255 153 204\n" +
            "102 255 102\n" +
            "255 153 204\n" +
            "255 153 204\n" +
            "255 153 204\n" +
            "255 255 204\n" +
            "255 0 127\n" +
            "255 0 127\n" +
            "255 255 204\n" +
            "255 0 127\n" +
            "255 0 127";
    Image kirbyFromFile = ImageUtil.readStringPPM(kirby);
    assertEquals(TestingUtil.kirby, kirbyFromFile);
  }

}
