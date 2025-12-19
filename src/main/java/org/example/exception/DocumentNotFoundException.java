package org.example.exception;

public class DocumentNotFoundException extends Exception {
    public DocumentNotFoundException(String id) {
        super("Document with id '" + id + "' not found");
    }
}
