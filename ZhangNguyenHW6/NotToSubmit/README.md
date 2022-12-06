# Image-Processing

- **Model**
  - Our main model, called Image Processor Model holds all of the required functionality and logic 
  that manipulates an image. It has 3 fields all of which are hashmaps. The first is a hashmap of 
  strings to our custom Image class which represents the current images on rotation (images that 
  have been loaded so far). The last two are hashmaps of strings to 2d arrays of the type double. 
  They represent the kernels and matrices used in our program.
    - **Image**
      - Our Image class represents an image which is just a series of pixels. Our image class has 
      several fields representing its qualities. Height, width, maxValue, and a 2d array of pixels. 
      It has methods that check the status of the image and updates a pixel according to certain 
      requirements.
    - **Pixel**
      - Our Pixel class represents a pixel which holds the color information in an array of a pixel
      on an image. It contains methods that call specific channel values and updates them.
- **Controller**
  - Our controller called Image Controller handles all client facing logic which includes loading in
  and saving files. It uses the view to communicate to the user. Our controller has the method,
  runImageProcessor, that accepts user inputs and decides what to do with it.
- **View**
  - Our view is very simple. It just acts as a liaison between the user and our program. It hands 
  all the communication we want to deliver to the user and helps our user connect to our program.


- **Using Our Program**
  - You can use command line arguments by running the main method. By doing so, a welcome message 
  will pop up explaining how to input commands and in what order.
  - Try it out yourself 
  - Generally, this is the order of how commands are inputted:
    - command type imageName desiredImageName
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