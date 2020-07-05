package com.mikan.json;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Utility Class for turning a Json String into a List of Json Tokens.
 *
 * @version 03.07.2020
 */
class Tokenizer {


    /**
     * Maps Json String escapes.
     */
    private static final Map<Character, Character> escapeMap = Map.of(
            '"', '"',
            '\\', '\\',
            '/', '/',
            'b', '\b',
            'f', '\f',
            'n', '\n',
            'r', '\r',
            't', '\t'
    );

    /**
     * Number of digits after a unicode escape.
     */
    private static final int UNICODE_POINTER_LENGTH = 4;

    /**
     * Private ctor.
     */
    private Tokenizer() {
    }

    /**
     * Turns a List of String Tokens into a List of Tokens as Objects.
     *
     * @param tokenStrings a valid List of Json Tokens.
     * @return a new List containing Json Tokens.
     * @see Token
     */
    static List<Token> tokenize(List<String> tokenStrings) {
        List<Token> result = new LinkedList<>();

        for (String tokenString : tokenStrings) {
            if (tokenString.startsWith("\""))
                result.add(new Token(unescapeString(tokenString)));
            else
                result.add(new Token(tokenString));
        }
        return result;
    }

    /**
     * UnEscapes a Json String.
     *
     * @param token the Token to UnEscape.
     * @return the escaped String.
     */
    private static String unescapeString(String token) {
        StringBuilder builder = new StringBuilder();
        char[] charArray = token.toCharArray();
        int pointer = 0;

        while (pointer < charArray.length) {
            char currentChar = charArray[pointer++];
            if (currentChar == '\\') {
                currentChar = charArray[pointer++];
                if (currentChar == 'u') {
                    builder.append(unescapeUnicode(Arrays.copyOfRange(charArray, pointer, pointer + UNICODE_POINTER_LENGTH)));
                    pointer += UNICODE_POINTER_LENGTH;
                } else
                    builder.append(escapeMap.get(currentChar));
            } else
                builder.append(currentChar);
        }
        return builder.toString();
    }

    /**
     * Escapes a unicode Character.
     *
     * @param digits Character buffer containing 4 HEX digits.
     * @return the corresponding Character.
     */
    private static char unescapeUnicode(char[] digits) {
        String digitString = new String(digits);
        int code = Integer.parseInt(digitString, 16);
        return (char) code;
    }
}
