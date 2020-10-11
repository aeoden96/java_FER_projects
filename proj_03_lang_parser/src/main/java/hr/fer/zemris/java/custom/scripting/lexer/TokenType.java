package hr.fer.zemris.java.custom.scripting.lexer;
/**
 * Vrste mogućih tokena.
 * EOF- određuje kraj dokumenta
 * STRING-određuje vrijednost u navodnicima
 * INT- cijeli broj
 * DOUBLE-realni broj
 * OPERATOR-određuje jedan od operatora +,-,*,/,^
 * FUNCTION-određuje ime funkcije, ispred znaka @
 * VARIABLE -određuuje ime varijable
 * TAGNAME -određuje ime trenutnog taga
 * TAGSTART,TAGEND-određuje niz znakova gdje se mijenja način rada
 * @author Mateo
 *
 */
public enum TokenType {
	EOF, STRING, INT,DOUBLE,OPERATOR,FUNCTION,VARIABLE,TAGNAME,TEXT,TAGSTART,TAGEND
}
