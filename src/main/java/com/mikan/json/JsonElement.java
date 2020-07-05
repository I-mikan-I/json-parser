package com.mikan.json;


import java.io.Serializable;

/**
 * Represents an Element of a Json Object.
 *
 * @version 05.07.2020
 */
public interface JsonElement extends Serializable {

    /**
     * Converts the Json Element to a Json List if possible.
     *
     * @return List representation of the Element.
     * @throws RuntimeException if the Element cannot be converted.
     */
    JsonList toJsonList();

    /**
     * Converts the Json Element to a Json Object if possible.
     *
     * @return Object representation of the Element.
     * @throws RuntimeException if the Element cannot be converted.
     */
    JsonObject toJsonObject();

    /**
     * Converts the Json Element to a Json String if possible.
     *
     * @return String representation of the Element.
     * @throws RuntimeException if the Element cannot be converted.
     */
    JsonString toJsonString();

    /**
     * Converts the Json Element to a Json Number if possible.
     *
     * @return Number representation of the Element.
     * @throws RuntimeException if the Element cannot be converted.
     */
    JsonNumber toJsonNumber();

    /**
     * Converts the Json Element to a Json Bool if possible.
     *
     * @return Bool representation of the Element.
     * @throws RuntimeException if the Element cannot be converted.
     */
    JsonBool toJsonBool();

    /**
     * Checks whether the Element can be converted to a Json Number.
     *
     * @return true if the Element can be converted.
     */
    boolean isJsonNumber();

    /**
     * Checks whether the Element can be converted to a Json List.
     *
     * @return true if the Element can be converted.
     */
    boolean isJsonList();

    /**
     * Checks whether the Element can be converted to a Json Object.
     *
     * @return true if the Element can be converted.
     */
    boolean isJsonObject();

    /**
     * Checks whether the Element can be converted to a Json String.
     *
     * @return true if the Element can be converted.
     */
    boolean isJsonString();

    /**
     * Checks whether the Element can be converted to a Json Bool.
     *
     * @return true if the Element can be converted.
     */
    boolean isJsonBool();

    /**
     * Checks whether the Element is a Json Null.
     *
     * @return true if the Element is Null.
     */
    boolean isNull();

    /**
     * Turns this Json Element into a String able to be parsed again.
     *
     * @return String describing Object.
     */
    String formatJson();

}
