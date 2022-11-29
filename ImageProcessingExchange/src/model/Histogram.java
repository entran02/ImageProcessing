package model;

/**
 * This interface represents operations that can be used to monitor the state of an image
 * model in the form of a Histogram, without changing it.
 */
public interface Histogram {

  void updateHistogram();

  int getFrequencyAt(int value, String type);

  int getLowValue();

  int getHighValue();

  int[] getHistogram(String type);


}
