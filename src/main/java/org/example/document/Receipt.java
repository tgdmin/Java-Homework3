package org.example.document;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Receipt extends Document {
    public Receipt(@NotNull String id,
                   @NotNull Map<String, Object> fields) {
        super(id, DocumentType.RECEIPT, fields);
    }
}
