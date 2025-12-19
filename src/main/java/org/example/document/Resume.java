package org.example.document;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Resume extends Document {
    public Resume(@NotNull String id,
                  @NotNull Map<String, Object> fields) {
        super(id, DocumentType.RESUME, fields);
    }
}
