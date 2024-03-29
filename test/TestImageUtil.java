import org.junit.Test;

import model.Image;
import model.ImageUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

/**
 * Test reading and writing PPM files in ImageUtil class.
 */
public class TestImageUtil {

  @Test
  public void testReadPPMFile() {
    Image kirbyFromFile = ImageUtil.readFile("res/Kirby/Kirby.ppm");
    assertEquals(TestingUtil.getKirby(), kirbyFromFile);
  }

  @Test
  public void testReadFail() {
    // file doesn't exist
    assertThrows(IllegalArgumentException.class, () -> ImageUtil.readFile("filedoesntexist.ppm"));
    // first line not P3
    assertThrows(IllegalArgumentException.class, () -> ImageUtil.readStringPPM(
            "P5\n1\n1\n255\n127 127 127"));
    assertThrows(IllegalArgumentException.class, () -> ImageUtil.readFile(
            "res/Kirby/invalid-ppm.ppm"));
    // negative pixel numbers
    assertThrows(IllegalArgumentException.class, () -> ImageUtil.readStringPPM(
            "P5\n1\n1\n255\n-127 127 127"));
    assertThrows(IllegalArgumentException.class, () -> ImageUtil.readStringPPM(
            "P5\n1\n1\n255\n127 -127 127"));
    assertThrows(IllegalArgumentException.class, () -> ImageUtil.readStringPPM(
            "P5\n1\n1\n255\n127 127 -127"));
    // negative width, height, maxval numbers
    assertThrows(IllegalArgumentException.class, () -> ImageUtil.readStringPPM(
            "P5\n-1\n1\n255\n127 127 127"));
    assertThrows(IllegalArgumentException.class, () -> ImageUtil.readStringPPM(
            "P5\n1\n-1\n255\n127 127 127"));
    assertThrows(IllegalArgumentException.class, () -> ImageUtil.readStringPPM(
            "P5\n1\n1\n-255\n127 127 127"));
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
    assertEquals(TestingUtil.getKirby(), kirbyFromFile);
  }

  @Test
  public void testSavePPM() {
    ImageUtil.saveFile("res/Kirby/kirby-saved.ppm", TestingUtil.getKirby());
    Image kirbyFromFile = ImageUtil.readFile("res/Kirby/kirby-saved.ppm");
    assertEquals(TestingUtil.getKirby(), kirbyFromFile);
  }

  @Test
  public void testSavePPMError() {
    // try saving null image
    assertThrows(IllegalArgumentException.class, () -> ImageUtil.saveFile("filename.ppm", null));
  }

  @Test
  public void testSavePNG() {
    ImageUtil.saveFile("res/Kirby/kirby-saved.png", TestingUtil.getKirby());
    Image kirbyFromFile = ImageUtil.readFile("res/Kirby/kirby-saved.png");
    assertEquals(TestingUtil.getKirby(), kirbyFromFile);
  }

  @Test
  public void testSaveBMP() {
    ImageUtil.saveFile("res/Kirby/kirby-saved.bmp", TestingUtil.getKirby());
    Image kirbyFromFile = ImageUtil.readFile("res/Kirby/kirby-saved.bmp");
    assertEquals(TestingUtil.getKirby(), kirbyFromFile);
  }

  @Test
  public void testSaveJPG() {
    try {
      ImageUtil.saveFile("res/Kirby/kirby-saved.jpg", TestingUtil.getKirby());
      Image kirbyFromFile = ImageUtil.readFile("res/Kirby/kirby-saved.jpg");
      // since saving a jpg is lossy, the results of a saved and reloaded jpg are not identical.
    } catch (Exception e) {
      fail();
    }
  }
}
