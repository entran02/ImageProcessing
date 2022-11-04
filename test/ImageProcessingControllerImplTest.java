import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import model.Pixel;
import view.ImageProcessingView;
import view.ImageProcessingViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the controller which runs the Image Processing Program.
 */
public class ImageProcessingControllerImplTest {

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorNoModel() {
    new ImageProcessingControllerImpl(null, new ImageProcessingViewImpl(),
            new BufferedReader(new InputStreamReader(System.in)));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorNoView() {
    new ImageProcessingControllerImpl(new ImageProcessingModelImpl(), null,
            new BufferedReader(new InputStreamReader(System.in)));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorNoReadable() {
    new ImageProcessingControllerImpl(
            new ImageProcessingModelImpl(), new ImageProcessingViewImpl(), null);
  }

  @Test
  public void testMenu() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Readable in = new StringReader("menu");
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, in);
    controller.run();
    String expectedOut = "Welcome to the image processing program!\r\n" +
            "Supported user instructions are: \r\n" +
            "load image-path image-name (Load an image from the path and name it)\r\n" +
            "save image-path image-name (Save the image with that name to the specified path)\r\n" +
            "red-component image-name dest-image-name (Create a greyscale image with the " +
            "red-component of the image with the given name)\r\n" +
            "green-component image-name dest-image-name (Create a greyscale " +
            "image with the green-component of the image with the given name)\r\n" +
            "blue-component image-name dest-image-name (Create a greyscale " +
            "image with the blue-component of the image with the given name)\r\n" +
            "value-component image-name dest-image-name (Create a greyscale " +
            "image with the value-component of the image with the given name)\r\n" +
            "luma-component image-name dest-image-name (Create a greyscale " +
            "image with the luma-component of the image with the given name)\r\n" +
            "intensity-component image-name dest-image-name (Create a " +
            "greyscale image with the intensity-component of the image with the given name)\r\n" +
            "horizontal-flip image-name dest-image-name (Flip an image " +
            "horizontally to create a new image)\r\n" +
            "vertical-flip image-name dest-image-name (Flip an image " +
            "vertically to create a new image)\r\n" +
            "brighten increment image-name dest-image-name (brighten " +
            "the image by the given increment to create a new image, " +
            "referred to henceforth by the given destination name. " +
            "The increment may be positive (brightening) or negative (darkening))\r\n" +
            "menu (Print supported instruction list)\r\n" +
            "q or quit (quit the program) \r\n" +
            "Type instruction: Welcome to the image processing program!\r\n" +
            "Supported user instructions are: \r\n" +
            "load image-path image-name (Load an image from the path and name it)\r\n" +
            "save image-path image-name (Save the image with that name to the specified path)\r\n" +
            "red-component image-name dest-image-name (Create a " +
            "greyscale image with the red-component of the image with the given name)\r\n" +
            "green-component image-name dest-image-name (Create a " +
            "greyscale image with the green-component of the image with the given name)\r\n" +
            "blue-component image-name dest-image-name (Create a " +
            "greyscale image with the blue-component of the image with the given name)\r\n" +
            "value-component image-name dest-image-name (Create a " +
            "greyscale image with the value-component of the image with the given name)\r\n" +
            "luma-component image-name dest-image-name (Create a " +
            "greyscale image with the luma-component of the image with the given name)\r\n" +
            "intensity-component image-name dest-image-name (Create a" +
            " greyscale image with the intensity-component of the image with the given name)\r\n" +
            "horizontal-flip image-name dest-image-name (Flip an image " +
            "horizontally to create a new image)\r\n" +
            "vertical-flip image-name dest-image-name (Flip an " +
            "image vertically to create a new image)\r\n" +
            "brighten increment image-name dest-image-name (brighten " +
            "the image by the given increment to create a new image, referred " +
            "to henceforth by the given destination name. The increment may be positive" +
            " (brightening) or negative (darkening))\r\n" +
            "menu (Print supported instruction list)\r\n" +
            "q or quit (quit the program) \r\n" +
            "Thank you for using this program!";
    assertEquals(expectedOut, out.toString());
  }

