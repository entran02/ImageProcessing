import org.junit.Test;

import java.io.StringReader;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Testing script parsing functionality.
 */
public class ScriptParsingTest {
  @Test
  public void testParseScript() {
    assertThrows(IllegalArgumentException.class, () -> ScriptParsingUtil.parseScript(null));

    Readable rd = new StringReader("");
    assertEquals(ScriptParsingUtil.parseScript(new Scanner(rd)), "");

    assertEquals(ScriptParsingUtil.parseScript(new Scanner(
            new StringReader("# comment \nnot comment"))), "not comment\r\n");
    assertEquals(ScriptParsingUtil.parseScript(new Scanner(
            new StringReader("# comment \nline 1\nline 2\n# another comment\nline 3"))),
            "line 1\r\nline 2\r\nline 3\r\n");

  }
}
