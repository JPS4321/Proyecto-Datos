public abstract  class LispExpression {
    // Abstract method to evaluate the expression
    public abstract LispExpression eval(LispEnvironment env) throws LispException;

    // Override the toString method to display the expression
    @Override
    public abstract String toString();

    // Override the equals method to compare expressions
    @Override
    public abstract boolean equals(Object obj);

    // Override the hashCode method to generate a hash code for expressions
    @Override
    public abstract int hashCode();


}
