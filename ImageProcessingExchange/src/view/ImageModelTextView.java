package view;

import java.io.IOException;

/**
 * Currently represents the only view class we need for our purpose of communicating
 * messages with the user.
 */
public class ImageModelTextView implements ImageModelView {
  protected Appendable out;

  /**
   * Constructor for the view to see the output of the controller.
   * @param out the Appendable object output of the text.
   */
  public ImageModelTextView(Appendable out) {
    if (out != null) {
      this.out = out;
    } else {
      throw new IllegalArgumentException("Given output cannot be null.");
    }
  }

  public ImageModelTextView() {
    this(System.out);
  }

  @Override
  public void renderMessage(String message) throws IOException {
    out.append(message);
  }
}
