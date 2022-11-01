package controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Macros.MacroRedGreyscale;
import model.ImageProcessingModel;
import model.ImageUtil;
import view.ImageProcessingView;

public class ImageProcessingControllerImpl implements ImageProcessingController{
  private final ImageProcessingModel model;
  private final ImageProcessingView view;
  private final Readable input;

  public ImageProcessingControllerImpl(ImageProcessingModel model, ImageProcessingView view,
                                       Readable input) throws IllegalArgumentException {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("Cannot have a null model, view, or input.");
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }

  /**
   * Runs the image processing program.
   *
   * @throws IllegalStateException if unable to read input or give output
   */
  @Override
  public void run() throws IllegalStateException {
    Scanner sc = new Scanner(input);
    boolean quit = false;

    this.welcomeMessage();

    while (!quit && sc.hasNext()) { //continue until the user quits
      writeMessage("Type instruction: "); //prompt for the instruction name
      String userInstruction = sc.next(); //take an instruction name
      if (userInstruction.equals("quit") || userInstruction.equals("q")) {
        quit = true;
      } else {
        processCommand(userInstruction, sc, model);
      }
    }

    this.farewellMessage();


  }

  protected void writeMessage(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message);

    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  protected void welcomeMessage() throws IllegalStateException {
    writeMessage("Welcome to the image processing program!" + System.lineSeparator());
    printMenu();
  }

  protected void printMenu() throws IllegalStateException {
    writeMessage("Supported user instructions are: " + System.lineSeparator());
    writeMessage("load image-path image-name (Load an image from the path and name it)"
            + System.lineSeparator());
    writeMessage("save image-path image-name (Save the image with that name to the specified path)"
            + System.lineSeparator());
    writeMessage("red-component image-name dest-image-name "
            + "(Create a greyscale image with the red-component of the image with the given name)"
            + System.lineSeparator());
    writeMessage("green-component image-name dest-image-name "
            + "(Create a greyscale image with the green-component of the image with the given name)"
            + System.lineSeparator());
    writeMessage("blue-component image-name dest-image-name "
            + "(Create a greyscale image with the blue-component of the image with the given name)"
            + System.lineSeparator());
    writeMessage("value-component image-name dest-image-name "
            + "(Create a greyscale image with the value-component of the image with the given name)"
            + System.lineSeparator());
    writeMessage("luma-component image-name dest-image-name "
            + "(Create a greyscale image with the luma-component of the image with the given name)"
            + System.lineSeparator());
    writeMessage("intensity-component image-name dest-image-name "
            + "(Create a greyscale image with the intensity-component of the image with"
            + " the given name)"
            + System.lineSeparator());
    writeMessage("horizontal-flip image-name dest-image-name "
            + "(Flip an image horizontally to create a new image)"
            + System.lineSeparator());
    writeMessage("vertical-flip image-name dest-image-name "
            + "(Flip an image vertically to create a new image)"
            + System.lineSeparator());
    writeMessage("brighten increment image-name dest-image-name"
            + " (brighten the image by the given increment to create a new image, referred to "
            + "henceforth by the given destination name. The increment may be positive "
            + "(brightening) or negative (darkening))"
            + System.lineSeparator());
    writeMessage("menu (Print supported instruction list)" + System.lineSeparator());
    writeMessage("q or quit (quit the program) " + System.lineSeparator());
  }

  protected void farewellMessage() throws IllegalStateException {
    writeMessage("Thank you for using this program!");
  }

  protected void processCommand(String userInstruction, Scanner sc, ImageProcessingModel model) {
    switch (userInstruction) {
      case "load":
        if (userInstruction.length() == 3) {
          try {
            String filePath = sc.next();
            String name = sc.next();
            // vvv this line
            this.model.add(name, new ImageUtil().readPPM(filePath));
          } catch (NoSuchElementException | IllegalArgumentException e) {
            writeMessage("Invalid operation! Please try again.\n");
          }
        } else {
            writeMessage("Invalid operation! Please try again.\n");
          }
        break;
      case "save":
        if (userInstruction.length() == 3) {
          try {
            String filePath = sc.next();
            String name = sc.next();
            new ImageUtil(this.model.getImage(name)).savePPM(filePath);
          } catch (NoSuchElementException | IllegalArgumentException | NullPointerException e) {
            writeMessage("Invalid operation! Please try again.\n");
          }
        } else {
          writeMessage("Invalid operation! Please try again.\n");
        }
        break;
      case "red-component":
        if (userInstruction.length() == 3) {
          try {
            String imgName = sc.next();
            // vvv this line
            String destName = sc.next();
            this.model.apply(imgName, new MacroRedGreyscale());
          } catch (NoSuchElementException | IllegalArgumentException e) {
            writeMessage("Invalid operation! Please try again.\n");
          }
        } else {
          writeMessage("Invalid operation! Please try again.\n");
        }
        break;
      case "menu": //print the menu of supported instructions
        welcomeMessage();
        break;
      default: //error due to unrecognized instruction
        writeMessage("Undefined instruction: " + userInstruction + System.lineSeparator());
    }
  }

}
