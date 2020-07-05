package com.mikan.json;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonListTest {

    private static JsonList getSUT() {
        return new JsonList();
    }

    @Test
    public void toJsonList() {
        JsonElement sut = getSUT();
        JsonList sutList = sut.toJsonList();

        assertTrue(sutList.getContents().isEmpty());

    }

    @Test
    public void isJsonList() {
        JsonElement sut = getSUT();
        assertTrue(sut.isJsonList());
    }

    @Test
    public void getContents() {

        JsonList sutList = getSUT();

        assertTrue(sutList.getContents().isEmpty());
    }

    @Test
    public void getContents2() {

        JsonList sutList = getSUT();
        sutList.getContents().add(new JsonBool(true));

        assertEquals(1, sutList.getContents().size());
    }

    @Test
    public void testToString() {
        JsonList sutList = getSUT();
        sutList.getContents().add(new JsonBool(true));

        assertEquals("[true]", sutList.toString());
    }

    @Test
    public void testEquals() {
        JsonList sutList1 = getSUT();
        JsonList sutList2 = getSUT();

        assertEquals(sutList1, sutList2);
    }

    @Test
    public void testEquals2() {
        JsonList sutList1 = getSUT();
        sutList1.getContents().add(new JsonBool(false));
        JsonList sutList2 = getSUT();
        sutList2.getContents().add(new JsonBool(false));

        assertEquals(sutList1, sutList2);
    }

    @Test
    public void testEquals3() {
        JsonList sutList1 = getSUT();
        sutList1.getContents().add(new JsonBool(false));
        JsonList sutList2 = getSUT();
        sutList2.getContents().add(new JsonBool(true));

        assertNotEquals(sutList1, sutList2);
    }

    @Test
    public void testEquals4() {
        JsonList sutList1 = getSUT();

        assertNotEquals(sutList1, null);
    }

    @Test
    public void testEquals6() {
        JsonList sutList1 = getSUT();

        assertNotEquals(sutList1, "String");
    }

    @Test
    public void testEquals7() {
        JsonList sutList1 = getSUT();

        assertEquals(sutList1, sutList1);
    }

    @Test
    public void testEquals8() {
        JsonList sutList1 = getSUT();
        sutList1.getContents().add(new JsonBool(false));
        JsonList sutList2 = new JsonList(sutList1.getContents());

        assertEquals(sutList1, sutList2);
    }

    @Test
    public void testHashCode() {
        JsonList sutList1 = getSUT();
        sutList1.getContents().add(new JsonBool(false));
        JsonList sutList2 = getSUT();
        sutList2.getContents().add(new JsonBool(false));

        assertEquals(sutList1.hashCode(), sutList2.hashCode());
    }
}