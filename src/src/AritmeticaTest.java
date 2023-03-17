import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

class AritmeticaTest {

    @Test
    void evaluateExpression_shouldReturnCorrectResult() {
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
                    Integer n2 = num.pop();
                    Integer n1 = num.pop();
                    String operador = op.pop();
                    switch (operador){
                        case "+":
                            num.push(n1+n2);
                            break;
                        case "-":
                            num.push(n1-n2);
                            break;
                        case "/":
                            num.push(n1/n2);
                            break;
                        case "*":
                            num.push(n1*n2);
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

    @Test
    void deleteParentesis_shouldReturnExpressionWithoutParenthesis() {
        String ex = "(+ (+ (+ 2 2) 4) 3)";

        String resp = deleteParentesis(ex);

        Assertions.assertEquals("+++2243", resp);
    }

    private String deleteParentesis(String exp) {
        exp = exp.replace("(","");
        exp = exp.replace(")","");
        exp = exp.replace(" ", "");
        return exp;
    }
}