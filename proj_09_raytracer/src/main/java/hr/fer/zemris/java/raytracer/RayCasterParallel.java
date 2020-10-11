package hr.fer.zemris.java.raytracer;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import hr.fer.zemris.java.raytracer.model.*;
import hr.fer.zemris.java.raytracer.viewer.RayTracerViewer;
/**
 * +++++++++++++++++++++++++++++++++++++++
 * MOLIM PROCITATI: Kad sam implemenitrao paralelizaciju pomoću ForkJoinPool,
 * u dobivenoj slici mi se događaju čudne greške i nisam uspio naći problem.
 * (prije paralelizacije mi se nije to javljalo)
 * Bio bih zahvalan ako uspijete naći,pa mi javite. LP
 * 
 *  
 *  
 * +++++++++++++++++++++++++++++++++++++++++
 * Klasa sadrži main od glavnog programa. Tu mu se zadaje defaultna scena koju je potrebno
 * procesirati za 3D prikaz.Korištene su klase poput Point3D koje spremaju točku u 3 koordinate,
 * i također sadrži fukcije za skalarni i vektorski produkt,pa analitičko rješenje nije potrebno.
 * Kasnije se svaki piksel koji korisnik vidi "boja" pomoću funkcija za osvijetljenje iz knjige.
 * @author Mateo
 *
 */
public class RayCasterParallel {
	/**
	 * Treshold govori koliko linija može maksimalno biti po poslu da bi se taj posao obavio ,
	 * a da se dalje ne dijeli na manje poslove.
	 */
	static final  int  TRESHOLD =  8;
	/**
	 * Funkcija inicijalizira glavni program tako da mu se proslijede izvori i RayTracerProducer 
	 * koji cijelu scenu mora "proći" i izračunati sve točke koje korisnik može vidjeti, i tada 
	 * tako spremljene informacije funkcija show() prikaže na ekran.
	 * 
	 * @param args ne koristi se 
	 */
	public static void main(String[] args) {
		RayTracerViewer.show(getIRayTracerProducer(), new Point3D(10, 0, 0), new Point3D(0, 0, 0),
				new Point3D(0, 0, 10), 20, 20);
		
		
	}
	
	/**
	 * Funckija obavlja posao pomoću ForkJoin frameworka. Svaki posao dobi za obaviti određen broj 
	 * horizontalnih linija.
	 * @author Mateo
	 *
	 */
	public static class PosaoIzracuna extends RecursiveAction {
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 *  uk.broj piksela horiznonalno za procesirati
		 */
		int width;
		/**
		 *  uk.broj piksla vertikalno
		 */
		int height;
		/**
		 *  minimalni redni br. linije koju ovaj posao procesira
		 */
		int yMin;
		/**
		 * maksimalni red. br. linije koju ovaj posao procesira
		 */
		int yMax;
		/**
		 * iterator za red ,green,blue polja da se zna u gdje ovaj posao treba spremiti finalne podatke
		 */
		int offset;
		/**
		 * Vektor koji pokazuje na gornji lijevi rub ekrana koji se korisniku prikazuje
		 */
		Point3D screenCorner;
		/**
		 * Vektor koji reprezentira pogled korisnika.
		 */
		Point3D eye;
		/**
		 * broj piksela horizontalno
		 */
		double horizontal;
		/**
		 * broj piksela vertikalno
		 */
		double vertical;
		/**
		 * privremeno polje za spremanje boje jednog piksela
		 */
		short[] rgb;
		/**
		 * pomoćni horizontalni vektor
		 */
		Point3D i;
		/**
		 * pomoćni vertikalni vektor
		 */
		Point3D j;
		/**
		 * tu se završna stanja za svaki piksel spremaju koji govori koliko crvene boje mu se dodljejuje
		 */
		short[] red;
		/**
		 * završna stanja za zelenu boju
		 */
		short[] green;
		/**
		 * završna stanja za plavu boju
		 */
		short[] blue ;
		/**
		 * Kolekcija cijele scene,svi izvori i tijela u sceni zadani koordinatama i još dodatnim 
		 * parametrima (npr. kod izvora jačina svjetlosti...)
		 */
		Scene scene;
		/**
		 * Konstruktor inicijalizira sve varijable na dobivene vrijednosti kako bi se počelo 
		 * s obavljanjem poslova.
		 * @param width
		 * @param height
		 * @param yMin
		 * @param yMax
		 * @param horizontal
		 * @param vertical
		 * @param offset
		 * @param screenCorner
		 * @param eye
		 * @param i
		 * @param j
		 * @param scene
		 * @param red
		 * @param green
		 * @param blue
		 */
		public PosaoIzracuna(int width, int height, int yMin, int yMax,double horizontal, double vertical, 
			int offset,Point3D screenCorner,Point3D eye,Point3D i,
				Point3D j,Scene scene,short[] red ,short[] green ,short[] blue) {
			super();
			
			this.width = width;
			this.height = height;
			this.yMin = yMin;
			this.yMax = yMax;
			
			this.offset=offset;
			this.screenCorner =screenCorner;
			this.eye=eye;
			this.vertical=vertical;
			this.horizontal=horizontal;
			this.rgb=new short[3];
			this.i=i;
			this.j=j;
			this.scene=scene;
			 this.red=red  ;
			 this.green=green  ;
			 this.blue=blue ;
		}
		
