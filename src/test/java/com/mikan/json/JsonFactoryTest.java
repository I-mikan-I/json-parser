package com.mikan.json;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonFactoryTest {

    @Test
    public void getJsonObject() {
        String input = """
                {"value1":-2.3E-3,"value2":[{}, true], "value3":""}
                """;
        final JsonObject have = JsonFactory.getJsonObject(input);
        final JsonObject want = new JsonObject();
        final JsonList inner1 = new JsonList();
        final JsonObject inner2 = new JsonObject();

        inner1.getContents().add(inner2);
        inner1.getContents().add(new JsonBool(true));
        want.getContents().put("value1", new JsonNumber(-0.0023));
        want.getContents().put("value2", inner1);
        want.getContents().put("value3", new JsonString(""));

        assertEquals(want, have);
    }
}