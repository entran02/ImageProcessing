package view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import model.HistogramImpl;
import model.ImageModel;

/**
 * The class implementing the GUI view.
 */
public class ImageGUIViewImpl extends JFrame implements ImageGUIView {
  private JButton load;
  private JButton save;
  private JButton brighten; //put brighten/darken together -> notify user to enter neg for darken
  private JButton flip;
  private JButton colorTransformation;
  private JButton filter;

  private JPanel loadSavePanel;
  private JPanel commandPanel;
  private JPanel imagePanel;
  private JScrollPane imageScroll;
  private JPanel histogramPanel;
  private JScrollPane histogramScroll;

  private ImageModel imageModel;

  // new fields

  private JButton mosaic;


  /**
   * Constructs the GUI implementation of the view.
   */
  public ImageGUIViewImpl() {
    super("Image Processor");
    this.setLayout(new BorderLayout());
    this.setSize(new Dimension(1200, 1000));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.initButtons();
    this.makeLoadSavePanel();
    this.makeCommandPanel();
    this.makeImagePanel();
    this.makeHistogramPanel();
    this.pack();
    this.setVisible(true);
  }

  /**
   * Creates the buttons that user will interact with.
   */
  private void initButtons() {
    this.load = new JButton("load");
    this.save = new JButton("save");
    this.brighten = new JButton("brighten/darken");
    this.flip = new JButton("flip");
    this.colorTransformation = new JButton("color transformation");
    this.filter = new JButton("filter");
    // new button
    this.mosaic = new JButton("mosaic");
  }

  /**
   * Makes the panel that holds the save and load buttons.
   */
  private void makeLoadSavePanel() {
    JPanel loadSavePanel = new JPanel();
    loadSavePanel.setLayout(new FlowLayout());
    this.add(loadSavePanel, BorderLayout.BEFORE_FIRST_LINE);
    loadSavePanel.add(this.load);
    loadSavePanel.add(this.save);
  }

  /**
   * Creates the panel that holds all of the commands.
   */
  private void makeCommandPanel() {
    JPanel commandPanel = new JPanel();
    commandPanel.setLayout(new FlowLayout());
    commandPanel.setPreferredSize(new Dimension(1200, 200));
    this.add(commandPanel, BorderLayout.PAGE_END);
    commandPanel.add(this.brighten);
    commandPanel.add(this.flip);
    commandPanel.add(this.colorTransformation);
    commandPanel.add(this.filter);
    // add new button to GUI
    commandPanel.add(this.mosaic);

  }

  /**
   * Creates the image panel.
   */
  private void makeImagePanel() {
    this.imagePanel = new JPanel();
    this.imagePanel.setLayout(new BorderLayout());
    this.imagePanel.setPreferredSize(new Dimension(600, 800));
    this.imagePanel.setBorder(BorderFactory.createTitledBorder("Current Image"));
    this.add(this.imagePanel, BorderLayout.CENTER);
    this.imageScroll = new JScrollPane();
    this.imagePanel.add(this.imageScroll, "Center");

  }

  /**
   * Makes the panel that holds  the  histogram.
   */
  private void makeHistogramPanel() {
    this.histogramPanel = new JPanel();
    this.histogramPanel.setLayout(new BorderLayout());
    this.histogramPanel.setPreferredSize(new Dimension(600, 800));
    this.histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    this.add(histogramPanel, BorderLayout.LINE_START);
    this.histogramScroll = new JScrollPane();
    this.histogramPanel.add(this.histogramScroll, "Center");
  }

  /**
   * Renders a histogram given an image.
   * @param imageModel the image that needs to be rendered in histogram form.
   */
  @Override
  public void renderHistogram(ImageModel imageModel) {
    JPanel histogram = new HistogramImpl(imageModel);
    histogram.setPreferredSize(new Dimension(600, 800));

    this.histogramScroll.setViewportView(histogram);
    this.histogramPanel.add(this.histogramScroll, "Center");
  }


  /**
   * Creates and sets the buttons.
   * @param actionListener the action listener that receives certain actions.
   */
  @Override
  public void setButtons(ActionListener actionListener) {
    this.load.setActionCommand("load");
    this.save.setActionCommand("save");
    this.brighten.setActionCommand("brighten");
    this.flip.setActionCommand("flip");
    this.colorTransformation.setActionCommand("color transformation");
    // set action command for mosaic
    this.mosaic.setActionCommand("mosaic");

    this.load.addActionListener(actionListener);
    this.save.addActionListener(actionListener);
    this.brighten.addActionListener(actionListener);
    this.flip.addActionListener(actionListener);
    this.colorTransformation.addActionListener(actionListener);
    // set action listener for mosaic
    this.mosaic.addActionListener(actionListener);


  }

  /**
   * Renders the error to user if there are any.
   * @param message error message to be loaded.
   * @return
   */
  @Override
  public int renderError(String message) {
    return 0;
  }


  /**
   * Loads specified image.
   * @param image to be loaded.
   */
  @Override
  public void loadImage(Image image) {
    this.imageScroll.setViewportView(new JLabel(new ImageIcon(image)));
    this.imagePanel.add(this.imageScroll, "Center");
    this.makeImagePanel();
  }

  /**
   * Saves the specified image.
   * @param image image to be saved.
   */
  @Override
  public void saveImage(Image image) {
    this.imageScroll.setViewportView(new JLabel(new ImageIcon(image)));
    this.imagePanel.add(this.imageScroll, "Center");
    this.makeImagePanel();
  }

  /**
   * Renders message to the user.
   * @param message the message to be written out to user.
   * @throws IOException when unable to write message.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    JFrame error = new JFrame("Error!");
    error.setLayout(new BorderLayout());
    error.setPreferredSize(new Dimension(300, 300));
    error.add(new JLabel(message), BorderLayout.CENTER);
    error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    error.pack();
    error.setVisible(true);

  }
}
