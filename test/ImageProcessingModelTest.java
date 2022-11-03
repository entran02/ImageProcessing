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
    ImageProcessingModel model = new ImageProcessingModelImpl();
    // get image that doesn't exist
    assertThrows(IllegalArgumentException.class, () -> model.getImage("img1"));

    model.add("kirby", TestingUtil.getKirby());
    assertEquals(TestingUtil.getKirby(), model.getImage("kirby"));
    model.add("twobytwo", TestingUtil.getTwoByTwoImg());
    assertEquals(TestingUtil.getTwoByTwoImg(), model.getImage("twobytwo"));

    // null name
    assertThrows(IllegalArgumentException.class, () -> model.getImage(null));
    assertThrows(IllegalArgumentException.class, () -> model.getImage(""));
  }

  @Test
  public void testAdd() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    model.add("img1", TestingUtil.getTwoByTwoImg());
    assertEquals(TestingUtil.getTwoByTwoImg(), model.getImage("img1"));

    // null name
    assertThrows(IllegalArgumentException.class, () -> model.add(null, TestingUtil.getKirby()));
    assertThrows(IllegalArgumentException.class, () -> model.add("", TestingUtil.getKirby()));
    // null image
    assertThrows(IllegalArgumentException.class, () -> model.add("asdf", null));
  }

  @Test
  public void remove() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    // no image yet
    assertThrows(IllegalArgumentException.class, () -> model.remove("img1"));

    model.add("img1", TestingUtil.getTwoByTwoImg());
    assertEquals(TestingUtil.getTwoByTwoImg(), model.getImage("img1"));
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
    MacroMock macro = new MacroMock(log);

    // apply on image that doesn't exist
    assertThrows(IllegalArgumentException.class, () -> model.apply("img", macro));

    model.add("twobytwo", TestingUtil.getTwoByTwoImg());
    model.apply("twobytwo", macro);
    assertEquals("Macro applied\n", macro.log.toString());
    model.apply("twobytwo", macro);
    model.apply("twobytwo", macro);
    assertEquals("Macro applied\nMacro applied\nMacro applied\n", macro.log.toString());

    // null macro
    assertThrows(NullPointerException.class, () -> model.apply("twobytwo", null));
  }

  @Test
  public void copy() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    model.add("kirby", TestingUtil.getKirby());
    model.copy("kirby", "kirby-copy");
    assertEquals(model.getImage("kirby-copy"), TestingUtil.getKirby());
    // copy non-existent image
    assertThrows(IllegalArgumentException.class, () -> model.copy("notkirby", "copy"));

    // null name
    assertThrows(IllegalArgumentException.class, () -> model.copy(null, "img"));
    assertThrows(IllegalArgumentException.class, () -> model.copy("", "img"));
    assertThrows(IllegalArgumentException.class, () -> model.copy("img", ""));
    assertThrows(IllegalArgumentException.class, () -> model.copy("img", null));
  }
}