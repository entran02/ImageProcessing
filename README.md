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
### Image Credts:
- `Kirby.ppm` created by Evan Tran manually with Intellij color-picker
