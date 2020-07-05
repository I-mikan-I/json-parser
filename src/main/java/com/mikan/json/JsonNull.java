package com.mikan.json;


/**
 * Represents a Null in a Json Structure.
 *
 * @version 05.07.2020
 */
public class JsonNull extends AbstractJsonElement {

    /**
     * Constructs a new Json Null.
     */
    public JsonNull() {
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public String formatJson() {
        return "null";
    }

    @Override
    public String toString() {
        return "null";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
