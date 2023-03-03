public class LispAtom {

    private Object value;

    public LispAtom(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String toString() {
        if (value instanceof String) {
            return """
            """ + value + """
            """;
        } else {
            return value.toString();
        }
    }
}
