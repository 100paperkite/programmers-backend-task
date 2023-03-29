package project.json.exception;

public class ObjectMappingException extends RuntimeException {
    public ObjectMappingException() {
    }

    public ObjectMappingException(String message) {
        super(message);
    }

    public ObjectMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectMappingException(Throwable cause) {
        super(cause);
    }
}
