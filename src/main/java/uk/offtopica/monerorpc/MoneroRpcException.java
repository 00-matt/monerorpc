package uk.offtopica.monerorpc;

public class MoneroRpcException extends Exception {
    public MoneroRpcException() {
    }

    public MoneroRpcException(String message) {
        super(message);
    }

    public MoneroRpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoneroRpcException(Throwable cause) {
        super(cause);
    }
}
