import org.junit.Test;

import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Tests the functions of ImageProcessingModel.
 */
public class ImageProcessingModelTest {

  @Test
  public void getImage() {
  }

  @Test
  public void testAdd() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    model.add("img1", TestingUtil.twoByTwoImg);
    assertEquals(TestingUtil.twoByTwoImg, model.getImage("img1"));

    // null name
    assertThrows(IllegalArgumentException.class, () -> model.add(null, TestingUtil.kirby));
    assertThrows(IllegalArgumentException.class, () -> model.add("", TestingUtil.kirby));
    // null image
    assertThrows(IllegalArgumentException.class, () -> model.add("asdf", null));
  }

  @Test
  public void remove() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    // no image yet
    assertThrows(IllegalArgumentException.class, () -> model.remove("img1"));

    model.add("img1", TestingUtil.twoByTwoImg);
    assertEquals(TestingUtil.twoByTwoImg, model.getImage("img1"));
    model.remove("img1");
    assertThrows(IllegalArgumentException.class, () -> model.getImage("img1"));

    // null name
    assertThrows(IllegalArgumentException.class, () -> model.remove(null));
    assertThrows(IllegalArgumentException.class, () -> model.remove(""));
  }

  @Test
  public void apply() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    StringBuilder log = new StringBuilder();
  }

  @Test
  public void copy() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    model.add("kirby", TestingUtil.kirby);
    model.copy("kirby", "kirby-copy");
    assertEquals(model.getImage("kirby-copy"), TestingUtil.kirby);
    // copy non-existent image
    assertThrows(IllegalArgumentException.class, () -> model.copy("notkirby", "copy"));

    // null name
    assertThrows(IllegalArgumentException.class, () -> model.copy(null, "img"));
    assertThrows(IllegalArgumentException.class, () -> model.copy("", "img"));
    assertThrows(IllegalArgumentException.class, () -> model.copy("img", ""));
    assertThrows(IllegalArgumentException.class, () -> model.copy("img", null));
  }
}