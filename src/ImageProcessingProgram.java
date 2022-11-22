import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Scanner;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import controller.ImageProcessingGUIController;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageGraphicsView;
import view.ImageProcessingView;
import view.ImageProcessingViewImpl;

/**
 * Main method to run the Image Processing Program. If no arguments are supplied, the console is
 * used for input and output. Add argument "path/to/script.txt" and optional argument
 * "path/to/output.txt" containing path to text file containing a list of commands separated
 * by a newline, and optionally a path to a text file for the outputs to be stored in.
 * If no output file is supplied, the default `"output.txt"` is used.
 */
public class ImageProcessingProgram {
  /**
   * Main method to run ImageProcessingController either from console or script file.
   * @param args Add argument "-file path/to/script.txt" containing path to text file
   *             containing a list of commands separated by a newline. If no arguments
   *             are supplied, the System console is used for input and output.
   */
  public static void main(String[] args) {
    Readable rd = new InputStreamReader(System.in);
    ImageProcessingView view = new ImageProcessingViewImpl(System.out);
    Appendable outAppendable = new StringBuilder();
    String scriptFile = "";
    for (int i = 0; i < args.length; i ++) { // making room for future CLI arguments
      if (args[i].equals("-file")) {
        if (i == args.length - 1) { // no argument after '-file'
          throw new IllegalArgumentException("No file provided after -file argument");
        }
        scriptFile = args[i + 1];
        Scanner sc;
        try {
          sc = new Scanner(new FileInputStream(scriptFile));
        } catch (FileNotFoundException e) {
          throw new IllegalArgumentException(scriptFile + " not found");
        }
        StringBuilder builder = new StringBuilder();
        //read the file line by line, and populate a string. This will throw away any comment lines
        while (sc.hasNextLine()) {
          String s = sc.nextLine();
          if (s.length() > 0 && s.charAt(0) != '#') {
            builder.append(s).append(System.lineSeparator());
          }
        }

        rd = new StringReader(builder.toString());
        view = new ImageProcessingViewImpl(outAppendable);
      }
    }
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, rd);
    ImageGraphicsView testView = new ImageGraphicsView();
    ImageProcessingGUIController testController = new ImageProcessingGUIController(model, testView);
    testController.run();
    //controller.run();
    if (args.length > 0) { // saves output to inputfile-output.txt
      String outfile = scriptFile.substring(0, scriptFile.lastIndexOf(".")) + "-output"
              + scriptFile.substring(scriptFile.lastIndexOf("."));
      try {
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outfile));
        byte[] file = outAppendable.toString().getBytes();
        out.write(file);
        out.flush();
        out.close();
      } catch (Exception e) {
        throw new IllegalArgumentException("Failed to save output.");
      }
    }
  }
}
