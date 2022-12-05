import org.junit.Test;

import java.io.StringReader;
import java.util.HashMap;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageModel;
import model.ImageProcessorModelImpl;
import view.ImageModelTextView;
import view.ImageModelView;

import static org.junit.Assert.assertTrue;

/**
 * Tests for brightening an image.
 */
public class TestBrighten {

  // test brighten
  @Test
  public void testBright() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test " +
            "load testSaveBrighten.ppm testBright " +
            "brighten 10 test test q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");
    ImageModel imageBright = images.get("testBright");

    assertTrue(image.equals(imageBright));
  }

  // test darken
  @Test
  public void testDark() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test " +
            "load testDarken.ppm testDark " +
            "darken -10 test test q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");
    ImageModel imageDark = images.get("testDark");

    assertTrue(image.equals(imageDark));
  }

  // test brighten - red is clamped
  @Test
  public void testBrightClampRed() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load testClampRed.ppm test " +
            "load testClampRedBright.ppm testNew " +
            "brighten 100 test test q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");
    ImageModel imageNew = images.get("testNew");

    assertTrue(image.equals(imageNew));
  }

  // test brighten - green is clamped
  @Test
  public void testBrightClampGreen() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load testClampGreen.ppm test " +
            "load testClampGreenBright.ppm testNew " +
            "brighten 100 test test q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");
    ImageModel imageNew = images.get("testNew");

    assertTrue(image.equals(imageNew));
  }

  // test brighten - blue is clamped
  @Test
  public void testBrightClampBlue() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load testClampBlue.ppm test " +
            "load testClampBlueBright.ppm testNew " +
            "brighten 100 test test q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");
    ImageModel imageNew = images.get("testNew");

    assertTrue(image.equals(imageNew));
  }

  // test brighten - all at Max
  @Test
  public void testBrightClampAll() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load testWhite.ppm test " +
            "load testWhiteBright.ppm testNew " +
            "brighten 100 test test q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");
    ImageModel imageNew = images.get("testNew");

    assertTrue(image.equals(imageNew));
  }

  // test darken - red is clamped
  @Test
  public void testDarkClampRed() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load testClampRed.ppm test " +
            "load testClampRedDark.ppm testNew " +
            "darken -100 test test q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");
    ImageModel imageNew = images.get("testNew");

    assertTrue(image.equals(imageNew));
  }


  // test darken - all at Min
  @Test
  public void testDarkClampAll() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load testBlack.ppm test " +
            "load testBlackDark.ppm testNew " +
            "darken -100 test test q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");
    ImageModel imageNew = images.get("testNew");

    assertTrue(image.equals(imageNew));
  }

}
