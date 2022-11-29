import org.junit.Test;

import java.io.StringReader;
import java.util.HashMap;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageModel;
import model.ImageProcessorModelImpl;
import view.ImageModelTextView;
import view.ImageModelView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for saving an image.
 */
public class TestSave {

  // test save on a new filename that does not already exist
  @Test
  public void testSaveNew() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test " +
            "save testSaved test ppm " +
            "load testSaved.ppm testNew q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    assertTrue(images.containsKey("testNew"));
  }

  // test save on an existing filename
  @Test
  public void testSaveOld() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test " +
            "save test test ppm " +
            "load test.ppm testNew q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");
    ImageModel imageNew = images.get("testNew");

    assertTrue(image.equals(imageNew));
  }

  // test save on modified file
  @Test
  public void testSaveModified() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test " +
            "flip horizontal test test " +
            "save testSaved test ppm " +
            "load testSaved.ppm testNew q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");
    ImageModel imageNew = images.get("testNew");

    // if file saved properly after being modified,
    // a new model with the same file name should be the same as the first model
    assertTrue(image.equals(imageNew));
  }

  // test save on null model
  @Test
  public void testSaveNull() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test " +
            "save testSaved null ppm q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    try {
      ImageController controller = new ImageControllerImpl(read, model, view);
      controller.runImageProcessor();
      fail("saved a null image");
    } catch (IllegalArgumentException e) {
      assertEquals("Specified image name does not exist in current images.",
              e.getMessage());
    }
  }

  // TEST FOR SAVE PPM as JPG, PNG, OR BMP ARE RETURNING NULL POINTER EXCEPTION

  // test save PPM as Jpg
  @Test
  public void testSaveAsJpg() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test " +
            "save test test jpg " +
            "load test.jpg testNew q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    assertTrue(images.containsKey("testNew"));
  }

  // test save PPM as PNG
  @Test
  public void testSaveAsPNG() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test " +
            "save test test png " +
            "load test.png testNew q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    assertTrue(images.containsKey("testNew"));
  }

  // test save PPM as BMP
  @Test
  public void testSaveAsBMP() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test " +
            "save test test bmp " +
            "load test.bmp testNew q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    assertTrue(images.containsKey("testNew"));
  }

}
