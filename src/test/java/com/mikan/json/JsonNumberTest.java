package com.mikan.json;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonNumberTest {

    private static JsonNumber getSUT(int i) {
        return new JsonNumber(i);
    }

    private static JsonNumber getSUT(double d) {
        return new JsonNumber(d);
    }


    @Test
    public void toJsonNumber() {
        JsonElement sut = getSUT(5);
        JsonNumber sut2 = sut.toJsonNumber();

        assertEquals(5, sut2.getIntValue());

    }

    @Test
    public void isJsonNumber() {

        assertTrue(getSUT(5).isJsonNumber());
    }

    @Test
    public void isJsonNumber2() {

        assertTrue(getSUT(5d).isJsonNumber());
    }

    @Test
    public void getIntValue() {
        var sut = getSUT(5);
        assertEquals(5, sut.getIntValue());
    }

    @Test
    public void getIntValue2() {
        var sut = getSUT(5.9);
        assertEquals(5, sut.getIntValue());
    }

    @Test
    public void getDValue() {
        var sut = getSUT(5);
        assertEquals(5d, sut.getDValue(), 0);
    }

    @Test
    public void getDValue2() {
        var sut = getSUT(5.1);
        assertEquals(5.1, sut.getDValue(), 0);
    }

    @Test
    public void testEquals() {
        var sut1 = getSUT(5);
        assertNotEquals(sut1, null);
    }

    @Test
    public void testEquals2() {
        var sut1 = getSUT(5);
        assertNotEquals(sut1, "String");
    }

    @Test
    public void testEquals3() {
        var sut1 = getSUT(5);
        var sut2 = getSUT(5);
        assertEquals(sut1, sut2);
    }

    @Test
    public void testEquals4() {
        var sut1 = getSUT(5);
        var sut2 = getSUT(5d);
        assertEquals(sut1, sut2);
    }

    @Test
    public void testEquals5() {
        var sut1 = getSUT(5.2);
        var sut2 = getSUT(5.2);
        assertEquals(sut1, sut2);
    }

    @Test
    public void testEquals6() {
        var sut1 = getSUT(5);
        var sut2 = getSUT(5.2);
        assertNotEquals(sut1, sut2);
    }

    @Test
    public void testEquals7() {
        var sut1 = getSUT(1d);
        var sut2 = getSUT(1.001);
        assertNotEquals(sut1, sut2);
    }

    @Test
    public void testEquals8() {
        var sut1 = getSUT(5);
        assertEquals(sut1, sut1);
    }

    @Test
    public void testHashCode() {
        var sut1 = getSUT(1d);
        var sut2 = getSUT(1.00);
        assertEquals(sut1.hashCode(), sut2.hashCode());
    }

}