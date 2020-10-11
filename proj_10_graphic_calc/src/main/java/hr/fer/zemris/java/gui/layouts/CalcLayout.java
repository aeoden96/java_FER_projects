package hr.fer.zemris.java.gui.layouts;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.util.Hashtable;

/**
 * Layout za kalkulator u 2.zadatku. Polje 0,0 jedino je veće od svih ostalih u
 * rešetci.
 * 
 * @author Mateo
 *
 */
public class CalcLayout implements LayoutManager2 {
	/**
	 * razmak između komponenti
	 */
	private int inset;
	/**
	 * Broj čelija po širini i dužini
	 */
	Dimension layoutDim;
	/**
	 * Čuva mapu svih komponenti u layoutu,i njihove koordinate
	 */
	Hashtable<Component, RCPosition> hash;

	/**
	 * Konstuktor prima inset, tj.koliko piksela hoćemo komponente da budu međusobno udaljene.
	 * @param inset
	 */
	public CalcLayout(int inset) {
		super();
		this.inset = inset;

		layoutDim = new Dimension(7, 5);

		hash = new Hashtable<Component, RCPosition>();

	}

	/**
	 * Defaultni konstruktor.
	 */
	public CalcLayout() {
		this(0);
	}

	/**
	 * Dodaje komponentu u layout.
	 */
	@Override
	public void addLayoutComponent(String name, Component comp) {
		String[] parts = name.split(",");

		RCPosition pos = new RCPosition(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));

		if (pos.x < 0 || pos.x > 6 || pos.y < 0 || pos.y > 4) {
			throw new IllegalArgumentException("position out of bounds");
		}

		if (pos.y == 0 && (pos.x > 0) && pos.x < 5) {
			throw new IllegalArgumentException("positions illegal");
		}

		hash.put(comp, new RCPosition(pos.x, pos.y));
	}

	/**
	 * Miče komponentu iz layouta.
	 */
	@Override
	public void removeLayoutComponent(Component comp) {
		hash.remove(comp);

	}

	/**
	 * Preferirana veličina layouta.(= maximalna vrijednost od preferiranih velič.komponenti)
	 * 	 */
	@Override
	public Dimension preferredLayoutSize(Container comp) {

		Dimension max = new Dimension(0, 0);
		for (int i = 0; i < comp.getComponentCount(); i++) {
			Component c = comp.getComponent(i);
			max.width = Math.max(max.width, c.getPreferredSize().width);
			max.height = Math.max(max.height, c.getPreferredSize().height);

			if (hash.get(c).x == 0 && hash.get(c).y == 0) {
				max.width = (max.width - inset * 4) / 5;
			}

		}
		Insets insets = comp.getInsets();
		max.width = max.width * layoutDim.width + inset * (layoutDim.width - 1) + insets.left + insets.right;
		max.height = max.height * layoutDim.height + inset * (layoutDim.height - 1) + insets.top + insets.bottom;
		return max;
	}

	/**
	 * Minimalna moguća veličina koju layout može poprimiti.
	 */
	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return null;
	}

	/**
	 * Funkcija dodjeljuje svakoj komponenti njene koordinate i veličinu.
	 */
	@Override
	public void layoutContainer(Container c) {
		int width = c.getSize().width - c.getInsets().left - c.getInsets().right;
		int height = c.getSize().height - c.getInsets().top - c.getInsets().bottom;
		for (Component i : c.getComponents()) {
			RCPosition pos = hash.get(i);
			int x = c.getInsets().left + width / layoutDim.width * pos.x;
			int y = c.getInsets().top + height / layoutDim.height * pos.y;
			int elementWidth = (width - 1) / layoutDim.width - 1;
			int elementHeight = (height - 1) / layoutDim.height - 1;

			if (pos.x == 0 && pos.y == 0) {
				i.setBounds(x, y, elementWidth * 5 - inset - inset, elementHeight - inset);
			} else {
				i.setBounds(x, y, elementWidth - inset, elementHeight - inset);
			}

		}
	}

	/**
	 * Dodaje kkomponentu u layout (u mapu)
	 */
	@Override
	public void addLayoutComponent(Component comp, Object constraints) {

		RCPosition pos = (RCPosition) constraints;

		if (pos.x < 0 || pos.x > 6 || pos.y < 0 || pos.y > 4) {
			throw new IllegalArgumentException("position out of bounds");
		}

		if (pos.y == 0 && (pos.x > 0) && pos.x < 5) {
			throw new IllegalArgumentException("positions illegal");
		}

		hash.put(comp, new RCPosition(pos.x, pos.y));

	}

	/**
	 * Računa maksimalnu veličinu moguću koju layout može imati.
	 */
	@Override
	public Dimension maximumLayoutSize(Container target) {
		return new Dimension(800, 600);
	}

	/**
	 * 
	 */
	@Override
	public float getLayoutAlignmentX(Container target) {
		return 0.5f;
	}

	/**
	 * 
	 */
	@Override
	public float getLayoutAlignmentY(Container target) {
		return 0.5f;
	}

	/**
	 * 
	 */
	@Override
	public void invalidateLayout(Container target) {
		// do nothing

	}

}
