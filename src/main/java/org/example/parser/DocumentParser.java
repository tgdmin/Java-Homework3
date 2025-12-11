package org.example.parser;

import org.example.document.*;
import org.example.exception.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class DocumentParser {

    @NotNull
    public Document parse(@NotNull String filePath)
            throws DocumentParsingException {

        Path path = Path.of(filePath);

        if (!Files.exists(path))
            throw new FileMissingException(filePath);

        String content;
        try {
            content = Files.readString(path);
        } catch (IOException e) {
            throw new DocumentParsingException("Error reading file: " + filePath, e);
        }

        Map<String, Object> fields =
                JsonKeyValueParser.parseFlatObject(content);

        Object typeObj = fields.get("document_type");
        Object idObj = fields.get("id");

        if (!(typeObj instanceof String))
            throw new MissingFieldException("document_type");
        if (!(idObj instanceof String))
            throw new MissingFieldException("id");

        String id = (String) idObj;
        String typeStr = (String) typeObj;

        DocumentType type;
        try {
            type = DocumentType.valueOf(typeStr);
        } catch (IllegalArgumentException e) {
            throw new UnknownDocumentTypeException(typeStr);
        }

        switch (type) {
            case CONTRACT:
                return new Contract(id, fields);
            case RECEIPT:
                return new Receipt(id, fields);
            case RESUME:
                return new Resume(id, fields);
            default:
                throw new UnknownDocumentTypeException(typeStr);
        }
    }
}
