package hr.fer.zemris.java.custom.scripting.parser;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import hr.fer.zemris.java.custom.scripting.nodes.DocumentNode;
import hr.fer.zemris.java.custom.scripting.nodes.ForLoopNode;
import hr.fer.zemris.java.custom.scripting.nodes.TextNode;



public class SmartScriptParserTest {
	
	@Test
	public void test() {

		SmartScriptParser parser = new SmartScriptParser("This is sample text.\r\n" + 
				"{$ FOR i 1 10 1 $}\r\n" + 
				"This is {$= i $}-th time this message is generated.\r\n" + 
				"{$END$}\r\n" + 
				"{$FOR i 0 10 2 $}\r\n" + 
				"sin({$=i$}^2) = {$= i i * @sin \"0.000\" @decfmt $}\r\n" + 
				"{$END$}");
		DocumentNode node=parser.getDocumentNode();
		TextNode a=(TextNode)node.getChild(0);
		assertEquals("This is sample text.\r\n",a.getText());
		ForLoopNode b=(ForLoopNode)node.getChild(1);
		assertEquals("i",b.getVariable().asText());
		
		


	}

}
