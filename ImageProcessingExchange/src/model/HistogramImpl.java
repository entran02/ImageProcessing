package model;

import java.awt.Graphics;
import java.awt.Color;
import java.util.HashMap;

import javax.swing.JPanel;


/**
 * Implementation for the histogram class representing an image.
 */
public class HistogramImpl extends JPanel implements Histogram {
  private ImageModel imageModel;
  private int lowValue;
  private int highValue;
  private HashMap<String, int[]> histograms;

  /**
   * Constructs a histogram class given an image.
   * @param imageModel the image the histogram will draw data from.
   */
  public HistogramImpl(ImageModel imageModel) {
    this.imageModel = imageModel;
    this.lowValue = 0;
    this.highValue = 256;
    this.histograms = this.initHistogram();

  }


  private HashMap<String, int[]> initHistogram() {
    HashMap<String, int[]> histograms = new HashMap<String, int[]>();

    histograms.put("red", this.makeHistogram(imageModel, 0));
    histograms.put("green", this.makeHistogram(imageModel, 1));
    histograms.put("blue", this.makeHistogram(imageModel, 2));
    histograms.put("intensity", this.makeHistogram(imageModel, 3));

    return histograms;
  }

  private int[] makeHistogram(ImageModel image, int type)  {
    int[] histogram = new int[256]; // histogram is a 2D array with values and frequencies

    for (int histIndex = 0; histIndex < this.highValue; histIndex++) {
      for (int row = 0; row < image.getHeight(); row++) {
        for (int col = 0; col < image.getWidth(); col++) {
          if (image.getChannelValueAt(row, col, type) == histIndex) {
            histogram[histIndex]++;
          }
        }
      }
    }

    return histogram;
  }

  /**
   * Updates the histogram values.
   */
  @Override
  public void updateHistogram() {


    for (int row = 0; row < imageModel.getHeight(); row++) {
      for (int col = 0; col < imageModel.getWidth(); col++) {
        int sum = 0;

        for (int compNumber = 0; compNumber <= 2; compNumber++) {

          int channelValue = imageModel.getChannelValueAt(row, col, compNumber);
          sum += channelValue;

          if (compNumber == 0) {
            this.histograms.get("red")[channelValue]++; // increments the frequency column
          }

          if (compNumber == 1) {
            this.histograms.get("green")[channelValue]++;
          }

          if (compNumber == 2) {
            this.histograms.get("blue")[channelValue]++;
          }

        }
        this.histograms.get("intensity")[sum / 3]++;
      }
    }
  }

  /**
   * Gets the frequency of a specified value.
   * @param value the frequency of a specified value
   * @param type the type of value requested
   * @return an int representing frequency
   */
  @Override
  public int getFrequencyAt(int value, String type) {
    int[] histogram = this.getHistogram(type);

    return histogram[value];
  }

  /**
   * Gets the low value, usually 0.
   * @return int as low value.
   */
  @Override
  public int getLowValue() {
    return this.lowValue;
  }

  /**
   * Gets the high value, usually 255.
   * @return int as high value.
   */
  @Override
  public int getHighValue() {
    return this.highValue;
  }

  /**
   * Gets the 2d array that represents the histogram.
   *
   * @param type specified type of histogram representation (r, g, b, or intensity)
   * @return a 2d array of values
   */
  @Override
  public int[] getHistogram(String type) {
    return this.histograms.get(type);
  }

  /**
   * Paints the histogram.
   *
   * @param g the <code>Graphics</code> object to protect
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    int[] red = this.getHistogram("red");
    int[] green = this.getHistogram("green");
    int[] blue = this.getHistogram("blue");
    int[] intensity = this.getHistogram("intensity");


    for (int value = 0; value < this.highValue - 1; value++) {
      g.setColor(Color.RED);
      g.drawLine(value, red[value], value + 1, red[value + 1]);
      g.setColor(Color.GREEN);
      g.drawLine(value, green[value], value + 1, green[value + 1]);
      g.setColor(Color.BLUE);
      g.drawLine(value, blue[value], value + 1, blue[value + 1]);
      g.setColor(Color.BLACK);
      g.drawLine(value, intensity[value], value + 1, intensity[value + 1]);
    }

    g.dispose();
  }

}
