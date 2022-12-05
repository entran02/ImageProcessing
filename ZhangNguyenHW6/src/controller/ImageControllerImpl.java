package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

import javax.imageio.ImageIO;

import controller.commands.Brighten;
import controller.commands.ColorTransformation;
import controller.commands.Filter;
import controller.commands.Flip;
import controller.commands.ImageCommand;
import controller.commands.Mosaic;
import model.PixelImpl;
import model.ImageModel;
import model.ImageProcessorModel;
import model.ImageModelImpl;
import model.Pixel;
import view.ImageModelView;

/**
 * This class represents the implementation of the Image Controller.
 * It controls the display of the Image.
 */
public class ImageControllerImpl implements ImageController {
  private Readable inputs;
  private ImageProcessorModel model;
  private ImageModelView view;
  private HashMap<String, Function<Scanner, ImageCommand>> commandMap;

  /**
   * Creates a controller that takes in commands and determines what to do with it.
   *
   * @param inputs commands from the user
   * @param model  a model of a processor
   * @param view   the view the controller will be sending messages to communicate to the user.
   */
  public ImageControllerImpl(Readable inputs, ImageProcessorModel model, ImageModelView view) {
    if (inputs == null) {
      throw new IllegalArgumentException("Given input is null.");
    }
    if (model == null) {
      throw new IllegalArgumentException("Given model is null.");
    }
    if (view == null) {
      throw new IllegalArgumentException("Given view is null.");
    }

    this.inputs = inputs;
    this.model = model;
    this.view = view;
    this.initCommands();

  }


  /**
   * Runs the image processor.
   */
  public void runImageProcessor() {
    Scanner sc = new Scanner(inputs);
    boolean gameState = true;

    this.writeMessage("Welcome to Katie and Crystal's image processor!");
    this.writeMessage("We currently only support the following commands:");
    this.writeMessage(this.currentCommands());
    this.writeMessage("Try it out :)");

    while (gameState) {
      String command = null;

      try {
        command = sc.next().toLowerCase();
      } catch (NoSuchElementException nsee) {
        this.writeMessage("Ran out of inputs, add more.");
        return;
      }

      if (command.equals("q") || command.equals("quit")) {
        this.writeMessage("Program quit!");
        gameState = false;
      } else if (command.equals("load")) {
        try {
          this.load(sc.next(), sc.next());
        } catch (NullPointerException npe) {
          this.writeMessage("Try again.");
        }
      } else if (command.equals("save")) {
        try {
          this.save(sc.next(), sc.next(), sc.next());
        } catch (NullPointerException npe) {
          this.writeMessage("Try again.");
        }
      } else {
        Function<Scanner, ImageCommand> currCommand =
                this.commandMap.getOrDefault(command, null);
        if (currCommand == null) {
          this.writeMessage("Invalid command. Please try again.");
          gameState = true;
        } else {
          ImageCommand comm = currCommand.apply(sc);
          try {
            comm.apply(this.model);
            this.writeMessage(command + " applied!");
          } catch (IllegalArgumentException iae) {
            this.writeMessage("Error: " + iae.getMessage());
          }
        }
      }
    }
  }

  /**
   * Loads an image from a file, creates an image representation, and adds it to the existing map of
   * loaded images.
   *
   * @param fileName    Filename of the image to be loaded as a String.
   * @param newFileName the name the user wants to refer to the image as in the future.
   */
  public void load(String fileName, String newFileName) {
    if (fileName.endsWith(".ppm")) {
      this.ppmLoader(fileName, newFileName);
    } else {
      this.imageLoader(fileName, newFileName);
    }
  }

  /**
   * Loads an image of type PPM.
   */
  private void ppmLoader(String fileName, String newFileName) {
    Scanner sc;
    Pixel[][] array;

    try {
      sc = new Scanner(new FileInputStream(fileName));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + fileName + " not found!");
    }

