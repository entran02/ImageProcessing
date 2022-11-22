package view;

import java.awt.event.ActionListener;

import javax.swing.event.ChangeListener;

import model.Image;

/**
 * Represents an interface for the views of the Image processing program.
 */
public interface IView {

  /**
   * Makes the GUI visible to the user.
   *
   * @param actionEvent provides the view with an action listener for buttons that cause a command.
   * @param changeEvent provides the view with a change listener for sliders that cause a command.
   */
  void makeVisible(ActionListener actionEvent, ChangeListener changeEvent);

  /**
   * Displays an image to the GUI for the user.
   *
   * @param image the image to be displayed
   */
  void displayImage(Image image);

  /**
   * Displays the histogram for the corresponding image to the GUI for the user.
   *
   * @param image the histogram to be displayed
   */
  void displayHistogram(Image image);

}
