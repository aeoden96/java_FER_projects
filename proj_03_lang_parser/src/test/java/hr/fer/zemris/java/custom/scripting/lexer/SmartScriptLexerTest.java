package hr.fer.zemris.java.custom.scripting.lexer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import hr.fer.zemris.java.custom.scripting.lexer.Token;
import hr.fer.zemris.java.custom.scripting.lexer.TokenType;
import hr.fer.zemris.java.custom.scripting.lexer.LexerState;

import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;

import hr.fer.zemris.java.custom.scripting.lexer.SmartScriptLexer;

public class SmartScriptLexerTest {
	@Test
	public void testNotNull() {
		SmartScriptLexer lexer = new SmartScriptLexer("");

		assertNotNull("Token was expected but null was returned.", lexer.nextToken());
	}

	@Test(expected = SmartScriptParserException.class)
	public void testNullInput() {
		// must throw!
		new SmartScriptLexer(null);
	}

	@Test
	public void testEmpty() {
		SmartScriptLexer lexer = new SmartScriptLexer("");

		assertEquals("Empty input must generate only EOF token.", TokenType.EOF, lexer.nextToken().getType());
	}

	@Test
	public void testGetReturnsLastNext() {
		// Calling getToken once or several times after calling nextToken must return
		// each time what nextToken returned...
		SmartScriptLexer lexer = new SmartScriptLexer("");

		Token token = lexer.nextToken();
		assertEquals("getToken returned different token than nextToken.", token, lexer.getToken());
		assertEquals("getToken returned different token than nextToken.", token, lexer.getToken());
	}

	@Test
	public void escape() {
		// When input is only of spaces, tabs, newlines, etc...
		SmartScriptLexer lexer = new SmartScriptLexer("   \r\n\t     {$");

		assertEquals("   \r\n\t     ", lexer.nextToken().getValue().asText());
	}

	@Test
	public void escape2() {

		SmartScriptLexer lexer = new SmartScriptLexer("   abcd \\{\\$");

		assertEquals("   abcd \\{\\$", lexer.nextToken().getValue().asText());
	}

	@Test
	public void escape3() {

		SmartScriptLexer lexer = new SmartScriptLexer(" \\\\Marko{$Ivan.");

		assertEquals(" \\\\Marko", lexer.nextToken().getValue().asText());
	}

	@Test
	public void escape4() {

		SmartScriptLexer lexer = new SmartScriptLexer("A tag follows {$= \"Joe \\\"Long\\\" Smith\"$}.");

		assertEquals("A tag follows ", lexer.nextToken().getValue().asText());
		assertEquals(TokenType.TAGSTART, lexer.nextToken().getType());

		lexer.setState(LexerState.TAG);
		assertEquals("=", lexer.nextToken().getValue().asText());
		assertEquals("Joe \\", lexer.nextToken().getValue().asText());
		assertEquals("Long\\\"", lexer.nextToken().getValue().asText());
		assertEquals("Smith\"", lexer.nextToken().getValue().asText());
		assertEquals(TokenType.TAGEND, lexer.nextToken().getType());
		


	}
	@Test
	public void escape5() {

		SmartScriptLexer lexer = new SmartScriptLexer("Example \\{$=1$}. ");
		lexer.setState(LexerState.TEXT);

		assertEquals("Example \\", lexer.nextToken().getValue().asText());
		assertEquals(TokenType.TAGSTART, lexer.nextToken().getType());

		lexer.setState(LexerState.TAG);
		assertEquals("=", lexer.nextToken().getValue().asText());
		assertEquals("1", lexer.nextToken().getValue().asText());
		
		assertEquals(TokenType.TAGEND, lexer.nextToken().getType());
		


	}

}
