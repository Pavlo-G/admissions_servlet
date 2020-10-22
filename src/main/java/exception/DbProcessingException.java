package exception;

public class DbProcessingException extends RuntimeException {
    public DbProcessingException(String message) {
        super(message);
    }
}
