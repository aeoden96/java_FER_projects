package hr.fer.zemris.math;

import static java.lang.Math.sin;
import static java.lang.Math.cos;

/**
 * translate/rotate/scale direktno modificiraju trenutni vektor dok metode
 * translated/rotated/scaled vraćaju novi vektor koji odgovara rezultatu
 * primjene zadane operacije nad trenutnim vektorom (pri čemu se trenutni vektor
 * ne mijenja).
 * 
 * @author Mateo
 *
 */
public class Vector2D {
	/**
	 * čuva x koord. vektora
	 */
	double x;
	/**
	 * čuva y koord. vektora
	 */
	double y;
	/**
	 * Inicijalizira vektor s  x i y koordinatom.
	 * @param x x koordinata vektora
	 * @param y y koordinata vektora
	 */
	public Vector2D(double x, double y) {
		super();
		
		this.x = x;
		this.y = y;
	}
	/**
	 * Vraća x koordinatu.
	 * @return x koordinata
	 */
	public double getX() {
		return x;
	}
	/**
	 * Vraća y koordinatu
	 * @return y koordiata
	 */
	public double getY() {
		return y;
	}
	/**
	 * Translatira trenutni objekt za neki offset ,praktički zbraja ta 2 vektora.
	 * @param offset vektor za koji se ovaj vektor pomiče
	 */
	public void translate(Vector2D offset) {
		if (offset == null) {
			throw new NullPointerException("offset nemoze biti null");
		}
		x = x + offset.getX();
		y = y + offset.getY();

	}
	/**
	 * ne mijenja trenutni objekt,translatira kordinate s parametrom offset.
	 * @param offset vektor za koji ce se vektor pomaknuti
	 * @return kopirani objekt koji je translatiran pomocu offset
	 */
	public Vector2D translated(Vector2D offset) {
		if (offset == null) {
			throw new NullPointerException("offset nemoze biti null");
		}
		double newX = x + offset.getX();
		double newY = y + offset.getY();
		return new Vector2D(newX, newY);
	}
	/**
	 * Rotira kordinate za parametar angle,mijenja trenutni objekt
	 * @param angle angle je kut izražen u stupnjevima
	 */
	public void rotate(double angle) {
		
		double tempX = x;
		angle = (angle / 360) * 2 * Math.PI;
		x = x * cos(angle) - y * sin(angle);
		y = tempX * sin(angle) + y * cos(angle);
	}
	/**
	 * ne mijenja trenutni objekt,rotira kordinate s parametrom angle.Angle je u stupnjevima.
	 * @param angle ange je kut u stupnjevima
	 * @return vraća se kopija objeka rotiranog za angle
	 */
	public Vector2D rotated(double angle) {
		angle = (angle / 360) * 2 * Math.PI;
		double tempX = x;
		double newX = x * cos(angle) - y * sin(angle);
		double newY = tempX * sin(angle) + y * cos(angle);
		return new Vector2D(newX, newY);
	}
	/**
	 * Skalira kordinate za parametar scaler,mijenja trenutni objekt
	 * @param scaler uvećava koordinate za ovaj parametar
	 */
	public void scale(double scaler) {
		
		x = scaler * x;
		y = scaler * y;
	}
	/**
	 * ne mijenja trenutni objekt,skalira kordinate s parametrom scaler
	 * @param scaler
	 * @return ne vraća ovaj objekt,nego novi kopirani uvećan za scaler
	 */
	public Vector2D scaled(double scaler) {
		return new Vector2D(scaler * x, scaler * y);

	}
	/**
	 * Stvara kopiju trenutnog objekta sa svim parametrima i vraća ga
	 * @return vraća identičnu kopiju objekta
	 */
	public Vector2D copy() {
		return new Vector2D(x, y);
	}
}
