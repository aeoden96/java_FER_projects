package hr.fer.zemris.java.hw03.prob1;

import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;

/**
 * Lekser se koristi da bi dobiveni String razbio na prije definirane tokene.
 * Svaki token se sasoji od TokenType-a i Objecta .Kada je token nađen
 * ,proslijeđuje se korisniku. Lekser radi u dvije vrste rada: u prvoj sve znakove
 * baca u string dok ne naleti na # koji predstavlja
 * početak BASIC naćina rada ,tada mu parser daje korisnik da prijeđe u BASIC način, gdje svaki
 * element unutar koda kategorizira, smješta u odgovarajuć token zajedno s
 * TokenType koji se prosljeđuje natrag parseru. Kada lekser proslijedi token
 * parseru s TokenType-om EOF ,tada parser zna da nema više razpoloživog teksta
 * za procesirati.
 * 
 * @author Mateo
 *
 */
public class Lexer {// leksicki anaizator
	/**
	 * ulazni tekst.Tijekom izvođenja,tekst se ne mijenja, mijenja se samo pokazivač
	 * na poziciju u tekstu
	 */
	private char[] data; // ulazni tekst
	/**
	 * trenutni token, ostaje fiksan sve dok sljedeći token ne zauzme njegovo mjesto
	 * ,tj. pozivanjem nextToken() fje
	 */
	private Token token; // trenutni token
	/**
	 * trenutni pokazivač na prvi neobrađeni znak u tekstu.
	 */
	private int currentIndex; // indeks prvog neobrađenog znaka
	/**
	 * Enumeracija koja gvori lexeru u kojem je načinu rada. BASIC -> lexer čita
	 * tagove za redom i porccesira ih ,dok ne dođe do niza znakova koji
	 * predstavljaju kraj TAG-a TEXT-> lexer čita tekst, sve znakove ,praznine i
	 * brojeve pretvara u string ,sve do pojave niza znakova koji predstavljaju
	 * početak TEXTa ,ili do kraja dokumenta
	 */
	private LexerState currentState = LexerState.BASIC;
	TokenType currentToken = null;

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
	public Lexer(String text) {
		if (text == null)
			throw new IllegalArgumentException("ulazni tekst nesmije biti null");
		data = text.toCharArray();
		currentIndex = 0;
		token = null;
		currentState = LexerState.BASIC;

	}
	/**
	 * fja briše bjeline s početka string;
	 */
	private void skipSpaces() {

		for (int i = currentIndex; i < data.length; i++) {
			if (Character.isWhitespace(data[i]))
				currentIndex++;
			else
				break;
		}
	}

	// generira i vraća sljedeći token
	// baca LexerException ako dođe do pogreške

	private boolean checkCurrentToken(TokenType token) {
		if (currentToken == null) {
			currentToken = TokenType.WORD;
			return true;
		}
		if (currentToken == token) {
			return true;
		}
		return false;

	}

	private long extractNumber() {
		long newTokenNumber = 0;
		while (currentIndex < data.length && Character.isDigit(data[currentIndex])) {

			if (newTokenNumber * 10 + (data[currentIndex] - '0') < 0) {
				throw new LexerException("ne stane u long");
			}
			newTokenNumber = newTokenNumber * 10 + (data[currentIndex] - '0');
			currentIndex++;
		}
		return newTokenNumber;
	}

	private char extractSymbol() {
		currentIndex++;
		return data[currentIndex - 1];
	}

	private String extractWord() {

		int startIndex = currentIndex;

		while (currentIndex < data.length && !Character.isWhitespace(data[currentIndex])) {
			if (data[currentIndex] == '#' && LexerState.EXTENDED == currentState) {

				break;
			} else if (Character.isLetter(data[currentIndex]) || LexerState.EXTENDED == currentState) {
				currentIndex++;
			}

			else
				break;
		}
		return new String(data, startIndex, currentIndex - startIndex);
	}

	public Token nextToken() {
		String newTokenValue = "";
		currentToken = null;
		// inicijaliziran na neku vrijednost
		if (token == null) {
			token = new Token(TokenType.SYMBOL, null);

		} else if (token.getType() == TokenType.EOF) {
			throw new LexerException("ne postoji iduci token,EOF je zadnji");
		}

		skipSpaces();

		if (currentIndex >= data.length) {
			return token = new Token(TokenType.EOF, null);

		}

		while (currentIndex < data.length && !Character.isWhitespace(data[currentIndex])) {

			if (data[currentIndex] == '\\') {

				if (!checkCurrentToken(TokenType.WORD))
					break;

				if (currentIndex + 1 >= data.length)
					throw new LexerException("Nedozvoljena kombinacija simbola");

				// ocekujem broj ili jos jedan znak '\'
				if (Character.isDigit(data[currentIndex + 1]) || data[currentIndex + 1] == '\\') {
					newTokenValue = newTokenValue + data[currentIndex + 1];
					currentIndex += 2;
					continue;
				} else
					throw new LexerException("Nedozvoljena kombinacija simbola");

			}

			else if (Character.isLetter(data[currentIndex])) {

				if (!checkCurrentToken(TokenType.WORD))
					break;

				newTokenValue = newTokenValue + extractWord();

			} else if (Character.isDigit(data[currentIndex])) {

				if (LexerState.EXTENDED == currentState) {
					if (!checkCurrentToken(TokenType.WORD))
						break;
					newTokenValue = newTokenValue + extractWord();
				} else {
					if (!checkCurrentToken(TokenType.NUMBER))
						break;
					long nextNumber = extractNumber();

					return new Token(TokenType.NUMBER, nextNumber);

				}

			} else {
				if (!checkCurrentToken(TokenType.SYMBOL))
					break;
				return new Token(TokenType.SYMBOL, extractSymbol());
			}

		} // while ends
			// jedino rijec nema returna u petlji
		if (currentToken == TokenType.WORD)
			return new Token(TokenType.WORD, newTokenValue);
		throw new LexerException("nešto nedefinirano se dogodilo");
	}

	// vraća zadnji generirani token; može se pozivati
	// više puta; ne pokreće generiranje sljedećeg tokena
	public Token getToken() {
		return token;
	}
	/**
	 * Namješta način rada lexer,deteljnije objašnjeno na početu klase	 
	 *  @param state
	 */
	public void setState(LexerState state) {
		if (state == null)
			throw new IllegalArgumentException("LexerState nemoze biti null");
		currentState = state;
	}
}