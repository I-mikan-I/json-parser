package com.mikan.json;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonBoolTest {

    @Test
    public void toJsonBool() {
        JsonElement sut = new JsonBool(true);
        JsonBool sut2 = sut.toJsonBool();

        assertTrue(sut2.getValue());
    }

    @Test
    public void isJsonBool() {
        JsonElement sut = new JsonBool(true);
        assertTrue(sut.isJsonBool());
    }

    @Test
    public void getValue() {
        JsonBool sut = new JsonBool(false);
        assertFalse(sut.getValue());

    }

    @Test
    public void testEquals1() {
        JsonBool sut1 = new JsonBool(false);
        JsonBool sut2 = new JsonBool(false);
        assertEquals(sut1, sut2);
    }

    @Test
    public void testEquals2() {
        JsonBool sut1 = new JsonBool(true);
        JsonBool sut2 = new JsonBool(true);
        assertEquals(sut1, sut2);
    }

    @Test
    public void testEquals3() {
        JsonBool sut1 = new JsonBool(false);
        JsonBool sut2 = new JsonBool(true);
        assertNotEquals(sut1, sut2);
    }

    @Test
    public void testEquals4() {
        JsonBool sut1 = new JsonBool(false);
        assertEquals(sut1, sut1);
    }

    @Test
    public void testEquals5() {
        JsonBool sut1 = new JsonBool(false);
        assertNotEquals(sut1, null);
    }

    @Test
    public void testEquals6() {
        JsonBool sut1 = new JsonBool(false);
        assertNotEquals(sut1, "not bool");
    }

    @Test
    public void testHashCode() {
        JsonBool sut1 = new JsonBool(true);
        JsonBool sut2 = new JsonBool(true);

        assertEquals(sut1.hashCode(), sut2.hashCode());
    }
}