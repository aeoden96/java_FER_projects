package demo;

import hr.fer.zemris.lsystems.LSystem;
import hr.fer.zemris.lsystems.LSystemBuilderProvider;
import hr.fer.zemris.lsystems.gui.LSystemViewer;
import hr.fer.zemris.lsystems.impl.LSystemBuilderImpl;

/**
 * Druga main funkcija.Tu se kroz tekst izrađuje SystemBuilder u obliku
 * funkcije,koja se proslijeđuje u glavni main.
 * 
 * @author Mateo
 *
 */
public class Glavni2 {
	/**
	 * Glavni main ne prima argumente preko konzolne linije. Sadrži kod za crtanje
	 * kojem se prosljeđuje LSystem objekt ,kojeg createKochCurve napravi
	 * 
	 * @param args
	 *            ne koristi se
	 * 
	 */
	public static void main(String[] args) {
		LSystemViewer.showLSystem(createKochCurve(LSystemBuilderImpl::new));

	}

	/**
	 * Funkcija proslijeđuje sve potrebne argumente u SystemBuilder kako bi napravio
	 * jednostavnu Kochovu pahulju,tj. samo jednu stranu pahulje
	 * 
	 * @param provider
	 *            pomoću njega se kreira prazan SystemBuilder u koji mi upisujemo
	 *            sve potrebne argumente
	 * @return vraća se LSystem objekt ,koji je tada spreman za ispis
	 */
	private static LSystem createKochCurve(LSystemBuilderProvider provider) {
		String[] data = new String[] { "origin 0.05 0.4", "angle 0", "unitLength 0.9",
				"unitLengthDegreeScaler 1.0 / 3.0", "", "command F draw 1", "command + rotate 60",
				"command - rotate -60", "", "axiom F", "", "production F F+F--F+F" };
		return provider.createLSystemBuilder().configureFromText(data).build();
	}
}
