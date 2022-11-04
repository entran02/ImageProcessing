import org.junit.Test;

import java.io.IOException;

import view.ImageProcessingView;
import view.ImageProcessingViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the view of the program which is displayed to the user.
 */
public class ImageProcessingViewImplTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    new ImageProcessingViewImpl(null);
  }

  @Test
  public void testRenderMessage() {
    Appendable out = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(out);
    String expectedOut = "BARK!\n";
    String expectedOut2 = "BARK!\nmeow uwu";
    try {
      view.renderMessage("BARK!\n");
    } catch (IOException e) {
      fail();
    }
    assertEquals(out.toString(), expectedOut);
    try {
      view.renderMessage("meow uwu");
    } catch (IOException e) {
      fail();
    }
    assertEquals(out.toString(), expectedOut2);
  }

}