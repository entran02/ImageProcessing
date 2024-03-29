package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.JToggleButton;

import java.awt.image.BufferedImage;
import java.io.IOException;

import model.Histogram;
import model.Image;
import model.Pixel;

/**
 * An implementation of the IView interface that uses Java Swing to
 * create a GUI for the Image Processing Program.
 * It provides the user with save and load functionality, photo operations,
 * the image, as well as the corresponding histogram.
 */
public class ImageGraphicsView extends JFrame implements IView {
  private JPanel imagePanel;
  private JPanel histoPanel;

  private JPanel previewPanel;
  private JLabel imageLabel;
  private JLabel previewLabel;
  private Container pane;

  public ImageGraphicsView() {
    super();
    this.imageLabel = new JLabel();
    this.previewLabel = new JLabel();
  }

  /**
   * Creates the frame that the user sees and interacts with.
   *
   * @param actionEvent the action listener provided to the user to operate buttons
   * @param changeEvent the change listener provided to the user to operate sliders
   */
  public void makeFrame(ActionListener actionEvent, ChangeListener changeEvent) {
    this.setTitle("PictureStore");
    this.setSize(1250, 750);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pane = new JPanel(new BorderLayout());

    //load and save panel
    JButton loadButton = new JButton("load");
    loadButton.setActionCommand("load");
    loadButton.addActionListener(actionEvent);
    JButton saveButton = new JButton("save");
    saveButton.setActionCommand("save");
    saveButton.addActionListener(actionEvent);
    final JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(loadButton, BorderLayout.EAST);
    buttonPanel.add(saveButton, BorderLayout.WEST);
    setContentPane(pane);
    pane.add(buttonPanel, BorderLayout.NORTH);


    // center panel for displaying images
    this.imagePanel = new JPanel();
    this.imagePanel.setBorder(BorderFactory.createTitledBorder("Image"));
    JScrollPane imageScrollPane = new JScrollPane(this.imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(600, 600));
    imagePanel.add(imageScrollPane);
    pane.add(imagePanel, BorderLayout.CENTER);

    // side panel for picture operations
    JLabel brightnessLabel = new JLabel("brightness");
    JSlider brightnessSlider = new JSlider(-200, 200, 0);
    brightnessSlider.setPaintTicks(true);
    brightnessSlider.setPaintLabels(true);
    brightnessSlider.setMajorTickSpacing(100);
    brightnessSlider.addChangeListener(changeEvent);
    Container filterPane = new JPanel();
    filterPane.setLayout(new BoxLayout(filterPane, BoxLayout.Y_AXIS));
    filterPane.add(Box.createVerticalStrut(30));
    brightnessLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    filterPane.add(brightnessLabel);
    brightnessSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
    filterPane.add(brightnessSlider);
    addOperation("preview mode", filterPane, actionEvent);
    addOperation("apply-preview", filterPane, actionEvent);
    addOperation("blur", filterPane, actionEvent);
    addOperation("sharpen", filterPane, actionEvent);
    addOperation("horizontal-flip", filterPane, actionEvent);
    addOperation("vertical-flip", filterPane, actionEvent);
    addOperation("red-component", filterPane, actionEvent);
    addOperation("green-component", filterPane, actionEvent);
    addOperation("blue-component", filterPane, actionEvent);
    addOperation("greyscale", filterPane, actionEvent);
    addOperation("intensity-component", filterPane, actionEvent);
    addOperation("luma-component", filterPane, actionEvent);
    addOperation("value-component", filterPane, actionEvent);
    addOperation("sepia", filterPane, actionEvent);
    addOperation("mosaic", filterPane, actionEvent);
    addOperation("downscale", filterPane, actionEvent);
    pane.add(filterPane, BorderLayout.WEST);
    filterPane.setPreferredSize(new Dimension(180, 700));

    // right side
    Container rightPane = new JPanel(new BorderLayout());
    pane.add(rightPane, BorderLayout.EAST);
    rightPane.setPreferredSize(new Dimension(425, 750));

    // histogram
    this.histoPanel = new JPanel();
    this.histoPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    rightPane.add(histoPanel, BorderLayout.CENTER);
    histoPanel.setPreferredSize(new Dimension(425, 425));

    // preview
    this.previewPanel = new JPanel();
    previewPanel.setBorder(BorderFactory.createTitledBorder("Preview"));
    rightPane.add(previewPanel, BorderLayout.NORTH);
    previewPanel.setPreferredSize(new Dimension(425, 250));
    JScrollPane previewScrollPane = new JScrollPane(this.previewLabel);
    previewScrollPane.setPreferredSize(new Dimension(218, 218));
    previewScrollPane.getViewport().addChangeListener(changeEvent);
    previewScrollPane.getHorizontalScrollBar().getModel().addChangeListener(changeEvent);
    previewScrollPane.getVerticalScrollBar().getModel().addChangeListener(changeEvent);
    previewPanel.add(previewScrollPane);

  }

  /**
   * Helper method to add an operation button to the GUI.
   */
  private static void addOperation(String text, Container container, ActionListener actionEvent) {
    container.add(Box.createVerticalStrut(10));
    JButton button = new JButton(text);
    button.setAlignmentX(Component.CENTER_ALIGNMENT);
    container.add(button);
    button.setActionCommand(text);
    button.addActionListener(actionEvent);
  }

  /**
   * Makes the GUI visible to the user.
   *
   * @param actionEvent provides the view with an action listener for buttons that cause a command.
   * @param changeEvent provides the view with a change listener for sliders that cause a command.
   */
  @Override
  public void makeVisible(ActionListener actionEvent, ChangeListener changeEvent) {
    this.makeFrame(actionEvent, changeEvent);
    this.setVisible(true);
  }

  /**
   * Displays an image to the GUI for the user.
   *
   * @param image the image to be displayed
   */
  @Override
  public void displayImage(Image image) {
    this.showImage(image, this.imagePanel, this.imageLabel);
  }

  private void showImage(Image image, JPanel panel, JLabel label) {
    BufferedImage display = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Pixel pix = image.getPixels().get(i).get(j);
        Color rgb = new Color(pix.getR(), pix.getG(), pix.getB());
        display.setRGB(j, i, rgb.getRGB());
      }
    }
    label.setIcon(new ImageIcon(display));
    panel.validate();
  }

  public void displayPreview(Image image) {
    if (image == null) { // toggle off preview mode
      this.previewLabel.setIcon(new ImageIcon());
      this.previewPanel.validate();
      return;
    }
    this.showImage(image, this.previewPanel, this.previewLabel);
  }

  /**
   * Displays the histogram for the corresponding image to the GUI for the user.
   *
   * @param image the histogram to be displayed
   */
  @Override
  public void displayHistogram(Image image) {
    this.histoPanel.removeAll();
    HistogramPanel histo = new HistogramPanel(new Histogram(image));
    histo.setPreferredSize(new Dimension(400, 400));
    histo.setBorder(BorderFactory.createLineBorder(Color.black));
    this.histoPanel.add(histo, BorderLayout.CENTER);
    this.histoPanel.validate();
  }

  /**
   * Render a specific message as a popup.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission to the provided destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    JOptionPane.showMessageDialog(this.pane, message);
  }
}
