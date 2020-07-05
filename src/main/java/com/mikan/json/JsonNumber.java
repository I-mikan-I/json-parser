package com.mikan.json;

import java.util.Objects;

/**
 * Represents a Number in a Json Structure.
 *
 * @version 05.07.2020
 */
public class JsonNumber extends AbstractJsonElement {

    /**
     * Integer representation of the value assigned, might be wrong if a double was used to construct this Number.
     */
    private final int intValue;
    /**
     * Double representation of the value assigned.
     */
    private final double dValue;

    /**
     * Constructs a new JsonNumber.
     *
     * @param value integer value to hold.
     */
    public JsonNumber(int value) {
        intValue = value;
        dValue = value;
    }

    /**
     * Constructs a new JsonNumber.
     *
     * @param value double value to hold.
     */
    public JsonNumber(double value) {
        dValue = value;
        intValue = (int) value;
    }

    @Override
    public JsonNumber toJsonNumber() {
        return this;
    }

    @Override
    public boolean isJsonNumber() {
        return true;
    }

    @Override
    public String formatJson() {
        final String result;
        if (Double.compare(getIntValue(), getDValue()) == 0)
            result = Integer.toString(getIntValue());
        else
            result = Double.toString(getDValue());
        return result;
    }

    /**
     * Getter Method for integer representation of the assigned value.
     * Might be missing decimals.
     *
     * @return value as int.
     */
    public int getIntValue() {
        return intValue;
    }

    /**
     * Getter Method for double representation of the assigned value.
     *
     * @return value as double.
     */
    public double getDValue() {
        return dValue;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonNumber that = (JsonNumber) o;
        return getIntValue() == that.getIntValue() &&
                Double.compare(that.dValue, dValue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIntValue(), dValue);
    }

    @Override
    public String toString() {
        return Double.toString(getDValue());
    }
}
