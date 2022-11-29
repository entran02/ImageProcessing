import org.junit.Test;

import java.util.HashMap;

import model.ImageModel;
import model.ImageProcessorModelImpl;

import static org.junit.Assert.assertTrue;

/**
 * Tests for the PPMImageProcessorModel. The methods are tested in their own test class.
 */
public class TestPPMImageProcessorModel {
  @Test
  public void testConstructor() {
    HashMap<String, ImageModel> images = new HashMap<String, ImageModel>();
    ImageProcessorModelImpl imageMap = new ImageProcessorModelImpl(images);
    assertTrue(images.isEmpty());
  }
}