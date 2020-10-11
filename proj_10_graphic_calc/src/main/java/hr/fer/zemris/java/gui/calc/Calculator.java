package hr.fer.zemris.java.gui.calc;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import hr.fer.zemris.java.gui.layouts.*;

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
public class Calculator extends JFrame implements CalcModel {

	/**
	 * Klasa naslijeđuje JButton kako bi mu modificirali konstruktor i dodali
	 * operator u ActionListener unutar konstruktora.
	 * 
	 * @author Mateo
	 *
	 */
	public class UnaryButton extends JButton {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Modificirani konstruktor. String delegira orginalnom konstruktoru, a operator
		 * zapisuje kao ActionListener koji odma po promijeni modificira zapis.
		 * 
		 * @param name
		 *            ime gumba koji predstavlja unarnu operaciju
		 * @param op
		 *            unarni opeartor
		 */
		UnaryButton(String name, DoubleUnaryOperator op) {
			super(name);
			addActionListener(a -> {
				setValue(op.applyAsDouble(getValue()));
			});
		}
	}

	/**
	 * Klasa koja naslijeđuje JButton i ima modificirani konstruktor. Njemu uz ime
	 * gumba proslijeđujemo naredb koju mora izvršiti pritiskom na gumb.
	 * 
	 * @author Mateo
	 *
	 */
	public class AButton extends JButton {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Konstruktor delegira ime gumba orginalnom konstruktoru,a operaciju tipa
		 * Runnable sprema kao ActionListener koji će tu operaciju pokrenuti kada se
		 * gumb klikne.
		 * 
		 * @param name
		 *            ime gumba
		 * @param op
		 *            operacija vezana za taj gumb
		 */
		AButton(String name, Runnable op) {
			super(name);
			addActionListener(a -> {
				op.run();
			});
		}
	}

