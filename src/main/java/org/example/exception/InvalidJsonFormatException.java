package org.example.exception;

public class InvalidJsonFormatException extends DocumentParsingException {
    public InvalidJsonFormatException(String message) {
        super(message);
    }
}
