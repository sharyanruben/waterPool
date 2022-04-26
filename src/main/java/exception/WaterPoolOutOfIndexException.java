package exception;

public class WaterPoolOutOfIndexException extends RuntimeException {
    public WaterPoolOutOfIndexException() { }

    public WaterPoolOutOfIndexException(String message) {
        super(message);
    }
}
