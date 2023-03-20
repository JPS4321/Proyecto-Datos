import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

class AritmeticaTest {

    /**
     * Esta funcion sirve para observar si esta funcionando de manera correcta una de las funciones matematicas.
     * @author Edwin Ortega
     * @return Int
     */
    @Test
    void evaluar() {
        String ex = "(+ (+ (+ 2 2) 4) 3)";
        Stack<Integer> num = new Stack<>();
        Stack<String> op = new Stack<>();

        for (int i = 0; i < ex.length(); i++){
            String x = String.valueOf(ex.charAt(i));
            switch (x){
                case " ":
                case "(":
                    break;
                case "+":
                case "/":
                case "-":
                case "*":
                    op.push(x);
                    break;
                case ")":
                    Integer num2 = num.pop();
                    Integer num1 = num.pop();
                    String operador = op.pop();
                    switch (operador){
                        case "+":
                            num.push(num1+num2);
                            break;
                        case "-":
                            num.push(num1-num2);
                            break;
                        case "/":
                            num.push(num1/num2);
                            break;
                        case "*":
                            num.push(num1*num2);
                            break;
                    }

                    break;
                default:
                    num.push(Integer.parseInt(x));
                    break;
            }
        }

        Assertions.assertEquals(11, num.pop());
    }

    /**
     * Esta funcion sirve para remover los parentesis de la expresion
     * @author Edwin Ortega / Esteban Zambrano
     * @return String
     */
    @Test
    void deleteParentesis() {
        String ex = "(+ (+ (+ 2 2) 4) 3)";

        String resp = deleteParents(ex);

        Assertions.assertEquals("+++2243", resp);
    }


    /**
     * Esta funcion sirve para remover los parentesis de la expresion
     * @author Edwin Ortega / Esteban Zambrano
     * @return String
     */
    private String deleteParents(String exp) {
        exp = exp.replace("(","");
        exp = exp.replace(")","");
        exp = exp.replace(" ", "");
        return exp;
    }
}