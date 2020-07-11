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
    public void testNumber1() {
        var sut1 = new Token("5");
        assertTrue(sut1.isNumber());
    }

    @Test
    public void testNumber2() {
        var sut1 = new Token("5.2");
        assertTrue(sut1.isNumber());
    }

    @Test
    public void testNumber3() {
        var sut1 = new Token("-5");
        assertTrue(sut1.isNumber());
    }

    @Test
    public void testNumber4() {
        var sut1 = new Token("-5.2");
        assertTrue(sut1.isNumber());
    }

    @Test
    public void testNumber5() {
        var sut1 = new Token("5e3");
        assertTrue(sut1.isNumber());
    }

    @Test
    public void testNumber6() {
        var sut1 = new Token("5e+3");
        assertTrue(sut1.isNumber());
    }

    @Test
    public void testNumber7() {
        var sut1 = new Token("5e-3");
        assertTrue(sut1.isNumber());
    }

    @Test
    public void testNumber8() {
        var sut1 = new Token("-5.3e+3");
        assertTrue(sut1.isNumber());
    }

    @Test
    public void testNumber9() {
        var sut1 = new Token("-5e.3");
        assertFalse(sut1.isNumber());
    }

    @Test
    public void testHashCode() {
        var sut1 = new Token("true");
        var sut2 = new Token("true");

        assertEquals(sut1.hashCode(), sut2.hashCode());
    }
}