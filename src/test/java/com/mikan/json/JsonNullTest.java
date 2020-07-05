package com.mikan.json;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonNullTest {

    @Test
    public void isNull() {
        assertTrue(new JsonNull().isNull());
    }

    @Test
    public void testToString() {
        assertEquals("null", new JsonNull().toString());
    }

    @Test
    public void testEquals() {
        var sut = new JsonNull();
        assertEquals(sut, sut);
    }

    @Test
    public void testEquals2() {
        var sut = new JsonNull();
        assertNotEquals(sut, null);
    }

    @Test
    public void testEquals3() {
        var sut = new JsonNull();
        assertNotEquals(sut, "not null");
    }

    @Test
    public void testEquals4() {
        var sut = new JsonNull();
        assertEquals(sut, new JsonNull());
    }

    @Test
    public void testHashCode() {
        var sut = new JsonNull();
        var sut2 = new JsonNull();
        assertEquals(sut.hashCode(), sut2.hashCode());
    }
}