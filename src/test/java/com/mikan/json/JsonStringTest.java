package com.mikan.json;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonStringTest {

    @Test
    public void toJsonString() {
        JsonElement sut = new JsonString("test");
        JsonString sut2 = sut.toJsonString();
        assertEquals("test", sut2.getContent());
    }

    @Test
    public void isJsonString() {
        assertTrue(new JsonString("test").isJsonString());
    }

    @Test
    public void getContent1() {
        var sut = new JsonString("");
        assertEquals("", sut.getContent());
    }

    @Test
    public void getContent2() {
        var sut = new JsonString("abc");
        assertEquals("abc", sut.getContent());
    }

    @Test
    public void testToString() {
        var sut = new JsonString("abc");
        assertEquals("abc", sut.toString());
    }

    @Test
    public void testEquals() {
        var sut = new JsonString("test");
        assertEquals(sut, sut);
    }

    @Test
    public void testEquals2() {
        var sut = new JsonString("test");
        assertNotEquals(sut, null);
    }

    @Test
    public void testEquals3() {
        var sut = new JsonString("test");
        assertNotEquals(sut, 23);
    }

    @Test
    public void testEquals4() {
        var sut = new JsonString("test");
        var sut2 = new JsonString("something else");
        assertNotEquals(sut, sut2);
    }

    @Test
    public void testEquals5() {
        var sut = new JsonString("test");
        var sut2 = new JsonString("test");
        assertEquals(sut, sut2);
    }

    @Test
    public void testHashCode() {

        var sut = new JsonString("test");
        var sut2 = new JsonString("test");
        assertEquals(sut.hashCode(), sut2.hashCode());
    }
}