  @Test
  public void testQ() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Readable in = new StringReader("q");
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, in);
    controller.run();
    String expectedOut = "Welcome to the image processing program!\r\n" +
            "Supported user instructions are: \r\n" +
            "load image-path image-name (Load an image from the path and name it)\r\n" +
            "save image-path image-name (Save the image with that name to the specified path)\r\n" +
            "red-component image-name dest-image-name (Create a greyscale image with the " +
            "red-component of the image with the given name)\r\n" +
            "green-component image-name dest-image-name (Create a greyscale " +
            "image with the green-component of the image with the given name)\r\n" +
            "blue-component image-name dest-image-name (Create a greyscale " +
            "image with the blue-component of the image with the given name)\r\n" +
            "value-component image-name dest-image-name (Create a greyscale " +
            "image with the value-component of the image with the given name)\r\n" +
            "luma-component image-name dest-image-name (Create a greyscale " +
            "image with the luma-component of the image with the given name)\r\n" +
            "intensity-component image-name dest-image-name (Create a " +
            "greyscale image with the intensity-component of the image with the given name)\r\n" +
            "horizontal-flip image-name dest-image-name (Flip an image " +
            "horizontally to create a new image)\r\n" +
            "vertical-flip image-name dest-image-name (Flip an image " +
            "vertically to create a new image)\r\n" +
            "brighten increment image-name dest-image-name (brighten " +
            "the image by the given increment to create a new image, " +
            "referred to henceforth by the given destination name. " +
            "The increment may be positive (brightening) or negative (darkening))\r\n" +
            "menu (Print supported instruction list)\r\n" +
            "q or quit (quit the program) \r\n" +
            "Type instruction: Thank you for using this program!";
    assertEquals(expectedOut, out.toString());
  }

  @Test
  public void testQuit() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Readable in = new StringReader("quit");
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, in);
    controller.run();
    String expectedOut = "Welcome to the image processing program!\r\n" +
            "Supported user instructions are: \r\n" +
            "load image-path image-name (Load an image from the path and name it)\r\n" +
            "save image-path image-name (Save the image with that name to the specified path)\r\n" +
            "red-component image-name dest-image-name (Create a greyscale image with the " +
            "red-component of the image with the given name)\r\n" +
            "green-component image-name dest-image-name (Create a greyscale " +
            "image with the green-component of the image with the given name)\r\n" +
            "blue-component image-name dest-image-name (Create a greyscale " +
            "image with the blue-component of the image with the given name)\r\n" +
            "value-component image-name dest-image-name (Create a greyscale " +
            "image with the value-component of the image with the given name)\r\n" +
            "luma-component image-name dest-image-name (Create a greyscale " +
            "image with the luma-component of the image with the given name)\r\n" +
            "intensity-component image-name dest-image-name (Create a " +
            "greyscale image with the intensity-component of the image with the given name)\r\n" +
            "horizontal-flip image-name dest-image-name (Flip an image " +
            "horizontally to create a new image)\r\n" +
            "vertical-flip image-name dest-image-name (Flip an image " +
            "vertically to create a new image)\r\n" +
            "brighten increment image-name dest-image-name (brighten " +
            "the image by the given increment to create a new image, " +
            "referred to henceforth by the given destination name. " +
            "The increment may be positive (brightening) or negative (darkening))\r\n" +
            "menu (Print supported instruction list)\r\n" +
            "q or quit (quit the program) \r\n" +
            "Type instruction: Thank you for using this program!";
    assertEquals(expectedOut, out.toString());
  }

  @Test
  public void testLoad() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Readable in = new StringReader("load res/Kirby/Kirby.ppm Kirby");
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, in);
    controller.run();
    String expectedOut = "Welcome to the image processing program!\r\n" +
            "Supported user instructions are: \r\n" +
            "load image-path image-name (Load an image from the path and name it)\r\n" +
            "save image-path image-name (Save the image with that name to the specified path)\r\n" +
            "red-component image-name dest-image-name (Create a greyscale image with the " +
            "red-component of the image with the given name)\r\n" +
            "green-component image-name dest-image-name (Create a greyscale " +
            "image with the green-component of the image with the given name)\r\n" +
            "blue-component image-name dest-image-name (Create a greyscale " +
            "image with the blue-component of the image with the given name)\r\n" +
            "value-component image-name dest-image-name (Create a greyscale " +
            "image with the value-component of the image with the given name)\r\n" +
            "luma-component image-name dest-image-name (Create a greyscale " +
            "image with the luma-component of the image with the given name)\r\n" +
            "intensity-component image-name dest-image-name (Create a " +
            "greyscale image with the intensity-component of the image with the given name)\r\n" +
            "horizontal-flip image-name dest-image-name (Flip an image " +
            "horizontally to create a new image)\r\n" +
            "vertical-flip image-name dest-image-name (Flip an image " +
            "vertically to create a new image)\r\n" +
            "brighten increment image-name dest-image-name (brighten " +
            "the image by the given increment to create a new image, " +
            "referred to henceforth by the given destination name. " +
            "The increment may be positive (brightening) or negative (darkening))\r\n" +
            "menu (Print supported instruction list)\r\n" +
            "q or quit (quit the program) \r\n" +
            "Type instruction: Thank you for using this program!";
    assertEquals(expectedOut, out.toString());
    assertEquals(model.getImage("Kirby").getPixels().get(0).get(0),
            new Pixel(102, 255, 102));
  }

  @Test
  public void testSave() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Readable in = new StringReader("load res/Kirby/Kirby.ppm Kirby " +
            "save res/Kirby/Kirby2.ppm Kirby " +
            "load res/Kirby/Kirby2.ppm Kirby2");
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, in);
    controller.run();
    String expectedOut = "Welcome to the image processing program!\r\n" +
            "Supported user instructions are: \r\n" +
            "load image-path image-name (Load an image from the path and name it)\r\n" +
            "save image-path image-name (Save the image with that name to the specified path)\r\n" +
            "red-component image-name dest-image-name (Create a greyscale image with the " +
            "red-component of the image with the given name)\r\n" +
            "green-component image-name dest-image-name (Create a greyscale " +
            "image with the green-component of the image with the given name)\r\n" +
            "blue-component image-name dest-image-name (Create a greyscale " +
            "image with the blue-component of the image with the given name)\r\n" +
            "value-component image-name dest-image-name (Create a greyscale " +
            "image with the value-component of the image with the given name)\r\n" +
            "luma-component image-name dest-image-name (Create a greyscale " +
            "image with the luma-component of the image with the given name)\r\n" +
            "intensity-component image-name dest-image-name (Create a " +
            "greyscale image with the intensity-component of the image with the given name)\r\n" +
            "horizontal-flip image-name dest-image-name (Flip an image " +
            "horizontally to create a new image)\r\n" +
            "vertical-flip image-name dest-image-name (Flip an image " +
            "vertically to create a new image)\r\n" +
            "brighten increment image-name dest-image-name (brighten " +
            "the image by the given increment to create a new image, " +
            "referred to henceforth by the given destination name. " +
            "The increment may be positive (brightening) or negative (darkening))\r\n" +
            "menu (Print supported instruction list)\r\n" +
            "q or quit (quit the program) \r\n" +
            "Type instruction: Type instruction: Type instruction:" +
            " Thank you for using this program!";
    assertEquals(expectedOut, out.toString());
    assertEquals(model.getImage("Kirby"), model.getImage("Kirby2"));

  }

  @Test
  public void testRed() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Readable in = new StringReader("load res/Kirby/Kirby.ppm Kirby " +
            "red-component Kirby RedKirby");
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, in);
    controller.run();
    String expectedOut = "Welcome to the image processing program!\r\n" +
            "Supported user instructions are: \r\n" +
            "load image-path image-name (Load an image from the path and name it)\r\n" +
            "save image-path image-name (Save the image with that name to the specified path)\r\n" +
            "red-component image-name dest-image-name (Create a greyscale image with the " +
            "red-component of the image with the given name)\r\n" +
            "green-component image-name dest-image-name (Create a greyscale " +
            "image with the green-component of the image with the given name)\r\n" +
            "blue-component image-name dest-image-name (Create a greyscale " +
            "image with the blue-component of the image with the given name)\r\n" +
            "value-component image-name dest-image-name (Create a greyscale " +
            "image with the value-component of the image with the given name)\r\n" +
            "luma-component image-name dest-image-name (Create a greyscale " +
            "image with the luma-component of the image with the given name)\r\n" +
            "intensity-component image-name dest-image-name (Create a " +
            "greyscale image with the intensity-component of the image with the given name)\r\n" +
            "horizontal-flip image-name dest-image-name (Flip an image " +
            "horizontally to create a new image)\r\n" +
            "vertical-flip image-name dest-image-name (Flip an image " +
            "vertically to create a new image)\r\n" +
            "brighten increment image-name dest-image-name (brighten " +
            "the image by the given increment to create a new image, " +
            "referred to henceforth by the given destination name. " +
            "The increment may be positive (brightening) or negative (darkening))\r\n" +
            "menu (Print supported instruction list)\r\n" +
            "q or quit (quit the program) \r\n" +
            "Type instruction: Type instruction: Thank you for using this program!";
    assertEquals(model.getImage("Kirby").getPixels().get(0).get(0),
            new Pixel(102, 102, 102));
    assertEquals(expectedOut, out.toString());
  }

  @Test
  public void testGreen() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Readable in = new StringReader("load res/Kirby/Kirby.ppm Kirby " +
            "green-component Kirby GreenKirby");
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, in);
    controller.run();
    String expectedOut = "Welcome to the image processing program!\r\n" +
            "Supported user instructions are: \r\n" +
            "load image-path image-name (Load an image from the path and name it)\r\n" +
            "save image-path image-name (Save the image with that name to the specified path)\r\n" +
            "red-component image-name dest-image-name (Create a greyscale image with the " +
            "red-component of the image with the given name)\r\n" +
            "green-component image-name dest-image-name (Create a greyscale " +
            "image with the green-component of the image with the given name)\r\n" +
            "blue-component image-name dest-image-name (Create a greyscale " +
            "image with the blue-component of the image with the given name)\r\n" +
            "value-component image-name dest-image-name (Create a greyscale " +
            "image with the value-component of the image with the given name)\r\n" +
            "luma-component image-name dest-image-name (Create a greyscale " +
            "image with the luma-component of the image with the given name)\r\n" +
            "intensity-component image-name dest-image-name (Create a " +
            "greyscale image with the intensity-component of the image with the given name)\r\n" +
            "horizontal-flip image-name dest-image-name (Flip an image " +
            "horizontally to create a new image)\r\n" +
            "vertical-flip image-name dest-image-name (Flip an image " +
            "vertically to create a new image)\r\n" +
            "brighten increment image-name dest-image-name (brighten " +
            "the image by the given increment to create a new image, " +
            "referred to henceforth by the given destination name. " +
            "The increment may be positive (brightening) or negative (darkening))\r\n" +
            "menu (Print supported instruction list)\r\n" +
            "q or quit (quit the program) \r\n" +
            "Type instruction: Type instruction: Thank you for using this program!";
    assertEquals(expectedOut, out.toString());
    assertEquals(model.getImage("Kirby").getPixels().get(0).get(0),
            new Pixel(255, 255, 255));
  }

  @Test
  public void testBlue() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Readable in = new StringReader("load res/Kirby/Kirby.ppm Kirby " +
            "blue-component Kirby BlueKirby");
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, in);
    controller.run();
    String expectedOut = "Welcome to the image processing program!\r\n" +
            "Supported user instructions are: \r\n" +
            "load image-path image-name (Load an image from the path and name it)\r\n" +
            "save image-path image-name (Save the image with that name to the specified path)\r\n" +
            "red-component image-name dest-image-name (Create a greyscale image with the " +
            "red-component of the image with the given name)\r\n" +
            "green-component image-name dest-image-name (Create a greyscale " +
            "image with the green-component of the image with the given name)\r\n" +
            "blue-component image-name dest-image-name (Create a greyscale " +
            "image with the blue-component of the image with the given name)\r\n" +
            "value-component image-name dest-image-name (Create a greyscale " +
            "image with the value-component of the image with the given name)\r\n" +
            "luma-component image-name dest-image-name (Create a greyscale " +
            "image with the luma-component of the image with the given name)\r\n" +
            "intensity-component image-name dest-image-name (Create a " +
            "greyscale image with the intensity-component of the image with the given name)\r\n" +
            "horizontal-flip image-name dest-image-name (Flip an image " +
            "horizontally to create a new image)\r\n" +
            "vertical-flip image-name dest-image-name (Flip an image " +
            "vertically to create a new image)\r\n" +
            "brighten increment image-name dest-image-name (brighten " +
            "the image by the given increment to create a new image, " +
            "referred to henceforth by the given destination name. " +
            "The increment may be positive (brightening) or negative (darkening))\r\n" +
            "menu (Print supported instruction list)\r\n" +
            "q or quit (quit the program) \r\n" +
            "Type instruction: Type instruction: Thank you for using this program!";
    assertEquals(expectedOut, out.toString());
    assertEquals(model.getImage("Kirby").getPixels().get(0).get(0),
            new Pixel(102, 102, 102));
  }

  @Test
  public void testValue() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Readable in = new StringReader("load res/Kirby/Kirby.ppm Kirby " +
            "value-component Kirby ValueKirby");
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, in);
    controller.run();
    String expectedOut = "Welcome to the image processing program!\r\n" +
            "Supported user instructions are: \r\n" +
            "load image-path image-name (Load an image from the path and name it)\r\n" +
            "save image-path image-name (Save the image with that name to the specified path)\r\n" +
            "red-component image-name dest-image-name (Create a greyscale image with the " +
            "red-component of the image with the given name)\r\n" +
            "green-component image-name dest-image-name (Create a greyscale " +
            "image with the green-component of the image with the given name)\r\n" +
            "blue-component image-name dest-image-name (Create a greyscale " +
            "image with the blue-component of the image with the given name)\r\n" +
            "value-component image-name dest-image-name (Create a greyscale " +
            "image with the value-component of the image with the given name)\r\n" +
            "luma-component image-name dest-image-name (Create a greyscale " +
            "image with the luma-component of the image with the given name)\r\n" +
            "intensity-component image-name dest-image-name (Create a " +
            "greyscale image with the intensity-component of the image with the given name)\r\n" +
            "horizontal-flip image-name dest-image-name (Flip an image " +
            "horizontally to create a new image)\r\n" +
            "vertical-flip image-name dest-image-name (Flip an image " +
            "vertically to create a new image)\r\n" +
            "brighten increment image-name dest-image-name (brighten " +
            "the image by the given increment to create a new image, " +
            "referred to henceforth by the given destination name. " +
            "The increment may be positive (brightening) or negative (darkening))\r\n" +
            "menu (Print supported instruction list)\r\n" +
            "q or quit (quit the program) \r\n" +
            "Type instruction: Type instruction: Thank you for using this program!";
    assertEquals(expectedOut, out.toString());
    assertEquals(model.getImage("Kirby").getPixels().get(0).get(0),
            new Pixel(255, 255, 255));
  }

  @Test
  public void testLuma() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Readable in = new StringReader("load res/Kirby/Kirby.ppm Kirby " +
            "luma-component Kirby LumaKirby");
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, in);
    controller.run();
    String expectedOut = "Welcome to the image processing program!\r\n" +
            "Supported user instructions are: \r\n" +
            "load image-path image-name (Load an image from the path and name it)\r\n" +
            "save image-path image-name (Save the image with that name to the specified path)\r\n" +
            "red-component image-name dest-image-name (Create a greyscale image with the " +
            "red-component of the image with the given name)\r\n" +
            "green-component image-name dest-image-name (Create a greyscale " +
            "image with the green-component of the image with the given name)\r\n" +
            "blue-component image-name dest-image-name (Create a greyscale " +
            "image with the blue-component of the image with the given name)\r\n" +
            "value-component image-name dest-image-name (Create a greyscale " +
            "image with the value-component of the image with the given name)\r\n" +
            "luma-component image-name dest-image-name (Create a greyscale " +
            "image with the luma-component of the image with the given name)\r\n" +
            "intensity-component image-name dest-image-name (Create a " +
            "greyscale image with the intensity-component of the image with the given name)\r\n" +
            "horizontal-flip image-name dest-image-name (Flip an image " +
            "horizontally to create a new image)\r\n" +
            "vertical-flip image-name dest-image-name (Flip an image " +
            "vertically to create a new image)\r\n" +
            "brighten increment image-name dest-image-name (brighten " +
            "the image by the given increment to create a new image, " +
            "referred to henceforth by the given destination name. " +
            "The increment may be positive (brightening) or negative (darkening))\r\n" +
            "menu (Print supported instruction list)\r\n" +
            "q or quit (quit the program) \r\n" +
            "Type instruction: Type instruction: Thank you for using this program!";
    assertEquals(expectedOut, out.toString());
    assertEquals(model.getImage("Kirby").getPixels().get(0).get(0),
            new Pixel(211, 211, 211));
  }

  @Test
  public void testIntensity() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Readable in = new StringReader("load res/Kirby/Kirby.ppm Kirby " +
            "intensity-component Kirby IntensityKirby");
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, in);
    controller.run();
    String expectedOut = "Welcome to the image processing program!\r\n" +
            "Supported user instructions are: \r\n" +
            "load image-path image-name (Load an image from the path and name it)\r\n" +
            "save image-path image-name (Save the image with that name to the specified path)\r\n" +
            "red-component image-name dest-image-name (Create a greyscale image with the " +
            "red-component of the image with the given name)\r\n" +
            "green-component image-name dest-image-name (Create a greyscale " +
            "image with the green-component of the image with the given name)\r\n" +
            "blue-component image-name dest-image-name (Create a greyscale " +
            "image with the blue-component of the image with the given name)\r\n" +
            "value-component image-name dest-image-name (Create a greyscale " +
            "image with the value-component of the image with the given name)\r\n" +
            "luma-component image-name dest-image-name (Create a greyscale " +
            "image with the luma-component of the image with the given name)\r\n" +
            "intensity-component image-name dest-image-name (Create a " +
            "greyscale image with the intensity-component of the image with the given name)\r\n" +
            "horizontal-flip image-name dest-image-name (Flip an image " +
            "horizontally to create a new image)\r\n" +
            "vertical-flip image-name dest-image-name (Flip an image " +
            "vertically to create a new image)\r\n" +
            "brighten increment image-name dest-image-name (brighten " +
            "the image by the given increment to create a new image, " +
            "referred to henceforth by the given destination name. " +
            "The increment may be positive (brightening) or negative (darkening))\r\n" +
            "menu (Print supported instruction list)\r\n" +
            "q or quit (quit the program) \r\n" +
            "Type instruction: Type instruction: Thank you for using this program!";
    assertEquals(expectedOut, out.toString());
    assertEquals(model.getImage("Kirby").getPixels().get(0).get(0),
            new Pixel(153, 153, 153));
  }

  @Test
  public void testHorizontal() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Readable in = new StringReader("load res/Kirby/Kirby.ppm Kirby " +
            "horizontal-flip Kirby HorizontalKirby");
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, in);
    controller.run();
    String expectedOut = "Welcome to the image processing program!\r\n" +
            "Supported user instructions are: \r\n" +
            "load image-path image-name (Load an image from the path and name it)\r\n" +
            "save image-path image-name (Save the image with that name to the specified path)\r\n" +
            "red-component image-name dest-image-name (Create a greyscale image with the " +
            "red-component of the image with the given name)\r\n" +
            "green-component image-name dest-image-name (Create a greyscale " +
            "image with the green-component of the image with the given name)\r\n" +
            "blue-component image-name dest-image-name (Create a greyscale " +
            "image with the blue-component of the image with the given name)\r\n" +
            "value-component image-name dest-image-name (Create a greyscale " +
            "image with the value-component of the image with the given name)\r\n" +
            "luma-component image-name dest-image-name (Create a greyscale " +
            "image with the luma-component of the image with the given name)\r\n" +
            "intensity-component image-name dest-image-name (Create a " +
            "greyscale image with the intensity-component of the image with the given name)\r\n" +
            "horizontal-flip image-name dest-image-name (Flip an image " +
            "horizontally to create a new image)\r\n" +
            "vertical-flip image-name dest-image-name (Flip an image " +
            "vertically to create a new image)\r\n" +
            "brighten increment image-name dest-image-name (brighten " +
            "the image by the given increment to create a new image, " +
            "referred to henceforth by the given destination name. " +
            "The increment may be positive (brightening) or negative (darkening))\r\n" +
            "menu (Print supported instruction list)\r\n" +
            "q or quit (quit the program) \r\n" +
            "Type instruction: Type instruction: Thank you for using this program!";
    assertEquals(expectedOut, out.toString());
    assertEquals(model.getImage("Kirby").getPixels().get(0).get(0),
            new Pixel(255, 255, 204));
  }

  @Test
  public void testVertical() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Readable in = new StringReader("load res/Kirby/Kirby.ppm Kirby " +
            "vertical-flip Kirby VerticalKirby");
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, in);
    controller.run();
    String expectedOut = "Welcome to the image processing program!\r\n" +
            "Supported user instructions are: \r\n" +
            "load image-path image-name (Load an image from the path and name it)\r\n" +
            "save image-path image-name (Save the image with that name to the specified path)\r\n" +
            "red-component image-name dest-image-name (Create a greyscale image with the " +
            "red-component of the image with the given name)\r\n" +
            "green-component image-name dest-image-name (Create a greyscale " +
            "image with the green-component of the image with the given name)\r\n" +
            "blue-component image-name dest-image-name (Create a greyscale " +
            "image with the blue-component of the image with the given name)\r\n" +
            "value-component image-name dest-image-name (Create a greyscale " +
            "image with the value-component of the image with the given name)\r\n" +
            "luma-component image-name dest-image-name (Create a greyscale " +
            "image with the luma-component of the image with the given name)\r\n" +
            "intensity-component image-name dest-image-name (Create a " +
            "greyscale image with the intensity-component of the image with the given name)\r\n" +
            "horizontal-flip image-name dest-image-name (Flip an image " +
            "horizontally to create a new image)\r\n" +
            "vertical-flip image-name dest-image-name (Flip an image " +
            "vertically to create a new image)\r\n" +
            "brighten increment image-name dest-image-name (brighten " +
            "the image by the given increment to create a new image, " +
            "referred to henceforth by the given destination name. " +
            "The increment may be positive (brightening) or negative (darkening))\r\n" +
            "menu (Print supported instruction list)\r\n" +
            "q or quit (quit the program) \r\n" +
            "Type instruction: Type instruction: Thank you for using this program!";
    assertEquals(expectedOut, out.toString());
    assertEquals(model.getImage("Kirby").getPixels().get(0).get(0),
            new Pixel(255, 0, 127));
  }

  @Test
  public void testBrighten() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Readable in = new StringReader("load res/Kirby/Kirby.ppm Kirby " +
            "brighten 52 Kirby BrightKirby");
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, in);
    controller.run();
    String expectedOut = "Welcome to the image processing program!\r\n" +
            "Supported user instructions are: \r\n" +
            "load image-path image-name (Load an image from the path and name it)\r\n" +
            "save image-path image-name (Save the image with that name to the specified path)\r\n" +
            "red-component image-name dest-image-name (Create a greyscale image with the " +
            "red-component of the image with the given name)\r\n" +
            "green-component image-name dest-image-name (Create a greyscale " +
            "image with the green-component of the image with the given name)\r\n" +
            "blue-component image-name dest-image-name (Create a greyscale " +
            "image with the blue-component of the image with the given name)\r\n" +
            "value-component image-name dest-image-name (Create a greyscale " +
            "image with the value-component of the image with the given name)\r\n" +
            "luma-component image-name dest-image-name (Create a greyscale " +
            "image with the luma-component of the image with the given name)\r\n" +
            "intensity-component image-name dest-image-name (Create a " +
            "greyscale image with the intensity-component of the image with the given name)\r\n" +
            "horizontal-flip image-name dest-image-name (Flip an image " +
            "horizontally to create a new image)\r\n" +
            "vertical-flip image-name dest-image-name (Flip an image " +
            "vertically to create a new image)\r\n" +
            "brighten increment image-name dest-image-name (brighten " +
            "the image by the given increment to create a new image, " +
            "referred to henceforth by the given destination name. " +
            "The increment may be positive (brightening) or negative (darkening))\r\n" +
            "menu (Print supported instruction list)\r\n" +
            "q or quit (quit the program) \r\n" +
            "Type instruction: Type instruction: Thank you for using this program!";
    assertEquals(expectedOut, out.toString());
    assertEquals(model.getImage("Kirby").getPixels().get(0).get(0),
            new Pixel(154, 255, 154));
  }

  @Test
  public void testDarken() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Readable in = new StringReader("load res/Kirby/Kirby.ppm Kirby " +
            "brighten -52 Kirby DarkKirby");
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, in);
    controller.run();
    String expectedOut = "Welcome to the image processing program!\r\n" +
            "Supported user instructions are: \r\n" +
            "load image-path image-name (Load an image from the path and name it)\r\n" +
            "save image-path image-name (Save the image with that name to the specified path)\r\n" +
            "red-component image-name dest-image-name (Create a greyscale image with the " +
            "red-component of the image with the given name)\r\n" +
            "green-component image-name dest-image-name (Create a greyscale " +
            "image with the green-component of the image with the given name)\r\n" +
            "blue-component image-name dest-image-name (Create a greyscale " +
            "image with the blue-component of the image with the given name)\r\n" +
            "value-component image-name dest-image-name (Create a greyscale " +
            "image with the value-component of the image with the given name)\r\n" +
            "luma-component image-name dest-image-name (Create a greyscale " +
            "image with the luma-component of the image with the given name)\r\n" +
            "intensity-component image-name dest-image-name (Create a " +
            "greyscale image with the intensity-component of the image with the given name)\r\n" +
            "horizontal-flip image-name dest-image-name (Flip an image " +
            "horizontally to create a new image)\r\n" +
            "vertical-flip image-name dest-image-name (Flip an image " +
            "vertically to create a new image)\r\n" +
            "brighten increment image-name dest-image-name (brighten " +
            "the image by the given increment to create a new image, " +
            "referred to henceforth by the given destination name. " +
            "The increment may be positive (brightening) or negative (darkening))\r\n" +
            "menu (Print supported instruction list)\r\n" +
            "q or quit (quit the program) \r\n" +
            "Type instruction: Type instruction: Thank you for using this program!";
    assertEquals(expectedOut, out.toString());
    assertEquals(model.getImage("Kirby").getPixels().get(0).get(0),
            new Pixel(50, 203, 50));
  }

  @Test
  public void testMacroBeforeLoad() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Readable in = new StringReader("green-component Kirby GreenKirby");
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, in);
    controller.run();
    String expectedOut = "Welcome to the image processing program!\r\n" +
            "Supported user instructions are: \r\n" +
            "load image-path image-name (Load an image from the path and name it)\r\n" +
            "save image-path image-name (Save the image with that name to the specified path)\r\n" +
            "red-component image-name dest-image-name (Create a greyscale image with the " +
            "red-component of the image with the given name)\r\n" +
            "green-component image-name dest-image-name (Create a greyscale " +
            "image with the green-component of the image with the given name)\r\n" +
            "blue-component image-name dest-image-name (Create a greyscale " +
            "image with the blue-component of the image with the given name)\r\n" +
            "value-component image-name dest-image-name (Create a greyscale " +
            "image with the value-component of the image with the given name)\r\n" +
            "luma-component image-name dest-image-name (Create a greyscale " +
            "image with the luma-component of the image with the given name)\r\n" +
            "intensity-component image-name dest-image-name (Create a " +
            "greyscale image with the intensity-component of the image with the given name)\r\n" +
            "horizontal-flip image-name dest-image-name (Flip an image " +
            "horizontally to create a new image)\r\n" +
            "vertical-flip image-name dest-image-name (Flip an image " +
            "vertically to create a new image)\r\n" +
            "brighten increment image-name dest-image-name (brighten " +
            "the image by the given increment to create a new image, " +
            "referred to henceforth by the given destination name. " +
            "The increment may be positive (brightening) or negative (darkening))\r\n" +
            "menu (Print supported instruction list)\r\n" +
            "q or quit (quit the program) \r\n" +
            "Type instruction: Invalid operation! Please try again.\n" +
            "Thank you for using this program!";
    assertEquals(expectedOut, out.toString());
  }

  @Test
  public void testInvalidInstruction() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Readable in = new StringReader("copy-image Kirby NewKirby");
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, in);
    controller.run();
    String expectedOut = "Welcome to the image processing program!\r\n" +
            "Supported user instructions are: \r\n" +
            "load image-path image-name (Load an image from the path and name it)\r\n" +
            "save image-path image-name (Save the image with that name to the specified path)\r\n" +
            "red-component image-name dest-image-name (Create a greyscale image with the " +
            "red-component of the image with the given name)\r\n" +
            "green-component image-name dest-image-name (Create a greyscale " +
            "image with the green-component of the image with the given name)\r\n" +
            "blue-component image-name dest-image-name (Create a greyscale " +
            "image with the blue-component of the image with the given name)\r\n" +
            "value-component image-name dest-image-name (Create a greyscale " +
            "image with the value-component of the image with the given name)\r\n" +
            "luma-component image-name dest-image-name (Create a greyscale " +
            "image with the luma-component of the image with the given name)\r\n" +
            "intensity-component image-name dest-image-name (Create a " +
            "greyscale image with the intensity-component of the image with the given name)\r\n" +
            "horizontal-flip image-name dest-image-name (Flip an image " +
            "horizontally to create a new image)\r\n" +
            "vertical-flip image-name dest-image-name (Flip an image " +
            "vertically to create a new image)\r\n" +
            "brighten increment image-name dest-image-name (brighten " +
            "the image by the given increment to create a new image, " +
            "referred to henceforth by the given destination name. " +
            "The increment may be positive (brightening) or negative (darkening))\r\n" +
            "menu (Print supported instruction list)\r\n" +
            "q or quit (quit the program) \r\n" +
            "Type instruction: Undefined instruction: copy-image\r\n" +
            "Type instruction: Undefined instruction: Kirby\r\n" +
            "Type instruction: Undefined instruction: NewKirby\r\n" +
            "Thank you for using this program!";
    assertEquals(expectedOut, out.toString());
  }

}