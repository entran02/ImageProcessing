package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import macro.Macro;
import macro.MacroAdjustBrightness;
import macro.MacroBlueGreyscale;
import macro.MacroBlur;
import macro.MacroDownscale;
import macro.MacroFlipHorizontal;
import macro.MacroFlipVertical;
import macro.MacroGreenGreyscale;
import macro.MacroGreyscale;
import macro.MacroIntensityRepresentation;
import macro.MacroLumaRepresentation;
import macro.MacroMosaic;
import macro.MacroRedGreyscale;
import macro.MacroSepia;
import macro.MacroSharpen;
import macro.MacroValueRepresentation;
import model.ImageProcessingModel;
import model.ImageUtil;
import view.IView;

/**
 * Implementation of Image Processing Controller to control the GUI.
 */
public class ImageProcessingGUIController implements ImageProcessingController, ActionListener,
        ChangeListener {
  private final ImageProcessingModel model;
  private final IView view;
  private final StringBuilder file;

  /**
   * Constructor that sets the model and view.
   *
   * @param model Model which stores images
   * @param view  GUI view
   * @throws IllegalArgumentException if null inputs
   */
  public ImageProcessingGUIController(ImageProcessingModel model, IView view)
          throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Cannot have a null model, view, or input.");
    }
    this.model = model;
    this.view = view;
    this.file = new StringBuilder("Image");
  }

  /**
   * Runs the image processing program.
   *
   * @throws IllegalStateException if unable to read input or give output
   */
  @Override
  public void run() throws IllegalStateException {
    this.view.makeVisible(this, this);
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    String descriptor;
    switch (e.getActionCommand()) {
      case "load": {
        JFileChooser chooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG, JPEG, BMP, PNG, and PPM Images",
                "jpg", "jpeg", "bmp", "png", "ppm");
        chooser.setFileFilter(filter);
        int retvalue = chooser.showOpenDialog(null);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = chooser.getSelectedFile();
          String filePath = f.getPath();
          String name = f.getName();
          this.file.delete(0, this.file.length());
          this.file.append(name);
          try {
            this.model.add(name, ImageUtil.readFile(filePath));
            view.displayImage(this.model.getImage(name));
            view.displayHistogram(this.model.getImage(name));
          } catch (Exception ex) {
            throw new IllegalArgumentException();
          }
        }
      }
      break;
      case "save": {
        final JFileChooser chooser = new JFileChooser(".");
        int retvalue = chooser.showSaveDialog(null);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = chooser.getSelectedFile();
          try {
            String filePath = chooser.getSelectedFile().getPath();
            String name = this.file.toString();
            ImageUtil.saveFile(filePath, this.model.getImage(name));
          } catch (Exception ex) {
            throw new IllegalArgumentException();
          }
        }
        break;
      }
      case "red-component":
        descriptor = "red";
        applyMacro(new MacroRedGreyscale(), descriptor);
        break;
      case "green-component":
        descriptor = "green";
        applyMacro(new MacroGreenGreyscale(), descriptor);
        break;
      case "blue-component":
        descriptor = "blue";
        applyMacro(new MacroBlueGreyscale(), descriptor);
        break;
      case "value-component":
        descriptor = "value";
        applyMacro(new MacroValueRepresentation(), descriptor);
        break;
      case "intensity-component":
        descriptor = "intensity";
        applyMacro(new MacroIntensityRepresentation(), descriptor);
        break;
      case "luma-component":
        descriptor = "luma";
        applyMacro(new MacroLumaRepresentation(), descriptor);
        break;
      case "horizontal-flip":
        descriptor = "horizontal";
        applyMacro(new MacroFlipHorizontal(), descriptor);
        break;
      case "vertical-flip":
        descriptor = "vertical";
        applyMacro(new MacroFlipVertical(), descriptor);
        break;
      case "blur":
        descriptor = "blurry";
        applyMacro(new MacroBlur(), descriptor);
        break;
      case "sharpen":
        descriptor = "sharp";
        applyMacro(new MacroSharpen(), descriptor);
        break;
      case "sepia":
        descriptor = "sepia";
        applyMacro(new MacroSepia(), descriptor);
        break;
      case "greyscale":
        descriptor = "greyscale";
        applyMacro(new MacroGreyscale(), descriptor);
        break;
      case "mosaic":
        descriptor = "mosaic";
        String askSeeds = JOptionPane.showInputDialog("How many seeds?");
        if (askSeeds.matches("^[0-9]*$")) {
          applyMacro(new MacroMosaic(Integer.parseInt(askSeeds)), descriptor);
        } else {
          try {
            this.view.renderMessage("not valid seed value, try again.");
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        }
        break;
      case "downscale":
        descriptor = "downscale";
        String askScaleX = JOptionPane.showInputDialog("Downscale image width to what percent?");
        String askScaleY = JOptionPane.showInputDialog("Downscale image height to what percent?");
        if (askScaleX.matches("^[0-9]*$") && askScaleY.matches("^[0-9]*$")) {
          applyMacro(new MacroDownscale(Double.parseDouble(askScaleX), Double.parseDouble(askScaleY)),
                  descriptor);
        } else {
          try {
            this.view.renderMessage("not valid scale value(s), try again.");
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        }
        break;
      case "preview mode":
        AbstractButton abstractButton = (AbstractButton)e.getSource();
        boolean selected = abstractButton.getModel().isSelected();
        if (selected == true) {
          // replace below code with placing 200x200 mask image on photo in gui and applying
          // whatever filter
          String testT = JOptionPane.showInputDialog("test true");
        }
        else {
          break;
        }
        break;
      default:
    }
  }


  /**
   * Invoked when a change in event occurs.
   *
   * @param e a ChangeEvent object
   */
  @Override
  public void stateChanged(ChangeEvent e) {
    String descriptor = "brightness";
    JSlider source = (JSlider) e.getSource();
    if (!source.getValueIsAdjusting()) {
      int brightness = source.getValue();
      applyMacro(new MacroAdjustBrightness(brightness), descriptor);
      source.setValue(0);
    }
  }

  /**
   * Helper method to apply the command to the image as well
   * as making it the current image in the model to be worked on.
   */
  private void applyMacro(Macro macro, String descriptor) {
    try {
      String imgName = file.toString();
      String destName = descriptor + imgName;
      this.model.copy(imgName, destName);
      this.model.apply(destName, macro);
      this.view.displayImage(this.model.getImage(destName));
      this.view.displayHistogram(this.model.getImage(destName));
      this.file.delete(0, file.length());
      this.file.append(destName);
    } catch (NoSuchElementException | IllegalArgumentException e) {
      throw new IllegalArgumentException();
    }
  }

}
