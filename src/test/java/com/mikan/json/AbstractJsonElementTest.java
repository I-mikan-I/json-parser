package com.mikan.json;

import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractJsonElementTest {

    private static AbstractJsonElement getSut() {
        return new AbstractJsonElement() {
            @Override
            public String formatJson() {
                return null;
            }
        };
    }

    @Test(expected = IllegalStateException.class)
    public void toJsonList() {
        getSut().toJsonList();
    }

    @Test(expected = IllegalStateException.class)
    public void toJsonObject() {
        getSut().toJsonObject();
    }

    @Test(expected = IllegalStateException.class)
    public void toJsonString() {
        getSut().toJsonString();
    }

    @Test(expected = IllegalStateException.class)
    public void toJsonNumber() {
        getSut().toJsonNumber();
    }

    @Test(expected = IllegalStateException.class)
    public void toJsonBool() {
        getSut().toJsonBool();
    }

    @Test
    public void isNull() {
        assertFalse(getSut().isNull());
    }

    @Test
    public void isJsonNumber() {
        assertFalse(getSut().isJsonNumber());
    }

    @Test
    public void isJsonBool() {
        assertFalse(getSut().isJsonBool());
    }

    @Test
    public void isJsonList() {
        assertFalse(getSut().isJsonList());
    }

    @Test
    public void isJsonObject() {
        assertFalse(getSut().isJsonObject());
    }

    @Test
    public void isJsonString() {
        assertFalse(getSut().isJsonString());
    }


    @Test
    public void formatJson() {
        String input = """
                {"value1":-2.3E-3,"value2":[{}, true], "value3":""}
                """;
        final JsonObject have = JsonFactory.getJsonObject(input);
        final String result = have.formatJson();

        assertTrue(result.contains("""
                "value1":-0.0023"""));
        assertTrue(result.contains("""
                "value2":[{},true]""") || result.contains("""
                "value2":[true,{}]"""));
        assertTrue(result.contains("""
                "value3":"\""""));
    }

    @Test
    public void formatJson2() {
        String input = """
                {"value1":-2.3E-3,"value2":[{}, null], "value3":""}
                """;
        final JsonObject have = JsonFactory.getJsonObject(input);
        final String result = have.formatJson();

        assertTrue(result.contains("""
                "value1":-0.0023"""));
        assertTrue(result.contains("""
                "value2":[{},null]""") || result.contains("""
                "value2":[null,{}]"""));
        assertTrue(result.contains("""
                "value3":"\""""));
    }

    @Test
    public void formatJson3() {
        String input = """
                {"value1":-10,"value2":[{}, null], "value3":""}
                """;
        final JsonObject have = JsonFactory.getJsonObject(input);
        final String result = have.formatJson();

        assertTrue(result.contains("""
                "value1":-10"""));
        assertTrue(result.contains("""
                "value2":[{},null]""") || result.contains("""
                "value2":[null,{}]"""));
        assertTrue(result.contains("""
                "value3":"\""""));
    }

    @Test
    public void formatJson4() {
        String input = """
                {"value1":0,"value2":[{}, null], "value3":""}
                """;
        final JsonObject have = JsonFactory.getJsonObject(input);
        final String result = have.formatJson();

        assertTrue(result.contains("""
                "value1":0"""));
        assertTrue(result.contains("""
                "value2":[{},null]""") || result.contains("""
                "value2":[null,{}]"""));
        assertTrue(result.contains("""
                "value3":"\""""));
    }

    @Test
    public void formatJson5() {
        String input = """
                {"value1":5,"value2":[{}, null], "value3":""}
                """;
        final JsonObject have = JsonFactory.getJsonObject(input);
        final String result = have.formatJson();

        assertTrue(result.contains("""
                "value1":5"""));
        assertTrue(result.contains("""
                "value2":[{},null]""") || result.contains("""
                "value2":[null,{}]"""));
        assertTrue(result.contains("""
                "value3":"\""""));
    }

    @Test
    public void formatJson6() {
        String input = """
                {"val\\nue1":5,"value2":[{}, null], "value3":"abc\\t\\u00e4cde"}
                """;
        final JsonObject have = JsonFactory.getJsonObject(input);
        final String result = have.formatJson();

        assertTrue(result.contains("""
                "val\\nue1":5"""));
        assertTrue(result.contains("""
                "value2":[{},null]""") || result.contains("""
                "value2":[null,{}]"""));
        assertTrue(result.contains("""
                "value3":"abc\\t\\u00e4cde\""""));
    }

    @Test
    public void formatJson7() {
        String input = """
                {"value1":-2.3E-3,"value2":[{}, true], "value3":[]}
                """;
        final JsonObject have = JsonFactory.getJsonObject(input);
        final String result = have.formatJson();

        assertTrue(result.contains("""
                "value1":-0.0023"""));
        assertTrue(result.contains("""
                "value2":[{},true]""") || result.contains("""
                "value2":[true,{}]"""));
        assertTrue(result.contains("""
                "value3":[]"""));
    }
}