	/**
	 * Klasa koja naslijeđuje JButton.Njen modificiran konstrukor prima uz ime gumba
	 * i binarnu operaciju,te ju sprema kao ActionListener,koji će pritiskom na gumb
	 * tu operaciju spremiti i zapisati operand koji je pisan prije toga,te će
	 * pobrisati trenutni zaslon.
	 * 
	 * @author Mateo
	 *
	 */
	public class BinaryButton extends JButton {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Konstruktor delegira ime gumba orginalnom konstruktoru.A operator sprema kao
		 * ActionListener koji će se pritiskom na gumb aktivirati.
		 * 
		 * @param name
		 *            ime gumba
		 * @param op
		 *            binarna operacija
		 */
		BinaryButton(String name, DoubleBinaryOperator op) {
			super(name);
			addActionListener(a -> {
				setPendingBinaryOperation(op);
				setActiveOperand(getValue());
				clear();
			});
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * spremnik koji čuva trenutni broj u upisan u kalkulator u obliku stringa
	 */
	String value = null;
	/**
	 * Lista listenera koji dobivaju update svaki put kada se dogodi promjena u
	 * rezultatu.
	 */
	private List<CalcValueListener> listeners;
	/**
	 * zastavica koja govori je li prvi operand zapisan u kalkulator ili ne
	 */
	boolean activeOperandFlag = false;
	/**
	 * spremnik koji čuva prvi operand
	 */
	double activeOperand;
	/**
	 * zastavica koja čuva negativnost operanda
	 */
	boolean negative = false;
	/**
	 * spremnik čuva trenutnu aktivnu binarnu operaciju
	 */
	DoubleBinaryOperator pendingOp = null;

	/**
	 * Funcija koja inicira glavni prozor u kojem se stvara kalkulator.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Calculator prozor = new Calculator();
				prozor.setVisible(true);
			}
		});
	}
	/**
	 * stog
	 */
	Stack<Double> stack= new Stack<>();

	/**
	 * Defaultni konstruktor definira prozor i pogreće initGUI koji stvara sve
	 * potrebne elemente u njemu.
	 */
	public Calculator() {
		super();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Calculator");
		listeners = new ArrayList<>();
		setLocation(20, 20);
		setSize(500, 200);
		initGUI();

	}

	/**
	 * Funkcija stvara sve potrebne elemente u kalkolatoru.Detaljnije je opisano u komentarima.
	 */
	private void initGUI() {
		// Definiranje layouta koji koristimo.
		Container cp = getContentPane();
		cp.setLayout(new CalcLayout(3));

		//Definiranje labele koju koristio kao povratnu informaciju uu našem kalkulatoru.
		JLabel labela = new JLabel();
		addCalcValueListener(new ResultListener(labela));
		labela.setFont(new Font("Calibri", Font.BOLD, 25));
		labela.setHorizontalAlignment(SwingConstants.RIGHT);
		cp.add(labela, new RCPosition(0, 0));
		
		//Definiranje gumbi koji dodaju znamenku od 0-9.
		JButton[] buttonDigits = new JButton[10];
		ActionListener digitL = a -> {
			JButton b = (JButton) a.getSource();
			insertDigit(Integer.parseInt(b.getText()));
		};
		for (int j = 4, t = 0; j >= 1; j--) {
			for (int i = 2; i < 5; i++) {
				if (j == 4 && i > 2)
					continue;
				buttonDigits[t] = new JButton(String.valueOf(t));
				buttonDigits[t].addActionListener(digitL);
				cp.add(buttonDigits[t++], new RCPosition(i, j));
			}
		}
		
		//Definiranje binarnih operacija u kalkulatoru.
		BinaryButton[] buttonOps = new BinaryButton[4];
		buttonOps[0] = new BinaryButton("/", (x, y) -> x / y);
		buttonOps[1] = new BinaryButton("*", (x, y) -> x * y);
		buttonOps[2] = new BinaryButton("-", (x, y) -> x - y);
		buttonOps[3] = new BinaryButton("+", (x, y) -> x + y);
		for (int i = 0; i < 4; i++) {
			cp.add(buttonOps[i], new RCPosition(5, i + 1));
		}
		
		//Definiranje svih unarnih operacija u kalkulatoru.
		JCheckBox invSign = new JCheckBox("inv");
		UnaryButton sinSign = new UnaryButton("sin", a -> invSign.isSelected() ? Math.asin(a) : Math.sin(a));
		UnaryButton cosSign = new UnaryButton("cos", a -> invSign.isSelected() ? Math.acos(a) : Math.cos(a));
		UnaryButton tanSign = new UnaryButton("tan", a -> invSign.isSelected() ? Math.atan(a) : Math.tan(a));
		UnaryButton ctgSign = new UnaryButton("ctg",
				a -> invSign.isSelected() ? Math.atan(Math.tan(a)) : 1.0 / Math.tan(a));
		UnaryButton logSign = new UnaryButton("log", a -> invSign.isSelected() ? Math.pow(10, a) : Math.log10(a));
		UnaryButton lnSign = new UnaryButton("ln", a -> invSign.isSelected() ? Math.pow(Math.E, a) : Math.log(a));
		UnaryButton recSign = new UnaryButton("1/x", a -> 1.0 / a);
		
		BinaryButton potSign = new BinaryButton("x^n", (x,y) -> invSign.isSelected() ? Math.pow(x,1/y) : Math.pow(x,y));

		cp.add(sinSign, new RCPosition(1, 1));
		cp.add(cosSign, new RCPosition(1, 2));
		cp.add(tanSign, new RCPosition(1, 3));
		cp.add(ctgSign, new RCPosition(1, 4));
		cp.add(recSign, new RCPosition(0, 1));
		cp.add(logSign, new RCPosition(0, 2));
		cp.add(lnSign, new RCPosition(0, 3));
		cp.add(potSign, new RCPosition(0, 4));

		// Definiranje checkboxa koji stvara nove gumbe u kalklatoru.
		invSign.addActionListener(a -> {
			if (invSign.isSelected()) {
				sinSign.setText("asin");
				cosSign.setText("acos");
				tanSign.setText("atan");
				ctgSign.setText("actg");
				logSign.setText("10^");
				lnSign.setText("e^");
				potSign.setText("x^(1/n)");
			} else {
				sinSign.setText("sin");
				cosSign.setText("cos");
				tanSign.setText("tan");
				ctgSign.setText("ctg");
				logSign.setText("log");
				lnSign.setText("ln");
				potSign.setText("x^n");
			}
		});
		cp.add(invSign, new RCPosition(6, 4));

		//Definiranje gumba za promjenu predznaka.
		AButton swSign = new AButton("+/-", () -> {swapSign();});
		cp.add(swSign, new RCPosition(3, 4));

		//Definiranje gumba za dodavanje decimalne točke.
		AButton dotSign = new AButton(".", () -> {insertDecimalPoint();});
		cp.add(dotSign, new RCPosition(4, 4));

		//Definiranje gumba za znak jednakosti.
		AButton eqSign = new AButton("=", () -> {
			if(isActiveOperandSet()) {
				
				setValue(getPendingBinaryOperation().applyAsDouble(getActiveOperand(), getValue()));
			}
		});
		cp.add(eqSign, new RCPosition(5, 0));

		//Definiranje gumba za brisanje trenutnog prikaza.
		AButton clrSign = new AButton("clr", () -> {clear();});
		cp.add(clrSign, new RCPosition(6, 0));

		//Definiranje gumba za brisanje memorije kalkulatora.
		AButton resSign = new AButton("res", () -> {clearAll();});
		cp.add(resSign, new RCPosition(6, 1));
		
		AButton pushSign=new AButton("push", () -> { stack.push(getValue());}   );
		cp.add(pushSign, new RCPosition(6, 2));
		
		AButton popSign=new AButton("pop", () -> { 
			if(!stack.empty()) {
				setValue(stack.pop());
			}  
			} );
		cp.add(popSign, new RCPosition(6, 3));

	}
	
	/**
	 * Funckija dodaje listener objekt koji će dobivati sve infmacije o promijeni operanda u kalkulatoru.
	 */
	@Override
	public void addCalcValueListener(CalcValueListener l) {
		if (!listeners.contains(l)) {
			listeners.add(l);
		}

	}
	/**
	 * Funckija miče iz liste traženi listener objekt,što znači da taj objekt više neče 
	 * primati informacije o projenama rezultata u kalkulatoru.
	 */
	@Override
	public void removeCalcValueListener(CalcValueListener l) {
		if (!listeners.contains(l))
			return;
		listeners.remove(l);

	}

	/**
	 * Funckija prolazi kroz listener objekte i obavještava svakog o promjeni koja se desila.
	 */
	private void notifyListeners() {
		if (listeners != null) {
			for (CalcValueListener l : listeners) {

				l.valueChanged(this);

			}
		}
	}

	/**
	 * Funkcija vraća trenutni zapis kalkulatora u obliku doublea .
	 */
	@Override
	public double getValue() {
		if (value == null)
			return 0;

		return negative ? Double.parseDouble(value) * (-1) : Double.parseDouble(value);
	}
	/**
	 * Funkcija vraća trenutni zapis u obliku stringa.
	 */
	@Override
	public String toString() {
		if (value == null)
			return "0";

		return negative ? "-" + value : value;
	}
	/**
	 * Funckija mijenja trenutni zapis i obavještava o tome sve listenere.
	 */
	@Override
	public void setValue(double value) {

		if (getValue() != value) {

			this.value = String.valueOf(value);
			if(this.value.charAt(0) == '-') {
				negative=true;
				this.value=this.value.substring(1);
			}

			notifyListeners();
		}

	}
	
	/**
	 * Funckija briše trenutni zapis u kalkulatoru i obavještava o tome sve listenere.
	 */
	@Override
	public void clear() {
		// activeOperandFlag=false;
		value = null;
		notifyListeners();

	}
	
	/**
	 * Funckija briše cjelokupnu memoriju kalkulatora i obavještava o tome sve listenere.
	 */
	@Override
	public void clearAll() {
		activeOperandFlag = false;
		value = null;
		setPendingBinaryOperation(null);
		notifyListeners();
	}
	
	/**
	 * Funckija mijenja predznak trenutnog zapisa i obavještava o tome sve listenere.
	 */
	@Override
	public void swapSign() {
		if (value == null) {

		}

		else if (negative) {
			negative = false;
		} else {
			negative = true;
		}
		notifyListeners();

	}
	
	/**
	 * Funkcija unaša u trenutni zapis jednu decimalnu točku,ako je već ima,
	 * tada se ništa ne mijenja ,i obavještava o tome sve listenere.
	 */
	@Override
	public void insertDecimalPoint() {
		if (value == null) {
			value = "0";
		}

		if (value.indexOf('.') == -1) {
			value = value + ".";
		}

		notifyListeners();

	}
	
	/**
	 * Funckija unaša jednu znamenku u trenutni zapis i obavještava o tome sve listenere.
	 */
	@Override
	public void insertDigit(int digit) {
		
		if (value == null) {
			value = String.valueOf(digit);

		}

		else if (value.length() < 308) {

			if (value.charAt(0) == '0') {
				if (value.indexOf('.') != -1) {
					value = value + String.valueOf(digit);

				} else {
					if (digit == 0) {

					} else {
						value = String.valueOf(digit);

					}
				}
			} else {

				value = value + String.valueOf(digit);
			}
		}

		
		notifyListeners();
		

	}

	/**
	 * Funckcija vraća true ako je prvi operand unešen,false inače.
	 */
	@Override
	public boolean isActiveOperandSet() {
		return activeOperandFlag;
	}
	
	/**
	 * Funkcija vraća prvi operand.
	 */
	@Override
	public double getActiveOperand() {
		if (!activeOperandFlag) {
			throw new IllegalStateException("There's no active operand yet.");
		}
		return activeOperand;
	}
	/**
	 * Funkcija modificira prvi oeprand.
	 */
	@Override
	public void setActiveOperand(double activeOperand) {
		this.activeOperand = activeOperand;
		activeOperandFlag = true;

	}

	/**
	 * Funckija briše prvi operand.
	 */
	@Override
	public void clearActiveOperand() {
		activeOperandFlag = false;

	}
	/**
	 * Funkcija vraća trenutnu spremljenu binarnu operaciju.
	 */
	@Override
	public DoubleBinaryOperator getPendingBinaryOperation() {
		return pendingOp;
	}
	
	/**
	 * Funkcija modificira trenutnu binarnu operaciju.
	 */
	@Override
	public void setPendingBinaryOperation(DoubleBinaryOperator op) {
		pendingOp = op;

	}

}
