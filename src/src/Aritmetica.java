import java.util.*;
public class Aritmetica {

    /**
     * Esta funcion realiza calculos matematicos de tipo prefix, separa la expresion en multiples partes.
     * @author Juan Pablo Solis
     * @param Expresion La expresion tipo prefix
     * @return Int
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
     * Esta funcion revisa si la expresion revisada es un numero. Si no lo es, entonce devolvera falso
     * @param str
     * @author Juan Pablo Solis
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
