package com.mikan.json;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.*;

public class TokenizerTest {

    @Test
    public void testTokenize1() throws IOException {
        final String initial = """
                {"test1":"result1","test2":"result2","test3":["content1","content2","content3"]}""";

        final List<Token> result = Tokenizer.tokenize(new JsonTokenReader(new StringReader(initial)).readAllTokens());
        final List<Token> want = List.of(
                new Token("{"),
                new Token("\"test1\""),
                new Token(":"),
                new Token("\"result1\""),
                new Token(","),
                new Token("\"test2\""),
                new Token(":"),
                new Token("\"result2\""),
                new Token(","),
                new Token("\"test3\""),
                new Token(":"),
                new Token("["),
                new Token("\"content1\""),
                new Token(","),
                new Token("\"content2\""),
                new Token(","),
                new Token("\"content3\""),
                new Token("]"),
                new Token("}")
        );

        assertEquals(want, result);

    }

    @Test
    public void testTokenize2() throws IOException {
        final String initial = """
                {"test1":"result1","test3":[{"inner1":"inner2"},{"inner3":[]}]}""";

        final List<Token> result = Tokenizer.tokenize(new JsonTokenReader(new StringReader(initial)).readAllTokens());
        final List<Token> want = List.of(
                new Token("{"),
                new Token("\"test1\""),
                new Token(":"),
                new Token("\"result1\""),
                new Token(","),
                new Token("\"test3\""),
                new Token(":"),
                new Token("["),
                new Token("{"),
                new Token("\"inner1\""),
                new Token(":"),
                new Token("\"inner2\""),
                new Token("}"),
                new Token(","),
                new Token("{"),
                new Token("\"inner3\""),
                new Token(":"),
                new Token("["),
                new Token("]"),
                new Token("}"),
                new Token("]"),
                new Token("}")
        );

        assertEquals(want, result);

    }

    @Test
    public void testTokenize3() throws IOException {
        final String initial = """
                {"test1":
                "result1",
                "test3":[
                {"inner1":"inner2"},
                {"inner3":[]}]}""";


        final List<Token> result = Tokenizer.tokenize(new JsonTokenReader(new StringReader(initial)).readAllTokens());
        final List<Token> want = List.of(
                new Token("{"),
                new Token("\"test1\""),
                new Token(":"),
                new Token("\"result1\""),
                new Token(","),
                new Token("\"test3\""),
                new Token(":"),
                new Token("["),
                new Token("{"),
                new Token("\"inner1\""),
                new Token(":"),
                new Token("\"inner2\""),
                new Token("}"),
                new Token(","),
                new Token("{"),
                new Token("\"inner3\""),
                new Token(":"),
                new Token("["),
                new Token("]"),
                new Token("}"),
                new Token("]"),
                new Token("}")
        );

        assertEquals(want, result);

    }

    @Test
    public void testTokenize4() throws IOException {
        final String initial = """
                {"test1":-3.2e5,"test2":5E10,"test3":[true,"content2",null ]}""";
        final List<Token> result = Tokenizer.tokenize(new JsonTokenReader(new StringReader(initial)).readAllTokens());
        final List<Token> want = List.of(
                new Token("{"),
                new Token("\"test1\""),
                new Token(":"),
                new Token("-3.2e5"),
                new Token(","),
                new Token("\"test2\""),
                new Token(":"),
                new Token("5E10"),
                new Token(","),
                new Token("\"test3\""),
                new Token(":"),
                new Token("["),
                new Token("true"),
                new Token(","),
                new Token("\"content2\""),
                new Token(","),
                new Token("null"),
                new Token("]"),
                new Token("}")
        );
        assertEquals(want, result);
    }

    @Test(expected = NumberFormatException.class)
    public void illegalTokens() throws IOException {
        Tokenizer.tokenize(new JsonTokenReader(new StringReader("\"\\u123\"")).readAllTokens());
    }

}