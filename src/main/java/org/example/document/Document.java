package org.example.document;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Document {

    @NotNull
    private final String id;

    @NotNull
    private final DocumentType documentType;

    @NotNull
    private final Map<String, Object> fields;

    protected Document(@NotNull String id,
                       @NotNull DocumentType documentType,
                       @NotNull Map<String, Object> fields) {
        this.id = id;
        this.documentType = documentType;
        this.fields = new HashMap<>(fields);
    }

    @NotNull
    public String getId() {
        return id;
    }

    @NotNull
    public DocumentType getDocumentType() {
        return documentType;
    }

    @NotNull
    public Map<String, Object> getFields() {
        return Collections.unmodifiableMap(fields);
    }

    @Nullable
    public Object getField(@NotNull String name) {
        return fields.get(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName())
                .append(" {");
        for (Map.Entry<String, Object> e : fields.entrySet()) {
            sb.append("\n  ").append(e.getKey()).append(" = ").append(e.getValue());
        }
        sb.append("\n}");
        return sb.toString();
    }
}
