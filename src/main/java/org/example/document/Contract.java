package org.example.document;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Contract extends Document {
    public Contract(@NotNull String id,
                    @NotNull Map<String, Object> fields) {
        super(id, DocumentType.CONTRACT, fields);
    }
}
