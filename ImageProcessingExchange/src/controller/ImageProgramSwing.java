package controller;

import view.ImageGUIViewImpl;

/**
 * Main class for Swing version of program.
 */
public class ImageProgramSwing {

  /**
   * Main method for swing version of the program.
   * @param args args taken in.
   */
  public static void main(String[] args) {
    ImageGUIViewImpl view = new ImageGUIViewImpl();
    ImageControllerSwing controller = new ImageControllerSwing(view);
    controller.runImageProcessor();
  }
}
