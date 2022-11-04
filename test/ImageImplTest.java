import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Image;
import model.ImageImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Test the functions of Image.
 */
public class ImageImplTest {
  private List<List<Pixel>> pixels22;

  @Before
  public void setup() {
    pixels22 = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(
                    new Pixel(0, 0, 0),
                    new Pixel(1, 1, 1))),
            new ArrayList<>(Arrays.asList(
                    new Pixel(2, 2, 2),
                    new Pixel(3, 3, 3)))));
  }

  @Test
  public void testConstructor() {
    List<List<Pixel>> pixels = new ArrayList<>(new ArrayList<>());
    // empty pixels
    assertThrows(IllegalArgumentException.class, () -> new ImageImpl(1, 1, 255, pixels));
    // width/height/max mismatch
    assertThrows(IllegalArgumentException.class, () -> new ImageImpl(1, 1, 255, pixels22));
    assertThrows(IllegalArgumentException.class, () -> new ImageImpl(2, 1, 255, pixels22));
    assertThrows(IllegalArgumentException.class, () -> new ImageImpl(1, 2, 255, pixels22));
    assertThrows(IllegalArgumentException.class, () -> new ImageImpl(1, 2, 1, pixels22));
    // negative values
    assertThrows(IllegalArgumentException.class, () -> new ImageImpl(2, -2, 255, pixels22));
    assertThrows(IllegalArgumentException.class, () -> new ImageImpl(-2, 2, 255, pixels22));
    assertThrows(IllegalArgumentException.class, () -> new ImageImpl(2, 2, -255, pixels22));

    // successfully load image
    Image img = new ImageImpl(2, 2, 255, pixels22);
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        assertEquals(pixels22.get(i).get(j), img.getPixels().get(i).get(j));
      }
    }
  }

  @Test
  public void getWidth() {
    assertEquals(2, TestingUtil.getTwoByTwoImg().getWidth());
    assertEquals(5, TestingUtil.getKirby().getWidth());
  }

  @Test
  public void getHeight() {
    assertEquals(2, TestingUtil.getTwoByTwoImg().getHeight());
    assertEquals(5, TestingUtil.getKirby().getHeight());
  }

  @Test
  public void getMaxVal() {
    assertEquals(255, TestingUtil.getTwoByTwoImg().getMaxVal());
    assertEquals(255, TestingUtil.getKirby().getMaxVal());
  }

  @Test
  public void getPixels() {
    Image img = new ImageImpl(2, 2, 255, pixels22);
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        assertEquals(pixels22.get(i).get(j), img.getPixels().get(i).get(j));
      }
    }
  }

  @Test
  public void setPixel() {
    Image img = new ImageImpl(2, 2, 255, pixels22);
    img.setPixel(0, 0, new Pixel(255, 255, 255));
    assertEquals(img.getPixels().get(0).get(0), new Pixel(255, 255, 255));
    img.setPixel(1, 1, new Pixel(200, 225, 255));
    assertEquals(img.getPixels().get(1).get(1), new Pixel(200, 225, 255));

    Pixel p = new Pixel(0, 0, 0);
    // negative values
    assertThrows(IllegalArgumentException.class, () -> img.setPixel(-1, 1, p));
    assertThrows(IllegalArgumentException.class, () -> img.setPixel(1, -1, p));
    assertThrows(IllegalArgumentException.class, () -> img.setPixel(1, -1, null));
  }

  @Test
  public void setPixels() {
    Image img = new ImageImpl(2, 2, 255, pixels22);
    List<List<Pixel>> newPixels = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(
                    new Pixel(255, 255, 255),
                    new Pixel(111, 111, 111))),
            new ArrayList<>(Arrays.asList(
                    new Pixel(123, 123, 123),
                    new Pixel(99, 99, 99)))));
    img.setPixels(newPixels);
    assertEquals(newPixels, img.getPixels());
    // null pixels
    assertThrows(IllegalArgumentException.class, () -> img.setPixels(null));
    List<List<Pixel>> nullPixels = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(
                    null,
                    new Pixel(111, 111, 111))),
            new ArrayList<>(Arrays.asList(
                    new Pixel(123, 123, 123),
                    new Pixel(99, 99, 99)))));
    assertThrows(IllegalArgumentException.class, () -> img.setPixels(nullPixels));
    // row/col mismatch
    List<List<Pixel>> pixels32 = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(
                    new Pixel(255, 255, 255),
                    new Pixel(111, 111, 111))),
            new ArrayList<>(Arrays.asList(
                    new Pixel(255, 255, 255),
                    new Pixel(111, 111, 111))),
            new ArrayList<>(Arrays.asList(
                    new Pixel(123, 123, 123),
                    new Pixel(99, 99, 99)))));
    assertThrows(IllegalArgumentException.class, () -> img.setPixels(pixels32));
  }
}