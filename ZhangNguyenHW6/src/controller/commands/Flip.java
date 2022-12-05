package controller.commands;

import java.awt.event.ActionListener;

import model.ImageProcessorModel;

/**
 * Represents a horizontal flip command object.
 */
public class Flip extends AbstractCommand {
  private String type;

  /**
   * Constructs a command object for a flip.
   */
  public Flip(String type, String fileName, String newFileName) {
    super(fileName, newFileName);
    this.type = type;
  }

  public Flip(ActionListener listener, String fileName) {
    super(fileName, fileName);

  }

  /**
   * Applies the operation.
   */
  @Override
  public void apply(ImageProcessorModel model) {
    if (model.hasImage(this.fileName)) {
      try {
        model.flip(this.type, this.fileName, this.newFileName);
      } catch (IllegalArgumentException iae) {
        throw new IllegalArgumentException(iae.getMessage());
      }
    } else {
      throw new IllegalArgumentException("Image name doesn't exist.");
    }
  }
}
