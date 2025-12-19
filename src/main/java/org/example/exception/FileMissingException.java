package org.example.exception;

public class FileMissingException extends DocumentParsingException {
    public FileMissingException(String path) {
        super("File does not exist: " + path);
    }
}
