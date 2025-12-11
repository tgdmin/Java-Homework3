package org.example.exception;

public class UnsupportedValueTypeException extends DocumentParsingException {
    public UnsupportedValueTypeException(String value) {
        super("Unsupported value type: " + value);
    }
}
