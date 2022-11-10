import org.junit.Test;

import model.Image;
import model.ImageUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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
  public void testReadJpg() {
    Image amongUsFromFile = ImageUtil.readFile("res/Kirby/amongus.jpg");
    assertEquals(TestingUtil.getKirby(), amongUsFromFile);
  }

}
