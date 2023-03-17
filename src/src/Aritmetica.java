import java.util.*;
public class Aritmetica {
    
    /** 
     * @param Expresion
     * @return int
     */
    public static int Matematicas(String Expresion) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = Expresion.split(" ");

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];

            if (isNumeric(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                int operand1 = stack.pop();
                int operand2 = stack.pop();

                switch (token) {
                    case "+" -> stack.push(operand1 + operand2);
                    case "-" -> stack.push(operand1 - operand2);
                    case "*" -> stack.push(operand1 * operand2);
                    case "/" -> stack.push(operand1 / operand2);
                }
            }
        }

        return stack.pop();
    }

    
    /** 
     * @param str
     * @return boolean
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }




}
