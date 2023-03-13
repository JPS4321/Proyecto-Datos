public class LispException extends Exception {
    private String message;

    public LispException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "LispException: " + message;
    }

}
