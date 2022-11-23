import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Utility class for parsing script files.
 */
public class ScriptParsingUtil {
  /**
   * Parses scanner, deletes comments, returns output as a string
   * @param sc Scanner containing the file
   * @return parsed script file
   */
  public static String parseScript(Scanner sc) {
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.length() > 0 && s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }
    return builder.toString();
  }

  /**
   * Reads script file, returns parsed output as a string.
   * @param fileName name of script file
   * @return file contents without comments
   */
  public static String readScript(String fileName) {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(fileName));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException(fileName + " not found");
    }
    return parseScript(sc);
  }
}
