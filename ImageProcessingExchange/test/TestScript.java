import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageModel;
import model.ImageProcessorModelImpl;
import view.ImageModelTextView;
import view.ImageModelView;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the script.
 */
public class TestScript {

  @Test
  public void testWelcomeMessage() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = null;
    try {
      read = new FileReader("testScript.txt");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();
    String[] apArr = apStr.split("\n");

    assertEquals("Try it out :)", apArr[22]);
  }

  @Test
  public void testNoMoreInputs() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = null;
    try {
      read = new FileReader("testScript.txt");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();
    String[] apArr = apStr.split("\n");

    assertEquals("Ran out of inputs, add more.", apArr[54]);
  }

  // test invalid command
  @Test
  public void testInvalidCommand() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = null;
    try {
      read = new FileReader("testScript.txt");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();
    String[] apArr = apStr.split("\n");

    String expectedStr = "Invalid command. Please try again.";
    assertEquals(expectedStr, apArr[23]);
  }

  // test load
  @Test
  public void testLoad() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = null;
    try {
      read = new FileReader("testScript.txt");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();
    String[] apArr = apStr.split("\n");

    String expectedStr = "Image file originalPopCat.jpeg was " +
            "successfully loaded under the name orgPopCat.";
    assertEquals(expectedStr, apArr[24]);
  }

  // test save
  @Test
  public void testSave() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = null;
    try {
      read = new FileReader("testScript.txt");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();
    String[] apArr = apStr.split("\n");

    String expectedStr = "Image orgPopCat was successfully saved under name: orgPopCat.ppm";
    assertEquals(expectedStr, apArr[41]);
  }

  // test horizontal-flip
  @Test
  public void testHorFlip() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = null;
    try {
      read = new FileReader("testScript.txt");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();
    String[] apArr = apStr.split("\n");

    String expectedStr = "flip applied!";
    assertEquals(expectedStr, apArr[37]);
  }

  // test vertical-flip
  @Test
  public void testVerFlip() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = null;
    try {
      read = new FileReader("testScript.txt");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();
    String[] apArr = apStr.split("\n");

    String expectedStr = "flip applied!";
    assertEquals(expectedStr, apArr[38]);
  }

  // test brighten
  @Test
  public void testBrighten() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = null;
    try {
      read = new FileReader("testScript.txt");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();
    String[] apArr = apStr.split("\n");

    String expectedStr = "brighten applied!";
    assertEquals(expectedStr, apArr[39]);
  }

  // test darken
  @Test
  public void testDarken() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = null;
    try {
      read = new FileReader("testScript.txt");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();
    String[] apArr = apStr.split("\n");

    String expectedStr = "brighten applied!";
    assertEquals(expectedStr, apArr[40]);
  }

  // test greyscale
  @Test
  public void testGreyscale() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = null;
    try {
      read = new FileReader("testScript.txt");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    String apStr = out.toString();
    String[] apArr = apStr.split("\n");

    String expectedStr = "color-transformation applied!";
    assertEquals(expectedStr, apArr[30]);
  }
}
