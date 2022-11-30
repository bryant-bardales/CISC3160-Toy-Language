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

     // Checks that the assignment ends with a semicolon.
     int eval() {
        int x = addOrSubtract();
        if (inputToken == ';') {
            return x;
        } else {
            throw new RuntimeException("Missing ';' token expected (2)");
        }
    }

    // Checks for a closing parenthesis.
    void checkParenth(char token) {
        if (inputToken == token) {
            nxtToken();
        } else {
            throw new RuntimeException("Missing Parenthesis");
        }
    }

    void ToyLangEval(String s) {
        this.s = s.replaceAll("\\s", ""); // Removes all empty spaces inside the string.
        currIdx = 0;
        nxtToken();
    }

    // Initializes the variable and the value assigned to the variable
    void declareVar() {

        String var = iD(); // Gets the sB.toString(), the variable
        int valueOrOperand = eval(); // Gets the value or the operand
        hMap.put(var, valueOrOperand); // Stores the var and operand into hashmap
        System.out.println("Output:");
        System.out.println(var + " = " + valueOrOperand);

    }

    // In the case the operator is addition or subtraction
    int addOrSubtract() {
        int x = multiplyOrDivide();
        while (inputToken == '+' || inputToken == '-') {
            char op = inputToken;
            nxtToken();
            int y = multiplyOrDivide();
            x = compute(op, x, y);
        }
        return x;
    }

    // In the case the operator is multiplication or division
    int multiplyOrDivide() {
        int x = factor();
        while (inputToken == '*' || inputToken == '/') {
            char op = inputToken;
            nxtToken();
            int y = factor();
            x = compute(op, x, y);
        }
        return x;
    }

}
