package org.example;

import org.example.document.Document;
import org.example.exception.DocumentParsingException;
import org.example.parser.DocumentParser;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java -jar HW3_Java.jar <path-to-json>");
            System.exit(1);
        }

        DocumentParser parser = new DocumentParser();

        try {
            Document doc = parser.parse(args[0]);
            System.out.println("Parsed class: " + doc.getClass().getSimpleName());
            System.out.println(doc);
        } catch (DocumentParsingException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(2);
        }
    }
}
