package controller.commands;

import model.ImageProcessorModel;

/**
 * Represents an image command.
 */
public interface ImageCommand {
  void apply(ImageProcessorModel model);
}
