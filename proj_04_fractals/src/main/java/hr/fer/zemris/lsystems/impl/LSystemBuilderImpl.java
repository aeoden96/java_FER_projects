package hr.fer.zemris.lsystems.impl;

import static java.lang.Math.pow;

import hr.fer.zemris.java.custom.collections.Dictionary;
import hr.fer.zemris.lsystems.LSystem;
import hr.fer.zemris.lsystems.LSystemBuilder;
import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.commands.ColorCommand;
import hr.fer.zemris.lsystems.impl.commands.DrawCommand;
import hr.fer.zemris.lsystems.impl.commands.PopCommand;
import hr.fer.zemris.lsystems.impl.commands.PushCommand;
import hr.fer.zemris.lsystems.impl.commands.RotateCommand;
import hr.fer.zemris.lsystems.impl.commands.ScaleCommand;
import hr.fer.zemris.lsystems.impl.commands.SkipCommand;
import hr.fer.zemris.math.Vector2D;

/**
 * Najvažnija klasa za konstrukciju fraktala u programu.Klasa korsti gotovu jar
 * arhivu koja sadrži krucijalan kod koji pokreće glavni prozor(i
 * izbornik).Također se unutra nalazi i klasa za crtanje koju ova klasa poziva.
 * Klasa radi tako da pribavlja početna stanja od korisnika, i tada program
 * ,pomoću uniježđene klase ,poziva draw() svaki puta kada se mijenja level
 * detalja u programu.Draw()je baza cijelog programa.Klasa također koristi
 * Dictionary i Vector2D klase iz ovog paketa, i niz Command klasa.
 * 
 * @author Mateo
 *
 */
public class LSystemBuilderImpl implements LSystemBuilder {
	/**
	 * sadrži sve produkcije koje su proslijeđene od korisnika , točnije,sadrži sve
	 * parove simbola i stringova , što označava u što će se taj simbol preslikati u
	 * idućoj iteraciji
	 */
	private Dictionary productions = new Dictionary();
	/**
	 * Sadrži sve komande u programu ,definirane od strane korsinika.Komande se
	 * sastoje od simbola ,koji je ključ , i strina koji se sastoji od ključne
	 * riječi koji opisuju taj string, tj. što se mora izvršiti kada program naiđe
	 * na taj string,a nakon kljčne riječi može i nemora biti jos jedan
	 * argument,koji se prosljeđuje.
	 */
	private Dictionary actions = new Dictionary();
	/**
	 * defaultna duljina jednog koraka,korisnik zadaje svoju ,ali ukoliko iz nekog
	 * razloga to ne napravi ,defautna duljina je uvijek zadana
	 */
	private double unitLength = 0.1;
	/**
	 * scaler koristimo kako bi rekli programu koliko treba umanjit unitLength u
	 * svakom koraku. Ako je defaultna vrijednost=1, on se nikad ne mijenja.
	 */
	private double unitLengthDegreeScaler = 1;
	/**
	 * origin je točka oblika Vector2D odkud program počne crtati.
	 */
	private Vector2D origin = new Vector2D(0, 0);
	/**
	 * Zadaje se početni kut za nultu iteraciju
	 */
	private double angle = 0;
	/**
	 * Axiom predstavlja početno pravilo,tj.početnu produkciju.
	 */
	private String axiom = "";

	/**
	 * Ovo je implementacija od LSystem.Taj objekt se vraća programu jer program
	 * korz njega izvršava program, tako da mu šalje svoj trenutni level i funckiju
	 * pomoću koje će taj objekt crtat na ekran. Korisnik ne zove draw, ali
	 * generate() ne poziva nitko, jedino se koristi u draw da bi joj predao
	 * trenutni niz simola.
	 */
	public class mySystem implements LSystem {
		/**
		 * Glavna funkcija u ovoj klasi. Poziva ju glavni program.U njoj se određuje
		 * koiko će bit velik korak ,ovisno o trenutnom razredu ,te poziva generate da
		 * izgenerira novi niz simbola ovisno o levelu. Tada funkcija prolazi korz
		 * simbole nad svakim zada naredbu da se izvrši. Svaki put kad se u programu
		 * promijeni level,ova funkcija se pokrene.
		 */
		@Override
		public void draw(int level, Painter painter) {
			Context myContext = new Context();
			double unit = unitLength * pow(unitLengthDegreeScaler, level);

			TurtleState newState = new TurtleState(origin, new Vector2D(1, 0).rotated(angle), "000000", unit);

			myContext.pushState(newState);

			String d = generate(level);
			char[] ds = d.toCharArray();
			for (int i = 0; i < ds.length; i++) {
				if (actions.get(ds[i]) == null)
					throw new IllegalArgumentException("nije definirana draw commanda");
				Command x = (Command) actions.get(ds[i]);
				x.execute(myContext, painter);

			}

		}

