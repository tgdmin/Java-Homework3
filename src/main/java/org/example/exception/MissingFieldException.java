package org.example.exception;

public class MissingFieldException extends DocumentParsingException {
    public MissingFieldException(String name) {
        super("Missing required field: " + name);
    }
}
