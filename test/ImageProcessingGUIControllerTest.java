import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;

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
  public void testLoad() {
    controller.actionPerformed(new ActionEvent("", 0, "load"));
    String expectedOut = "";
    assertEquals(expectedOut, log.toString());
  }

}