		/**
		 * Funkcija dijeli rekurzivno poslove. Svaki posao mora određen broj linija procesirati,
		 * ako je broj linija  posluu prevelik, posao se dijeli na dva posla rekurzivo
		 * sve dok broj linija nije manji od zadanog thresholda.
		 */
		public void compute() {
			if(yMax-yMin+1 <= TRESHOLD) {
				computeDirect();
				return;
			}
			invokeAll(
					
				new PosaoIzracuna( width, height, yMin, yMin+(yMax-yMin)/2 ,horizontal,vertical, width*yMin,
						screenCorner,eye,i,j,scene,red,green,blue),
				new PosaoIzracuna( width, height, yMin+(yMax-yMin)/2+1, yMax,horizontal,vertical, width*(yMin+(yMax-yMin)/2+1),
						screenCorner,eye,i,j,scene,red,green,blue)
			);
		}

		/**
		 * Funkcija koja prolazi kroz piksele i poziva funkcije koje računaju boju za taj piksel.
		 * Sve informacije se zapisjuju u polja red,green,blue
		 */
		public void computeDirect() {
	
			for (int y = yMin; y <= yMax; y++) {
				for (int x = 0; x < width; x++) {
					Point3D screenPoint = screenCorner.add(i.scalarMultiply(horizontal * x / (width - 1)))
							.sub(j.scalarMultiply(vertical * y / (height - 1)));
					Ray ray = Ray.fromPoints(eye, screenPoint);						
					
					tracer(scene, ray, rgb);
					
					red[offset] = rgb[0] > 255 ? 255 : rgb[0];
					green[offset] = rgb[1] > 255 ? 255 : rgb[1];
					blue[offset] = rgb[2] > 255 ? 255 : rgb[2];
					offset++;
				}
			}
			
		}
	}
	/**
	 * Funkcija inicijalizira ForkJoin pool koji organizira poslove ,tj zove prvi koji će se 
	 * onda "sami" raspodijeliti na manje poslove.
	 * @return vraća IRayTracerProducer objekt pomoću acceptResult()
	 */
	private static IRayTracerProducer getIRayTracerProducer() {
		return new IRayTracerProducer() {
			@Override
			public void produce(Point3D eye, Point3D view, Point3D viewUp, double horizontal, double vertical,
					int width, int height, long requestNo, IRayTracerResultObserver observer) {
				
				Point3D OG = view.sub(eye).scalarMultiply(1 / view.sub(eye).norm());
				Point3D VUV = viewUp.scalarMultiply(1 / viewUp.norm());
				Point3D j = VUV.sub(OG.scalarMultiply(OG.scalarProduct(VUV)));
				j = j.scalarMultiply(1 / j.norm());
				Point3D i = OG.vectorProduct(j);
				i = i.scalarMultiply(1 / i.norm());
				Point3D screenCorner = view.sub(i.scalarMultiply(horizontal / 2)).
						add(j.scalarMultiply(vertical / 2));

				short[] red = new short[width * height];
				short[] green = new short[width * height];
				short[] blue = new short[width * height];
				
				Scene scene = RayTracerViewer.createPredefinedScene();
				
				ForkJoinPool pool = new ForkJoinPool();
				
				pool.invoke(new PosaoIzracuna( width, height, 0, height-1, horizontal,vertical,
						0,screenCorner,eye,i,j,scene,red,green,blue));
				
				pool.shutdown();
				observer.acceptResult(red, green, blue, requestNo);
				
			}
		};
	}
	/**
	 * Funkcija prolazi kroz elemente scene i gleda koji element se vidi kroz dobivenu zraku.
	 * Ako ih se više vidi ,uuzima se onaj bliže promatraču. Zatim se računa boja za taj piksel(zraku).
	 * @param scene scena koja sadrži sve elemente scene i izvore
	 * @param ray zraka koja reprezentira jednu zraku svijetlosti od promatrača u scenu
	 * @param rgb privremeni spremnik za rezltate boje koju ova fja. izračuna
	 */
	protected static void tracer(Scene scene, Ray ray, short[] rgb) {
		rgb[0] = 0;
		rgb[1] = 0;
		rgb[2] = 0;

		RayIntersection closest = null;
		RayIntersection temp = null;
		boolean first = true;

		for (GraphicalObject i : scene.getObjects()) {
			temp = i.findClosestRayIntersection(ray);
			if (temp == null)
				continue;
			if (first) {
				closest = temp;
				first = false;
			}

			if (temp.getDistance() < closest.getDistance())
				closest = temp;

		}
		short Idr=0,Irr=0,Idg=0,Irg=0,Idb=0,Irb=0;
		double k=1;
		for (LightSource i : scene.getLights()) {
			//Vektori su isto nazvani kao u knjizi
			
			if (closest != null) {
				Point3D l=i.getPoint().sub(closest.getPoint()).scalarMultiply(1/i.getPoint().sub(closest.getPoint()).norm());
				Point3D n=closest.getNormal();
				Point3D r= l.sub(n.scalarMultiply(2*l.scalarProduct(n))).scalarMultiply(1/l.sub(n.scalarMultiply(2*l.scalarProduct(n))).norm());
				Point3D v=ray.start.sub(closest.getPoint()).scalarMultiply(1/ray.start.sub(closest.getPoint()).norm());
				k= 1;//closest.getPoint().sub(i.getPoint()).norm()*0.3;
				double x= Math.pow(r.scalarProduct(v), closest.getKrn());
				
				Idr = (short) (Idr+i.getR()*((l.scalarProduct(n)>0) ? l.scalarProduct(n) : 0));
				Idg = (short) (Idg+i.getG()*((l.scalarProduct(n)>0) ? l.scalarProduct(n) : 0));
				Idb = (short) (Idb+i.getB()*((l.scalarProduct(n)>0) ? l.scalarProduct(n) : 0));
				
				Irr = (short) (Irr+i.getR()*x);
				Irg = (short) (Irg+i.getG()*x);
				Irb = (short) (Irb+i.getB()*x);
				
			

			}

		}
		if (closest == null) {
			
			return;

		}
		Idr = (short) (Idr*closest.getKdr());
		Idg = (short) (Idg*closest.getKdg());
		Idb = (short) (Idb*closest.getKdb());
		
		Irr = (short) (Irr*closest.getKrr());
		Irg = (short) (Irg*closest.getKrg());
		Irb = (short) (Irb*closest.getKrb());
		
		rgb[0] = (short) ( 10 + (Idr + Irr)/k);
		rgb[1] = (short) ( 10 + (Idg + Irg)/k);
		rgb[2] = (short) ( 10 + (Idb + Irb)/k);
		

		
	}

}
