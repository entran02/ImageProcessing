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
 * Tests for loading an image.
 */
public class TestLoad {

  // test that a loaded image can be saved
  @Test
  public void testLoad1() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();
    String[] apArr = apStr.split("\n");

    //i rewrote this test using a new image has image because i made image field private. it is
    //better to use getters and stuff rather than making fields public.
    assertTrue(model.hasImage("test"));
  }

  // test load jpg
  @Test
  public void testLoadJpg() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load testNew.jpg test q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();
    String[] apArr = apStr.split("\n");

    assertTrue(model.hasImage("test"));
  }

  @Test
  public void testLoadPNG() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load testNew.png test q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();
    String[] apArr = apStr.split("\n");

    assertTrue(model.hasImage("test"));
  }

  @Test
  public void testLoadBMP() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load testNew.bmp test q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();
    String[] apArr = apStr.split("\n");

    assertTrue(model.hasImage("test"));
  }


  // test for loading a file not found
  @Test
  public void testLoadNotFound() {
    try {
      HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
      ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
      Readable read = new StringReader("load dog.ppm test q");
      Appendable out = new StringBuilder();
      ImageModelView view = new ImageModelTextView(out);

      ImageController controller = new ImageControllerImpl(read, model, view);
      controller.runImageProcessor();
      fail("Made a file that cannot be found");
    } catch (IllegalArgumentException e) {
      assertEquals("File dog.ppm not found!", e.getMessage());
    }
  }

  // test PPM that does not have P3 as header
  @Test
  public void testLoadWrongPPM() {
    try {
      HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
      ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
      Readable read = new StringReader("load p6.ppm p6 q");
      Appendable out = new StringBuilder();
      ImageModelView view = new ImageModelTextView(out);

      ImageController controller = new ImageControllerImpl(read, model, view);
      controller.runImageProcessor();
      fail("Made a file with incorrect PPM header");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid PPM file: plain RAW file should begin with P3.",
              e.getMessage());
    }
  }

  // test for loading the same image as two objects
  @Test
  public void testLoadTwo() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test1 load test.ppm test2 q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image1 = images.get("test1");
    ImageModel image2 = images.get("test2");

    assertTrue(image1.equals(image2));
  }


}
