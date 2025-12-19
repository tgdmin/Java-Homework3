package org.example.exception;

public class DuplicateDocumentIdException extends Exception {
    public DuplicateDocumentIdException(String id) {
        super("Document with id '" + id + "' already exists");
    }
}
