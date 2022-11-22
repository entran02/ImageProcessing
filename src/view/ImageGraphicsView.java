package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.Container;
import java.awt.image.BufferedImage;

import model.Image;
import model.Pixel;

public class ImageGraphicsView extends JFrame implements IView {
  private JButton imageButton;
  private JPanel imagePanel, histoPanel;
  private JTextField imageText;
  private JLabel imageLabel;

  public ImageGraphicsView() {
    super();
    this.imageLabel = new JLabel();
  }

  public void makeFrame(ActionListener actionEvent) {
    this.setTitle("PictureStore");
    this.setSize(1200, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container pane = new JPanel(new BorderLayout());

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
    imageScrollPane.setPreferredSize(new Dimension(650, 500));
    imagePanel.add(imageScrollPane);

    pane.add(imagePanel, BorderLayout.CENTER);

    // side panel for picture operations
    JSlider brightnessSlider = new JSlider(0, 200, 100);
    //brightnessSlider.addChangeListener();
    JLabel brightnessLabel = new JLabel("brightness :" + brightnessSlider.getValue());
    Container filterPane = new JPanel();
    filterPane.setLayout(new BoxLayout(filterPane, BoxLayout.Y_AXIS));
    filterPane.add(Box.createVerticalStrut(30));
    brightnessSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
    filterPane.add(brightnessSlider);
    brightnessLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    filterPane.add(brightnessLabel);
    addOperation("blur", filterPane);
    addOperation("sharpen", filterPane);
    addOperation("horizontal flip", filterPane);
    addOperation("vertical flip", filterPane);
    addOperation("red greyscale", filterPane);
    addOperation("green greyscale", filterPane);
    addOperation("blue greyscale", filterPane);
    addOperation("greyscale", filterPane);
    addOperation("intensity representation", filterPane);
    addOperation("luma representation", filterPane);
    addOperation("value representation", filterPane);
    addOperation("sepia filter", filterPane);
    pane.add(filterPane, BorderLayout.WEST);
    filterPane.setPreferredSize(new Dimension(180, 700));

    // histogram
    // this.histoPanel = new Histogram();
    JPanel temp = new JPanel();
    pane.add(temp, BorderLayout.EAST);
    temp.setPreferredSize(new Dimension(300, 700));

  }

  private static void addOperation(String text, Container container) {
    container.add(Box.createVerticalStrut(10));
    JButton button = new JButton(text);
    button.setAlignmentX(Component.CENTER_ALIGNMENT);
    container.add(button);
  }


  @Override
  public void makeVisible(ActionListener actionEvent) {
    this.makeFrame(actionEvent);
    this.setVisible(true);
  }

  @Override
  public void displayImage(Image image) {
    BufferedImage display = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Pixel pixel = image.getPixels().get(i).get(j);
        Color color = new Color(pixel.getR(), pixel.getG(), pixel.getB());
        display.setRGB(j, i, color.getRGB());
      }
    }
    this.imageLabel.setIcon(new ImageIcon(display));
    imagePanel.validate();
  }

  @Override
  public void displayHistogram(Image image) {

  }
}
