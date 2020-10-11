package hr.fer.zemris.java.gui.calc;

import java.util.function.DoubleBinaryOperator;
/**
 * Klasa stvara kalkulator sličan kalkulatoru na Windows XPu. Kalkulator
 * podržava operacije zbrajanja,množenja,dijeljenja i oduzimanja , i također par
 * unarnih operacija. Tipkom inv pojavljuje se josš par unarnih operacija. Clr
 * služi da se trenutni unos obriše, a res briše kompletnu memoriju kalkulatora.
 * Za ostale tipke bi trebalo biti očito što rade.
 * 
 * @author Mateo
 *
 */
public interface CalcModel {
	/**
	 * Funckija dodaje listener objekt koji će dobivati sve infmacije o promijeni operanda u kalkulatoru.
	 * @param l listener objekt koji se želi dodati
	 */
	void addCalcValueListener(CalcValueListener l);
	/**
	 * Funckija miče iz liste traženi listener objekt,što znači da taj objekt više neče 
	 * primati informacije o projenama rezultata u kalkulatoru.
	 * @param l listener objekt koji se želi maknuti
	 */
	void removeCalcValueListener(CalcValueListener l);
	
	/**
	 * Funkcija vraća trenutni zapis u obliku stringa.
	 * @return trenutni zapis
	 */
	String toString();
	/**
	 * Funkcija vraća trenutni zapis kalkulatora u obliku doublea .
	 * @return  vraća trenutni zapis kalkulatora
	 */
	double getValue();
	/**
	 * Funckija mijenja trenutni zapis i obavještava o tome sve listenere.
	 * @param value novi trenutni zapis
	 */
	void setValue(double value);
	/**
	 * Funckija briše trenutni zapis u kalkulatoru i obavještava o tome sve listenere.
	 */
	void clear();
	/**
	 * Funckija briše cjelokupnu memoriju kalkulatora i obavještava o tome sve listenere.
	 */
	void clearAll();
	/**
	 * Funckija mijenja predznak trenutnog zapisa i obavještava o tome sve listenere.
	 */
	void swapSign();
	/**
	 * Funkcija unaša u trenutni zapis jednu decimalnu točku,ako je već ima,
	 * tada se ništa ne mijenja ,i obavještava o tome sve listenere.
	 */
	void insertDecimalPoint();
	/**
	 * Funckija unaša jednu znamenku u trenutni zapis i obavještava o tome sve listenere.
	 * @param digit nova znamenka 
	 */
	void insertDigit(int digit);
	/**
	 * Funckcija vraća true ako je prvi operand unešen,false inače.
	 * @return true ako je prvi operand unešen,false inače
	 */
	boolean isActiveOperandSet();
	/**
	 * Funkcija vraća prvi operand.
	 * @return spremljeni prvi operand
	 */
	double getActiveOperand();
	/**
	 * Funkcija modificira prvi oeprand.
	 * @param activeOperand trenutni spremljeni operand
	 */
	void setActiveOperand(double activeOperand);
	/**
	 * Funckija briše prvi operand.
	 */
	void clearActiveOperand();
	/**
	 * Funkcija vraća trenutnu spremljenu binarnu operaciju.
	 * @return binarna operacija
	 */
	DoubleBinaryOperator getPendingBinaryOperation();
	/**
	 * Funkcija modificira trenutnu binarnu operaciju.
	 * @param op binarna operacija 
	 */
	void setPendingBinaryOperation(DoubleBinaryOperator op);
}
