import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Scanner;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
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
   * @param args Add argument "path/to/script.txt" and optional argument
   *             "path/to/output.txt" containing path to text file containing a list of commands
   *             separated by a newline, and optionally a path to a text file for the outputs to be
   *             stored in. If no output file is supplied, the default `"output.txt"` is used.
   */
  public static void main(String[] args) {
    Readable rd;
    ImageProcessingView view;
    Appendable outAppendable = new StringBuilder();
    if (args.length == 0) {
      rd = new InputStreamReader(System.in);
      view = new ImageProcessingViewImpl(System.out);
    } else if (args.length <= 2) {
      Scanner sc;
      try {
        sc = new Scanner(new FileInputStream(args[0]));
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException(args[0] + " not found");
      }
      StringBuilder builder = new StringBuilder();
      //read the file line by line, and populate a string. This will throw away any comment lines
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          builder.append(s).append(System.lineSeparator());
        }
      }
      rd = new StringReader(builder.toString());
      view = new ImageProcessingViewImpl(outAppendable);
    } else {
      throw new IllegalArgumentException("Illegal amount of arguments.");
    }
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, rd);
    controller.run();
    if (args.length > 0) {
      String outfile = "output.txt";
      if (args.length == 2) {
        outfile = args[1];
      }
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
