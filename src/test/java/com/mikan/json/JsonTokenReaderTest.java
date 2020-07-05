package com.mikan.json;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JsonTokenReaderTest {

    @Test
    public void testReadToken() throws IOException {
        String input = """
                {"test1":"result1","test2":"result2","test3":["content1","content2","content3"]}""";
        var sut = new JsonTokenReader(new StringReader(input));

        List<String> output = new ArrayList<>();
        String currentString = sut.readToken();
        while (currentString != null) {
            output.add(currentString);
            currentString = sut.readToken();
        }
        String want = "[{, \"test1\", :, \"result1\", ,, \"test2\", :, \"result2\", ,, \"test3\", :, [, \"content1\", ,, \"content2\", ,, \"content3\", ], }]";
        String have = output.toString();
        assertEquals(want, have);

    }

    @Test
    public void testReadToken2() throws IOException {
        String input = """
                {"test1":
                "result1",
                "test3":[
                {"inner1":"inner2"},
                {"inner3":[]}]}""";
        var sut = new JsonTokenReader(new StringReader(input));

        List<String> output = new ArrayList<>();
        String currentString = sut.readToken();
        while (currentString != null) {
            output.add(currentString);
            currentString = sut.readToken();
        }
        String want = "[{, \"test1\", :, \"result1\", ,, \"test3\", :, [, {, \"inner1\", :, \"inner2\", }, ,, {, \"inner3\", :, [, ], }, ], }]";
        String have = output.toString();
        assertEquals(want, have);
    }

    @Test
    public void testReadToken3() throws IOException {
        String input = """
                {"te\\st1"  : "res\\"ult"}""";
        var sut = new JsonTokenReader(new StringReader(input));

        List<String> output = new ArrayList<>();
        String currentString = sut.readToken();
        while (currentString != null) {
            output.add(currentString);
            currentString = sut.readToken();
        }
        String want = "[{, \"te\\st1\", :, \"res\\\"ult\", }]";
        String have = output.toString();
        assertEquals(want, have);
    }

    @Test
    public void testReadToken4() throws IOException {
        String input = """
                {"test1":"result1","test2":1234 ,"test3":["content1",-3.1e-10, 3.1E5]}""";
        var sut = new JsonTokenReader(new StringReader(input));

        List<String> output = new ArrayList<>();
        String currentString = sut.readToken();
        while (currentString != null) {
            output.add(currentString);
            currentString = sut.readToken();
        }
        String want = "[{, \"test1\", :, \"result1\", ,, \"test2\", :, 1234, ,, \"test3\", :, [, \"content1\", ,, -3.1e-10, ,, 3.1E5, ], }]";
        String have = output.toString();

        assertEquals(want, have);
    }

    @Test
    public void testReadToken5() throws IOException {
        String input = """
                {"test1":"result1","test2": true ,"test3":["content1",false , null  
                ]}""";
        var sut = new JsonTokenReader(new StringReader(input));

        List<String> output = new ArrayList<>();
        String currentString = sut.readToken();
        while (currentString != null) {
            output.add(currentString);
            currentString = sut.readToken();
        }
        String want = "[{, \"test1\", :, \"result1\", ,, \"test2\", :, true, ,, \"test3\", :, [, \"content1\", ,, false, ,, null, ], }]";
        String have = output.toString();
        assertEquals(want, have);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegal() throws IOException {
        String input = """
                ()/""";
        new JsonTokenReader(new StringReader(input)).readAllTokens();
    }

    @Test
    public void read() throws IOException {
        String input = "Not tokenizeable";
        var sut = new JsonTokenReader(new StringReader(input));
        var bufferedReader = new BufferedReader(sut);
        assertEquals("Not tokenizeable", bufferedReader.readLine());
    }

    @Test
    public void read2() throws IOException {
        String input = "35Not tokenizeable";
        var sut = new JsonTokenReader(new StringReader(input));
        sut.readToken();
        var bufferedReader = new BufferedReader(sut);
        assertEquals("Not tokenizeable", bufferedReader.readLine());
    }

}