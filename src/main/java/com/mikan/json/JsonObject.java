package com.mikan.json;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents an Object containing Properties.
 *
 * @version 05.07.2020
 */
public class JsonObject extends AbstractJsonElement {

    /**
     * The contents of this JsonObject.
     * Each Property is a String, JsonElement pair.
     */
    private final Map<String, JsonElement> contents;

    /**
     * Constructs a new Json Object.
     */
    public JsonObject() {
        contents = new HashMap<>();
    }

    /**
     * Constructs a new Json Object using the specified contents.
     *
     * @param contents the contents to copy.
     */
    public JsonObject(Map<String, JsonElement> contents) {
        this.contents = new HashMap<>(Objects.requireNonNull(contents));
    }

    @Override
    public JsonObject toJsonObject() {
        return this;
    }

    @Override
    public boolean isJsonObject() {
        return true;
    }

    @Override
    public String formatJson() {
        final StringBuilder result = new StringBuilder();
        result.append('{');
        for (Map.Entry<String, JsonElement> entry : getContents().entrySet()) {
            result.append(JsonFactory.escapeString(entry.getKey()));
            result.append(':');
            result.append(entry.getValue().formatJson());
            result.append(',');
        }
        final int lastIndex = result.length() - 1;
        if (result.charAt(lastIndex) == ',')
            result.deleteCharAt(lastIndex);
        result.append('}');
        return result.toString();
    }

    public Map<String, JsonElement> getContents() {
        return contents;
    }

    @Override
    public String toString() {
        return contents.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonObject that = (JsonObject) o;
        return getContents().equals(that.getContents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContents());
    }
}
