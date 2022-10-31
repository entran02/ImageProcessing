package view;

import java.io.IOException;

/**
 * This interface represents operations offered by a view for ImageProcessing.
 */
public interface ImageProcessingView {
  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission to the provided destination fails
   */
  void renderMessage(String message) throws IOException;
}
