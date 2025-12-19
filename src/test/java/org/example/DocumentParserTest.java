package org.example;

import org.example.document.Contract;
import org.example.document.Document;
import org.example.exception.MissingFieldException;
import org.example.exception.UnknownDocumentTypeException;
import org.example.parser.DocumentParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class DocumentParserTest {

    private Path createTempJson(String content) throws IOException {
        Path temp = Files.createTempFile("doc", ".json");
        Files.writeString(temp, content);
        return temp;
    }

    @Test
    void parsesContractSuccessfully() throws Exception {
        String json = "{ \"cost\": 5, \"date\": \"1-1-2023\", \"id\": \"A1\", \"document_type\": \"CONTRACT\" }";
        Path path = createTempJson(json);

        DocumentParser parser = new DocumentParser();
        Document doc = parser.parse(path.toString());

        assertTrue(doc instanceof Contract);
        assertEquals("A1", doc.getId());
        assertEquals(5, doc.getFields().get("cost"));
    }

    @Test
    void missingIdThrowsException() throws Exception {
        String json = "{ \"cost\": 5, \"document_type\": \"CONTRACT\" }";
        Path path = createTempJson(json);

        DocumentParser parser = new DocumentParser();

        assertThrows(MissingFieldException.class,
                () -> parser.parse(path.toString()));
    }

    @Test
    void unknownDocumentTypeThrowsException() throws Exception {
        String json = "{ \"id\": \"A1\", \"document_type\": \"UNKNOWN\" }";
        Path path = createTempJson(json);

        DocumentParser parser = new DocumentParser();

        assertThrows(UnknownDocumentTypeException.class,
                () -> parser.parse(path.toString()));
    }
}
