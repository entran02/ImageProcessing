package view;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.ImageModel;

/**
 * Interface representing the GUI view of image processor.
 */
public interface ImageGUIView {
  void setButtons(ActionListener actionListener);

  int renderError(String message);

  void renderHistogram(ImageModel imageModel);

  void loadImage(Image image);

  void saveImage(Image image);


  /**
   * Renders a given message to a given output.
   *
   * @param message the message to be written out to user.
   * @throws IOException when the message cannot be transmitted.
   */
  void renderMessage(String message) throws IOException;

}
