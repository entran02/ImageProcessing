package controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.ImageProcessorModelImpl;
import view.ImageModelTextView;
import view.ImageModelView;

/**
 * A class containing the main function.
 */
public class ImageProgram {
  /**
   * The main function.
   *
   * @param args command line args.
   */
  public static void main(String[] args) {
    Readable rd = null;

    if (args.length > 0) {
      if (!args[3].equals("-file")) {
        rd = new InputStreamReader(System.in);
      } else {
        File file = new File(args[4]);
        try {
          rd = new FileReader(file);
        } catch (IOException ioe) {
          throw new IllegalArgumentException("Unable to read given file/file does not exist.");
        }
      }
    } else {
      rd = new InputStreamReader(System.in);
    }

    ImageModelView view = new ImageModelTextView();
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();

    ImageControllerImpl controller = new ImageControllerImpl(rd, model, view);
    controller.runImageProcessor();
  }
}