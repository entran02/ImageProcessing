# ImageProcessing
Joshua Cheng and Evan Tran CS3500 HW5

## To Run:
1. ImageProgessingProgram.java main method with no arguments. Instructions are printed out.
2. ImageProgessingProgram.java main method with argument `"path/to/script.txt"` and optional argument `"path/to/output.txt"` containing path to text file containing a list of commands separated by a newline, and optionally a path to a text file for the outputs to be stored in. If no output file is supplied, the default `"output.txt"` is used. See example script in `exampleScript.txt` included.
Example: ```ImageProgessingProgram.java exampleScript.txt output.txt``` (Image outputs are in res/examples)

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
- Macros: Does the actual editting of the image. 
    - AdjustBrightness
    - FlipHorizontal
    - FlipVertrical
    - RedGreyscale
    - BlueGreyscale
    - GreenGreyScale
    - IntensityRepresentation
    - LumaRepresentation
    - ValueRepresentation
- ImageUtil.java: read and store PPM files
- Image.java: represents an image. Stores its dimensions and pixel values.
- Pixel.java: represents a single pixel. Has RGB values.

----------------------------
### Image Credts:
- `Kirby.ppm` created by Evan Tran manually with Intellij color-picker
- `Koala.ppm` provided from in-class materials
