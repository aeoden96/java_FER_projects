package hr.fer.zemris.java.hw05.db;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw05.collections.SimpleHashtable;



/**
 * klasa iz datoteke čita sve upise studentata i sprema ih u StudentRecord objekte.Također 
 * se korsti mapom da bi lakše indeksirali traženu pretragu ako se radi samo o direktnom upitu
 * 
 * @author Mateo
 *
 */
public class StudentDatabase {
	/**
	 * mapa za traženi index.Unutra spremamo vezu između kljča(jmbaga) i objekta StudentRecord 
	 * da bi lakše došli do traženih upita.
	 */
	SimpleHashtable<String, StudentRecord> index;
	/**
	 * Defaultna lista za sve ostale upite.Sadrži sve studente
	 */
	List<StudentRecord> record;
	/**
	 * Glavni konstruktor.Ne prima ništa,nego iz datoteke čita sve informacije o studentima i 
	 * stvara za vakom studenta objekt u koji sprema informacije o njemu.
	 * @throws IOException
	 */
	public StudentDatabase() throws IOException {
		super();

		String filepath = "src/main/resources/database.txt";
		String doc;
		doc = new String(Files.readAllBytes(Paths.get(filepath)), StandardCharsets.UTF_8);
		String[] lines = doc.split("\\n");
		index = new SimpleHashtable<>(lines.length);
		record=new ArrayList<>();
		for (int i = 0; i < lines.length; i++) {
			String[] param = lines[i].split("\\s+");

			double grade ;
			StudentRecord newStudent;
			if (param.length == 5) {
				 grade = Double.parseDouble(param[4]);
				 newStudent = new StudentRecord(param[0], grade,param[3], param[1]+ param[2]);
			}
			else {
				grade = Double.parseDouble(param[3]);
				newStudent = new StudentRecord(param[0], grade, param[2], param[1]);
			}
			
			record.add(newStudent);
			index.put(param[0], newStudent);


		}
	}
	/**
	 * Implementirano pomoću mape za brži pristup ovakvim upitima
	 * @param jmbag 
	 * @return vraća StudentRecord koji pripada zadanom ključu
	 */
	public StudentRecord forJMBAG(String jmbag) {
		/*for(StudentRecord i :record) {
			if(i.getJmbag().equals(jmbag))
				return i;
		}
		return null;*/
		
		return index.get(jmbag);
	}
	/**
	 * Funkcija prolazi kroz istu kako bi odredila koji studente je korisnik tražio,
	 * te se ti vraćaju u novoj listi
	 * @param filter filter koji implementira fnkciju koju kojoj se svaki StudentRecord prosljeđuje
	 * @return vraća se lista s prihvatljivim izborom studenata
	 */
	public List<StudentRecord> filter(IFilter filter) {
		List<StudentRecord> lista=new ArrayList<>();
		for(StudentRecord i : record) {
			
			if(filter.accepts(i)) {
				lista.add(i);
			}
			
		}
		return lista;
	}

}
