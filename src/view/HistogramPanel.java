package view;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import model.Histogram;

public class HistogramPanel extends JPanel {
  private Histogram histogram;

  /**
   * Set data and show the updated histogram.
   * @param histogram histogram to display
   */
  public HistogramPanel(Histogram histogram) {
    if (histogram == null) {
      return;
    }
    this.histogram = histogram;
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    if (histogram == null) {
      return;
    }
    super.paintComponent(g);
    int height = getHeight() - 20; // make room for title

    HashMap<String, Color> colorLookup = new HashMap<>();
    colorLookup.put("Red", Color.RED);
    colorLookup.put("Green", Color.GREEN);
    colorLookup.put("Blue", Color.BLUE);
    colorLookup.put("Intensity", Color.BLACK);

    for (Map.Entry<String, int[]> e : histogram.getHistograms().entrySet()) {
      int prevx = 0;
      int prevy = height;
      g.setColor(colorLookup.get(e.getKey()));
      for (int i = 0; i < e.getValue().length; i++) {
        int binHeight = (e.getValue()[i] * height) / histogram.getMaxCount();
        g.drawLine(prevx, prevy, (int) (i * ((float) getWidth() / (float) e.getValue().length)), height - binHeight);
        prevx = (int) (i * ((float) getWidth() / (float) e.getValue().length));
        prevy = height - binHeight;
      }
    }
  }

}
