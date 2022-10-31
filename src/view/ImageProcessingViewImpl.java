package view;

import java.io.IOException;

import model.ImageProcessingModelImpl;

/**
 * Represents the text view that the user will see from the image processor.
 */
public class ImageProcessingViewImpl implements ImageProcessingView{
  private Appendable out;

  public ImageProcessingViewImpl() {
    this(System.out);
  }

  public ImageProcessingViewImpl(Appendable out) throws IllegalArgumentException {
    if (out == null) {
      throw new IllegalArgumentException("Cannot have a null appendable.");
    }
    this.out = out;
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission to the provided destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.out.append(message);
  }
}
