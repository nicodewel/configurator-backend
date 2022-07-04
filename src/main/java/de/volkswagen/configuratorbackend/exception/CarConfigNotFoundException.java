package de.volkswagen.configuratorbackend.exception;

public class CarConfigNotFoundException extends Throwable {
    public CarConfigNotFoundException(String message) {
        super(message);
    }
}