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
        assertEquals(13, have.getContents().get("result").toJsonObject().getContents().size());
        assertEquals(4, have.getContents().get("result_info").toJsonObject().getContents().size());
        assertEquals(0, have.getContents().get("errors").toJsonList().getContents().size());
    }

    @Test
    public void getJsonObject3() {
        String input = """
                {
                  "success": true,
                  "errors": [],
                  "messages": [],
                  "result": [
                    {
                      "id": "ed17574386854bf78a67040be0a770b0",
                      "name": "readonly token",
                      "status": "active",
                      "issued_on": "2018-07-01T05:20:00Z",
                      "modified_on": "2018-07-02T05:20:00Z",
                      "not_before": "2018-07-01T05:20:00Z",
                      "expires_on": "2020-01-01T00:00:00Z",
                      "policies": [
                        {
                          "id": "f267e341f3dd4697bd3b9f71dd96247f",
                          "effect": "allow",
                          "resources": {
                            "com.cloudflare.api.account.zone.eb78d65290b24279ba6f44721b3ea3c4": "*",
                            "com.cloudflare.api.account.zone.22b1de5f1c0e4b3ea97bb1e963b06a43": "*"
                          },
                          "permission_groups": [
                            {
                              "id": "c8fed203ed3043cba015a93ad1616f1f",
                              "name": "Zone Read"
                            },
                            {
                              "id": "82e64a83756745bbbb1c9c2701bf816b",
                              "name": "DNS Read"
                            }
                          ]
                        }
                      ],
                      "condition": {
                        "request.ip": {
                          "in": [
                            "199.27.128.0/21",
                            "2400:cb00::/32"
                          ],
                          "not_in": [
                            "199.27.128.0/21",
                            "2400:cb00::/32"
                          ]
                        }
                      }
                    }
                  ]
                }
                """;
        final JsonObject have = JsonFactory.getJsonObject(input);
        assertEquals(9, have.getContents().get("result").toJsonList().getContents().get(0).toJsonObject().getContents().size());
    }
}