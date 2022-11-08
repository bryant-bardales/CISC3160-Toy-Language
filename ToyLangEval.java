import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Scanner;

public class ToyLangEval {

    private String s;
    private int currIdx;
    private char inputToken;
    private HashMap<String, Integer> hMap = new HashMap<String, Integer>(); // HashMap (key, value)

    // Method starts reading the file, line-by-line, using a while loop.
    public void startRead(Scanner fileText) {
        // Checks if the input scanner has another line to be read.
        while (fileText.hasNextLine()) {
            ToyLangEval(fileText.nextLine());
            declareVar();
        }
    }

    // Checks if the string ends with a semicolon.
    // If so, this method obtains the next element of the string.
    void nxtToken() {
        char c;
        if (!s.endsWith(";"))
            throw new RuntimeException("Missing ';' token exptected (1)");
        c = s.charAt(currIdx++);

        inputToken = c;
    }
}