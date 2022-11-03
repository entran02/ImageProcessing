import org.junit.Test;

import model.Image;
import model.ImageUtil;

import static org.junit.Assert.assertEquals;

public class TestImageUtil {

  @Test
  public void testReadPPMFile() {
    Image kirbyFromFile = ImageUtil.readPPM("res/Kirby/Kirby.ppm");
    ImageUtil.savePPM("res/Kirby/kirbtest.ppm", kirbyFromFile);
    assertEquals(kirbyFromFile, TestingUtil.kirby);
  }

}
