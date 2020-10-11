package hr.fer.zemris.java.hw05.db;

import java.util.List;
/**
 * Klasa koja prolazi kroz sve uvjete dane u trenutnom queryju i , uvjet po uvjet provjerava 
 * za trenutni record dali je record prihvatljiv za ispis ili ne.
 * @author Mateo
 *
 */
public class QueryFilter implements IFilter{
	/**
	 * Lista trojki,sadrži uvjete koje treba pozvati i ispitati
	 */
	List<ConditionalExpression> list;
	/**
	 * Jedini konstruktor.Prima listu u sprema referencu na nju. 
	 * @param list lista koju kasnije treba ispitati
	 */
	public QueryFilter(List<ConditionalExpression> list) {
		super();
		this.list = list;
	}

	/**
	 * Prolazi kroz sve uvjete za trenutnog studenta i ako je student prihvatljiv, vraća true
	 * ,inače vraća false.Student mora zadovoljavati sve kriterije.
	 * @param record za trenutni record provjerava uvjete
	 * @return vraća true ako je record prihvatljiv,false ako nije
	 * 
	 */
	public boolean accepts(StudentRecord record) {
		for(ConditionalExpression i : list) {
			if(!i.getComparisonOperator().satisfied(i.getFieldGetter().get(record), i.getStringLiteral())) {
				return false;
			}
	
		}
		return true;
	}
}
