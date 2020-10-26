package exception;

public class AdmissionRequestNotFoundException extends RuntimeException{
    public AdmissionRequestNotFoundException(String message) {
        super(message);
    }
}
