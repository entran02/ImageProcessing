import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

import controller.ImageProcessingGUIController;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.IView;

import static org.junit.Assert.*;

public class ImageProcessingGUIControllerTest {
  private ImageProcessingModel model;
  private IView view;
  private ImageProcessingGUIController controller;
  private Appendable log;

  @Before
  public void setup() {
    this.model = new ImageProcessingModelImpl();
    this.log = new StringBuilder();
    this.view = new ViewMock(log);
    this.controller = new ImageProcessingGUIController(model, view);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructor() {
    new ImageProcessingGUIController(null, null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructor2() {
    new ImageProcessingGUIController(null, new ViewMock(log));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructor3() {
    new ImageProcessingGUIController(new ImageProcessingModelImpl(), null);
  }

  @Test
  public void testRedComponent() {
    this.model = new ImageProcessingModelImpl();
    model.add("Image", TestingUtil.getKirby());
    this.log = new StringBuilder();
    this.view = new ViewMock(log);
    this.controller = new ImageProcessingGUIController(model, view);
    controller.actionPerformed(new ActionEvent("", 0, "red-component"));
    String expectedOut = "Image displayed\n" +
            "Histogram displayed\n";
    assertEquals(expectedOut, log.toString());
  }

  @Test
  public void testGreenComponent() {
    this.model = new ImageProcessingModelImpl();
    model.add("Image", TestingUtil.getKirby());
    this.log = new StringBuilder();
    this.view = new ViewMock(log);
    this.controller = new ImageProcessingGUIController(model, view);
    controller.actionPerformed(new ActionEvent("", 0, "green-component"));
    String expectedOut = "Image displayed\n" +
            "Histogram displayed\n";
    assertEquals(expectedOut, log.toString());
  }

  @Test
  public void testBlueComponent() {
    this.model = new ImageProcessingModelImpl();
    model.add("Image", TestingUtil.getKirby());
    this.log = new StringBuilder();
    this.view = new ViewMock(log);
    this.controller = new ImageProcessingGUIController(model, view);
    controller.actionPerformed(new ActionEvent("", 0, "blue-component"));
    String expectedOut = "Image displayed\n" +
            "Histogram displayed\n";
    assertEquals(expectedOut, log.toString());
  }

  @Test
  public void testBlur() {
    this.model = new ImageProcessingModelImpl();
    model.add("Image", TestingUtil.getKirby());
    this.log = new StringBuilder();
    this.view = new ViewMock(log);
    this.controller = new ImageProcessingGUIController(model, view);
    controller.actionPerformed(new ActionEvent("", 0, "blur"));
    String expectedOut = "Image displayed\n" +
            "Histogram displayed\n";
    assertEquals(expectedOut, log.toString());
  }

  @Test
  public void testSharpen() {
    this.model = new ImageProcessingModelImpl();
    model.add("Image", TestingUtil.getKirby());
    this.log = new StringBuilder();
    this.view = new ViewMock(log);
    this.controller = new ImageProcessingGUIController(model, view);
    controller.actionPerformed(new ActionEvent("", 0, "sharpen"));
    String expectedOut = "Image displayed\n" +
            "Histogram displayed\n";
    assertEquals(expectedOut, log.toString());
  }

  @Test
  public void testHorizontal() {
    this.model = new ImageProcessingModelImpl();
    model.add("Image", TestingUtil.getKirby());
    this.log = new StringBuilder();
    this.view = new ViewMock(log);
    this.controller = new ImageProcessingGUIController(model, view);
    controller.actionPerformed(new ActionEvent("", 0, "horizontal-flip"));
    String expectedOut = "Image displayed\n" +
            "Histogram displayed\n";
    assertEquals(expectedOut, log.toString());
  }

  @Test
  public void testVertical() {
    this.model = new ImageProcessingModelImpl();
    model.add("Image", TestingUtil.getKirby());
    this.log = new StringBuilder();
    this.view = new ViewMock(log);
    this.controller = new ImageProcessingGUIController(model, view);
    controller.actionPerformed(new ActionEvent("", 0, "vertical-flip"));
    String expectedOut = "Image displayed\n" +
            "Histogram displayed\n";
    assertEquals(expectedOut, log.toString());
  }

  @Test
  public void testGreyscale() {
    this.model = new ImageProcessingModelImpl();
    model.add("Image", TestingUtil.getKirby());
    this.log = new StringBuilder();
    this.view = new ViewMock(log);
    this.controller = new ImageProcessingGUIController(model, view);
    controller.actionPerformed(new ActionEvent("", 0, "greyscale"));
    String expectedOut = "Image displayed\n" +
            "Histogram displayed\n";
    assertEquals(expectedOut, log.toString());
  }

  @Test
  public void testIntensityComponent() {
    this.model = new ImageProcessingModelImpl();
    model.add("Image", TestingUtil.getKirby());
    this.log = new StringBuilder();
    this.view = new ViewMock(log);
    this.controller = new ImageProcessingGUIController(model, view);
    controller.actionPerformed(new ActionEvent("", 0, "intensity-component"));
    String expectedOut = "Image displayed\n" +
            "Histogram displayed\n";
    assertEquals(expectedOut, log.toString());
  }

  @Test
  public void testLumaComponent() {
    this.model = new ImageProcessingModelImpl();
    model.add("Image", TestingUtil.getKirby());
    this.log = new StringBuilder();
    this.view = new ViewMock(log);
    this.controller = new ImageProcessingGUIController(model, view);
    controller.actionPerformed(new ActionEvent("", 0, "luma-component"));
    String expectedOut = "Image displayed\n" +
            "Histogram displayed\n";
    assertEquals(expectedOut, log.toString());
  }

  @Test
  public void testValueComponent() {
    this.model = new ImageProcessingModelImpl();
    model.add("Image", TestingUtil.getKirby());
    this.log = new StringBuilder();
    this.view = new ViewMock(log);
    this.controller = new ImageProcessingGUIController(model, view);
    controller.actionPerformed(new ActionEvent("", 0, "value-component"));
    String expectedOut = "Image displayed\n" +
            "Histogram displayed\n";
    assertEquals(expectedOut, log.toString());
  }

  @Test
  public void testSepia() {
    this.model = new ImageProcessingModelImpl();
    model.add("Image", TestingUtil.getKirby());
    this.log = new StringBuilder();
    this.view = new ViewMock(log);
    this.controller = new ImageProcessingGUIController(model, view);
    controller.actionPerformed(new ActionEvent("", 0, "sepia"));
    String expectedOut = "Image displayed\n" +
            "Histogram displayed\n";
    assertEquals(expectedOut, log.toString());
  }

  @Test
  public void testBrightness() {
    this.model = new ImageProcessingModelImpl();
    model.add("Image", TestingUtil.getKirby());
    this.log = new StringBuilder();
    this.view = new ViewMock(log);
    this.controller = new ImageProcessingGUIController(model, view);
    controller.stateChanged(new ChangeEvent(new JSlider()));
    String expectedOut = "Image displayed\n" +
            "Histogram displayed\n";
    assertEquals(expectedOut, log.toString());
  }

  @Test
  public void testMultipleActions() {
    this.model = new ImageProcessingModelImpl();
    model.add("Image", TestingUtil.getKirby());
    this.log = new StringBuilder();
    this.view = new ViewMock(log);
    this.controller = new ImageProcessingGUIController(model, view);
    controller.actionPerformed(new ActionEvent("", 0, "sepia"));
    controller.actionPerformed(new ActionEvent("", 0, "horizontal-flip"));
    controller.stateChanged(new ChangeEvent(new JSlider()));
    controller.actionPerformed(new ActionEvent("", 0, "vertical-flip"));
    String expectedOut = "Image displayed\n" +
            "Histogram displayed\n" +
            "Image displayed\n" +
            "Histogram displayed\n" +
            "Image displayed\n" +
            "Histogram displayed\n" +
            "Image displayed\n" +
            "Histogram displayed\n";
    assertEquals(expectedOut, log.toString());
  }
}