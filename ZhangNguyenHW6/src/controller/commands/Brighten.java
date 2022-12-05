package controller.commands;

import model.ImageProcessorModel;

/**
 * Represents the brighten or darken command object.
 */
public class Brighten extends AbstractCommand {
  private int value;

  /**
   * Constructs a birghten or darken command object.
   */
  public Brighten(int value, String fileName, String newFileName) {
    super(fileName, newFileName);
    this.value = value;
  }

  /**
   * Applies the command object.
   */
  @Override
  public void apply(ImageProcessorModel model) {
    if (model.hasImage(this.fileName)) {
      model.brighten(this.value, this.fileName, this.newFileName);
    } else {
      throw new IllegalArgumentException("Image name doesn't exist.");
    }
  }
}
