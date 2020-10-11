package hr.fer.zemris.java.gui.prim;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;


/**
 * Klasa stvara dvije liste i iscartava iz u GUI.Ispod lista se nalazi gumb koji svaki put stavlja novi
 * prosti broj  obje liste.
 * @author Mateo
 *
 */
public class PrimDemo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Konstruktor definira veličinu i lokaciju prozora i pokreće glavnu funkciju koja 
	 * iscrtava liste u GUI.
	 */
	public PrimDemo() {
		setLocation(20, 50);
		setSize(300, 200);
		setTitle("PrimDemo");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		initGUI();
	}
	/**
	 * Klasa definira jedan model liste.Model kroz metodu next() u liste ispisuje idući po 
	 * redu prosti broj.
	 * @author Mateo
	 *
	 * @param <T> integer u ovom zadatku
	 */
	static class PrimListModel<T> implements ListModel<T> {
		/**
		 * Lista svih prostih brojeva u listi.
		 */
		private List<Integer> elementi = new ArrayList<>();
		/**
		 * Lista svih promatrača nad ovim modelom.
		 */
		private List<ListDataListener> promatraci = new ArrayList<>();
		/**
		 * Sprema zadnji ispisani prosti broj
		 */
		int last = 1;
		/**
		 * Funckija dodaje u listu idući po redu prosti broj.
		 * @return također vraća idući prosti broj
		 */
		public int next() {
			
			if (last == 1) {
				last=2;
			
				add(last);
				return last ;
			}
			int next=last;	
			while (true) {
				next++;
				if (!prime(next))continue;
				
				last=next;
				
				add((last));
				return last;

			}
		}
		/**
		 * Funkcija provjerava je li broj prost ili ne.
		 * @param x broj koji se provjerava
		 * @return true ako je prost, false inače
		 */
		private boolean prime(int x) {
			if (x % 2 == 0)
				return false;
			double sqr = Math.sqrt(x);
			for (int i = 3; i <= sqr; i+=2) {
				if (x % i == 0)
					return false;

			}
			return true;
		}
		/**
		 * Funkcija dodaje jednog promatrača koji je proslijeđen kroz argument i stavlja ga  listu
		 * primatrača.
		 */
		@Override
		public void addListDataListener(ListDataListener l) {
			promatraci.add(l);
		}
		/**
		 * Funkcija miče promatrača iz liste promatrača.
		 */
		@Override
		public void removeListDataListener(ListDataListener l) {
			promatraci.remove(l);
		}
		/**
		 * Funkcija vraća broj elemenata liste prostih brojeva.
		 */
		@Override
		public int getSize() {
			return elementi.size();
		}
		/**
		 * Funkcija vraća element u listi ,ovisno o rednom broju.
		 */
		@SuppressWarnings("unchecked")
		@Override
		public T getElementAt(int index) {
			return (T) elementi.get(index);
		}
		/**
		 * Funkcija dodaje u listu jedan cijeli broj(u ovom zadatku, sljedeći prosti broj).
		 * @param element cijeli broj 
		 */
		public void add(Integer element) {
			int pos = elementi.size();
			elementi.add(element);
			
			ListDataEvent event = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, pos, pos);
			for(ListDataListener l : promatraci) {
				l.intervalAdded(event);
			}
		}
	
	}
	
	/**
	 * Funkcija inicijalizira liste,dodaje gumb,i dodaje Listener na gumb kako bi se 
	 * pritiskom na njega vrijednost i u listi mogle ažuurirati.
	 */
	private void initGUI() {
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		PrimListModel<Integer> model = new PrimListModel<>();
		
		JList<Integer> list1 = new JList<>(model);
		JList<Integer> list2 = new JList<>(model);
		
		
		JButton dodaj = new JButton("Sljedeći");
		
		
		dodaj.addActionListener(e -> {
			model.next();
		});

		JPanel central = new JPanel(new GridLayout(1, 0));
		central.add(new JScrollPane(list1));
		central.add(new JScrollPane(list2));
		
		cp.add(central, BorderLayout.CENTER);
		cp.add(dodaj, BorderLayout.PAGE_END);
	}
	
	/**
	 * Glavna funkcija koja inicijalizira novi prozor i pokreće konstruktor koji 
	 * inicijalizira sve ostalo potrebno da se prozor iscrta.
	 * @param args
	 */
	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			JFrame frame = new PrimDemo();
			frame.pack();
			frame.setVisible(true);
		});
	}
}
