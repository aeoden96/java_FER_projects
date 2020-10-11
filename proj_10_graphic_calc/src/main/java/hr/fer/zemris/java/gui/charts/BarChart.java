package hr.fer.zemris.java.gui.charts;

import java.util.List;
/**
 * BarChart klasa služi za pohranu informacija o kompletnom chartu.Iz njega se čitaju informacije
 * o redcima i stupcima i iz toga se generira chart koji se onda iscrtava na ekran.
 * @author Mateo
 *
 */
public class BarChart {

	/**
	 * čuva listu parova koji opisuju stupac i visinu stupca
	 */
	List<XYValue> list;
	/**
	 * opis uz x os
	 */
	String opisX;
	/**
	 * opis uz y os
	 */
	String opisY;
	/**
	 * minimalna moguća y vrijednost u grafu
	 */
	int minY;
	/**
	 * maksimalna moguća y vrijednost  grafu.
	 */
	int maxY;
	/**
	 * pomak između dva redka u grafu
	 */
	int razmak;
	/**
	 * 
	 * @param list čuva listu parova koji opisuju stupac i visinu stupca
	 * @param opisX opis uz x os
	 * @param opisY opis uz y os
	 * @param minY minimalna moguća y vrijednost u grafu.
	 * @param maxY maksimalna moguća y vrijednost  grafu.
	 * @param razmak pomak između dva redka u grafu
	 */
	public BarChart(List<XYValue> list, String opisX, String opisY, int minY, int maxY, int razmak) {
		super();
		this.list = list;
		this.opisX = opisX;
		this.opisY = opisY;
		this.minY = minY;
		this.maxY = maxY;
		this.razmak = razmak;
	}
	
	

	
	
}
