package com.mikan.json;

import java.util.Objects;

/**
 * Represents a Token in a Json String.
 * The Token may either be a Json String, or a unique identifier like braces, colons, commas, etc.
 *
 * @version 03.07.2020
 */
class Token {
    /**
     * The content of the Token represented as a String.
     */
    private final String content;

    /**
     * Constructs a new Token.
     *
     * @param content the Contents of this token.
     */
    Token(String content) {
        this.content = Objects.requireNonNull(content);
    }


    /**
     * Returns whether this Token represents a Number.
     *
     * @return true if this Token represents a Number.
     */
    boolean isNumber() {
        return content.matches("([\\d-]\\d*(\\.\\d+)?([eE][-+]?\\d+)?)");
    }

    /**
     * Returns whether this Token represents a bool.
     *
     * @return true if this Token represents a bool.
     */
    boolean isBool() {
        return content.equals("true") || content.equals("false");
    }

    /**
     * Returns whether this Token represents null.
     *
     * @return true if this Token represents null.
     */
    boolean isNull() {
        return content.equals("null");
    }

    /**
     * Returns whether this Token represents a String.
     *
     * @return true if this Token represents a String.
     */
    boolean isString() {
        return content.startsWith("\"");
    }

    /**
     * Returns whether this Token represents an opening brace.
     *
     * @return true if this Token represents an opening brace.
     */
    boolean isOpeningBrace() {
        return content.equals("{");
    }

    /**
     * Returns whether this Token represents a closing brace.
     *
     * @return true if this Token represents a closing brace.
     */
    boolean isClosingBrace() {
        return content.equals("}");
    }

    /**
     * Returns whether this Token represents an opening bracket.
     *
     * @return true if this Token represents an opening bracket.
     */
    boolean isOpeningBracket() {
        return content.equals("[");
    }

    /**
     * Returns whether this Token represents a closing bracket.
     *
     * @return true if this Token represents a closing bracket.
     */
    boolean isClosingBracket() {
        return content.equals("]");
    }

    /**
     * Getter for this Tokens contents.
     *
     * @return contents of this Token.
     */
    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return getContent();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return getContent().equals(token.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContent());
    }
}
