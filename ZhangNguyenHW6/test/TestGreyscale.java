import org.junit.Test;

import java.io.StringReader;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageModel;
import model.ImageProcessorModel;
import model.ImageProcessorModelImpl;
import view.ImageModelTextView;

import static org.junit.Assert.assertTrue;

/**
 * Tests for greyscale, color-transformations, and filters.
 */
public class TestGreyscale {

  // test greyscale value
  @Test
  public void testGreyscaleValue() {
    ImageProcessorModel imageModel = new ImageProcessorModelImpl();
    Appendable out = new StringBuilder();
    ImageModelTextView view = new ImageModelTextView(out);
    Readable readable = new StringReader("load testNew.ppm new " +
            "load valueGrey.ppm grey " +
            "color-transformation value new value-greyed q");
    ImageController controller = new ImageControllerImpl(readable, imageModel, view);
    controller.runImageProcessor();

    ImageModel img = imageModel.getImage("value-greyed");
    ImageModel imgExpected = imageModel.getImage("grey");

    assertTrue(img.equals(imgExpected));
  }

  // test greyscale intensity
  @Test
  public void testGreyscaleIntensity() {
    ImageProcessorModel imageModel = new ImageProcessorModelImpl();
    Appendable out = new StringBuilder();
    ImageModelTextView view = new ImageModelTextView(out);
    Readable readable = new StringReader("load testNew.ppm new " +
            "load intensityGrey.ppm grey " +
            "color-transformation intensity new intensity-greyed q");
    ImageController controller = new ImageControllerImpl(readable, imageModel, view);
    controller.runImageProcessor();

    ImageModel img = imageModel.getImage("intensity-greyed");
    ImageModel imgExpected = imageModel.getImage("grey");

    assertTrue(img.equals(imgExpected));
  }

  // test greyscale luma
  @Test
  public void testGreyscaleLuma() {
    ImageProcessorModel imageModel = new ImageProcessorModelImpl();
    Appendable out = new StringBuilder();
    ImageModelTextView view = new ImageModelTextView(out);
    Readable readable = new StringReader("load testNew.ppm new " +
            "load lumaGrey.ppm grey " +
            "color-transformation luma new luma-greyed q");
    ImageController controller = new ImageControllerImpl(readable, imageModel, view);
    controller.runImageProcessor();

    ImageModel img = imageModel.getImage("luma-greyed");
    ImageModel imgExpected = imageModel.getImage("grey");

    assertTrue(img.equals(imgExpected));
  }


  // test greyscale red
  @Test
  public void testGreyscaleRed() {
    ImageProcessorModel imageModel = new ImageProcessorModelImpl();
    Appendable out = new StringBuilder();
    ImageModelTextView view = new ImageModelTextView(out);
    Readable readable = new StringReader("load testNew.ppm new " +
            "load redGrey.ppm grey " +
            "color-transformation red new red-greyed q");
    ImageController controller = new ImageControllerImpl(readable, imageModel, view);
    controller.runImageProcessor();

    ImageModel img = imageModel.getImage("red-greyed");
    ImageModel imgExpected = imageModel.getImage("grey");

    assertTrue(img.equals(imgExpected));
  }

  // test greyscale green
  @Test
  public void testGreyscaleGreen() {
    ImageProcessorModel imageModel = new ImageProcessorModelImpl();
    Appendable out = new StringBuilder();
    ImageModelTextView view = new ImageModelTextView(out);
    Readable readable = new StringReader("load testNew.ppm new " +
            "load greenGrey.ppm grey " +
            "color-transformation green new green-greyed q");
    ImageController controller = new ImageControllerImpl(readable, imageModel, view);
    controller.runImageProcessor();

    ImageModel img = imageModel.getImage("green-greyed");
    ImageModel imgExpected = imageModel.getImage("grey");

    assertTrue(img.equals(imgExpected));
  }

  // test greyscale blue
  @Test
  public void testGreyscaleBlue() {
    ImageProcessorModel imageModel = new ImageProcessorModelImpl();
    Appendable out = new StringBuilder();
    ImageModelTextView view = new ImageModelTextView(out);
    Readable readable = new StringReader("load testNew.ppm new " +
            "load blueGrey.ppm grey " +
            "color-transformation blue new blue-greyed q");
    ImageController controller = new ImageControllerImpl(readable, imageModel, view);
    controller.runImageProcessor();

    ImageModel img = imageModel.getImage("blue-greyed");
    ImageModel imgExpected = imageModel.getImage("grey");

    assertTrue(img.equals(imgExpected));
  }

  // test sepia
  @Test
  public void testSepia() {
    ImageProcessorModel imageModel = new ImageProcessorModelImpl();
    Appendable out = new StringBuilder();
    ImageModelTextView view = new ImageModelTextView(out);
    Readable readable = new StringReader("load testNew.ppm new " +
            "load sepia.ppm exp " +
            "color-transformation sepia new result q");
    ImageController controller = new ImageControllerImpl(readable, imageModel, view);
    controller.runImageProcessor();

    ImageModel img = imageModel.getImage("result");
    ImageModel imgExpected = imageModel.getImage("exp");

    assertTrue(img.equals(imgExpected));
  }

  // test blur
  @Test
  public void testBlur() {
    ImageProcessorModel imageModel = new ImageProcessorModelImpl();
    Appendable out = new StringBuilder();
    ImageModelTextView view = new ImageModelTextView(out);
    Readable readable = new StringReader("load testNew.ppm new " +
            "load blur.ppm exp " +
            "filter gaussian new result q");
    ImageController controller = new ImageControllerImpl(readable, imageModel, view);
    controller.runImageProcessor();

    ImageModel img = imageModel.getImage("result");
    ImageModel imgExpected = imageModel.getImage("exp");

    assertTrue(img.equals(imgExpected));
  }

  // test sharpen
  @Test
  public void testSharpen() {
    ImageProcessorModel imageModel = new ImageProcessorModelImpl();
    Appendable out = new StringBuilder();
    ImageModelTextView view = new ImageModelTextView(out);
    Readable readable = new StringReader("load testNew.ppm new " +
            "load sharpen.ppm exp " +
            "filter sharpen new result q");
    ImageController controller = new ImageControllerImpl(readable, imageModel, view);
    controller.runImageProcessor();

    ImageModel img = imageModel.getImage("result");
    ImageModel imgExpected = imageModel.getImage("exp");

    assertTrue(img.equals(imgExpected));
  }


}
