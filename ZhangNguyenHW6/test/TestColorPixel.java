import org.junit.Test;

import java.awt.Color;

import model.PixelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the ColorPixel class.
 */
public class TestColorPixel {
  // test constructor
  @Test
  public void testConstructor() {
    int[] black = {0, 0, 0};
    PixelImpl pixelImpl = new PixelImpl(black);
    assertEquals(black, pixelImpl.getChannelValues());
    assertEquals(0, pixelImpl.getRedChannel());
    assertEquals(0, pixelImpl.getGreenChannel());
    assertEquals(0, pixelImpl.getBlueChannel());
  }

  // test constructor2
  @Test
  public void testConstructor2() {
    PixelImpl pixelImpl = new PixelImpl(0, 0, 0);
    assertEquals(0, pixelImpl.getChannelValues()[0]);
    assertEquals(0, pixelImpl.getChannelValues()[1]);
    assertEquals(0, pixelImpl.getChannelValues()[2]);
    assertEquals(0, pixelImpl.getRedChannel());
    assertEquals(0, pixelImpl.getGreenChannel());
    assertEquals(0, pixelImpl.getBlueChannel());
  }

  // test getColor
  @Test
  public void getColor() {
    int[] black = {0, 0, 0};
    PixelImpl pixelImpl = new PixelImpl(black);
    assertEquals(Color.BLACK, pixelImpl.getColor());

  }


  // test getChannelValues
  @Test
  public void testGetChannelValues() {
    int[] black = {0, 0, 0};
    PixelImpl pixelImpl = new PixelImpl(black);
    assertEquals(0, pixelImpl.getChannelValues()[0]);
    assertEquals(0, pixelImpl.getChannelValues()[1]);
    assertEquals(0, pixelImpl.getChannelValues()[2]);
  }


  // test updatePixelRedChannel
  @Test
  public void testUpdatePixelRedChannel() {
    int[] black = {0, 0, 0};
    PixelImpl pixelImpl = new PixelImpl(black);
    pixelImpl.updatePixelRedChannel(255);

    Color newColor = new Color(255, 0, 0);

    assertEquals(newColor, pixelImpl.getColor());
    assertEquals(255, pixelImpl.getRedChannel());
    assertEquals(255, pixelImpl.getChannelValues()[0]);
    assertEquals(0, pixelImpl.getGreenChannel());
    assertEquals(0, pixelImpl.getChannelValues()[1]);
    assertEquals(0, pixelImpl.getBlueChannel());
    assertEquals(0, pixelImpl.getChannelValues()[2]);

  }

  // test updatePixelGreenChannel
  @Test
  public void testUpdatePixelGreenChannel() {
    int[] black = {0, 0, 0};
    PixelImpl pixelImpl = new PixelImpl(black);
    pixelImpl.updatePixelGreenChannel(255);

    Color newColor = new Color(0, 255, 0);

    assertEquals(newColor, pixelImpl.getColor());
    assertEquals(0, pixelImpl.getRedChannel());
    assertEquals(0, pixelImpl.getChannelValues()[0]);
    assertEquals(255, pixelImpl.getGreenChannel());
    assertEquals(255, pixelImpl.getChannelValues()[1]);
    assertEquals(0, pixelImpl.getBlueChannel());
    assertEquals(0, pixelImpl.getChannelValues()[2]);

  }

  // test updatePixelBlueChannel
  @Test
  public void testUpdatePixelBlueChannel() {
    int[] black = {0, 0, 0};
    PixelImpl pixelImpl = new PixelImpl(black);
    pixelImpl.updatePixelBlueChannel(255);

    Color newColor = new Color(0, 0, 255);

    assertEquals(newColor, pixelImpl.getColor());
    assertEquals(0, pixelImpl.getRedChannel());
    assertEquals(0, pixelImpl.getChannelValues()[0]);
    assertEquals(0, pixelImpl.getGreenChannel());
    assertEquals(0, pixelImpl.getChannelValues()[1]);
    assertEquals(255, pixelImpl.getBlueChannel());
    assertEquals(255, pixelImpl.getChannelValues()[2]);

  }


  // test clamp
  @Test
  public void testClamp() {
    int[] black = {0, 0, 0};
    PixelImpl pixelImpl = new PixelImpl(black);

    pixelImpl.updatePixelRedChannel(300);
    assertEquals(300, pixelImpl.getChannelValues()[0]);

    pixelImpl.clamp();

    Color newColor = new Color(255, 0, 0);

    assertEquals(255, pixelImpl.getRedChannel());
    assertEquals(0, pixelImpl.getGreenChannel());
    assertEquals(0, pixelImpl.getBlueChannel());
  }


  // test equals
  @Test
  public void testEquals() {
    int[] black = {0, 0, 0};
    PixelImpl pixelImpl1 = new PixelImpl(black);
    PixelImpl pixelImpl2 = new PixelImpl(black);

    assertTrue(pixelImpl1.equals(pixelImpl2));

  }

  // test hashCode
  @Test
  public void testHashCode() {
    int[] black = {0, 0, 0};
    PixelImpl pixelImpl1 = new PixelImpl(black);
    PixelImpl pixelImpl2 = new PixelImpl(black);

    assertEquals(pixelImpl1.hashCode(), pixelImpl2.hashCode());
  }

  // test toString
  @Test
  public void testToString() {
    int[] black = {0, 0, 0};
    PixelImpl pixelImpl = new PixelImpl(black);

    String expected = "0\n" +
            "0\n" +
            "0\n";

    assertEquals(expected, pixelImpl.toString());
  }

}
