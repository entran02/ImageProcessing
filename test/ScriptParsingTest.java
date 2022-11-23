import org.junit.Test;

import java.io.StringReader;
import java.util.Scanner;

public class ScriptParsingTest {
  @Test
  public void testParseScript() {
    Readable rd = new StringReader("");
    ScriptParsingUtil.parseScript(new Scanner(rd));
  }
}
