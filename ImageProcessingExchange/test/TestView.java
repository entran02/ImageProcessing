import org.junit.Test;

import java.io.IOException;

import view.ImageModelTextView;
import view.ImageModelView;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the View of the image.
 */
public class TestView {

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    ImageModelView view = new ImageModelTextView(null);
  }

  @Test
  public void testConstructor2() {
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);
    String apStr = out.toString();

    assertEquals("", apStr);
  }

  @Test
  public void testRenderMessage() {
    Appendable out = new StringBuilder();
    ImageModelView view = new ImageModelTextView(out);
    try {
      view.renderMessage("Hello");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    String apStr = out.toString();

    assertEquals("Hello", apStr);
  }

}