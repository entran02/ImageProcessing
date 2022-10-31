package model;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {
  private final Image image;

  public ImageUtil(Image image) {
    this.image = image;
  }

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file. 
   */
  public static void readPPM(String filename) {
    Scanner sc;
    
    try {
        sc = new Scanner(new FileInputStream(filename));
    }
    catch (FileNotFoundException e) {
        System.out.println("File "+filename+ " not found!");
        return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0)!='#') {
            builder.append(s+System.lineSeparator());
        }
    }
    
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token; 

    token = sc.next();
    if (!token.equals("P3")) {
        System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: "+width);
    int height = sc.nextInt();
    System.out.println("Height of image: "+height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): "+maxValue);
    
    for (int i=0;i<height;i++) {
        for (int j=0;j<width;j++) {
            int r = sc.nextInt();
            int g = sc.nextInt();
            int b = sc.nextInt();
            System.out.println("Color of pixel ("+j+","+i+"): "+ r+","+g+","+b);
        }
    }
  }

  public void savePPM(String filename) {
    try {
      try {
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filename));
        StringBuilder pixelValues = new StringBuilder();
        for (int i = 0; i < this.image.getHeight(); i++) {
          for (int j = 0; j < this.image.getWidth(); j++) {
            Pixel pix = this.image.getPixels().get(i).get(j);
            pixelValues.append(pix.getR())
                    .append(" ")
                    .append(pix.getG())
                    .append(" ")
                    .append(pix.getB())
                    .append(" ");
          }
        }
        String filePPM = "P3\n " + this.image.getWidth() + " " + this.image.getHeight() + " "
                        + this.image.getMaxVal() + " " + pixelValues.toString();
        byte[] file = filePPM.getBytes();
        out.write(file);
        out.flush();
        out.close();
      } catch (Exception e) {
        throw new IllegalArgumentException("Failed to save PPM file.");
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Save failed.");
    }
  }

  //demo main
  public static void main(String []args) {
      String filename;
      
      if (args.length>0) {
          filename = args[0];
      }
      else {
          filename = "sample.ppm";
      }
      
      ImageUtil.readPPM(filename);
  }
}

