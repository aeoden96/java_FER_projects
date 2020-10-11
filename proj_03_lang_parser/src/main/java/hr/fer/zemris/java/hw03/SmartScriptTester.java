package hr.fer.zemris.java.hw03;

import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParser;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;
import hr.fer.zemris.java.custom.scripting.elems.Element;
import hr.fer.zemris.java.custom.scripting.elems.ElementFunction;
import hr.fer.zemris.java.custom.scripting.elems.ElementString;
import hr.fer.zemris.java.custom.scripting.nodes.DocumentNode;
import hr.fer.zemris.java.custom.scripting.nodes.EchoNode;
import hr.fer.zemris.java.custom.scripting.nodes.ForLoopNode;
import hr.fer.zemris.java.custom.scripting.nodes.TextNode;

import java.nio.file.Files;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

/**
 * Glavna klasa koja služi za testiranje parsera (i njegvog lexera).Klasa sadrži
 * main funkciju u kojoj se izvode testovi parsera.Kao argument komandne linije
 * se dobiva ime datoteke u kojoj se nalaze source kodovi koje će parser primiti
 * 
 * @author Mateo
 *
 */
public class SmartScriptTester {
	/**
	 * Glavna main funkcija. Prima kao argument(jedan) putanju do source koda ,koji
	 * će se dalje proslijediti parseru.
	 * 
	 * @param args
	 *            putanja do odgovarajućih source kodova koji se nalaze u resources
	 *            mapi
	 * @throws IOException
	 *             main baca iznimku u slučaju da se file nemože naći
	 */
	public static void main(String[] args) throws IOException {
		String filepath;
		String docBody;
		filepath = args[0];

		docBody = new String(Files.readAllBytes(Paths.get(filepath)), StandardCharsets.UTF_8);

		SmartScriptParser parser = null;
		try {
			parser = new SmartScriptParser(docBody);
		} catch (SmartScriptParserException e) {
			System.out.println("Unable to parse document!" + e.getMessage());
			System.exit(-1);
		}
		DocumentNode document = parser.getDocumentNode();
		createOriginalDocumentBody(document);
		

	}

	/**
	 * Funkcija vraća String koji je prošao korz parser ,dakle argument je stablo iz
	 * kojeg će funkcija natrag vratiti orginalni source kod.
	 * 
	 * @param node
	 *            korijen stabla u kojem je pohranjen dokument.
	 * @return vraća se string koji bi trebao biti jednak orginalnom source kodu
	 *         (vjerojatno to nije slučaj jer se prilikom parsiranja izgube podaci o
	 *         svim pozicijama praznina u dokumentu)
	 */
	public static String createOriginalDocumentBody(DocumentNode node) {
		String mainDoc = "";

		for (int i = 0; i < node.numberOfChildren(); i++) {
			if (node.getChild(i) instanceof TextNode) {
				
				TextNode child = (TextNode) node.getChild(i);
				System.out.format(child.getText());
			} else if (node.getChild(i) instanceof EchoNode) {

				System.out.format(printEcho(mainDoc, (EchoNode) node.getChild(i)));
			} else if (node.getChild(i) instanceof ForLoopNode) {

				System.out.format(printLoop(mainDoc, (ForLoopNode) node.getChild(i)));
			}

		}
		return mainDoc;

	}
	/**
	 * help funkcija za print echo naredbe
	 * @param mainDoc
	 * @param node
	 * @return
	 */
	private static String printEcho(String mainDoc, EchoNode node) {

		Element[] elm = node.getElements();
		System.out.format("{$=");
		for (int i = 0; i < elm.length; i++) {
			if (elm[i] == null)
				break;
			if (elm[i] instanceof ElementFunction) {
				System.out.format("@" + elm[i].asText() + " ");
			} else if (elm[i] instanceof ElementString) {
				System.out.format("\"" + elm[i].asText() + "\" ");

			} else {
				System.out.format(elm[i].asText() + " ");


				
			}
		}
		System.out.format("$}");

		return mainDoc;
	}
/**
 * pomocna funkcija za ispis loop petlje
 * @param mainDoc
 * @param node
 * @return
 */
	private static String printLoop(String mainDoc, ForLoopNode node) {

		System.out.format("{$ FOR " + node.getVariable().asText() + " " + node.getStartExpression().asText() + " "
				+ node.getEndExpression().asText() + " " + node.getStepExpression().asText() + " $}");
		for (int i = 0; i < node.numberOfChildren(); i++) {
			if (node.getChild(i) instanceof TextNode) {
				TextNode child = (TextNode) node.getChild(i);
				System.out.format(child.getText());

			} else if (node.getChild(i) instanceof EchoNode) {
				System.out.format(printEcho(mainDoc, (EchoNode) node.getChild(i)));
			}

		}
		System.out.format("{$ END $}");
		return mainDoc;
	}

}