package com.mikan.json;

import java.util.Objects;

/**
 * Represents a bool in json format.
 *
 * @version 06.07.2020
 */
public class JsonBool extends AbstractJsonElement {
    private final boolean value;

    /**
     * Creates a new JsonBool.
     *
     * @param value boolean value to hold.
     */
    public JsonBool(boolean value) {
        this.value = value;
    }

    @Override
    public JsonBool toJsonBool() {
        return this;
    }

    @Override
    public boolean isJsonBool() {
        return true;
    }

    @Override
    public String formatJson() {
        return Boolean.toString(value);
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonBool jsonBool = (JsonBool) o;
        return getValue() == jsonBool.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }
}
