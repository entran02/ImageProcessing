package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import macro.Macro;
import macro.MacroAdjustBrightness;
import macro.MacroBlueGreyscale;
import macro.MacroBlur;
import macro.MacroFlipHorizontal;
import macro.MacroFlipVertical;
import macro.MacroGreenGreyscale;
import macro.MacroGreyscale;
import macro.MacroIntensityRepresentation;
import macro.MacroLumaRepresentation;
import macro.MacroRedGreyscale;
import macro.MacroSepia;
import macro.MacroSharpen;
import macro.MacroValueRepresentation;
import model.ImageProcessingModel;
import model.ImageUtil;
import view.IView;

public class ImageProcessingGUIController implements ImageProcessingController, ActionListener {
  private final ImageProcessingModel model;
  private final IView view;
  private final StringBuilder file;

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
    this.view.makeVisible(this);
  }

  private String getFileName() {
    return file.toString();
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
      //case "brighten":
      default:
    }
  }

  private void applyMacro(Macro macro, String descriptor) {
    try {
      String imgName = file.toString();
      String destName = descriptor + imgName;
      this.model.copy(imgName, destName);
      this.model.apply(destName, macro);
      this.view.displayImage(this.model.getImage(destName));
      this.file.delete(0, file.length());
      this.file.append(destName);
    } catch (NoSuchElementException | IllegalArgumentException e) {
      throw new IllegalArgumentException();
    }
  }

}
