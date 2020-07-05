package com.mikan.json;

import java.util.Map;
import java.util.Objects;

/**
 * Represents a Json String.
 *
 * @version 03.07-2020
 */
public class JsonString extends AbstractJsonElement {

    /**
     * Content of the String.
     */
    private final String content;

    /**
     * Constructs a new Json String.
     *
     * @param content the String this Json String holds.
     */
    public JsonString(String content) {
        this.content = Objects.requireNonNull(content);
    }

    @Override
    public JsonString toJsonString() {
        return this;
    }

    @Override
    public boolean isJsonString() {
        return true;
    }

    @Override
    public String formatJson() {
        return JsonFactory.escapeString(getContent());
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonString that = (JsonString) o;
        return getContent().equals(that.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContent());
    }
}
