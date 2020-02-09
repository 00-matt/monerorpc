package uk.offtopica.monerorpc.utils;

public class InvalidHexStringException extends Exception {
    public InvalidHexStringException() {
    }

    public InvalidHexStringException(String message) {
        super(message);
    }

    public InvalidHexStringException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidHexStringException(Throwable cause) {
        super(cause);
    }
}
