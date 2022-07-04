package de.volkswagen.configuratorbackend.exception;

public class CarConfigAttributeNotFoundException extends RuntimeException {
    public CarConfigAttributeNotFoundException(String s) {
        super(s);
    }
}