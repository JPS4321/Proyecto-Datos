import java.util.*;
public class LispEnvironment {

    private final Map<String, LispExpression> symbolTable;

    public LispEnvironment() {
        symbolTable = new HashMap<>();
    }

    public void setSymbol(String symbol, LispExpression value) {
        symbolTable.put(symbol, value);
    }

    public LispExpression getSymbol(String symbol) {
        return symbolTable.get(symbol);
    }

    public boolean isSymbolDefined(String symbol) {
        return symbolTable.containsKey(symbol);
    }
}
