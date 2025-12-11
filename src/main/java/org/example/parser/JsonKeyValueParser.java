package org.example.parser;

import org.example.exception.InvalidJsonFormatException;
import org.example.exception.UnsupportedValueTypeException;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public final class JsonKeyValueParser {

    private JsonKeyValueParser() {}

    @NotNull
    public static Map<String, Object> parseFlatObject(@NotNull String json)
            throws InvalidJsonFormatException, UnsupportedValueTypeException {

        String trimmed = json.trim();
        if (!trimmed.startsWith("{") || !trimmed.endsWith("}")) {
            throw new InvalidJsonFormatException("JSON must start with '{' and end with '}'");
        }

        String inner = trimmed.substring(1, trimmed.length() - 1).trim();
        Map<String, Object> result = new HashMap<>();

        if (inner.isEmpty()) return result;

        String[] pairs = inner.split(",");

        for (String rawPair : pairs) {
            String pair = rawPair.trim();
            int index = pair.indexOf(':');
            if (index < 0)
                throw new InvalidJsonFormatException("Missing ':' in: " + pair);

            String rawKey = pair.substring(0, index).trim();
            String rawValue = pair.substring(index + 1).trim();

            String key = stripQuotes(rawKey);
            Object value = parseValue(rawValue);

            result.put(key, value);
        }

        return result;
    }

    @NotNull
    private static String stripQuotes(@NotNull String s)
            throws InvalidJsonFormatException {
        s = s.trim();
        if (s.startsWith("\"") && s.endsWith("\""))
            return s.substring(1, s.length() - 1);

        throw new InvalidJsonFormatException("Key must be a quoted string: " + s);
    }

    @NotNull
    private static Object parseValue(@NotNull String s)
            throws UnsupportedValueTypeException {

        s = s.trim();
        if (s.startsWith("\"") && s.endsWith("\""))
            return s.substring(1, s.length() - 1);

        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new UnsupportedValueTypeException(s);
        }
    }
}