    StringBuilder builder = new StringBuilder();

    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());
    String current;

    current = sc.next();

    if (!current.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3.");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    array = new Pixel[height][width];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        if ((r >= 0 && r <= 255) && (g >= 0 && g <= 255) && (b >= 0 && b <= 255)) {
          //Color color = new Color(r, g, b);
          int[] color = {r, g, b};
          Pixel pixel = new PixelImpl(color);
          array[row][col] = pixel;
        } else {
          this.writeMessage("Inappropriate image file. " +
                  "At least one channel value of one pixel is " +
                  "negative or greater than the max.");
        }
      }
    }

    ImageModel currImg = new ImageModelImpl(array, height, width, maxValue);
    this.model.addImage(newFileName, currImg);
    this.writeMessage("Image file " + fileName + " was successfully loaded under the name "
            + newFileName + ".");
  }

  /**
   * Loads an image of a conventional type.
   */
  private void imageLoader(String fileName, String newFileName) {
    File imageFile = new File(fileName);
    BufferedImage image = null;

    if (imageFile.isFile()) {
      try {
        image = ImageIO.read(imageFile);
      } catch (IOException ioe) {
        this.writeMessage("Unable to read image.");
      }
    } else {
      this.writeMessage("Given file name does not refer to a file.");
    }

    int height = image.getHeight();
    int width = image.getWidth();
    Pixel[][] imageChannels = new Pixel[height][width];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Color color = new Color(image.getRGB(col, row));
        int[] colorArray = {color.getRed(), color.getGreen(), color.getBlue()};
        Pixel pixel = new PixelImpl(colorArray);
        imageChannels[row][col] = pixel;
      }
    }

    ImageModel currImg = new ImageModelImpl(imageChannels, height, width, 1000);
    this.model.addImage(newFileName, currImg);
    this.writeMessage("Image file " + fileName + " was successfully loaded under the name "
            + newFileName + ".");
  }

  /**
   * Saves an image to a specified format.
   *
   * @param fileName     the file path or name of where the user wants to save the image.
   * @param oldImageName the name of the file they want to save it as.
   */
  public void save(String fileName, String oldImageName, String fileType) {
    if (fileType.equals("ppm")) {
      this.ppmSave(fileName, oldImageName);
    } else {
      this.imageSave(fileName, oldImageName, fileType);
    }
  }

  /**
   * Saves an image to the PPM file type.
   */
  private void ppmSave(String fileName, String oldImageName) {
    ImageModel img = model.getImage(oldImageName);
    FileOutputStream fos;
    File file;
    String imgToString = null;

    try {
      file = new File(fileName + ".ppm");

      if (file.createNewFile()) {
        fos = new FileOutputStream(file);
      } else {
        fos = new FileOutputStream(fileName, false);
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    if (img != null) {
      imgToString = img.toString();
    } else {
      throw new IllegalArgumentException("Specified image name does not exist in current images.");
    }

    byte[] inBytes = imgToString.getBytes();

    try {
      fos.write(inBytes);
      fos.flush();
      fos.close();
    } catch (IOException ioe) {
      throw new IllegalStateException("Unable to save file.");
    }

    this.writeMessage("Image " + fileName + " was successfully saved under name: " + file);
  }

  /**
   * Saves a conventional image type.
   */
  private void imageSave(String fileName, String oldImageName, String fileType) {
    ImageModel img = model.getImage(oldImageName);
    String file = fileName + "." + fileType;

    try {
      File newImage = new File(file);

      if (!newImage.isFile()) {
        newImage.createNewFile();
      }

      BufferedImage bImage = new BufferedImage(img.getWidth(),
              img.getHeight(), BufferedImage.TYPE_INT_RGB);

      for (int row = 0; row < img.getHeight(); row++) {
        for (int col = 0; col < img.getWidth(); col++) {
          Pixel currPixel = img.getPixelAt(row, col);
          Color color = currPixel.getColor();
          bImage.setRGB(col, row, color.getRGB());
        }
      }

      ImageIO.write(bImage, fileType, newImage);
      this.writeMessage("Image " + fileName + " was successfully saved under name: " + file);

    } catch (IOException e) {
      throw new IllegalArgumentException("Unable to save image.");
    }
  }

  /**
   * Writes a message to the user.
   */
  private void writeMessage(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Unable to transmit message");
    }
  }

  /**
   * List of current commands.
   */
  private String currentCommands() {
    return "load <image path/name> <desired new image name>\n"
            + "save <image path/name> <name of image to be saved> <file type>*\n"
            + "*file types should be entered as specified:\n"
            + "e.g. ppm, jpeg, png and so on.\n"
            + "\n"
            + "IMAGE OPERATIONS:\n"
            + "flip vertical/horizontal <image name> <desired new image name>\n"
            + "brighten value* <image name> <desired new image name>\n"
            + "*enter a positive value to brighten or a negative value to darken\n"
            + "color-transformation type <image name> <desired new image name>\n"
            + "types of color transformations we support:\n"
            + "sepia\n"
            + "luma\n"
            + "value\n"
            + "intensity\n"
            + "red/green/blue\n"
            + "filter gaussian-blur/sharpen <image name> <desired new image name>\n"
            + "\n"
            + "OTHER OPERATIONS:\n"
            + "q or quit to quit";
  }

  /**
   * Initializes the known commands of the program.
   */
  private void initCommands() {
    HashMap<String, Function<Scanner, ImageCommand>> commands = new HashMap<String,
            Function<Scanner,
                    ImageCommand>>();

    commands.put("color-transformation", s -> new ColorTransformation(s.next(),
            s.next(), s.next()));
    commands.put("brighten", s -> new Brighten(s.nextInt(), s.next(), s.next()));
    commands.put("flip", s -> new Flip(s.next(), s.next(), s.next()));
    commands.put("filter", s -> new Filter(s.next(), s.next(), s.next()));
    commands.put("mosaic", s -> new Mosaic(s.nextInt(), s.next(), s.next()));

    this.commandMap = commands;
  }


}