package org.example;

import org.example.document.Contract;
import org.example.exception.DocumentNotFoundException;
import org.example.exception.DuplicateDocumentIdException;
import org.example.library.Library;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    private Contract createContract(String id) {
        return new Contract(id, Map.of(
                "id", id,
                "document_type", "CONTRACT",
                "cost", 10
        ));
    }

    @Test
    void putAndGetDocument() throws Exception {
        Library<Contract> lib = new Library<>();
        Contract c = createContract("C1");

        lib.put(c);
        Contract got = lib.get("C1");

        assertSame(c, got);
        assertEquals(1, lib.size());
    }

    @Test
    void duplicateIdThrowsException() throws Exception {
        Library<Contract> lib = new Library<>();
        Contract c1 = createContract("C1");
        Contract c2 = createContract("C1");

        lib.put(c1);
        assertThrows(DuplicateDocumentIdException.class,
                () -> lib.put(c2));
    }

    @Test
    void removeNonExistingThrowsException() {
        Library<Contract> lib = new Library<>();

        assertThrows(DocumentNotFoundException.class,
                () -> lib.remove("NOPE"));
    }

    @Test
    void removeExistingThenGetThrowsException() throws Exception {
        Library<Contract> lib = new Library<>();
        Contract c = createContract("C1");

        lib.put(c);
        lib.remove("C1");

        assertThrows(DocumentNotFoundException.class,
                () -> lib.get("C1"));
    }
}
