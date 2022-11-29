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

/**
 * Tests for the controller.
 */
public class TestController {

  String welcomeMsg = "Welcome to Katie and Crystal's image processor!\n" +
          "We currently only support the following commands:\n" +
          "load <image path/name> <desired new image name>\n" +
          "save <image path/name> <name of image to be saved> <file type>*\n" +
          "*file types should be entered as specified:\n" +
          "e.g. ppm, jpeg, png and so on.\n" +
          "\n" +
          "IMAGE OPERATIONS:\n" +
          "flip vertical/horizontal <image name> <desired new image name>\n" +
          "brighten value* <image name> <desired new image name>\n" +
          "*enter a positive value to brighten or a negative value to darken\n" +
          "color-transformation type <image name> <desired new image name>\n" +
          "types of color transformations we support:\n" +
          "sepia\n" +
          "luma\n" +
          "value\n" +
          "intensity\n" +
          "red/green/blue\n" +
          "filter gaussian-blur/sharpen <image name> <desired new image name>\n" +
          "\n" +
          "OTHER OPERATIONS:\n" +
          "q or quit to quit\n" +
          "Try it out :)\n";

  String loadMsg = "Image file test.ppm was successfully loaded under the name test.\n";

  String saveMsg = "Image testSaved was successfully saved under name: testSaved.ppm\n";

  String moreInputsMsg = "Ran out of inputs, add more.\n";


  @Test
  public void testWelcomeMessage() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();
    String expectedStr = welcomeMsg + "Program quit!\n";

    assertEquals(expectedStr, apStr);
  }

  // test no more inputs
  @Test
  public void testNoMoreInputs() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();

    String expectedStr = welcomeMsg + "Ran out of inputs, add more.\n";
    assertEquals(expectedStr, apStr);
  }

  // test invalid command
  @Test
  public void testInvalidCommand() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("cheese");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();

    String expectedStr = welcomeMsg + "Invalid command. Please try again.\n" +
            moreInputsMsg;
    assertEquals(expectedStr, apStr);
  }

  // test load
  @Test
  public void testLoad() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();

    String expectedStr = welcomeMsg + loadMsg + moreInputsMsg;
    assertEquals(expectedStr, apStr);
  }

  // test save
  @Test
  public void testSave() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test "
            + "save testSaved test ppm");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();

    String expectedStr = welcomeMsg + loadMsg + saveMsg + moreInputsMsg;
    assertEquals(expectedStr, apStr);
  }

  // test horizontal-flip
  @Test
  public void testHorFlip() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test "
            + "flip horizontal test test");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();

    String expectedStr = welcomeMsg + loadMsg +
            "flip applied!\n" + moreInputsMsg;
    assertEquals(expectedStr, apStr);
  }

  // test vertical-flip
  @Test
  public void testVerFlip() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test "
            + "flip vertical test test");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();

    String expectedStr = welcomeMsg + loadMsg +
            "flip applied!\n" + moreInputsMsg;
    assertEquals(expectedStr, apStr);
  }

  // test brighten
  @Test
  public void testBrighten() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test "
            + "brighten 10 test test");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();

    String expectedStr = welcomeMsg + loadMsg +
            "brighten applied!\n" + moreInputsMsg;
    assertEquals(expectedStr, apStr);
  }

  // test darken
  @Test
  public void testDarken() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test "
            + "brighten -10 test test");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();

    String expectedStr = welcomeMsg + loadMsg +
            "brighten applied!\n" + moreInputsMsg;
    assertEquals(expectedStr, apStr);
  }

  // test greyscale
  @Test
  public void testGreyscale() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test "
            + "color-transformation red test test");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();

    String expectedStr = welcomeMsg + loadMsg +
            "color-transformation applied!\n" + moreInputsMsg;
    assertEquals(expectedStr, apStr);
  }

}
