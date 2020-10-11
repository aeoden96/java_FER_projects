package hr.fer.zemris.java.custom.scripting.parser;

import java.util.Arrays;

import hr.fer.zemris.java.custom.scripting.elems.Element;
import hr.fer.zemris.java.custom.scripting.elems.ElementVariable;
import hr.fer.zemris.java.custom.scripting.lexer.LexerState;
import hr.fer.zemris.java.custom.scripting.lexer.SmartScriptLexer;
//import hr.fer.zemris.java.custom.scripting.lexer.Token;
import hr.fer.zemris.java.custom.scripting.lexer.TokenType;
import hr.fer.zemris.java.custom.scripting.nodes.DocumentNode;
import hr.fer.zemris.java.custom.scripting.nodes.EchoNode;
import hr.fer.zemris.java.custom.scripting.nodes.ForLoopNode;
import hr.fer.zemris.java.custom.scripting.nodes.Node;
import hr.fer.zemris.java.custom.scripting.nodes.TextNode;;

/**
 * Glavna parser klasa. Tu se odvija procesiranje na višem apstraktnom nivou(za
 * razliku od lexera) koji pomoću tokena koje dobiva od lexera stvara
 * odgovarajuće nodove ,koje će kasnije složiti u stablo. Takvo stablo je
 * ekvivalent orginalnom source kodu, i može se jednostavno rekonstruirati iz
 * njega. Sam korisnik ne upravlja procesom parsiranja (jer se parsiranje
 * pokreće već konstruktoru), korisnik kao rezultat dobiva korijen (node)
 * dokumenta.Prilikom stvaranja stabla,nužno je koristiti i neku vrstu stoga, u
 * koju će se spremati node u kojem se trenutno nalazimo.
 * 
 * @author Mateo
 *
 */
public class SmartScriptParser {
	/**
	 * Parser koristi jednu instancu lexera u svojoj klasi ,kojem prosljeđuje
	 * primljeni tekst
	 */
	private SmartScriptLexer lexer;
	/**
	 * u ovaj bi se node na kraju trebalo spremiti stablo dokumenta, koje će se
	 * vratiti natrag korisniku
	 */
	private DocumentNode node;
	/**
	 * Koristi se instanca stoga u kojoj se sprema trenutni nodovi,radi (lakše
	 * kategorizacije tokena) Stog se ne ispisuje,isključivo za internu upotrebu
	 */
	ObjectStack documentStack;

	/**
	 * Jedini konstruktor u parseru.Prima kao argument String koji sadrži source kod
	 * dokumenta,koji se naknadno proslijeđje lexeru(koji se tim
	 * inicijalizira).Samim pokretanjem konstrktora,tj. inicijalizacijom parsera
	 * korisnik pokreće porces parsiranja,ne postoje funckije za upravljanjem toka
	 * izvođenja parsera
	 * 
	 * @param documentBody
	 *            Izvorni kod našeg dokumenta,parser ga prima i ne zadržava ,već
	 *            prosljeđuje svojoj instanci lexera
	 */
	public SmartScriptParser(String documentBody) {
		lexer = new SmartScriptLexer(documentBody);
		documentStack = new ObjectStack();
		node = new DocumentNode();
		node = beginParsing();

	}

	// vraca glavni DocumentNode ,tj korijen cijelog stabla dokumenta
	public DocumentNode getDocumentNode() {
		return node;
	}

	/**
	 * interna funkcija za lakše dolaženje do tipa trenutnog tokena . "Trenutnog"
	 * jer je funkcija sama po sebi pasivna,tj. ne mijenja svoje argumente ni druge
	 * varijable
	 * 
	 * @param type
	 *            prima kao argument neki tip iz enumeracije TokenType
	 * 
	 * @return vraća bool vrijednost tipa tokena koja se uspoređuje s danim tipom
	 *         enumeracije TokenType
	 */
	private boolean tokenType(TokenType type) {
		return lexer.getToken().getType() == type;
	}

	/**
	 * interna funkcija za lakše dolaženje do vrijednosti trenutnog tokena .
	 * "Trenutnog" jer je funkcija sama po sebi pasivna,tj. ne mijenja svoje
	 * argumente ni druge varijable
	 * 
	 * @return vraća trenutnu vrijednost tokena ,tj. objekt tipa Element
	 */
	private String getTokenValue() {
		return lexer.getToken().getValue().asText();
	}

	/**
	 * Kada parser dođe do petlje,tada ova funkcija sprema argumente petlje, te ju
	 * oblikuje u jedan node. Taj se node potom salje na stack kao trenutni parent
	 * element ,u koji ćemo stavljat sve nodove koji dolaze nakon FOR noda,a koji su
	 * prije END noda Funkcija ne šalje ni ne prima ništa.
	 */
	private void processLoop() {
		Element[] elementsToBeReturned = new Element[4];
		int i = 0;

		// prolazi kroz tokene FOR taga
		while (lexer.nextToken().getType() != TokenType.TAGEND) {
			if (tokenType(TokenType.EOF) || tokenType(TokenType.TAGSTART) || tokenType(TokenType.TAGNAME)) {
				terminate("FOR: found wrong tag");
			}

			elementsToBeReturned[i] = lexer.getToken().getValue();
			i++;
		}
		if (Arrays.asList(elementsToBeReturned).contains(null)) {

			terminate("FOR: format.");
		} else if (!(elementsToBeReturned[0] instanceof ElementVariable)) {
			System.out.println(elementsToBeReturned[0].asText() + elementsToBeReturned[1].asText()
					+ elementsToBeReturned[2].asText() + elementsToBeReturned[3].asText());
			terminate("FOR: first element is not a variable.");
		}
		ForLoopNode newNode = new ForLoopNode((ElementVariable) elementsToBeReturned[0], elementsToBeReturned[1],
				elementsToBeReturned[2], elementsToBeReturned[3]);
		addNodeAsChild(newNode);
		documentStack.push(newNode);
		lexer.setState(LexerState.TEXT);
	}

