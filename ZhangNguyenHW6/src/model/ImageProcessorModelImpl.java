package model;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Represents a model for a processor that does operations on a PPM image type.
 */
public class ImageProcessorModelImpl implements ImageProcessorModel {
  private HashMap<String, ImageModel> images;
  private HashMap<String, double[][]> kernels;
  private HashMap<String, double[][]> matrices;

  /**
   * Constructs a processor given a hashmap of images.
   *
   * @param images a hashmap of images one can work with with this processor.
   */
  public ImageProcessorModelImpl(HashMap<String, ImageModel> images) {
    if (images == null) {
      throw new IllegalArgumentException("images cannot be null");
    }
    this.images = images;

    this.initKernels();
    this.initMatrices();
  }

  /**
   * Constructs a default processor with an empty hashmap of images.
   */
  public ImageProcessorModelImpl() {
    this(new HashMap<String, ImageModel>());
  }

  /**
   * Initiates the kernels to be used.
   */
  private void initKernels() {
    HashMap<String, double[][]> kernels = new HashMap<String, double[][]>();

    kernels.put("gaussian", new double[][] {{0.0625, 0.125, 0.0625},
                                            {0.125, 0.25, 0.125},
                                            {0.0625, 0.125, 0.0625}});
    kernels.put("sharpen", new double[][] {{-0.125, -0.125, -0.125, -0.125, -0.125},
                                           {-0.125, 0.25, 0.25, 0.25, -0.125},
                                           {-0.125, 0.25, 1, 0.25, -0.125},
                                           {-0.125, 0.25, 0.25, 0.25, -0.125},
                                           {-0.125, -0.125, -0.125, -0.125, -0.125}});

    this.kernels = kernels;
  }

  /**
   * Initiates the matrices to be used.
   */
  private void initMatrices() {
    HashMap<String, double[][]> matrices = new HashMap<String, double[][]>();

    matrices.put("sepia", new double[][] {{0.393, 0.769, 0.189},
                                          {0.349, 0.686, 0.168},
                                          {0.272, 0.534, 0.131}});
    matrices.put("luma", new double[][] {{0.2126, 0.7152, 0.0722},
                                         {0.2126, 0.7152, 0.0722},
                                         {0.2126, 0.7152, 0.0722}});
    matrices.put("intensity", new double[][] {{0.3333, 0.3333, 0.3333},
                                              {0.3333, 0.3333, 0.3333},
                                              {0.3333, 0.3333, 0.3333}});
    matrices.put("red", new double[][] {{1, 0, 0},
                                        {1, 0, 0},
                                        {1, 0, 0}});
    matrices.put("green", new double[][]{{0, 1, 0},
                                         {0, 1, 0},
                                         {0, 1, 0}});
    matrices.put("blue", new double[][]{{0, 0, 1},
                                        {0, 0, 1},
                                        {0, 0, 1}});

    this.matrices = matrices;
  }

  /**
   * Adds an image to the hash map of currently loaded images.
   *
   * @param fileName the name that will correspond with the given image in the hashmap.
   * @param img      the image to be inserted into the hashmap.
   */
  public void addImage(String fileName, ImageModel img) {
    this.images.put(fileName, img);
  }

  /**
   * Gets a specified image in the hash map of images currently loaded into the processor.
   *
   * @param fileName the specified image name the user is looking for.
   * @return the image corresponding to the given name.
   */
  public ImageModel getImage(String fileName) {
    return images.get(fileName);
  }

  /**
   * Checks if the model current has an image in rotation/if an image with given name has been
   * loaded.
   *
   * @param fileName the name of the image to be checked.
   * @return
   */
  public boolean hasImage(String fileName) {
    return this.images.containsKey(fileName);
  }

  /**
   * Flips the specified image in the specified direction.
   *
   * @param type the type of flip to be done.
   * @param oldImageName the original image name.
   * @param newFileName  the new image name.
   */
  @Override
  public void flip(String type, String oldImageName, String newFileName) {
    ImageModel oldImage = this.getImage(oldImageName);
    ImageModel newImage = new ImageModelImpl(new Pixel[oldImage.getHeight()][oldImage.getWidth()],
            oldImage.getHeight(),
            oldImage.getWidth(),
            oldImage.getMaxValue());

    for (int row = 0; row < oldImage.getHeight(); row++) {
      for (int col = 0; col < oldImage.getWidth(); col++) {
        Pixel newValue;

        switch (type) {
          case "horizontal":
            newValue = oldImage.getPixelAt(row, oldImage.getWidth() - (col + 1));
            break;
          case "vertical":
            newValue = oldImage.getPixelAt(oldImage.getHeight() - (row + 1), col);
            break;
          default:
            throw new IllegalArgumentException("Invalid flip type.");
        }

        newImage.updateImagePixel(row, col, newValue);
      }
    }

    images.put(newFileName, newImage);
  }

  /**
   * Brightens or darkens an image by adding a constant value to all the RGB values.
   *
   * @param value the incrementation value that should be added to the image's RGB values.
   *              A positive value brightens, while a negative value darkens.
   */
  @Override
  public void brighten(int value, String oldImageName, String newFileName) {
    ImageModel oldImage = this.getImage(oldImageName);
    ImageModel newImage = new ImageModelImpl(oldImage.getImagePixels(),
            oldImage.getHeight(),
            oldImage.getWidth(),
            oldImage.getMaxValue());

    for (int row = 0; row < oldImage.getHeight(); row++) {
      for (int col = 0; col < oldImage.getWidth(); col++) {

        Pixel currPixel = oldImage.getPixelAt(row, col);
        Color currColor = currPixel.getColor();

        int red = currColor.getRed() + value;
        int green = currColor.getGreen() + value;
        int blue = currColor.getBlue() + value;

        int[] newColor = {red, green, blue};
        //Color newColor = new Color(red, green, blue);

        Pixel newPixel = new PixelImpl(newColor);
        newPixel.clamp();

        newImage.updateImagePixel(row, col, newPixel);
      }
    }

    images.put(newFileName, newImage);
  }

