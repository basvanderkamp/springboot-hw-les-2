package nl.novi.TechItEasyController.Exceptions;


public class IndexOutOfBounceException extends RuntimeException{

    public IndexOutOfBounceException() {
        super();
    }
    public IndexOutOfBounceException(String message) {
        super(message);
    }
}

