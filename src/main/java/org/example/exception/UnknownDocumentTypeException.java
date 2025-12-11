package org.example.exception;

public class UnknownDocumentTypeException extends DocumentParsingException {
    public UnknownDocumentTypeException(String type) {
        super("Unknown document_type: " + type);
    }
}
