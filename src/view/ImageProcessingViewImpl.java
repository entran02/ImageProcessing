package view;

import java.io.IOException;

/**
 * Represents the text view that the user will see from the image processor.
 */
public class ImageProcessingViewImpl implements ImageProcessingView {
  private Appendable out;

  /**
   * Default constructor that outputs to the console.
   */
  public ImageProcessingViewImpl() {
    this.out = System.out;
  }

  /**
   * Constructor to output to a supplied appendable.
   * @param out appendable to write to
   * @throws IllegalArgumentException if out is null
   */
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
