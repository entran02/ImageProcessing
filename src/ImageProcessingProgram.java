import java.io.InputStreamReader;
import java.io.StringReader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import controller.ImageProcessingGUIController;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.IView;
import view.ImageGraphicsView;
import view.ImageProcessingView;
import view.ImageProcessingViewImpl;

/**
 * Main method to run the Image Processing Program. Can launch in text mode, GUI mode, or run off
 * a script.
 */
public class ImageProcessingProgram {
  /**
   * Main method to run ImageProcessingController either from console or script file.
   * @param args Add argument "-file path/to/script.txt" containing path to text file
   *             containing a list of commands separated by a newline. If "-text"
   *             is supplied, the System console is used for input and output. If no
   *             arguments are supplied, the GUI is launched.
   */
  public static void main(String[] args) {
    Appendable outAppendable = new StringBuilder();
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = null;
    String scriptFile = "";
    if (args.length == 0) {
      IView view = new ImageGraphicsView();
      controller = new ImageProcessingGUIController(model, view);
    } else if (args[0].equals("-file")) {
      if (args.length < 2) { // no argument after '-file'
        throw new IllegalArgumentException("No file provided after -file argument");
      }
      scriptFile = args[1];
      Readable rd = new StringReader(ScriptParsingUtil.readScript(scriptFile));
      ImageProcessingView view = new ImageProcessingViewImpl(outAppendable);
      controller = new ImageProcessingControllerImpl(model, view, rd);
    } else if (args[0].equals("-text")) {
      Readable rd = new InputStreamReader(System.in);
      ImageProcessingView view = new ImageProcessingViewImpl(System.out);
      controller = new ImageProcessingControllerImpl(model, view, rd);
    } else {
      throw new IllegalArgumentException("Invalid command-line arguments. See README for list of" +
              " legal arguments");
    }
    controller.run();
  }
}
