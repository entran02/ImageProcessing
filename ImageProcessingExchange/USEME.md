# Image-Processing

- **Using Our Program**
  - You can use command line arguments by running the main method. By doing so, a welcome message 
  will pop up explaining how to input commands and in what order.
  - You must load an image in before trying to manipulate it or do any other operation on it, 
  otherwise, the image does not exist in current rotation of available images.
  - Try it out yourself 
  - Generally, this is the order of how commands are inputted:
    - command type imageName desiredImageName
    - desiredImageName refers to the name by which you would like to refer to the image in future 
    operations.
    - imageName refers to the name of the image that is currently "in rotation".
      - load and save follow a different format:
        - load imageFileName desiredName
        - save newImageFileName desiredImage desiredFileType
    - **command**
      - load
      - save
      - filter
      - color-transformation
      - flip
      - brighten
    - **type**
      - filter
        - sharpen
        - gaussian
      - color transformation
        - red
        - green
        - blue
        - value
        - luma
        - intensity
      - flip
        - vertical
        - horizontal
      - brighten
        - negative or positive value
- **Changes from our last assignment**
  - We decided to abstract some of our functionality by having to user specify the operation(filter, 
  flip, etc.) followed by the specific type of operation. By doing so, we were able to abstract some
  of the behavior that was repetitive across similar transformations.
  - From our last assignment, we added a pixel class because we wanted a direct representation of 
  what we were dealing with rather than using arrays and color objects. It provided us with more 
  customizability for our needs by representing Pixel as a class.