		/**
		 * Generira idući niz pravila ,ovisno o levelu, koji će se predati u draw kako
		 * bi draw prošao kroz njega i pozvao nad svakim simbolom određenu funkciju.Nije
		 * preporučljivo da se koristi kod puno iteracija, jer veličina stringa koji
		 * šalje eksponencijalno raste.
		 */
		@Override
		public String generate(int level) {

			if (level == 0) {
				return axiom;
			}

			else {

				StringBuilder novi = new StringBuilder();
				char[] polje = axiom.toCharArray();
				for (int j = 0; j < level; j++) {
					novi = new StringBuilder();
					for (int i = 0; i < polje.length; i++) {
						if (productions.get(polje[i]) != null) {

							novi.append(productions.get(polje[i]));
						} else {
							novi.append(polje[i]);
						}
					}
					polje = novi.toString().toCharArray();
				}
				return novi.toString();
			}
		}
	}

	/**
	 * Build stvara instancu ugniježđenog razreda koji implementira LSystem i vraća
	 * ga glavnom programu.
	 */
	@Override
	public LSystem build() {
		return new mySystem();
	}

	/**
	 * Program dobiva linije koje mora obraditi i jednu po jednu i poziva određee
	 * funkcije u istoj klasi koje su onda odgovorne za stvaranje određenih
	 * komandi.Vraća se SystemBuilder nad kojim se onda mora pozvati build.
	 */
	@Override
	public LSystemBuilder configureFromText(String[] lines) {
		int length = lines.length;
		for (int i = 0; i < length; i++) {
			if (lines[i].equals(""))
				continue;
			String[] parts = lines[i].split("\\s+");
			switch (parts[0]) {
			case "origin":
				double originX = Double.parseDouble(parts[1]);
				double originY = Double.parseDouble(parts[2]);
				setOrigin(originX, originY);
				break;
			case "angle":
				double angle = Double.parseDouble(parts[1]);
				setAngle(angle);

				break;
			case "unitLength":
				double unitLength = Double.parseDouble(parts[1]);
				setUnitLength(unitLength);

				break;
			case "unitLengthDegreeScaler":
				double prvi, drugi;
				try {
					prvi = Double.parseDouble(parts[1]);
					drugi = Double.parseDouble(parts[3]);
				} catch (ArrayIndexOutOfBoundsException ex) {

					prvi = Double.parseDouble(parts[1]);
					drugi = Double.parseDouble(parts[2].substring(1));
				}

				setUnitLengthDegreeScaler(prvi / drugi);

				break;
			case "command":
				try {
					registerCommand(parts[1].charAt(0), parts[2] + " " + parts[3]);
				} catch (ArrayIndexOutOfBoundsException ex) {
					registerCommand(parts[1].charAt(0), parts[2]);
				}

				break;
			case "production":
				registerProduction(parts[1].charAt(0), parts[2]);
				break;
			case "axiom":
				setAxiom(parts[1]);

				break;
			default:
				throw new IllegalArgumentException("Invalid directive : " + parts[0]);
			}

		}
		return this;
	}

	/**
	 * registrira sve komande koje je korsinik definirao .Ovisno o naredbi,program
	 * nalazi odgovarajuću klasu gdje će spremiti naredbu takvog oblika.
	 */
	@Override
	public LSystemBuilder registerCommand(char symbol, String action) {
		String[] parts = action.split("\\s+");
		switch (parts[0]) {
		case "rotate":
			double angle = Double.parseDouble(parts[1]);
			RotateCommand myRotateCommand = new RotateCommand(angle);
			actions.put(symbol, myRotateCommand);
			break;
		case "pop":
			actions.put(symbol, new PopCommand());
			break;
		case "push":
			actions.put(symbol, new PushCommand());
			break;
		case "color":
			actions.put(symbol, new ColorCommand(parts[1]));
			break;
		case "draw":
			double step = Double.parseDouble(parts[1]);
			DrawCommand myDrawCommand = new DrawCommand(step);
			actions.put(symbol, myDrawCommand);
			break;
		case "scale":
			double factor = Double.parseDouble(parts[1]);
			actions.put(symbol, new ScaleCommand(factor));
			break;
		case "skip":
			double step2 = Double.parseDouble(parts[1]);
			actions.put(symbol, new SkipCommand(step2));
			break;

		}
		return this;
	}

	/**
	 * postavlja se nova produkija, i briše defaultna vrijednost
	 */
	@Override
	public LSystemBuilder registerProduction(char symbol, String production) {

		productions.put(symbol, production);
		return this;
	}

	/**
	 * postavlja se kut u stupnjevima, i briše defaultna vrijednost
	 */
	@Override
	public LSystemBuilder setAngle(double angle) {
		this.angle = angle;
		return this;
	}

	/**
	 * postavlja se axiom, i briše defaultna vrijednost
	 */
	@Override
	public LSystemBuilder setAxiom(String axiom) {
		this.axiom = axiom;
		return this;
	}

	/**
	 * postavlja se lokacija početne iteracije, i briše defaultna vrijednost
	 */
	@Override
	public LSystemBuilder setOrigin(double x, double y) {
		origin = new Vector2D(x, y);
		return this;
	}

	/**
	 * postavlja se unitLength, i briše defaultna vrijednost
	 */
	@Override
	public LSystemBuilder setUnitLength(double unitLength) {
		this.unitLength = unitLength;
		return this;
	}

	/**
	 * postavlja se unitLengthDegreeScaler, i briše defaultna vrijednost
	 */
	@Override
	public LSystemBuilder setUnitLengthDegreeScaler(double unitLengthDegreeScaler) {
		this.unitLengthDegreeScaler = unitLengthDegreeScaler;
		return this;
	}

}
