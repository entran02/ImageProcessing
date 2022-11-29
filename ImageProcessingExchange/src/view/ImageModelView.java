package view;

import java.io.IOException;

/**
 * This interface represents potential views for the image processor.
 */
public interface ImageModelView {
  /**
   * Renders a given message to a given output.
   *
   * @param message the message to be written out to user.
   * @throws IOException when the message cannot be transmitted.
   */
  void renderMessage(String message) throws IOException;
}
