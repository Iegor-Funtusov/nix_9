package ua.com.alevel.exception;

public class EntityExistException extends RuntimeException {

    public EntityExistException(String message) {
        super(message);
    }
}
