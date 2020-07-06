package com.mikan.json;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

/**
 * Factory for parsing a Json Object.
 *
 * @version 05.07.2020
 */
public class JsonFactory {

    /**
     * Maps Characters to escape sequences.
     */
    private static final Map<Character, String> escapeMap = Map.of(
            '"', "\\\"",
            '\\', "\\\\",
            '\b', "\\b",
            '\f', "\\f",
            '\n', "\\n",
            '\r', "\\r",
            '\t', "\\t"
    );

    /**
     * Parses a Json String into a Json Object.
     *
     * @param json the String to parse.
     * @return a new JsonObject.
     * @throws RuntimeException if the String is of invalid format.
     * @see JsonObject
     */
    public static JsonObject getJsonObject(String json) {
        try {
            return Parser.parseObject(Tokenizer.tokenize(new JsonTokenReader(new StringReader(json)).readAllTokens()));
        } catch (IOException e) {
            throw new AssertionError();
        }
    }

    /**
     * Escapes a String into Json compatible format.
     *
     * @param string String to escape.
     * @return the escaped String.
     */
    static String escapeString(String string) {
        final StringBuilder result = new StringBuilder();
        result.append('"');
        for (final Character currentChar : string.toCharArray()) {
            if (escapeMap.containsKey(currentChar))
                result.append(escapeMap.get(currentChar));
            else if (currentChar > 127)
                result.append(String.format("\\u%04x", (int) currentChar));
            else
                result.append(currentChar);
        }
        result.append('"');
        return result.toString();
    }
}
