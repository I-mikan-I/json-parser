package com.mikan.json;

import org.junit.Test;

import static org.junit.Assert.*;

public class TokenTest {

    @Test
    public void testToString() {
        assertEquals("true", new Token("true").getContent());
    }

    @Test
    public void testEquals() {
        var sut1 = new Token("true");
        assertEquals(sut1, sut1);
    }

    @Test
    public void testEquals2() {
        var sut1 = new Token("true");
        assertNotEquals(sut1, null);
    }

    @Test
    public void testEquals3() {
        var sut1 = new Token("true");
        assertNotEquals(sut1, "not a token");
    }

    @Test
    public void testEquals4() {
        var sut1 = new Token("true");
        assertNotEquals(sut1, new Token("false"));
    }

    @Test
    public void testEquals5() {
        var sut1 = new Token("true");
        assertEquals(sut1, new Token("true"));
    }

    @Test
    public void testHashCode() {
        var sut1 = new Token("true");
        var sut2 = new Token("true");

        assertEquals(sut1.hashCode(), sut2.hashCode());
    }
}