package org.example.library;

import org.example.document.Document;
import org.example.exception.DocumentNotFoundException;
import org.example.exception.DuplicateDocumentIdException;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Library<T extends Document> {

    @NotNull
    private final Map<String, T> storage = new HashMap<>();

    public void put(@NotNull T document) throws DuplicateDocumentIdException {
        String id = document.getId();
        if (storage.containsKey(id))
            throw new DuplicateDocumentIdException(id);

        storage.put(id, document);
    }

    @NotNull
    public T get(@NotNull String id) throws DocumentNotFoundException {
        T doc = storage.get(id);
        if (doc == null)
            throw new DocumentNotFoundException(id);

        return doc;
    }

    public void remove(@NotNull String id) throws DocumentNotFoundException {
        if (!storage.containsKey(id))
            throw new DocumentNotFoundException(id);

        storage.remove(id);
    }

    public int size() {
        return storage.size();
    }

    @NotNull
    public Map<String, T> asUnmodifiableMap() {
        return Collections.unmodifiableMap(storage);
    }
}
