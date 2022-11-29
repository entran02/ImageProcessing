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
 * Tests for flipping an image horizontally or vertically.
 */
public class TestFlip {

  // test horizontal flip
  @Test
  public void testFlipHorizontal() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load crystalstest.ppm test " +
            "load crystalstestExpected.ppm testFlipH " +
            "flip horizontal test test-flipped q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test-flipped");
    ImageModel imageFlipH = images.get("testFlipH");

    assertTrue(image.equals(imageFlipH));
  }

  // test vertical flip
  @Test
  public void testFlipVertical() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load testVertical.ppm test " +
            "load testVerticalFlip.ppm testFlipV " +
            "flip vertical test testResult q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("testResult");
    ImageModel imageFlipH = images.get("testFlipV");

    assertTrue(image.equals(imageFlipH));
  }

  // test horizontal-vertical == vertical-horizontal
  @Test
  public void testFlipHorVer() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test " +
            "load test.ppm test2 q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");
    ImageModel image2 = images.get("test2");

    model.flip("horizontal", "test", "test");
    model.flip("vertical", "test", "test");
    model.flip("horizontal", "test2", "test2");
    model.flip("vertical", "test2", "test2");

    assertTrue(image.equals(image2));

  }

  // test flip back to original
  @Test
  public void testFlipBack() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test " +
            "load test.ppm test2 q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");
    ImageModel image2 = images.get("test2");

    model.flip("horizontal", "test", "test");
    model.flip("vertical", "test", "test");
    model.flip("horizontal", "test", "test");
    model.flip("vertical", "test", "test");

    assertTrue(image.equals(image2));

  }

}
