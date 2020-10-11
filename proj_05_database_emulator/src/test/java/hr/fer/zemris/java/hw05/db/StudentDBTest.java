package hr.fer.zemris.java.hw05.db;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

/**
 * 
 * @author Mateo
 *
 */
public class StudentDBTest {

	/**
	 * 
	 */
	@Test
	public void compOperator() {

		IComparisonOperator oper = ComparisonOperators.LESS;

		assertEquals(true, oper.satisfied("Ana", "Jasna"));

	}

	/**
	 * 
	 */
	@Test
	public void Comparison2() {
		IComparisonOperator oper = ComparisonOperators.LIKE;
		assertEquals(false, oper.satisfied("Zagreb", "Aba*")); // false
		assertEquals(false, oper.satisfied("AAA", "AA*AA")); // false
		assertEquals(true, oper.satisfied("AAAA", "AA*AA")); // true

	}

	/**
	 * 
	 */
	@Test
	public void studRecord() {
		StudentRecord record = new StudentRecord("1191238020", 5, "Mateo", "Martinjak");
		assertEquals("Mateo", FieldValueGetters.FIRST_NAME.get(record));
		assertEquals("Martinjak", FieldValueGetters.LAST_NAME.get(record));
		assertEquals("1191238020", FieldValueGetters.JMBAG.get(record));
	}

	/**
	 * 
	 */
	@Test
	public void conditions3() {
		ConditionalExpression expr = new ConditionalExpression(FieldValueGetters.LAST_NAME, "Bos*",
				ComparisonOperators.LIKE);
		StudentRecord record = new StudentRecord("0147258745", 4, "Iva", "Bosner");
		boolean recordSatisfies = expr.getComparisonOperator().satisfied(expr.getFieldGetter().get(record),
				expr.getStringLiteral());
		assertEquals(true, recordSatisfies);
	}

	/**
	 * 
	 */
	@Test
	public void parser() {
		QueryParser qp1 = new QueryParser(" jmbag = \"0123456789\" ");
		assertEquals(true, qp1.isDirectQuery()); // true
		assertEquals("0123456789", qp1.getQueriedJMBAG()); // 0123456789
		assertEquals(1, qp1.getQuery().size()); // 1
	}

	/**
	 * 
	 */
	@Test
	public void parser2() {
		QueryParser qp2 = new QueryParser("jmbag = \"0123456789\" AND lastName > \"J\" ");
		assertEquals(false, qp2.isDirectQuery()); // false

		assertEquals(2, qp2.getQuery().size()); // 2
	}

	/**
	 * 
	 */
	@Test
	public void everything() {
		StudentDatabase db;
		try {
			db = new StudentDatabase();

			QueryParser parser = new QueryParser("jmbag = \"0000000003\" AND lastName LIKE \"L*\"");

			for (StudentRecord r : db.filter(new QueryFilter(parser.getQuery()))) {
				assertEquals("0000000003",r.getJmbag());
				assertEquals("Bosnić",r.getLastName());
			}

		} catch (IOException ex) {

		}

	}
}
