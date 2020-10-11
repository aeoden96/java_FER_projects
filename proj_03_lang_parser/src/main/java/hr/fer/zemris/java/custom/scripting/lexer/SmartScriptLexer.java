package hr.fer.zemris.java.custom.scripting.lexer;

import hr.fer.zemris.java.custom.scripting.elems.ElementConstantDouble;
import hr.fer.zemris.java.custom.scripting.elems.ElementConstantInteger;
import hr.fer.zemris.java.custom.scripting.elems.ElementFunction;
import hr.fer.zemris.java.custom.scripting.elems.ElementOperator;
import hr.fer.zemris.java.custom.scripting.elems.ElementString;
import hr.fer.zemris.java.custom.scripting.elems.ElementVariable;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;

/**
 * Lekser se koristi da bi dobiveni String razbio na prije definirane tokene.
 * Svaki token se sasoji od TokenType-a i Elementa .Kada je token nađen
 * ,proslijeđuje se parseru. Lekser radi u dvije vrste rada: u prvoj sve znakove
 * baca u string dok ne naleti na odgovarajući niz znakova koji predstavljaju
 * početak TAG-a ,tada mu parser daje naredbu da prijeđe u TAG način, gdje svaki
 * element unutar TAG-a kategorizira, smješta u odgovarajuć token zajedno s
 * TokenType koji se prosljeđuje natrag parseru. Kada lekser proslijedi token
 * parseru s TokenType-om EOF ,tada parser zna da nema više razpoloživog teksta
 * za procesirati.
 * 
 * @author Mateo
 *
 */
public class SmartScriptLexer {
	/**
	 * ulazni tekst.Tijekom izvođenja,tekst se ne mijenja, mijenja se samo pokazivač
	 * na poziciju u tekstu
	 */
	private String data; // ulazni tekst
	/**
	 * trenutni token, ostaje fiksan sve dok sljedeći token ne zauzme njegovo mjesto
	 * ,tj. pozivanjem nextToken() fje
	 */
	private Token currentToken; // trenutni token
	/**
	 * trenutni pokazivač na prvi neobrađeni znak u tekstu.
	 */
	private int currentIndex;
	/**
	 * Enumeracija koja gvori lexeru u kojem je načinu rada. TAG -> lexer čita
	 * tagove za redom i porccesira ih ,dok ne dođe do niza znakova koji
	 * predstavljaju kraj TAG-a TEXT-> lexer čita tekst, sve znakove ,praznine i
	 * brojeve pretvara u string ,sve do pojave niza znakova koji predstavljaju
	 * početak TAG-a ,ili do kraja dokumenta
	 */
	private LexerState currentState;

	/**
	 * jedini konstruktor za ovu klasu, prima kao argument polazni tekst koji je
	 * potrebno procesirati. Lexer također postavlja default način rada kao TEXT.
	 * Tekst nesmije biti null.
	 * 
	 * @param text
	 *            ulazni tekst koji je potrebno procesirati
	 * @throws SmartScriptParserException
	 *             konstruktor baca iznimku u slučaju da se proslijedi null
	 *             vrijednost
	 */
	public SmartScriptLexer(String text) {
		if (text == null)
			throw new SmartScriptParserException("ulazni tekst nesmije biti null");
		currentIndex = 0;
		data = text;
		currentToken = null;
		currentState = LexerState.TEXT;

	}

	/**
	 * funkcija vraća trenutni token. Funcija je pasivna, ne mijenja trenutni tok
	 * programa.
	 * 
	 * @return vraća se trenutni token
	 */
	public Token getToken() {
		return currentToken;
	}

	/**
	 * Javna funcija mijenja trenutni tok programa,dohvaća sljedeći token u
	 * nizu.Također postavlja novu vrijednost za trenutni token.
	 * 
	 * @return novi token u nizu
	 */
	public Token nextToken() {

		return currentToken = extractToken();
	}

	/**
	 * Javna funkcija koja se poziva da se promijeni način rada lexera. 2.su načina
	 * rada :TEXT i TAG . u TEXT načinu lexer prolazi kroz dokument sve dok ne naiđe
	 * na niz znakova (koji su zaseban token) ,i tada je na korisniku da promijeni
	 * način rada u TAG načinu čitaju se pojedini argumenti tag-a , kategoriziraju
	 * se i šalju se u obliku tokena korisniku
	 * 
	 * @param state
	 *            dopušteni načini rada su TEXT i TAG . value nesmije biti null
	 * @throws SmartScriptParserException
	 *             u slučaju da se za način rada proslijedi null, funkcija vraća
	 *             iznimku
	 */
	public void setState(LexerState state) {
		if (state == null)
			throw new SmartScriptParserException("LexerState nemoze biti null");
		currentState = state;
	}

	/**
	 * Pomoćna funckija ,ali zapravo se u njoj glavno procesiranje dešava. Ovisno o
	 * tome koji je način rada uključen ,funkcija prolazi korz tekst i konstruira
	 * tokene koje će dallje proslijediti glavnoj funkciji.Funkcija ne prima nikakve
	 * argumente
	 * 
	 * @return vraća se idući token u nizu
	 */
	private Token extractToken() {

		if (LexerState.TEXT == currentState) {
			int a = data.indexOf("{$", currentIndex);
			String newTextValue;
		
			if (a == currentIndex) {
				currentIndex += 2;
				return new Token(TokenType.TAGSTART, null);
			}
			
			if (a == -1 && data.length() - currentIndex > 0) {
				a = data.length();
			} else if (a == -1) {
				return new Token(TokenType.EOF, null);
			}
			newTextValue = data.substring(currentIndex, a);
			ElementString tempElement = new ElementString(newTextValue);
			currentIndex = a;

			return new Token(TokenType.TEXT, tempElement);
		} else {
			int a = data.indexOf("$}", currentIndex);

			String tempSubstr = data.substring(currentIndex, a);
			String trimmedStr = tempSubstr.replaceAll("^\\s+", "");
			currentIndex = currentIndex + tempSubstr.length() - trimmedStr.length();
			if (a == currentIndex) {
				currentIndex += 2;
				return new Token(TokenType.TAGEND, null);
			}
			return processTag(data.substring(currentIndex, a));
		}
	}

