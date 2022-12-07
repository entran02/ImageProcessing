package controller;

import java.io.InputStreamReader;
import java.io.StringReader;

import model.ImageProcessorModel;
import model.ImageProcessorModelImpl;
import view.ImageGUIViewImpl;
import view.ImageModelTextView;
import view.ImageModelView;

/**
 * Main class for running either the GUI or text-based mode of the program.
 */
public class ImageProgramExchange {
  /**
   * Main method to run ImageProcessingController either from console, script file,
   * or launch to GUI.
   *
   * @param args Add argument "-file path/to/script.txt" containing path to text file
   *             containing a list of commands separated by a newline. If "-text"
   *             is supplied, the System console is used for input and output. If no
   *             arguments are supplied, the GUI is launched.
   */
  public static void main(String[] args) {
    Appendable outAppendable = new StringBuilder();
    ImageProcessorModel model = new ImageProcessorModelImpl();
    ImageControllerSwing guiController = null;
    ImageController imageController = null;
    String scriptFile = "";
    if (args.length == 0) {
      ImageGUIViewImpl controllerView = new ImageGUIViewImpl();
      guiController = new ImageControllerSwing(controllerView);
      guiController.runImageProcessor();
    } else if (args[0].equals("-file")) {
      if (args.length < 2) { // no argument after '-file'
        throw new IllegalArgumentException("No file provided after -file argument");
      }
      scriptFile = args[1];
      Readable rd = new StringReader(ScriptParsingUtil.readScript(scriptFile));
      ImageModelView view = new ImageModelTextView(outAppendable);
      imageController = new ImageControllerImpl(rd, model, view);
      imageController.runImageProcessor();
    } else if (args[0].equals("-text")) {
      Readable rd = new InputStreamReader(System.in);
      ImageModelView view = new ImageModelTextView(System.out);
      imageController = new ImageControllerImpl(rd, model, view);
      imageController.runImageProcessor();
    } else {
      throw new IllegalArgumentException("Invalid command-line arguments. See README for list of" +
              " legal arguments");
    }
  }
}
