package hr.fer.zemris.java.raytracer.model;

/**
 * Klasa opisuje sferu u sceni .Dobiva argumente poput radijusa koji je samo
 * double , i centra koji se čuva kao Point3D varijabla.Također su proslijeđeni
 * koeficijenti za svjetlinu da bi se mogla izračunati boja.
 * 
 * @author Mateo
 *
 */
public class Sphere extends GraphicalObject {
	/**
	 * Varijabla koja čuva centar kružnice u obliku vektora.
	 */
	Point3D center;
	/**
	 * Radijus kružnice
	 */
	double radius;
	/**
	 * Difuzna komponenta za crvenu boju
	 */
	double kdr;
	/**
	 * Difuzna komponenta za zelenu boju
	 */
	double kdg;
	/**
	 * Difuzna komponenta za plavu boju
	 */
	double kdb;
	/**
	 * Reflektivna komponenta za crvenu boju
	 */
	double krr;
	/**
	 * Reflektivna komponenta za zelenu boju.
	 */
	double krg;
	/**
	 * Reflektivna komponenta za plavu boju.
	 */
	double krb;
	/**
	 * Reflektivna komponenta za potenciranje.
	 */
	double krn;
	/**
	 * normala(vektor) iz sjecišta . Računa se kao (Sjecište - centar) , te se
	 * normira.
	 */
	Point3D normal;

	/**
	 * Konstruktor inicijalizira sve potrebne komponente za ovaj model.
	 * 
	 * @param center
	 * @param radius
	 * @param kdr
	 * @param kdg
	 * @param kdb
	 * @param krr
	 * @param krg
	 * @param krb
	 * @param krn
	 */
	public Sphere(Point3D center, double radius, double kdr, double kdg, double kdb, double krr, double krg, double krb,
			double krn) {
		this.center = center;
		this.radius = radius;
		this.kdr = kdr;
		this.kdb = kdb;
		this.kdg = kdg;
		this.krg = krg;
		this.krr = krr;
		this.krb = krb;
		this.krn = krn;

	}

	/**
	 * Funkcija izračunava koje je najbliže sjecište sfere i pravca koji je
	 * reprezentiran s ray. Tu je detaljno geometrijski objašnjeno kako se dolazi do
	 * sjecišta:
	 * 
	 * <ul>
	 * <li>treba se izračunati udaljenost između gledaoca i centra sfere (koristi se
	 * skalarni produkt za to), i ta vrijednost mora biti pozitivna
	 * <li>vektor TE udaljenosti , vektor ortog.projectije centra na pravac, i
	 * vektor razlike centra i gledaoca stvara pravokutni trokut,odavde potrebno
	 * izlučiti vektor ortog.projectije centra na pravac,naravno 
	 * ispod korijena nesmije biti negativan broj
	 * <li>vektor ortog.projectije centra na pravac,vektor od projekcije centra do sjecišta i radijus 
	 * čine drugi pravokutni trokut ,potrebno je izlučiti vektor od projekcije centra do sjecišta 
	 * <li>Sada se samo treba porvjeriti koje je sjecište dalje a koje bliže i ono bliže je korektno
	 * 
	 * 
	 * </ul>
	 */
	@Override
	public RayIntersection findClosestRayIntersection(Ray ray) {

		class R extends RayIntersection {

			protected R(Point3D point, double distance, boolean outer) {
				super(point, distance, outer);

			}

			@Override
			public double getKdb() {
				return kdb;

			}

			@Override
			public double getKdg() {
				return kdg;
			}

			@Override
			public double getKdr() {
				return kdr;
			}

			@Override
			public double getKrb() {
				return krb;
			}

			@Override
			public double getKrg() {
				return krg;
			}

			@Override
			public double getKrn() {
				return krn;
			}

			@Override
			public double getKrr() {
				return krr;
			}

			@Override
			public Point3D getNormal() {
				return normal;
			}

		}

		Point3D eyeToCenter = center.sub(ray.start);
		double eyeToProjection = eyeToCenter.scalarProduct(ray.direction);

		if (eyeToCenter.scalarProduct(eyeToCenter) -
				(eyeToProjection * eyeToProjection) > radius * radius)
			return null;

		double interscToProjection = Math.sqrt(radius * radius - 
				eyeToCenter.scalarProduct(eyeToCenter) + 
				(eyeToProjection * eyeToProjection));

		double intersection1 = eyeToProjection - interscToProjection;
		double intersection2 = eyeToProjection + interscToProjection;
		double smaller = (intersection1 < intersection2) ? intersection1 : intersection2;

		Point3D result = ray.start.add(ray.direction.scalarMultiply(smaller));

		normal = result.sub(center).scalarMultiply(1 / result.sub(center).norm());

		return new R(result, smaller, true);

	}

}
