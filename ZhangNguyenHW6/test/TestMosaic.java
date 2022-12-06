import org.junit.Test;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Random;

import controller.ImageController;
import controller.ImageControllerImpl;
import controller.commands.ImageCommand;
import controller.commands.Mosaic;
import model.ImageModel;
import model.ImageProcessorModelImpl;
import view.ImageModelTextView;
import view.ImageModelView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Test class for the Mosaic Image Command. Uses pre-generated expected outputs.
 */
public class TestMosaic {
  @Test
  public void testMosaic() {
    // use controller to load image
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);
    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.load("res/shanghai.png", "image");
    ImageCommand mosaic = new Mosaic(1000, "image", "mosaicImage-1000", new Random(12345));
    mosaic.apply(model);
    mosaic = new Mosaic(5000, "image", "mosaicImage-5000", new Random(12345));
    mosaic.apply(model);
    controller.load("res/shanghai-1000.png", "expectedMosaic-1000");
    controller.load("res/shanghai-5000.png", "expectedMosaic-5000");

    assertEquals(images.get("expectedMosaic-1000"), images.get("mosaicImage-1000"));
    assertEquals(images.get("expectedMosaic-5000"), images.get("mosaicImage-5000"));
  }

  @Test
  public void testMosaicError() {
    assertThrows(IllegalArgumentException.class, () -> new Mosaic(-1, "testName", "testName"));
    assertThrows(IllegalArgumentException.class, () -> new Mosaic(0, "testName", "testName"));
    assertThrows(NullPointerException.class, () -> new Mosaic(10, "testName", "testName", null));
  }
}