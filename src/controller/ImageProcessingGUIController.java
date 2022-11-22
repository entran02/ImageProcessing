package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.ImageProcessingModel;
import model.ImageUtil;
import view.IView;

public class ImageProcessingGUIController implements ImageProcessingController, ActionListener {
  private final ImageProcessingModel model;
  private final IView view;
  private final StringBuilder file;
  JLabel fileSaveDisplay;

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
          file.delete(0, file.length());
          file.append(name);
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
              String name = file.toString();
              ImageUtil.saveFile(filePath, this.model.getImage(name));
            } catch (Exception ex) {
              throw new IllegalArgumentException();
            }
          }
      }
      default:
    }
  }
}
