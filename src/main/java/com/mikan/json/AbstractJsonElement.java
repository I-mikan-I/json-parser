package com.mikan.json;

/**
 * ABC for a JsonElement.
 *
 * @version 04.07.2020
 */
abstract class AbstractJsonElement implements JsonElement {

    /**
     * Creates a new AbstactJsonElement.
     */
    protected AbstractJsonElement() {
    }

    @Override
    public JsonList toJsonList() {
        throw new IllegalStateException();
    }

    @Override
    public JsonObject toJsonObject() {
        throw new IllegalStateException();
    }

    @Override
    public JsonString toJsonString() {
        throw new IllegalStateException();
    }

    @Override
    public JsonNumber toJsonNumber() {
        throw new IllegalStateException();
    }

    @Override
    public JsonBool toJsonBool() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isJsonNumber() {
        return false;
    }

    @Override
    public boolean isJsonBool() {
        return false;
    }

    @Override
    public boolean isJsonList() {
        return false;
    }

    @Override
    public boolean isJsonObject() {
        return false;
    }

    @Override
    public boolean isJsonString() {
        return false;
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
