package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import model.ImageModel;
import model.ImageModelImpl;
import model.ImageProcessorModel;
import model.ImageProcessorModelImpl;
import model.Pixel;
import model.PixelImpl;
import view.ImageGUIViewImpl;

/**
 * A controller for image processor that supports Java Swing.
 */
public class ImageControllerSwing implements ActionListener {

  private ImageGUIViewImpl view;
  private ImageProcessorModel model;
  private ImageModel currImage;


  /**
   * Constructor for the controller that supports Java Swing.
   * @param view the view that supports swing.
   */
  public ImageControllerSwing(ImageGUIViewImpl view) {
    if (view == null) {
      throw new IllegalArgumentException("Given view is null");
    }

    this.model = new ImageProcessorModelImpl();
    this.view = view;
  }

  /**
   * The method that runs the program.
   */
  public void runImageProcessor() {
    this.view.setButtons(this);
  }

  /**
   * Takes the action event and decides what to do with it.
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    switch (command) {
      case "load":
        this.load();
        break;
      case "save":
        this.save();
        break;
      case "brighten":
      case "flip":
      case "color transformation":
      case "filter":
        this.doCommand(command, currImage);
        break;
      default:
        this.view.renderError("Invalid command. Please try again.");
    }
  }

  private void save() {
    JFileChooser fileName = new JFileChooser(".");


    BufferedImage image;

    if (fileName.showOpenDialog(this.view) == 0) {
      File file = fileName.getSelectedFile();

      try {
        image = ImageIO.read(file);
        this.view.saveImage(image);
      } catch (IOException e) {
        this.writeMessage("Unable to read image");
        throw new IllegalArgumentException("Unable to read image");
      }

      /*
      try {
        File newImage = new File(fileName);

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
       */
    }
  }

  private void load() {

    JFileChooser fileName = new JFileChooser(".");


    BufferedImage image;

    if (fileName.showOpenDialog(this.view) == 0) {
      File file = fileName.getSelectedFile();

      try {
        image = ImageIO.read(file);
        this.view.loadImage(image);
      } catch (IOException e) {
        this.writeMessage("Unable to read image");
        throw new IllegalArgumentException("Unable to read image");
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
      this.model.addImage(file.getName(), currImg);

      this.currImage = currImg;
      this.view.renderHistogram(currImg);

    }
  }


  private void doCommand(String command, ImageModel model) {
    /*
    Function<Scanner, ImageCommand> currCommand =
            this.commandMap.getOrDefault(command, null);
    if (currCommand == null) {
      this.writeMessage("Invalid command. Please try again.");
    } else {
      ImageCommand comm = currCommand.apply();
      try {
        comm.apply(this.model);
        this.writeMessage(command + " applied!");
      } catch (IllegalArgumentException iae) {
        this.writeMessage("Error: " + iae.getMessage());
      }
    }
     */
  }


  private void writeMessage(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Unable to transmit message");
    }
  }
}
