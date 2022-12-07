package macro;

import java.util.ArrayList;
import java.util.List;

import model.Image;
import model.ImageImpl;
import model.Pixel;

public class MacroDownscale implements Macro {
  private final double scaleX;
  private final double scaleY;


  public MacroDownscale(double scaleX, double scaleY) throws IllegalArgumentException {
    if (scaleX < 0 || scaleX > 100 || scaleY < 0 || scaleY > 100) {
      throw new IllegalArgumentException("Invalid scale to downsize.");
    }
    this.scaleX = scaleX;
    this.scaleY = scaleY;
  }

  /**
   * Apply this macro to the image.
   *
   * @param img image to apply macro to
   */
  @Override
  public void apply(Image img) {
    this.downsize(img);
  }

  /**
   * Creates a new Image with the new width, height, and list of pixels
   *
   * @return the new image
   */
  public Image downsize(Image img) {
    int newWidth = (int) ((1.0 - this.scaleX / 100) * img.getWidth());
    int newHeight = (int) ((1.0 - this.scaleY / 100) * img.getHeight());

    List<List<Pixel>> lop = new ArrayList<>();
    for (int i = 0; i < newHeight; i++) {
      List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < newWidth; j++) {
        row.add(pixelMath(img,i * img.getHeight() / (double) newHeight,
                j * img.getWidth() / (double) newWidth));
      }
      lop.add(row);
    }
    return new ImageImpl(newWidth, newHeight, 255, lop);
  }

  private Pixel pixelMath(Image image, double i, double j) {
    Pixel ca = image.getPixels().get((int) Math.floor(i)).get((int) Math.floor(j));
    Pixel cb = image.getPixels().get((int) Math.ceil(i)).get((int) Math.floor(j));
    Pixel cc = image.getPixels().get((int) Math.floor(i)).get((int) Math.ceil(j));
    Pixel cd = image.getPixels().get((int) Math.ceil(i)).get((int) Math.ceil(j));

    int cpRed = (int) (Math.round(getNR(cd, cc, j) * (i - Math.floor(i)) + getMR(cb, ca, j)
            * (Math.round(i + 0.5) - i)));
    int cpGreen = (int) (Math.round(getNG(cd, cc, j) * (i - Math.floor(i)) + getMG(cb, ca, j)
            * (Math.round(i + 0.5) - i)));
    int cpBlue = (int) (Math.round(getNB(cd, cc, j) * (i - Math.floor(i)) + getMB(cb, ca, j)
            * (Math.round(i + 0.5) - i)));

    return new Pixel(cpRed, cpGreen, cpBlue);
  }

  private double getMR(Pixel cb, Pixel ca, double j) {
    return cb.getR() * (j - Math.floor(j)) + ca.getR() * (Math.round(j + 0.5) - j);
  }

  private double getMG(Pixel cb, Pixel ca, double j) {
    return cb.getG() * (j - Math.floor(j)) + ca.getG() * (Math.round(j + 0.5) - j);
  }
  private double getMB(Pixel cb, Pixel ca, double j) {
    return cb.getB() * (j - Math.floor(j)) + ca.getB() * (Math.round(j + 0.5) - j);
  }

  private double getNR(Pixel cd, Pixel cc, double j) {
    return cd.getR() * (j - Math.floor(j)) + cc.getR() * (Math.round(j + 0.5) - j);
  }

  private double getNG(Pixel cd, Pixel cc, double j) {
    return cd.getG() * (j - Math.floor(j)) + cc.getG() * (Math.round(j + 0.5) - j);
  }

  private double getNB(Pixel cd, Pixel cc, double j) {
    return cd.getB() * (j - Math.floor(j)) + cc.getB() * (Math.round(j + 0.5) - j);
  }
}