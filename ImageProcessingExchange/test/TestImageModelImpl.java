import org.junit.Test;

import java.io.StringReader;
import java.util.HashMap;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.PixelImpl;
import model.ImageModel;
import model.ImageProcessorModelImpl;
import view.ImageModelTextView;
import view.ImageModelView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the ImageModelImpl class.
 */
public class TestImageModelImpl {

  // test Equals
  @Test
  public void testEquals() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test1 " +
            "load test.ppm test2 " +
            "load testBlack.ppm testBlack q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image1 = images.get("test1");
    ImageModel image2 = images.get("test2");
    ImageModel image3 = images.get("testBlack");

    assertTrue(image1.equals(image1));
    assertTrue(image1.equals(image2));
    assertTrue(image2.equals(image1));
    assertFalse(image1.equals(image3));
  }


  // test HashCode
  @Test
  public void testHashCode() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test1 " +
            "load test.ppm test2 " +
            "load testBlack.ppm testBlack q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image1 = images.get("test1");
    ImageModel image2 = images.get("test2");
    ImageModel image3 = images.get("testBlack");

    int image1Hash = image1.hashCode();
    int image2Hash = image2.hashCode();
    int image3Hash = image3.hashCode();

    assertTrue(image1Hash == image1Hash);
    assertTrue(image1Hash == image2Hash);
    assertTrue(image2Hash == image1Hash);
    assertFalse(image1Hash == image3Hash);
  }

  // test toString
  @Test
  public void testToString() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test ");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");

    String expectedStr = "P3\n" +
            "3 1\n" +
            "255\n" +
            "230\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "230\n" +
            "100\n" +
            "100\n" +
            "230\n" +
            "230\n";

    assertEquals(expectedStr, image.toString());
  }

  // test getHeight
  @Test
  public void testGetHeight() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test ");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");

    assertEquals(1, image.getHeight());

  }

  // test getWidth
  @Test
  public void testGetWidth() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test ");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");

    assertEquals(3, image.getWidth());

  }

  // test getMaxValue
  @Test
  public void testGetMaxValue() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test ");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");

    assertEquals(255, image.getMaxValue());

  }

  // test getImagePixels
  @Test
  public void testGetImagePixels() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test ");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");

    int[] red = new int[]{230, 100, 100};
    int[] green = new int[]{100, 230, 100};
    int[] blue = new int[]{100, 230, 230};

    PixelImpl redPixel = new PixelImpl(red);
    PixelImpl greenPixel = new PixelImpl(green);
    PixelImpl bluePixel = new PixelImpl(blue);

    assertTrue(redPixel.equals(image.getImagePixels()[0][0]));
    assertTrue(greenPixel.equals(image.getImagePixels()[0][1]));
    assertTrue(bluePixel.equals(image.getImagePixels()[0][2]));
  }

  // test getPixelAt
  @Test
  public void testGetPixelAt() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test ");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");

    String red = "230\n100\n100\n";
    String green = "100\n230\n100\n";
    String blue = "100\n230\n230\n";

    assertEquals(red, image.getPixelAt(0, 0).toString());
    assertEquals(green, image.getPixelAt(0, 1).toString());
    assertEquals(blue, image.getPixelAt(0, 2).toString());
  }

  // test getChannelValueAt
  @Test
  public void testGetChannelValueAt() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test ");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");

    assertEquals(230, image.getChannelValueAt(0, 0, 0));
  }

  // test updateImageComp

  // test updateImagePixel
  @Test
  public void testUpdateImagePixel() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test ");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");

    int[] newVal = new int[]{5, 5, 5};
    PixelImpl pixelImpl = new PixelImpl(newVal);
    String newValStr = "5\n5\n5\n";

    image.updateImagePixel(0, 0, pixelImpl);

    assertEquals(newValStr, image.getPixelAt(0, 0).toString());
  }

}
