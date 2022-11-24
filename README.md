# ImageProcessing
Joshua Cheng and Evan Tran CS3500 HW6

## To Run:
1. ImageProgessingProgram.jar with no arguments. Instructions are printed out.
2. ImageProgessingProgram.jar with argument `"-file path/to/script.txt"` containing path to text file containing a list of commands separated by a newline. See example script in `exampleScript.txt` included.
Example: ```java -jar ImageProgessingProgram.jar -file exampleScript.txt``` (Image outputs are in res/examples)

---------------------------
## Model -- ImageProcessingModel.java
- Stores a list of images that the user can work on. Stored in a map of name-image pairs.
- Can add, get, remove, etc.

## View -- ImageProcessingView.java
- renders the output of the controller to a user-specified output

## Controller -- ImageProcessingController.java
- handles user-inputs and outputs
- calls appropriate methods in the model to process desired images

## Others:
- macro: Does the actual editting of the image. 
    - AdjustBrightness
    - FlipHorizontal
    - FlipVertrical
    - RedGreyscale
    - BlueGreyscale
    - GreenGreyScale
    - IntensityRepresentation
    - LumaRepresentation
    - ValueRepresentation
    - Blur
    - Sharpen
    - Greyscale
    - Sepia
- ImageUtil.java: read and store PPM, png, bmp, jpg files
- Image.java: represents an image. Stores its dimensions and pixel values.
- Pixel.java: represents a single pixel. Has RGB values.

----------------------------
### Design Changes:
- V2 - Extended use update
  - Created new methods in the ImageUtil class
    - Extends program use to allow for other file types to be read and saved
  - Created AMacro class
    - Allows for abstraction of logic and minimizes code duplication
  - Created FilterMacro, ColorTransformationMacro as well as other Macro classes
    - Allows for new operations including: blur, sharpen, sepia, and greyscale
  - Edited ImageProcessingControllerImpl
    - To allow for the user to use new macro operations
- V3 - GUI update
  - Implemented IView interface and implementing class ImageGraphicsView
    - Used to create the GUI interface in which the user interacts with including: buttons to load and save image, photo operation buttons, image display, and histogram display.
  - Created ImageProcessingGUIController class
    - Allows for the user events from the GUI to interact with the model, in order for the user to user the Image Program.
  - Created Histogram and HistogramPanel classes
    - Histogram class located in model package and contains the logic for the histogram
    - HistogramPanel is the object for the histogram when displayed on the GUI


----------------------------
### Image Credts:
- `Kirby.ppm` created by Evan Tran manually with Intellij color-picker
- `shanghai.jpg` from https://en.wikipedia.org/wiki/File:Pudong_Shanghai_November_2017_panorama.jpg under Creative Commons License 
