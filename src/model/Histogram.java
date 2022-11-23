package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Stores the data of all histograms created from an Image.
 */
public class Histogram {
  private Map<String,int[]> histograms; // Title, values

  /**
   * Constructor to initialize the values with the values of an Image
   * @param img Image
   */
  public Histogram(Image img) {
    if (img == null) {
      throw new IllegalArgumentException("Image is null");
    }
    int[][] bins = new int[4][img.getMaxVal() + 1];
    for (List<Pixel> row : img.getPixels()) {
      for (Pixel p : row) {
        bins[0][p.getR()] += 1;
        bins[1][p.getG()] += 1;
        bins[2][p.getB()] += 1;
        bins[3][(p.getR() + p.getG() + p.getB())/3] += 1;
      }
    }

    this.histograms = new HashMap<>();
    this.histograms.put("Red", bins[0]);
    this.histograms.put("Green", bins[1]);
    this.histograms.put("Blue", bins[2]);
    this.histograms.put("Intensity", bins[3]); // represents intensity

    for (int i = 0; i < img.getMaxVal(); i ++) { // check if image is greyscale
      if (bins[0][i] != bins[1][i] || bins[1][i] != bins[2][i] || bins[2][i] != bins[3][i]) {
        return;
      }
    }
    this.histograms.remove("Red");
    this.histograms.remove("Green");
    this.histograms.remove("Blue");
  }

  /**
   * Returns the stored histograms
   * @return histograms
   */
  public Map<String, int[]> getHistograms() {
    return this.histograms;
  }

  public int getMaxCount() {
    int maxCount = 0;
    for (int[] bins : this.histograms.values()) {
      for (int i : bins) {
        maxCount = Math.max(i, maxCount);
      }
    }
    return maxCount;
  }
}