	/**
	 * Funkcija koja ,kada se uoči END token ,mora pobrinuti da se FOR petlja
	 * zatvori.Zato iz stacka brise zadnju FOR petlju
	 */
	private void closeOpenedLoop() {
		if (lexer.nextToken().getType() != TokenType.TAGEND) {
			terminate("END: expected nothing, but found something ");
		}
		if (documentStack.isEmpty()) {
			terminate("END: no FOR loop in stack to end.");
		}
		lexer.setState(LexerState.TEXT);
		documentStack.pop();

	}

	/**
	 * u slučaju echo taga ,funckija se poziva i potom sve argumente kategorizira i
	 * sprema u jedan node, funkcija ne prima i ne vraća ništa, jedino koristi
	 * addNodeAsChild() kako bi napravljeni node dodao odgovarajućem parentu
	 */
	private void processEcho() {
		Element[] elementsToBeReturned = new Element[1];
		int i = 0;
		Element temp;
		while (lexer.nextToken().getType() != TokenType.TAGEND) {
			temp = lexer.getToken().getValue();
			if (tokenType(TokenType.EOF) || tokenType(TokenType.TAGSTART) || tokenType(TokenType.TAGNAME)) {
				terminate("found wrong tag in Echo");
			}
			
			elementsToBeReturned = Arrays.copyOf(elementsToBeReturned, i +1);
			elementsToBeReturned[i] = temp;
			i++;

		}
		lexer.setState(LexerState.TEXT);
		EchoNode newNode = new EchoNode(elementsToBeReturned);
		addNodeAsChild(newNode);

	}

	/**
	 * Pomoćna funkcija koja proslijeđuje odgovarajuću poruku o iznimci. Funkcija je
	 * napravljena zbog čitljivosti koda,ne vraća ništa
	 * 
	 * @param value
	 *            prima se poruka o grešci u obliku Stringa
	 * @throws SmartScriptParserException
	 *             baca se iznimka u runtimeu
	 */
	private void terminate(String value) {
		throw new SmartScriptParserException(value);
	}

	/**
	 * Funkcija kojoj druge funkcije proslijeđuju nodove koje je potrebno staviti
	 * kao child nekom parentu iz stacka.Može se neograničeno puta zvati Baca
	 * iznimku ukoliko se pokuša pozvati nad praznim stackom.
	 * 
	 * @param newChild
	 *            node koji se dodaje parent elementu pohranjenom na stacku
	 */
	private void addNodeAsChild(Node newChild) {
		if (documentStack.isEmpty())
			terminate("Tried to pop() in empty stack");
		Node parent = (Node) documentStack.pop();
		parent.addChildNode(newChild);
		documentStack.push(parent);
	}

	/**
	 * Glavna funkcija za parsiranje tagova.Ona poziva sve ostale pomoćne funkcije
	 * koje se bave tagovima u parseru.ne vraća ni ne prima ništa Vraća iznimku u
	 * slučaju da je tag krivo konstruiran,dakle ako se nemože naći odgovarajući
	 * naziv TAGa unutar samog TAGa
	 */
	private void parseTag() {
		lexer.nextToken();
		if (!tokenType(TokenType.TAGNAME)) {
			terminate("entered a tag,but tagname wasn't there");
		} else if (getTokenValue().equals("FOR")) {
			processLoop();
		} else if (getTokenValue().equals("END")) {
			closeOpenedLoop();
		} else if (getTokenValue().equals("=")) {
			processEcho();
		} else {
			terminate("undefined tagname, it was: " + getTokenValue());
		}
	}

	/**
	 * Glavna funkcija za generalno parsiranje.Ona se poziva direktno iz
	 * konstruktora. Brine se o načinu rada lexera i mijenja ga po potrebi.Također
	 * po potrebi zove funkciju za parsiranje TAGova. Funkcija vraća DocumentNode
	 * element, tj. korijen stabla u koje mje pohranjen cijeli dokument
	 * 
	 * @return korijen stabla u koje mje pohranjen cijeli dokument
	 */
	private DocumentNode beginParsing() {
		documentStack.push(node);
		while (true) {
			lexer.nextToken();

			if (tokenType(TokenType.EOF))
				break;

			lexer.setState(LexerState.TEXT);

			if (tokenType(TokenType.TAGSTART)) {
				lexer.setState(LexerState.TAG);
				parseTag();

			} else if (tokenType(TokenType.TEXT)) {
				TextNode tempNode = new TextNode(lexer.getToken().getValue().asText());
				addNodeAsChild(tempNode);

			} else {
				terminate("undefined combination of tokens recieved");
			}

		}
		return node;
	}

}
