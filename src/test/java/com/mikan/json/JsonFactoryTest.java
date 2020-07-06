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

    @Test
    public void getJsonObject2() {
        String input = """
                {
                  "result": {
                    "id":"2d4d028de3015345da9420df5514dad0",
                    "type":"A",
                    "name":"blog.example.com",
                    "content":"2.6.4.5",
                    "proxiable":true,
                    "proxied":false,
                    "ttl":1,
                    "priority":0,
                    "locked":false,
                    "zone_id":"cd7d068de3012345da9420df9514dad0",
                    "zone_name":"example.com",
                    "modified_on":"2014-05-28T18:46:18.764425Z",
                    "created_on":"2014-05-28T18:46:18.764425Z"
                  },
                  "success": true,
                  "errors": [],
                  "messages": [],
                  "result_info": {
                    "page": 1,
                    "per_page": 20,
                    "count": 1,
                    "total_count": 200
                    }
                }
                """;
        final JsonObject have = JsonFactory.getJsonObject(input);
        assertEquals(5, have.getContents().size());
    }
}