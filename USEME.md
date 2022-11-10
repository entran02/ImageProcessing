# List of Commands: 
## Command-line arguments:
- `-file <script-path.txt>`
    - Loads script file, executes commands in the file, then quits program.
    - i.e. `java -jar ImageProgessingProgram.jar -file exampleScript.txt`
## General:
- `menu`
    - prints the menu
- `quit` or `q`
    - quits program
- `load <image-path> <image-name>`
    - loads and image from the given path and names it
- `save <image-path> <image-name>`
    - saves the image with that name at the specified path. Saves the image as the designated format i.e. `save img.png image1` saves the image as a png. Support PPM, png, jpg, bmp.
    - Only images previously loaded can be saved.
## Image Manipulation:
Note: Images may only be manipulated if they have already been loaded in (see above)
- `horizontal-flip <image-name> <dest-image-name>`
    - flips `<image-name>` horizontally and stores it as `<dest-image-name>`
- `vertical-flip <image-name> <dest-image-name>`
    - flips `<image-name>` vertical and stores it as `<dest-image-name>`
- `brighten <increment> <image-name> <dest-image-name>`
    - brightens `<image-name>` by `<increment>` amount and stores it as `<dest-image-name>`
    - if `<increment>` is a negative value, the image darkens
- `red-component <image-name> <dest-image-name>`
    - converts `<image-name>` to greyscale with the red channel of each pixel and stores it as `<dest-image-name>`
- `blue-component <image-name> <dest-image-name>`
    - converts `<image-name>` to greyscale with the blue channel of each pixel and stores it as `<dest-image-name>`
- `green-component <image-name> <dest-image-name>`
    - converts `<image-name>` to greyscale with the green channel of each pixel and stores it as `<dest-image-name>`
- `value-representation <image-name> <dest-image-name>`
    - converts `<image-name>` to greyscale with the maximum value of each pixel and stores it as `<dest-image-name>`
- `luma-representation <image-name> <dest-image-name>`
    - converts `<image-name>` to greyscale with the luma value of each pixel and stores it as `<dest-image-name>`
- `intensity-representation <image-name> <dest-image-name>`
    - converts `<image-name>` to greyscale with the average value of each pixel and stores it as `<dest-image-name>`
- `blur <image-name> <dest-image-name>`
    - blurs `<image-name>` and stores it as `<dest-image-name>`
- `sharpen <image-name> <dest-image-name>`
    - sharpens `<image-name>` and stores it as `<dest-image-name>`
- `sepia <image-name> <dest-image-name>`
    - applies sepia filter to `<image-name>` and stores it as `<dest-image-name>`
- `greyscale <image-name> <dest-image-name>`
    - converts `<image-name>` to greyscale with the luma value of each pixel and stores it as `<dest-image-name>`