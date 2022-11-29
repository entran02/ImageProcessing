package controller.commands;

import model.ImageProcessorModel;

/**
 * Represents the abstract command class.
 */
public abstract class AbstractCommand implements ImageCommand {
  protected String fileName;
  protected String newFileName;

  /**
   * Abstract constructor for command objects.
   */
  public AbstractCommand(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }

  /**
   * Applies the respective command object.
   */
  public abstract void apply(ImageProcessorModel model);
}
