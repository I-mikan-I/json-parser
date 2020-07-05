package com.mikan.json;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonObjectTest {

    @Test
    public void toJsonObject() {
        JsonElement sut = new JsonObject();
        JsonObject sut2 = sut.toJsonObject();
        assertTrue(sut2.getContents().isEmpty());

    }

    @Test
    public void isJsonObject() {
        assertTrue(new JsonObject().isJsonObject());
    }

    @Test
    public void getContents() {
        var sut = new JsonObject();
        sut.getContents().put("test1", new JsonNumber(5));

        assertTrue(sut.getContents().containsKey("test1"));
    }

    @Test
    public void getContents2() {
        var sut = new JsonObject();
        sut.getContents().put("test1", new JsonNumber(5));

        assertTrue(sut.getContents().containsValue(new JsonNumber(5)));
    }

    @Test
    public void testToString() {
        var sut = new JsonObject();
        sut.getContents().put("test1", new JsonNull());

        assertEquals("{test1=null}", sut.toString());
    }

    @Test
    public void testEquals() {
        var sut = new JsonObject();

        assertEquals(sut, sut);
    }

    @Test
    public void testEquals2() {
        var sut = new JsonObject();

        assertNotEquals(sut, null);
    }

    @Test
    public void testEquals3() {
        var sut = new JsonObject();

        assertNotEquals(sut, "not a Json");
    }

    @Test
    public void testEquals4() {
        var sut = new JsonObject();
        var sut2 = new JsonObject();
        sut.getContents().put("test", new JsonString("value"));

        assertNotEquals(sut, sut2);
    }

    @Test
    public void testEquals5() {
        var sut = new JsonObject();
        var sut2 = new JsonObject();
        sut.getContents().put("test", new JsonString("value"));
        sut2.getContents().put("test", new JsonString("value"));

        assertEquals(sut, sut2);
    }

    @Test
    public void testEquals6() {
        var sut = new JsonObject();
        var sut2 = new JsonObject();
        sut.getContents().put("test", new JsonString("value"));
        sut2.getContents().put("test", new JsonString("value other"));

        assertNotEquals(sut, sut2);
    }

    @Test
    public void testEquals7() {
        var sut = new JsonObject();
        var sut2 = new JsonObject();
        sut.getContents().put("test", new JsonString("value"));
        sut2.getContents().put("test Other", new JsonString("value"));

        assertNotEquals(sut, sut2);
    }

    @Test
    public void testEquals8() {
        var sut = new JsonObject();
        sut.getContents().put("test", new JsonString("value"));
        var sut2 = new JsonObject(sut.getContents());

        assertEquals(sut, sut2);
    }

    @Test
    public void testHashCode() {
        var sut = new JsonObject();
        var sut2 = new JsonObject();
        sut.getContents().put("test", new JsonString("value"));
        sut2.getContents().put("test", new JsonString("value"));

        assertEquals(sut.hashCode(), sut2.hashCode());
    }
}