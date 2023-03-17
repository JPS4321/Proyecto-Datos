import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

class AritmeticaTest {

    @Test
    void evaluateExpression_shouldReturnCorrectResult() {
        String expresion = "(+ (+ (+ 2 2) 4) 3)";
        Stack<Integer> numeros = new Stack<>();
        Stack<String> operadores = new Stack<>();

        for (int i = 0; i < expresion.length(); i++){
            String x = String.valueOf(expresion.charAt(i));
            switch (x){
                case " ":
                case "(":
                    break;
                case "+":
                case "/":
                case "-":
                case "*":
                    operadores.push(x);
                    break;
                case ")":
                    Integer n2 = numeros.pop();
                    Integer n1 = numeros.pop();
                    String op = operadores.pop();
                    switch (op){
                        case "+":
                            numeros.push(n1+n2);
                            break;
                        case "-":
                            numeros.push(n1-n2);
                            break;
                        case "/":
                            numeros.push(n1/n2);
                            break;
                        case "*":
                            numeros.push(n1*n2);
                            break;
                    }

                    break;
                default:
                    numeros.push(Integer.parseInt(x));
                    break;
            }
        }

        // Assert
        Assertions.assertEquals(11, numeros.pop());
    }
}