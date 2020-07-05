package com.mikan.json;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void testParseObject() throws IOException {

        final String input = """
                {"test1":"result1","test2":"result2","test3":
                ["content1","content2","content3"]}""";
        final JsonObject have = Parser.parseObject(Tokenizer.tokenize(new JsonTokenReader(new StringReader(input)).readAllTokens()));
        final JsonObject want = new JsonObject();

        final JsonList contentList = new JsonList();
        contentList.getContents().add(new JsonString("content1"));
        contentList.getContents().add(new JsonString("content2"));
        contentList.getContents().add(new JsonString("content3"));

        want.getContents().put("test1", new JsonString("result1"));
        want.getContents().put("test2", new JsonString("result2"));
        want.getContents().put("test3", contentList);

        assertEquals(want, have);
    }

    @Test
    public void testParseObject2() throws IOException {

        final String initial = """
                {"test1":"result1","test3":[{"inner1":"inner2"},{"inner3":[]}]}""";

        final JsonObject have = Parser.parseObject(Tokenizer.tokenize(new JsonTokenReader(new StringReader(initial)).readAllTokens()));
        final JsonObject want = new JsonObject();
        final JsonObject object1 = new JsonObject();
        final JsonObject object2 = new JsonObject();
        object1.getContents().put("inner1", new JsonString("inner2"));
        object2.getContents().put("inner3", new JsonList());

        final JsonList contentList = new JsonList();
        contentList.getContents().add(object1);
        contentList.getContents().add(object2);
        want.getContents().put("test1", new JsonString("result1"));
        want.getContents().put("test3", contentList);


        assertEquals(want, have);
    }

    @Test
    public void testParseObject3() throws IOException {

        final String initial = """
                {"test1":3.2e4,"test3":[{"inner1":"inner2"},{"inner3":[]}]}""";

        final JsonObject have = Parser.parseObject(Tokenizer.tokenize(new JsonTokenReader(new StringReader(initial)).readAllTokens()));
        final JsonObject want = new JsonObject();
        final JsonObject object1 = new JsonObject();
        final JsonObject object2 = new JsonObject();
        object1.getContents().put("inner1", new JsonString("inner2"));
        object2.getContents().put("inner3", new JsonList());

        final JsonList contentList = new JsonList();
        contentList.getContents().add(object1);
        contentList.getContents().add(object2);
        want.getContents().put("test1", new JsonNumber(32000));
        want.getContents().put("test3", contentList);
        System.out.println(have);
    }

    @Test
    public void testParseObject4() throws IOException {

        final String input = """
                {"test1":"result1","test2":"result2","test3":
                [56,0,-3.5e-2]}""";
        final JsonObject have = Parser.parseObject(Tokenizer.tokenize(new JsonTokenReader(new StringReader(input)).readAllTokens()));
        final JsonObject want = new JsonObject();

        final JsonList contentList = new JsonList();
        contentList.getContents().add(new JsonNumber(56));
        contentList.getContents().add(new JsonNumber(0));
        contentList.getContents().add(new JsonNumber(-0.035));

        want.getContents().put("test1", new JsonString("result1"));
        want.getContents().put("test2", new JsonString("result2"));
        want.getContents().put("test3", contentList);

        assertEquals(want, have);
    }

    @Test
    public void testParseObject5() throws IOException {

        final String input = """
                {"te\\nst1":"res\\rult1","te\\tst2":"result2","test3":
                [56,0,-3.5e-2]}""";
        final JsonObject have = Parser.parseObject(Tokenizer.tokenize(new JsonTokenReader(new StringReader(input)).readAllTokens()));
        final JsonObject want = new JsonObject();

        final JsonList contentList = new JsonList();
        contentList.getContents().add(new JsonNumber(56));
        contentList.getContents().add(new JsonNumber(0));
        contentList.getContents().add(new JsonNumber(-0.035));

        want.getContents().put("te\nst1", new JsonString("res\rult1"));
        want.getContents().put("te\tst2", new JsonString("result2"));
        want.getContents().put("test3", contentList);

        assertEquals(want, have);
    }

    @Test
    public void testParseObject6() throws IOException {

        final String input = """
                {"te\\fst1":"res\\"ult1","te\\bst2":"result2","test3":
                [56,0,-3.5e-2]}""";
        final JsonObject have = Parser.parseObject(Tokenizer.tokenize(new JsonTokenReader(new StringReader(input)).readAllTokens()));
        final JsonObject want = new JsonObject();

        final JsonList contentList = new JsonList();
        contentList.getContents().add(new JsonNumber(56));
        contentList.getContents().add(new JsonNumber(0));
        contentList.getContents().add(new JsonNumber(-0.035));

        want.getContents().put("te\fst1", new JsonString("res\"ult1"));
        want.getContents().put("te\bst2", new JsonString("result2"));
        want.getContents().put("test3", contentList);

        assertEquals(want, have);
    }

    @Test
    public void testParseObject7() throws IOException {

        final String input = """
                {"te\\\\st1":"res\\u00C4ult1","te\\/st2":"result2","test3":
                [56,0,-3.5e-2]}""";
        final JsonObject have = Parser.parseObject(Tokenizer.tokenize(new JsonTokenReader(new StringReader(input)).readAllTokens()));
        final JsonObject want = new JsonObject();

        final JsonList contentList = new JsonList();
        contentList.getContents().add(new JsonNumber(56));
        contentList.getContents().add(new JsonNumber(0));
        contentList.getContents().add(new JsonNumber(-0.035));

        want.getContents().put("te\\st1", new JsonString("res\u00C4ult1"));
        want.getContents().put("te/st2", new JsonString("result2"));
        want.getContents().put("test3", contentList);

        assertEquals(want, have);
    }

    @Test
    public void testParseObject8() throws IOException {

        final String input = """
                {"test1":true,"test2":false,"test3":
                [56,null,-3.5e-2]}""";
        final JsonObject have = Parser.parseObject(Tokenizer.tokenize(new JsonTokenReader(new StringReader(input)).readAllTokens()));
        final JsonObject want = new JsonObject();

        final JsonList contentList = new JsonList();
        contentList.getContents().add(new JsonNumber(56));
        contentList.getContents().add(new JsonNull());
        contentList.getContents().add(new JsonNumber(-0.035));

        want.getContents().put("test1", new JsonBool(true));
        want.getContents().put("test2", new JsonBool(false));
        want.getContents().put("test3", contentList);

        assertEquals(want, have);
    }

    @Test
    public void testParseObject9() throws IOException {

        final String input = """
                {}""";
        final JsonObject have = Parser.parseObject(Tokenizer.tokenize(new JsonTokenReader(new StringReader(input)).readAllTokens()));
        final JsonObject want = new JsonObject();

        assertEquals(want, have);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseObjectIllegal() {

        Parser.parseNextElement(List.of(new Token("invalid")));
    }

}