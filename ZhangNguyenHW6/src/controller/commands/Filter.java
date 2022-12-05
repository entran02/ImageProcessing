package controller.commands;

import model.ImageProcessorModel;

/**
 * Command class for Filter.
 */
public class Filter extends AbstractCommand {
  private String type;

  public Filter(String type, String fileName, String newFileName) {
    super(fileName, newFileName);
    this.type = type;
  }

  @Override
  public void apply(ImageProcessorModel model) {
    if (model.hasImage(this.fileName)) {
      try {
        model.filter(this.type, this.fileName, this.newFileName);
      } catch (NullPointerException npe) {
        throw new IllegalArgumentException("Not a valid operation type.");
      }
    } else {
      throw new IllegalArgumentException("Image name doesn't exist.");
    }
  }
}
