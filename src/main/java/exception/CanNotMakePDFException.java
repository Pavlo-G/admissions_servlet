package exception;

public class CanNotMakePDFException extends RuntimeException{
    public CanNotMakePDFException(String message) {
        super(message);
    }
}
