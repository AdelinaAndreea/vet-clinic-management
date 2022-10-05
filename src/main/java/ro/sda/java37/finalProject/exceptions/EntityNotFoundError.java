package ro.sda.java37.finalProject.exceptions;

public class EntityNotFoundError extends RuntimeException{
    public EntityNotFoundError(String message) {
        super(message);
    }
}