package hr.fer.zemris.java.hw05.db;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa za parsiranje trenutnog query izraza. Trenutni izraz može biti odvojen
 * u puno logičkih cjelina pa klasa,sve cjeline parsira u listu i takv šalje po
 * potrebi.
 * 
 * @author Mateo
 */
public class QueryParser {
	/**
	 * string koji dobivamo od programa,trenutni query izraz
	 */
	String querryCommand;
	/**
	 * privremeni objekt u koji spremamo operator prije spremanja u listu
	 */
	IComparisonOperator oper;
	/**
	 * privremeni objekt,u njega spremamo vrstu informacije o studentu,pa onda to
	 * prosljeđujemo u listu
	 */
	IFieldValueGetter getter;
	/**
	 * Lista koja sprema sve trojke ,koje opisuju uvjete koje smo dobili iz query
	 * upita
	 */
	List<ConditionalExpression> lista;
	/**
	 * jmbag iz query upita za lakši pristup informaciji ,ako je dostupna u upitu
	 */
	String JMBAG;
	/**
	 * brine se o tome koja je informacija dalje u redu u query upitu
	 */
	int counter = 0;

	/**
	 * Glavnni konstruktor.Također se svo parsiranje odvija u njemu.
	 * 
	 * @param querryCommand
	 *            iz glavnog programa dobivamo query upit u obliku stringa.
	 */
	public QueryParser(String querryCommand) {
		super();
		this.querryCommand = querryCommand;
		lista = new ArrayList<>();
		String[] querry = querryCommand.trim().split("\\s+");

		for (int i = 0; i < querry.length; i++) {
			switch (querry[i]) {
			case "AND":
				continue;
			}
			if (counter == 0) {
				switch (querry[i]) {
				case "lastName":
					getter = FieldValueGetters.LAST_NAME;
					counter++;
					break;
				case "firstName":
					getter = FieldValueGetters.FIRST_NAME;
					counter++;

					break;
				case "jmbag":
					getter = FieldValueGetters.JMBAG;
					counter++;
					break;

				default:
					throw new IllegalArgumentException();
				}
			} else if (counter == 1) {
				switch (querry[i]) {
				case "=":
					oper = ComparisonOperators.EQUALS;

					counter++;
					break;
				case "LIKE":
					oper = ComparisonOperators.LIKE;
					counter++;
					break;
				case "<":
					oper = ComparisonOperators.LESS;
					counter++;
					break;
				case ">":
					oper = ComparisonOperators.GREATER;
					counter++;
					break;
				case ">=":
					oper = ComparisonOperators.GREATER_OR_EQUALS;
					counter++;
					break;
				case "<=":
					oper = ComparisonOperators.LESS_OR_EQUALS;
					counter++;
					break;
				case "!=":
					oper = ComparisonOperators.NOT_EQUALS;
					counter++;
					break;

				default:
					throw new IllegalArgumentException();
				}
			} else if (counter == 2) {
				if (getter == FieldValueGetters.JMBAG) {
					JMBAG = querry[i].substring(1, querry[i].length() - 1);
				}
				ConditionalExpression expr = new ConditionalExpression(getter,
						querry[i].substring(1, querry[i].length() - 1), oper);
				counter = 0;
				lista.add(expr);
			}
		}

	}

	/**
	 * Provjerava ako se query sastoji od samo jednakosti i Stringa s kojim
	 * provjeravamo jesu li jmbagovi jednaki,tada bi trebali dobiti samo jednog
	 * studenta.
	 * 
	 * @return vraća true ako je to istina,false ako nije
	 */
	boolean isDirectQuery() {
		if (lista.size() == 1 && getter == FieldValueGetters.JMBAG)
			return true;
		return false;
	}

	/**
	 * ako je query direktan, onda vraća jmbag studenta,inače vraćća iznimku.
	 * 
	 * @return vrača jmbag studenta
	 */
	String getQueriedJMBAG() {
		if (!isDirectQuery())
			throw new IllegalArgumentException();
		return JMBAG;
	}

	/**
	 * Vraća listu svih uvjeta koji su parsirani iz query upita za kasniju obradu.
	 * @return vraća se lista uvjeta
	 */
	List<ConditionalExpression> getQuery() {
		return lista;
	}
}
