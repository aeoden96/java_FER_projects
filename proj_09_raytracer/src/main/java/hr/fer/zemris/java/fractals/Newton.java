package hr.fer.zemris.java.fractals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

import hr.fer.zemris.java.fractals.viewer.FractalViewer;
import hr.fer.zemris.java.fractals.viewer.IFractalProducer;
import hr.fer.zemris.java.fractals.viewer.IFractalResultObserver;
import hr.fer.zemris.math.*;

/**
 * Program iscrtava na ekrran fraktal koji zadaje korsnik kkoz nultočke polinoma.
 * Kada korisnik upiše sve korijene funkcije , treba upisati "exit" kako bi 
 * se konstruirao polinom i iscrtao na ekran. 
 * @author Mateo
 *
 */
public class Newton {
	/**
	 * točka mora biti dovoljno blizu nekog korijena (manje od ovog broja),da bi se 
	 * mogao netrivijalno obojati
	 */
	static final  double  ROOT_TRESHOLD =  1E-3;
	/**
	 * ukoliko razlika u zadnjoj i predzadnjoj iteraciji bude manja od ovog broja,
	 * tada točka "sigurno" konvergira
	 */
	static final double CONVERGENCE_TRESHOLD = 1E-3;
	/**
	 * maksimalni broj iteracija po točki
	 */
	static final int MAX_ITER =16*16*16;
	/**
	 * Glavna main funkcija koja pokreće petlju gdje se mogu upisati korijeni.
	 * @param args ne korsti se u ovom programu
	 */
	public static void main(String[] args) {
		
	
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String r=null;
		List<Complex> list= new ArrayList<>();
		
		do {
			System.out.format("> ");
			
			try {
				
				r= reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(r.equals("exit")) break;
			list.add(Complex.parse(r));
			
		}while(true);
		
		Complex[] roots= new Complex[list.size()];
		int i=0;
		for(Complex t : list) {
			roots[i]=t;
			i++;
		}
		
		ComplexRootedPolynomial polynoimal = new ComplexRootedPolynomial(roots);

		FractalViewer.show(new MojProducer(polynoimal));
	}
	
	/**
	 * Klasa koja prolazi korz iteracije i crta fraktal. Klasa prihvaća minimalni i maximalni redni 
	 * broj reda koji treba obraditi, tako da je pogodna za višedretvena okruženja.Klasa
	 * dakle treba točku po točku ispitati dali je ta točka dio skupa (fraktala) ili ne, 
	 * pa potom tu informaciju zapisuje u spremnik koji se kasnije čita da bi se te točke obojale.
	 * @author Mateo
	 *
	 */
	public static class PosaoIzracuna implements Callable<Void> {
		/**
		 * minimalni realni broj u kompleksnoj ravnini
		 */
		double reMin;
		/**
		 * maksimalni realni broj u kompleksnoj ravnini
		 */
		double reMax;
		/**
		 * minimalni imaginarni broj u kompleksnoj ravnini
		 */
		double imMin;
		/**
		 * maksimalni kompleksni broj u kompleksnoj ravnini
		 */
		double imMax;
		/**
		 * uk. broj piksela po dužini
		 */
		int width;
		/**
		 * uk . broj piksela po visini
		 */
		int height;
		/**
		 * minimalni red piksela koju samo ova instanca klase procesira
		 */
		int yMin;
		/**
		 * maksimalni red piksela koju samo ova instanca klase procesira
		 */
		int yMax;
		/**
		 * sadrži informaciju o konvergenciji za svaku točku koja se treba iscrtati
		 */
		short[] data;
		/**
		 * iterator za data, govori koji je redni broj sljedeći za pisanje u data
		 */
		int offset;
		/**
		 * polinom koji sadrži sve korijene
		 */
		ComplexRootedPolynomial polRoot;
		/**
		 * polinom koji sadrži sve korijene,samo što je zapisan u općem obliku
		 */
		ComplexPolynomial pol;
		
		/**
		 * Glavnii konstrktor, inicijalizira sve vrijednosti prije početka programa.
		 * @param reMin
		 * @param reMax
		 * @param imMin
		 * @param imMax
		 * @param width
		 * @param height
		 * @param yMin
		 * @param yMax
		 * @param data
		 * @param offset
		 * @param polRoot
		 */
		public PosaoIzracuna(double reMin, double reMax, double imMin,
				double imMax, int width, int height, int yMin,
				int yMax, short[] data,int offset,
				 ComplexRootedPolynomial polRoot) {
			super();
			this.reMin = reMin;
			this.reMax = reMax;
			this.imMin = imMin;
			this.imMax = imMax;
			this.width = width;
			this.height = height;
			this.yMin = yMin;
			this.yMax = yMax;
			
			this.data = data;
			this.offset=offset;
			
			
			this.polRoot=polRoot;
			this.pol=polRoot.toComplexPolynom();
		}
		
		/**
		 * Funkcija koja obavlja samo iteriranje.Ništa ne vraća ,neko mijenja data polje ,
		 * koje glavni program potom čita i tako boja fraktal.
		 */
		@Override
		public Void call() {

			for (int y = yMin; y <= yMax; y++) {
				for (int x = 0; x < width; x++) {

					Complex c = new Complex(x / (width - 1.0) * (reMax - reMin) + reMin,
							(height - 1.0 - y) / (height - 1) * (imMax - imMin) + imMin); 
					
					
					
					Complex zn=new Complex( c.getRe(),c.getIm());
					Complex zn1;
					
					int iter = 0;
					double module=0;
					do {
						Complex numerator = pol.apply(zn);
						Complex denominator = pol.derive().apply(zn);
						Complex fraction = numerator.divide(denominator);
						zn1 = zn.sub(fraction);
						module = zn1.sub(zn).module();
						
						zn=zn1;
						iter++;
						
					} while (module > CONVERGENCE_TRESHOLD && iter < MAX_ITER);
					short index= polRoot.indexOfClosestRootFor(zn1, ROOT_TRESHOLD);
					
					if (index == -1) {
						data[offset++] = 0;
					} else {
						data[offset++] = (short)(index+1);
					}
				
				}
			}

			return null;
		}
	}
	/**
	 * Klasa dobiva polinom iz glavnog programa. Zadužena je za stvaranje threadova i organizaciju
	 * koji posao obavlja koji thread,same iteracije se ne izvode tu.
	 * @author Mateo
	 *
	 */
	public static class MojProducer implements IFractalProducer {
		/**
		 * dobiven polino koji sadrži sve nultočke
		 */
		private ComplexRootedPolynomial pol;
		/**
		 * polinom koji sadrži sve nultoče,ali zapisan u općem obliku
		 */
		private ComplexPolynomial poly;
		/**
		 * pool sadrži kolekcijuu svih poslova koji se moraju obaviti
		 */
		private ExecutorService pool ;
		/**
		 * Konstruktor inicijalizira polinome koje dobije iz gl.programa
		 * @param pol
		 */
		public MojProducer(ComplexRootedPolynomial pol) {
			super();
			this.pol = pol;
			poly=pol.toComplexPolynom();
		
		}
		/**
		 * Funkcija se pokreće iz glavnog prograa,ona je zadužena za raspodjelu poslova,
		 * te potom zove funkciju za iteriranje nad skupom redova.
		 */
		@Override
		public void produce(double reMin, double reMax, double imMin, double imMax, int width, int height,
				long requestNo, IFractalResultObserver observer) {
			
			this.pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(),
					new ThreadFactory() {

						@Override
						public Thread newThread(Runnable r) {
							Thread t = new Thread(r);
							t.setDaemon(true);
							return t;
						}

					});
			

			
			int offset = 0;
			short[] data = new short[width * height];
			
			final int brojTraka = 8;
			int brojYPoTraci = height / brojTraka;
			List<Future<Void>> rezultati = new ArrayList<>(); // lista opisnika poslova
			
			for (int i = 0; i < brojTraka; i++) {
				int yMin = i * brojYPoTraci;
				int yMax = (i + 1) * brojYPoTraci - 1;
				if (i == brojTraka - 1) {
					yMax = height - 1;
				}
				offset=width*i*brojYPoTraci;
				PosaoIzracuna posao = new PosaoIzracuna(reMin, reMax, imMin,
						imMax, width, height, yMin, yMax,  data,offset,pol);
				rezultati.add(pool.submit(posao));
			}

			
			for (Future<Void> posao : rezultati) {
				try {
					posao.get();
				} catch (InterruptedException | ExecutionException e) {
				}
			}
			pool.shutdown();
			observer.acceptResult(data, (short) (poly.order()+1), requestNo);
			

		}// kraj fje

	}// kraj mojproducera

}
