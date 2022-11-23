import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.event.ChangeListener;

import model.Image;
import view.IView;

/**
 * Mock class to represent a view for testing.
 */
public class ViewMock implements IView {
  private final Appendable log;

  public ViewMock(Appendable log) {
    this.log = log;
  }

  /**
   * Makes the GUI visible to the user.
   *
   * @param actionEvent provides the view with an action listener for buttons that cause a command.
   * @param changeEvent provides the view with a change listener for sliders that cause a command.
   */
  @Override
  public void makeVisible(ActionListener actionEvent, ChangeListener changeEvent) {
  }

  /**
   * Displays an image to the GUI for the user.
   *
   * @param image the image to be displayed
   */
  @Override
  public void displayImage(Image image) {
    try {
      log.append("Image displayed\n");
    } catch (Exception e) {
      return;
    }

  }

  /**
   * Displays the histogram for the corresponding image to the GUI for the user.
   *
   * @param image the histogram to be displayed
   */
  @Override
  public void displayHistogram(Image image) {
    try {
      log.append("Histogram displayed\n");
    } catch (Exception e) {
      return;
    }
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission to the provided destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    log.append("Message displayed: ").append(message).append("\n");
  }
}
