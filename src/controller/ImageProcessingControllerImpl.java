package controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import macro.Macro;
import macro.MacroAdjustBrightness;
import macro.MacroBlueGreyscale;
import macro.MacroFlipHorizontal;
import macro.MacroFlipVertical;
import macro.MacroGreenGreyscale;
import macro.MacroIntensityRepresentation;
import macro.MacroLumaRepresentation;
import macro.MacroRedGreyscale;
import macro.MacroValueRepresentation;
import model.ImageProcessingModel;
import model.ImageUtil;
import view.ImageProcessingView;

/**
 * Controller for ImageProcessingModel. Takes user commands and runs macros depending
 * on the user input.
 */
public class ImageProcessingControllerImpl implements ImageProcessingController {
  private final ImageProcessingModel model;
  private final ImageProcessingView view;
  private final Readable input;

  /**
   * Constructor to instantiate this class, ensures arguments are non-null.
   * @param model Image processing model
   * @param view view to return outputs
   * @param input readable to receive user input
   * @throws IllegalArgumentException if arguments are null
   */
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

  private void applyMacro(Macro macro, Scanner sc) {
    try {
      String imgName = sc.next();
      String destName = sc.next();
      this.model.copy(imgName, destName);
      this.model.apply(destName, macro);
    } catch (NoSuchElementException | IllegalArgumentException e) {
      writeMessage("Invalid operation! Please try again.\n");
    }
  }

  protected void processCommand(String userInstruction, Scanner sc, ImageProcessingModel model) {
    switch (userInstruction) {
      case "load":
        try {
          String filePath = sc.next();
          String name = sc.next();
          this.model.add(name, ImageUtil.readFile(filePath));
        } catch (NoSuchElementException | IllegalArgumentException e) {
          writeMessage("Invalid operation! Please try again.\n");
        }
        break;
      case "save":
        try {
          String filePath = sc.next();
          String name = sc.next();
          ImageUtil.saveFile(filePath, this.model.getImage(name));
        } catch (NoSuchElementException | IllegalArgumentException | NullPointerException e) {
          writeMessage("Invalid operation! Please try again.\n");
        }
        break;
      case "red-component":
        applyMacro(new MacroRedGreyscale(), sc);
        break;
      case "green-component":
        applyMacro(new MacroGreenGreyscale(), sc);
        break;
      case "blue-component":
        applyMacro(new MacroBlueGreyscale(), sc);
        break;
      case "value-component":
        applyMacro(new MacroValueRepresentation(), sc);
        break;
      case "intensity-component":
        applyMacro(new MacroIntensityRepresentation(), sc);
        break;
      case "luma-component":
        applyMacro(new MacroLumaRepresentation(), sc);
        break;
      case "horizontal-flip":
        applyMacro(new MacroFlipHorizontal(), sc);
        break;
      case "vertical-flip":
        applyMacro(new MacroFlipVertical(), sc);
        break;
      case "brighten":
        int increment = sc.nextInt();
        applyMacro(new MacroAdjustBrightness(increment), sc);
        break;
      case "menu": //print the menu of supported instructions
        welcomeMessage();
        break;
      default: //error due to unrecognized instruction
        writeMessage("Undefined instruction: " + userInstruction + System.lineSeparator());
    }
  }

}
