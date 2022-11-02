import java.io.InputStreamReader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingView;
import view.ImageProcessingViewImpl;

/**
 * Main method to run the Image Processing Program.
 */
public class ImageProcessingProgram {
  public static void main(String[] args){
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingViewImpl();
    Readable rd = new InputStreamReader(System.in);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, rd);
    controller.run();
  }
}