  private ImageModel copyImage(ImageModel oldImage) {
    return new ImageModelImpl(oldImage.getImagePixels(),
            oldImage.getHeight(),
            oldImage.getWidth(),
            oldImage.getMaxValue());
  }

  private int initKernelStart(int kernelLength, int row) {
    int kernelStart = (kernelLength / 2) - row;
    if (kernelStart < 0) {
      kernelStart = 0;
    }
    return kernelStart;
  }

  private int initKernelEnd(int kernelLength, int height, int row) {
    int kernelEnd = kernelLength / 2 + (height - 1 - row);
    if ((kernelLength / 2) <= (height - 1 - row)) {
      kernelEnd = kernelLength - 1;
    }
    return kernelEnd;
  }

  private int initPixelIndex(int kernelLength, int row, int kernelRow) {
    int pixelRow = row - (kernelLength / 2) + kernelRow;
    return pixelRow;
  }

  /**
   * Applies a specified filter to the given image and updates the image rotation accordingly.
   *
   * @param type the type of filter to be applied
   * @param oldImageName the image the filter will be applied on
   * @param newFileName how the new image will be saved
   */
  public void filter(String type, String oldImageName, String newFileName) {
    ImageModel oldImage = this.getImage(oldImageName);
    ImageModel newImage = this.copyImage(oldImage);
    int imageHeight = oldImage.getHeight();
    int imageWidth  = oldImage.getWidth();

    double redSum = 0;
    double greenSum = 0;
    double blueSum = 0;
    double[][] kernel = this.kernels.get(type);
    int kernelLength = kernel.length;

    for (int row = 0; row < imageHeight; row++) {
      for (int col = 0; col < imageWidth; col++) {

        int kernelStartRow = this.initKernelStart(kernelLength, row);
        int kernelStartCol = this.initKernelStart(kernelLength, col);

        int kernelEndRow = this.initKernelEnd(kernelLength, imageHeight, row);
        int kernelEndCol = this.initKernelEnd(kernelLength, imageWidth, col);

        for (int kernelRow = kernelStartRow; kernelRow <= kernelEndRow; kernelRow++) {
          for (int kernelCol = kernelStartCol; kernelCol <= kernelEndCol; kernelCol++) {
            int pixelRow = this.initPixelIndex(kernelLength, row, kernelRow);
            int pixelCol = this.initPixelIndex(kernelLength, col, kernelCol);

            Pixel pixel = oldImage.getPixelAt(pixelRow, pixelCol);


            redSum += kernel[kernelRow][kernelCol] * pixel.getRedChannel();
            greenSum += kernel[kernelRow][kernelCol] * pixel.getGreenChannel();
            blueSum += kernel[kernelRow][kernelCol] * pixel.getBlueChannel();
          }
        }

        int red = (int) Math.round(redSum);
        int green = (int) Math.round(greenSum);
        int blue = (int) Math.round(blueSum);

        int[] newChannel = new int[]{red, green, blue};
        Pixel newPixel = new PixelImpl(newChannel);
        newPixel.clamp();
        newImage.updateImagePixel(row, col, newPixel);
      }
    }

    images.put(newFileName, newImage);
  }

  /**
   * Performs a specified color transformation on the given image and updates image rotation.
   *
   * @param type        the type of color transformation user wants to do.
   * @param oldImageName    the file name they want to do it on.
   * @param newFileName the name of the image after the greyscale has been applied.
   */
  public void colorTransformation(String type, String oldImageName, String newFileName) {
    ImageModel oldImage = this.getImage(oldImageName);
    ImageModel newImage = new ImageModelImpl(oldImage.getImagePixels(),
            oldImage.getHeight(),
            oldImage.getWidth(),
            oldImage.getMaxValue());

    for (int row = 0; row < oldImage.getHeight(); row++) {
      for (int col = 0; col < oldImage.getWidth(); col++) {
        Pixel newPixel = null;
        Pixel currPixel = oldImage.getPixelAt(row, col);

        if (type.equals("value")) {
          int[] channelValues = currPixel.getChannelValues();
          Arrays.sort(channelValues);
          int newValue = channelValues[2];

          int[] newColor = {newValue, newValue, newValue};
          //Color newColor = new Color(newValue, newValue, newValue);

          newPixel = new PixelImpl(newColor);
        } else if (this.matrices.containsKey(type)) {
          newPixel = this.colorTransformationMatrix(this.matrices.get(type), currPixel);
        } else {
          throw new IllegalArgumentException("Invalid color transformation type.");
        }

        if (newPixel == null) {
          throw new IllegalArgumentException("Transformation unsuccessful.");
        }

        newPixel.clamp();
        newImage.updateImagePixel(row, col, newPixel);
      }
    }

    images.put(newFileName, newImage);
  }

  /**
   * Given a matrix, computes a new pixel.
   */
  private Pixel colorTransformationMatrix(double[][] matrix, Pixel pixel) {
    int[] channelValues = pixel.getChannelValues();
    Pixel newPixel;
    int[] newChannelValue = new int[channelValues.length];

    for (int row = 0; row < matrix.length; row++) {
      double newValue = 0;
      for (int channel = 0; channel < channelValues.length; channel++) {
        newValue += matrix[row][channel] * channelValues[channel];
      }
      newChannelValue[row] = (int) Math.round(newValue);
    }

    newPixel = new PixelImpl(newChannelValue);

    return newPixel;
  }

}


