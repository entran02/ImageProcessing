import org.junit.Test;

import java.io.StringReader;
import java.util.HashMap;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.Histogram;
import model.HistogramImpl;
import model.ImageModel;
import model.ImageProcessorModelImpl;
import view.ImageModelTextView;
import view.ImageModelView;

import static org.junit.Assert.assertEquals;


/**
 * Class that tests the histogram implementation.
 */
public class TestHistogram {

  @Test
  public void testConstructor() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test ");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");

    Histogram histogram = new HistogramImpl(image);

    assertEquals(0, histogram.getLowValue());
    assertEquals(255, histogram.getHighValue());

    int[][] redHistogram = histogram.getHistogram("red");

    assertEquals(0, redHistogram[0][0]);
    assertEquals(155, redHistogram[155][0]);
    assertEquals(255, redHistogram[255][0]);

    int[][] greenHistogram = histogram.getHistogram("green");

    assertEquals(0, greenHistogram[0][0]);
    assertEquals(155, greenHistogram[155][0]);
    assertEquals(255, greenHistogram[255][0]);

    int[][] blueHistogram = histogram.getHistogram("blue");

    assertEquals(0, blueHistogram[0][0]);
    assertEquals(155, blueHistogram[155][0]);
    assertEquals(255, blueHistogram[255][0]);

    int[][] intensityHistogram = histogram.getHistogram("intensity");

    assertEquals(0, intensityHistogram[0][0]);
    assertEquals(155, intensityHistogram[155][0]);
    assertEquals(255, intensityHistogram[255][0]);
  }

  @Test
  public void testUpdateHistogram() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test ");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");

    Histogram histogram = new HistogramImpl(image);

    histogram.updateHistogram();

    int[][] redHistogram = histogram.getHistogram("red");

    assertEquals(1, redHistogram[230][1]);
    assertEquals(2, redHistogram[100][1]);
    assertEquals(0, redHistogram[0][1]);
    assertEquals(0, redHistogram[255][1]);

    int[][] greenHistogram = histogram.getHistogram("green");

    assertEquals(2, greenHistogram[230][1]);
    assertEquals(1, greenHistogram[100][1]);
    assertEquals(0, greenHistogram[0][1]);
    assertEquals(0, greenHistogram[255][1]);

    int[][] blueHistogram = histogram.getHistogram("blue");

    assertEquals(1, blueHistogram[230][1]);
    assertEquals(2, blueHistogram[100][1]);
    assertEquals(0, blueHistogram[0][1]);
    assertEquals(0, blueHistogram[255][1]);

  }

  @Test
  public void testGetFrequencyAt() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load test.ppm test ");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");

    Histogram histogram = new HistogramImpl(image);

    histogram.updateHistogram();

    assertEquals(1, histogram.getFrequencyAt(230, "red"));
    assertEquals(2, histogram.getFrequencyAt(100, "red"));
    assertEquals(0, histogram.getFrequencyAt(0, "red"));
    assertEquals(0, histogram.getFrequencyAt(255, "red"));

    assertEquals(2, histogram.getFrequencyAt(230, "green"));
    assertEquals(1, histogram.getFrequencyAt(100, "green"));
    assertEquals(0, histogram.getFrequencyAt(0, "green"));
    assertEquals(0, histogram.getFrequencyAt(255, "green"));

    assertEquals(1, histogram.getFrequencyAt(230, "blue"));
    assertEquals(2, histogram.getFrequencyAt(100, "blue"));
    assertEquals(0, histogram.getFrequencyAt(0, "blue"));
    assertEquals(0, histogram.getFrequencyAt(255, "blue"));
  }

  @Test
  public void testIntensity() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load testNew.ppm test ");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel image = images.get("test");

    Histogram histogram = new HistogramImpl(image);

    histogram.updateHistogram();

    int[][] intensityHistogram = histogram.getHistogram("intensity");

    assertEquals(3, intensityHistogram[143][1]);
    assertEquals(0, intensityHistogram[0][1]);
    assertEquals(0, intensityHistogram[255][1]);

    assertEquals(3, histogram.getFrequencyAt(143, "intensity"));
    assertEquals(0, histogram.getFrequencyAt(0, "intensity"));
    assertEquals(0, histogram.getFrequencyAt(255, "intensity"));
  }
}
