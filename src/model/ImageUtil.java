package model;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {
  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   * @return image the image
   */
  public static Image readPPM(String filename) {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException(filename + " not found");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }
    return readStringPPM(builder.toString());
  }

  /**
   * Read an image in the PPM format as a string and return an Image.
   *
   * @param ppm the string representation of the ppm file.
   * @return image the image
   */
  public static Image readStringPPM(String ppm) {
    //now set up the scanner to read from the string we just built
    Scanner sc = new Scanner(ppm);
    String token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    List<List<Pixel>> pixels = new ArrayList<>(new ArrayList<>());

    for (int i = 0; i < height; i++) {
      List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        row.add(new Pixel(r, g, b));
      }
      pixels.add(row);
    }
    return new ImageImpl(width, height, maxValue, pixels);
  }

  /**
   * Saves an Image as a PPM file.
   *
   * @param filename file to store to
   * @param image    image to store
   */
  public static void savePPM(String filename, Image image) {
    try {
      BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filename));
      StringBuilder pixelValues = new StringBuilder();
      for (int i = 0; i < image.getHeight(); i++) {
        for (int j = 0; j < image.getWidth(); j++) {
          Pixel pix = image.getPixels().get(i).get(j);
          pixelValues.append(pix.getR()).append("\n")
                  .append(pix.getG()).append("\n")
                  .append(pix.getB()).append("\n");
        }
      }
      String filePPM = "P3\n" + image.getWidth() + " " + image.getHeight() + "\n"
              + image.getMaxVal() + "\n" + pixelValues;
      byte[] file = filePPM.getBytes();
      out.write(file);
      out.flush();
      out.close();
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to save PPM file.");
    }
  }

  /**
   * Demo main function to test loading and saving of PPMs.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "res/Kirby/Kirby.ppm";
    }
    System.out.println("Working Directory = " + System.getProperty("user.dir"));
    ImageUtil.readPPM(filename);


  }
}

