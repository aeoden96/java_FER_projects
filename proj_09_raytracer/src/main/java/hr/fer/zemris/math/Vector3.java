package hr.fer.zemris.math;

/**
 * Klasa služi za zapis vektora u 3D prostoru.Klasa također sadrži niz funkcija za manipulaciju vektorima.
 * Sve klase stvaraju nove vektore, ne mijenjaju sadržaj trenutnog.
 * @author Mateo
 *
 */
public class Vector3 {
	/**
	 * x koordinata vektora
	 */
	private final double x;
	/**
	 * y koordinata vektora
	 */
	private final double y;
	/**
	 * z koordinata vektora
	 */
	private final double z;
	
	/**
	 * Konstruktor postavlja argumente .Nakon toga se nemogu mijenjati.
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector3(double x, double y, double z) {
		this.x=x;
		this.y=y;
		this.z=z;
	} 
	/**
	 * Vraća normu vektora , tj . duljinu od ishodišta do završne točke
	 * @return vraća noru vektora
	 */
	public double norm() {
		return Math.sqrt(x*x+y*y+z*z);
		
	}
	
	/**
	 * Funkcija normalizira vektor.
	 * @return Funkcija normalizira vektor 
	 */
	public Vector3 normalized() {
		double norm= norm();
		return new Vector3(x/norm,y/norm ,z/norm);
	} // normalizirani vektor

	/**
	 * Zbraja dva vektora i vraća instancu novog kao rezultat.
	 * @param other drugi vektor koji se zbraja
	 * @return vraća zbroj
	 */
	public Vector3 add(Vector3 other) {
		return new Vector3(other.x+x ,other.y+y,other.z+z);
	} // zbrajanje
	/**
	 * Vraća razliku 2 vektora
	 * @param other drugi vektor koji se oduzima od prvog
	 * @return vraća razliku vektora
	 */
	public Vector3 sub(Vector3 other) {
		return new Vector3(x-other.x,y-other.y,z-other.z);
	} // oduzimanje: ja minus drugi
	/**
	 * Vraća skalarni produkt 2 vektora.
	 * @param other drugi vektor
	 * @return vraća skalarni produkt vektora
	 */
	public double dot(Vector3 other) {
		return x*other.x+y*other.y+z*other.z;
	} // skalarni produkt

	/**
	 * Vraća vektorski produkt dva vektora.
	 * @param other drugi vektor
	 * @return Vraća treći vektor (rezultat produkta).Taj vektor je okomit na prva dva.
	 * Za smjer se koristi pravilo desne ruke.
	 */
	public Vector3 cross(Vector3 other) {
		return new Vector3(
				y*other.z-z*other.y,
				z*other.x-x*other.z,
				x*other.y-y*other.x);
	} // vektorski produkt: ja i on

	/**
	 * Skalira vektor nekim skalarom
	 * @param s skalar
	 * @return rezultat je instanca novog vektora
	 */
	public Vector3 scale(double s) {
		return new Vector3(x*s,y*s,z*s);
	} // skaliranje zadanim faktorom

	/**
	 * Vraća kosinus kuta između vektora i drugog veektora
	 * @param other drugi vektor
	 * @return kosinus kuta između njih
	 */
	public double cosAngle(Vector3 other) {
		return dot(other)/(norm()*other.norm());
	} // kosinus kuta između mene i njega

	/**
	 * Vraća x komponentu vektora
	 * @return x komponenta
	 */
	public double getX() {
		return x;
	} // prva komponenta vektora
	/**
	 * Vraća y komponentu vektora.
	 * @return y komponenta
	 */
	public double getY() {
		return y;
	} // druga komponenta vektora
	/**
	 * Vraća z komponentu vektora.
	 * @return z komponenta.
	 */
	public double getZ() {
		return z;
	} // treća komponenta vektora
	
	/**
	 * Vraća elemente vektora kao polje.
	 * @return polje koje sadrži komponente vektora.
	 */
	public double[] toArray() {
		return new double[]{x,y,z};
	} // pretvorba u polje s 3 elementa
	/**
	 * Vraća string koji sadrži zapis vektora.
	 * @return vraća string
	 */
	public String toString() {
		return "(" + x + ", " + y + ", "+ z + ")";
	} // pretvorba u string
}
