package macro;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.Image;
import model.ImageImpl;
import model.Pixel;

public class MacroPreview implements Macro {
  private final Macro macro;
  private final int x;
  private final int y;

  public MacroPreview(Macro macro, int x, int y) {
    if (macro == null || x < 0 || y < 0) {
      throw new IllegalArgumentException("macro is null or x or y is negative");
    }
    this.macro = macro;
    this.x = x;
    this.y = y;
  }

  /**
   * Apply this macro to the image.
   *
   * @param img image to apply macro to
   */
  @Override
  public void apply(Image img) {
    Objects.requireNonNull(img);

    List<List<Pixel>> pix = new ArrayList<>(new ArrayList<>());
    for (int r = x; r < x + 200; r++) {
      List<Pixel> row = new ArrayList<>();
      for (int c = y; c < y + 200; c++) {
        row.add(new Pixel(img.getPixel(r, c).getR(), img.getPixel(r, c).getG(),
                img.getPixel(r, c).getB()));
      }
      pix.add(row);
    }
    Image preview = new ImageImpl(200, 200, img.getMaxVal(), pix);
    this.macro.apply(preview);

    for (int r = x; r < x + 200; r++) {
      for (int c = y; c < y + 200; c++) {
        img.setPixel(r, c, preview.getPixel(r, c));
      }
    }
  }
}