	/**
	 * u slučaju da se tadi o TAG načinu rada, ova funkcija prolazi kroz cijeli tag
	 * i vraća elemente TAGa.Kao ulaz dobiva izrezani string za lakše
	 * procesiranje,koji sadrži samo elemente trenutnog taga (dakle bez graničnih
	 * nizova znakova koji predstavljaju promjenu načina rada)
	 * 
	 * @param tagInterior
	 *            izrezan substring koji funkcija procesira znak po znak i vraća ih
	 *            po redu
	 * @return vraća se treutni token koji se sastoji od elementa (djela TAG-a) i
	 *         vrste taga
	 * @throws SmartScriptParserException
	 *             ako u slučaju prosesiranja taga dođe do bilokakve greške u
	 *             prepoznavanju pojedinih elemenata ,funkcija vraća iznimku
	 *             SmartScriptParserException
	 */
	private Token processTag(String tagInterior) {

		//ako se radi o echo elementu
		if (tagInterior.charAt(0) == '=') {
			ElementVariable tempElement = new ElementVariable("=");
			Token tempToken = new Token(TokenType.TAGNAME, tempElement);
			currentIndex++;
			return tempToken;
		//ako je for petlja
		} else if (tagInterior.toLowerCase().indexOf("for") == 0) {
			ElementVariable tempElement = new ElementVariable("FOR");
			Token tempToken = new Token(TokenType.TAGNAME, tempElement);
			currentIndex += 3;

			return tempToken;
		//ako je kraj for petlje
		} else if (tagInterior.toLowerCase().indexOf("end") == 0) {
			ElementVariable tempElement = new ElementVariable("END");
			Token tempToken = new Token(TokenType.TAGNAME, tempElement);
			currentIndex += 3;
			return tempToken;
		// ako je funkcija unutar tag-a
		} else if (tagInterior.toLowerCase().indexOf("@") == 0) {
			if (!Character.isAlphabetic(tagInterior.charAt(1)))
				throw new SmartScriptParserException("function is invaild");
			int index = tagInterior.indexOf(" ");
			if (index == -1) {
				index = tagInterior.length();
			}
			String functionName = tagInterior.substring(1, index);
			ElementFunction tempElement = new ElementFunction(functionName);
			Token tempToken = new Token(TokenType.FUNCTION, tempElement);
			currentIndex += functionName.length() + 1;
			return tempToken;
		}
		// varijabla
		else if (Character.isAlphabetic(tagInterior.charAt(0))) {
			int index = tagInterior.indexOf(" ");
			if (index == -1) {
				index = tagInterior.length();
			}
			String variableName = tagInterior.substring(0, index);
			ElementVariable tempElement = new ElementVariable(variableName);
			Token tempToken = new Token(TokenType.VARIABLE, tempElement);
			currentIndex += variableName.length();
			return tempToken;

		}
		//ako je string element tag-a
		else if (tagInterior.charAt(0) == '"') {
			int index = tagInterior.indexOf('"', 1);
			if (index == -1)
				throw new SmartScriptParserException("string is invalid");
			String stringValue = tagInterior.substring(1, index);
			ElementString tempElement = new ElementString(stringValue);
			Token tempToken = new Token(TokenType.STRING, tempElement);
			currentIndex += stringValue.length() + 2;
			return tempToken;
		} 
		//ako je operator
		else if (tagInterior.charAt(0) == '+' || tagInterior.charAt(0) == '/' || tagInterior.charAt(0) == '^'
				|| tagInterior.charAt(0) == '*' || tagInterior.charAt(0) == '-') {

			ElementOperator tempElement = new ElementOperator(tagInterior.substring(0, 1));
			Token tempToken = new Token(TokenType.OPERATOR, tempElement);
			currentIndex++;
			return tempToken;
		} 
		//ako nije ništa ostalo,onda je broj
		else {
			int index = tagInterior.indexOf(' ');
			if (index == -1) {
				index = tagInterior.length();
			}
			if (tagInterior.indexOf(".") == -1) {
				int temp;
				try {
				temp = Integer.parseInt(tagInterior.substring(0, index));
				}
				catch(NumberFormatException e) {
					throw new SmartScriptParserException("tried to parse " + tagInterior.substring(0, index)+ " to int");
				}
				ElementConstantInteger tempElement = new ElementConstantInteger(temp);
				Token tempToken = new Token(TokenType.INT, tempElement);
				currentIndex += tagInterior.substring(0, index).length();
				return tempToken;
			} else if (tagInterior.indexOf(".") < index) {
				double temp;
				try {
				temp = Double.parseDouble(tagInterior.substring(0, index));
				}
				catch(NumberFormatException e) {
					throw new SmartScriptParserException("tried to parse " + tagInterior.substring(0, index)+ " to double");
				}
				ElementConstantDouble tempElement = new ElementConstantDouble(temp);
				Token tempToken = new Token(TokenType.DOUBLE, tempElement);
				currentIndex += tagInterior.substring(0, index).length();
				return tempToken;
			} else
				throw new SmartScriptParserException("error");

		}

	}

}
