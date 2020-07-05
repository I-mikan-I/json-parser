package com.mikan.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Json List containing Json Elements.
 *
 * @version 05.07.2020
 */
public class JsonList extends AbstractJsonElement implements JsonElement {

    /**
     * The JsonElements of this List.
     */
    private final List<JsonElement> contents;

    /**
     * Constructs a new JsonList.
     */
    public JsonList() {
        this.contents = new ArrayList<>();
    }

    /**
     * Constructs a new JsonList using the specified contents.
     *
     * @param contents a List of Elements to be copied.
     */
    public JsonList(List<JsonElement> contents) {
        this.contents = new ArrayList<>(Objects.requireNonNull(contents));
    }

    @Override
    public JsonList toJsonList() {
        return this;
    }


    @Override
    public boolean isJsonList() {
        return true;
    }

    @Override
    public String formatJson() {
        StringBuilder result = new StringBuilder();
        result.append('[');
        for (JsonElement element : getContents()) {
            result.append(element.formatJson());
            result.append(',');
        }
        final int lastIndex = result.length() - 1;
        if (result.charAt(lastIndex) == ',')
            result.deleteCharAt(lastIndex);
        result.append(']');
        return result.toString();
    }


    public List<JsonElement> getContents() {
        return contents;
    }

    @Override
    public String toString() {
        return getContents().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonList jsonList = (JsonList) o;
        return getContents().equals(jsonList.getContents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContents());
    }
}
