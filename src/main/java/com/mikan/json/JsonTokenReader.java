package com.mikan.json;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * FilterReader for turning a Stream of a valid Json Object into Tokens.
 *
 * @version 04.07.2020
 */
class JsonTokenReader extends FilterReader {

    /**
     * Maps special Json Tokens (such as braces or commas) to their corresponding character.
     */
    private static final Set<Character> ordinaryChars = Set.of(
            '{', '}', ':', ',', '[', ']'
    );
    /**
     * Whitespaces that Json may include and should be ignored.
     */
    private static final Set<Character> whiteSpaces = Set.of(
            '\n', '\r', ' ', '\t'
    );
    /**
     * Characters that are included in a valid json number excluding digits.
     */
    private static final Set<Character> numberChars = Set.of(
            '-', '+', 'e', 'E', '.'
    );
    /**
     * First character of a valid json bool.
     */
    private static final Set<Character> boolStartChars = Set.of(
            't', 'f');
    /**
     * buffered Character for lookahead.
     */
    private char bufferedChar;

    /**
     * Creates a new filtered reader.
     *
     * @param in a Reader object providing the underlying stream.
     * @throws NullPointerException if {@code in} is {@code null}
     */
    JsonTokenReader(Reader in) {
        super(in);
        bufferedChar = '\0';
    }

    @Override
    public int read(char[] cBuf, int off, int len) throws IOException {
        char[] tmpBuf = new char[len];
        int result = super.read(tmpBuf, off, len - 1);
        if (result >= 0) {
            if (bufferedChar > 0) {
                cBuf[0] = bufferedChar;
                bufferedChar = '\0';
                System.arraycopy(tmpBuf, 0, cBuf, 1, result);
                result += 1;
            } else
                System.arraycopy(tmpBuf, 0, cBuf, 0, result);
        }
        return result;
    }

    @Override
    public int read() throws IOException {
        char currentBuffer = bufferedChar;
        if (currentBuffer > 0) {
            bufferedChar = '\0';
            return currentBuffer;
        } else
            return super.read();
    }

    /**
     * Turns a Json String into a Token.
     *
     * @param c the first Character received. Should be a '"'.
     * @return the Token as a String.
     * @throws IOException if super.read() throws an IOException.
     */
    private String tokenizeString(char c) throws IOException {
        var tokenBuilder = new StringBuilder();
        boolean escaped = true;
        while (c != '"' || escaped) {
            tokenBuilder.append(c);
            escaped = c == '\\';
            c = (char) read();
        }
        tokenBuilder.append('"');
        return tokenBuilder.toString();
    }

    /**
     * Turns a Json String into a Number.
     *
     * @param c the first Character received. Should be a digit.
     * @return the Token as a String.
     * @throws IOException if super.read() throws an IOException.
     */
    private String tokenizeNumber(char c) throws IOException {
        var tokenBuilder = new StringBuilder();
        while (isNumber(c)) {
            tokenBuilder.append(c);
            c = (char) read();
        }
        bufferedChar = c;
        return tokenBuilder.toString();
    }

    /**
     * Turns a Json Bool into a Token.
     *
     * @param c the first Character received. Should be contained in the respective Map.
     * @return the Token as a String.
     * @throws IOException if super.read() throws an IOException.
     */
    private String tokenizeBool(char c) throws IOException {
        var tokenBuilder = new StringBuilder();
        while (c != 'e') {
            tokenBuilder.append(c);
            c = (char) read();
        }
        tokenBuilder.append('e');
        return tokenBuilder.toString();
    }

    /**
     * Turns a Json null into a Token.
     *
     * @param c the first Character received. Should be a 'n'.
     * @return the Token as a String.
     * @throws IOException if super.read() throws an IOException.
     */
    private String tokenizeNull(char c) throws IOException {
        var tokenBuilder = new StringBuilder();
        tokenBuilder.append(c);
        for (int i = 0; i < 3; ++i) {
            c = (char) read();
            tokenBuilder.append(c);
        }
        return tokenBuilder.toString();
    }

    /**
     * Turns the entire input into Tokens until the end is reached.
     *
     * @return a List containing the Tokens in order.
     * @throws IOException if super.read() throws an IOException.
     */
    public List<String> readAllTokens() throws IOException {
        List<String> result = new LinkedList<>();
        String currentToken = readToken();
        while (currentToken != null) {
            result.add(currentToken);
            currentToken = readToken();
        }
        return result;
    }

    /**
     * Reads the next Token from super.
     *
     * @return the Token represented as a String.
     * @throws IOException if super.read() throws an IOException.
     */
    public String readToken() throws IOException {
        String result = null;
        int nextCode = read();
        while (whiteSpaces.contains((char) nextCode)) {
            nextCode = read();
        }
        char nextChar = (char) nextCode;
        if (nextCode > 0) {
            if (ordinaryChars.contains(nextChar)) {
                result = Character.toString(nextChar);
            }
            //tokenize string
            else if (nextChar == '"') {
                result = tokenizeString(nextChar);
            }
            //tokenize number
            else if (isNumber(nextChar)) {
                result = tokenizeNumber(nextChar);
            }
            //tokenize bool
            else if (boolStartChars.contains(nextChar)) {
                result = tokenizeBool(nextChar);
            }
            //tokenize null
            else if (nextChar == 'n') {
                result = tokenizeNull(nextChar);
            } else
                throw new IllegalArgumentException();
        }
        return result;
    }

    /**
     * Checks whether the Character may be contained in a Json formatted number.
     *
     * @param c affected Character.
     * @return true, if the Character may be contained in a number.
     */
    private boolean isNumber(char c) {
        return Character.isDigit(c) || numberChars.contains(c);
    }

}
