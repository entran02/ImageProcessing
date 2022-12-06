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

public class TestMosaic {

  @Test
  public void testMosaic() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl(images);
    Readable read = new StringReader("load /res/shanghai.jpg image " +
            "load /res/shanghai-1000.png expectedMosaic " +
            "mosaic 1000 image mosaicImage q");
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);

    ImageController controller = new ImageControllerImpl(read, model, view);
    controller.runImageProcessor();

    ImageModel expectedImage = images.get("expectedMosaic");
    ImageModel mosaicImage = images.get("mosaicImage");

    assertTrue(expectedImage.equals(mosaicImage));
  }
}