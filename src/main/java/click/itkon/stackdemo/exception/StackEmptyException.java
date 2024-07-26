package click.itkon.stackdemo.exception;

public class StackEmptyException extends RuntimeException {

    public static final String ERROR_MESSAGE = "Stack is empty";

    public StackEmptyException() {
        super(ERROR_MESSAGE);
    }
}
