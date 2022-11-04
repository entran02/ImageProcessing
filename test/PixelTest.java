import org.junit.Test;

import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test for class which represents a pixel.
 */
public class PixelTest {
  Pixel red = new Pixel(255, 6, 0);
  Pixel blue = new Pixel(0, 9, 255);
  Pixel red2 = new Pixel(255, 6, 0);

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidR() {
    new Pixel(-1, 0, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidG() {
    new Pixel(0, -2, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidB() {
    new Pixel(0, 0, -3);
  }

  @Test
  public void testGetR() {
    int r = red.getR();
    int b = blue.getR();
    assertEquals(r, 255);
    assertEquals(b, 0);
  }

  @Test
  public void testGetG() {
    int r = red.getG();
    int b = blue.getG();
    assertEquals(r, 6);
    assertEquals(b, 9);
  }

  @Test
  public void testGetB() {
    int r = red.getB();
    int b = blue.getB();
    assertEquals(r, 0);
    assertEquals(b, 255);
  }

  @Test
  public void testEquals() {
    assertFalse(red.equals(blue));
    assertTrue(red.equals(red2));
  }

}