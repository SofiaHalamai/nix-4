package ua.com.alevel.exception;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(int id) {
        super("Resource with id = " + id + " was not found!");
    }

    public ResourceNotFoundException() {
        super("This resource was not found!");
    }
}
