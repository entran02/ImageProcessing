- mosaic works correctly: yes
- mosaic works with script command: yes
- mosaic works via GUI: no

To implement the mosaic feature, we added a new `ImageCommand` object to mosaic an Image. Following the pattern of the other commands in the existing code, we had an `apply(ImageProcessorModel model)` method. However, we deviated from the existing codebase by implementing the mosaic functionality in the `Mosaic` command object, rather than creating another public method in the `ImageProcessingModel` class like the other commands. This was to adhere to the SOLID principle.

To include functionality of the mosaic feature, the new command-object was added to the command map in the text-based controller. GUI elements including a button and a popup asking how many seeds were added. However, as the existing GUI controller does not have support for any of applying any of the commands, we did not implement the mosaic in the GUI.

### Example images:
- original: `res/shanghai.png`
- 100 seeds: `res/shanghai-100.png`
- 1000 seeds: `res/shanghai-1000.png`
- 5000 seeds: `res/shanghai-5000.png`
- 10000 seeds: `res/shanghai-10000.png`