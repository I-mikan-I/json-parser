package com.mikan.json;

import java.util.List;
import java.util.Map;

/**
 * Utility Class for Parsing Json Tokens into Json Elements.
 *
 * @version 04.07.2020
 */
class Parser {

    /**
     * Holds a Token representing a closing Brace.
     */
    private static final Token closingBraceToken = new Token("}");
    /**
     * Holds a Token representing a closing Bracket.
     */
    private static final Token closingBracketToken = new Token("]");

    /**
     * private constructor.
     */
    private Parser() {
    }


    /**
     * Parses a JsonObject.
     *
     * @param tokens List of Tokens with the beginning of a JsonObject at index 0.
     * @return a new JsonObject.
     */
    static JsonObject parseObject(List<Token> tokens) {

        final JsonObject result = new JsonObject();
        final Map<String, JsonElement> contents = result.getContents();
        boolean isEmpty = true;

        tokens.remove(0);
        Token nextToken = tokens.get(0);
        while (!nextToken.isClosingBrace()) {
            isEmpty = false;
            nextToken = tokens.get(0);
            //remove key and colon
            tokens.remove(0);
            tokens.remove(0);

            final String key = nextToken.getContent();
            contents.put(key.substring(1, key.length() - 1), parseNextElement(tokens));
            nextToken = tokens.get(0);
            tokens.remove(0); //remove comma or }
        }
        if (isEmpty) //remove closing symbol for empty Objects.
            tokens.remove(0);
        return result;
    }

    /**
     * Parses a JsonList.
     *
     * @param tokens List of Tokens with the beginning of a JsonList at index 0.
     * @return a new JsonList.
     */
    static JsonList parseList(List<Token> tokens) {
        boolean isEmpty = true;
        final JsonList result = new JsonList();
        final List<JsonElement> contents = result.getContents();


        tokens.remove(0);
        Token nextToken = tokens.get(0);
        while (!nextToken.isClosingBracket()) {
            isEmpty = false;
            contents.add(parseNextElement(tokens));
            nextToken = tokens.get(0);
            tokens.remove(0); //remove comma or ]
        }

        if (isEmpty) //remove closing symbol for empty Lists.
            tokens.remove(0);
        return result;
    }

    /**
     * Parses a JsonString.
     *
     * @param tokens List of Tokens with a JsonString at index 0.
     * @return a new JsonString.
     */
    static JsonString parseString(List<Token> tokens) {
        String content = tokens.get(0).getContent();
        tokens.remove(0);
        return new JsonString(content.substring(1, content.length() - 1));
    }

    /**
     * Parses a JsonBool.
     *
     * @param tokens List of Tokens with a JsonBool at index 0.
     * @return a new JsonBool
     */
    static JsonBool parseBool(List<Token> tokens) {
        boolean value = Boolean.parseBoolean(tokens.get(0).getContent());
        tokens.remove(0);
        return new JsonBool(value);
    }

    /**
     * Parses a JsonNumber.
     *
     * @param tokens List of Tokens with a JsonNumber at index 0.
     * @return a new JsonNumber
     */
    static JsonNumber parseNumber(List<Token> tokens) {
        double value = Double.parseDouble(tokens.get(0).getContent());
        tokens.remove(0);
        return new JsonNumber(value);
    }

    /**
     * Parses a JsonNull.
     *
     * @param tokens List of Tokens with a JsonNull at index 0.
     * @return a new JsonNull
     */
    static JsonNull parseNull(List<Token> tokens) {
        tokens.remove(0);
        return new JsonNull();
    }


    /**
     * Parses the next Element in a List of Json Tokens.
     *
     * @param tokens A list of Json Tokens.
     * @return a new JsonElement.
     * @throws IllegalArgumentException if it receives an unexpected Token.
     */
    static JsonElement parseNextElement(List<Token> tokens) {
        final JsonElement returnVal;
        final Token nextToken = tokens.get(0);
        if (nextToken.isString())
            returnVal = parseString(tokens);
        else if (nextToken.isOpeningBrace())
            returnVal = parseObject(tokens);
        else if (nextToken.isOpeningBracket())
            returnVal = parseList(tokens);
        else if (nextToken.isNumber())
            returnVal = parseNumber(tokens);
        else if (nextToken.isBool())
            returnVal = parseBool(tokens);
        else if (nextToken.isNull())
            returnVal = parseNull(tokens);
        else
            throw new IllegalArgumentException();

        return returnVal;
    }
}
