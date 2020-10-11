package hr.fer.zemris.java.hw06.demo4;

/**
 * Reprezentira informacije jednog studenta.Sadrži njegovo ime,prezime ,jmbag i ocjenu.
 * Sadrži jedan konstruktor i gettere.
 * 
 * @author Mateo
 *
 */
public class StudentRecord {
	/**
	 * jmbag studenta
	 */
	private String jmbag;
	/**
	 * prezime studenta
	 */
	private String lastName;
	/**
	 * ime studenta
	 */
	private String firstName;
	
	
	
	/**
	 * broj bodova u međusipitu
	 */
	private double meduIspit;
	/**
	 * broj bodova u završnom ispitu
	 */
	private double zavrsniIspit;
	/**
	 * broj bodova s vježbi
	 */
	private double labVjezbe;
	/**
	 * ocjena studenta
	 */
	private int finalGrade;
	/**
	 * Konstruktor prima početne vrijednosti za studenta, i pohranjuje ih.
	 * @param jmbag
	 * @param lastName
	 * @param firstName
	 * @param meduIspit
	 * @param zavrsniIspit
	 * @param labVjezbe
	 * @param finalGrade
	 */
	public StudentRecord(String jmbag, String lastName, String firstName, double meduIspit, double zavrsniIspit,
			double labVjezbe, int finalGrade) {
		super();
		this.jmbag = jmbag;
		this.lastName = lastName;
		this.firstName = firstName;
		this.meduIspit = meduIspit;
		this.zavrsniIspit = zavrsniIspit;
		this.labVjezbe = labVjezbe;
		this.finalGrade = finalGrade;
	}
	/**
	 * getter za jmbag
	 * @return vraća jmbag studenta
	 */
	public String getJmbag() {
		return jmbag;
	}
	/**
	 * Getter za ocjenu.
	 * @return vraća završnu ocjenu studenta.
	 */
	public int getFinalGrade() {
		return finalGrade;
	}
	/**
	 * Getter za ime .
	 * @return Vraća ime studenta.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Getter za prezime
	 * @return vraća prezime studenta.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Getter za bodove međuispita
	 * @return vraća broj bodova na međuispitu
	 */
	public double getMeduIspit() {
		return meduIspit;
	}
	/**
	 * Getter za bodove završnog ispita.
	 * @return vraća broj bodova s završnog ispita
	 */
	public double getZavrsniIspit() {
		return zavrsniIspit;
	}
	/**
	 * Getter za bodove s vježbi.
	 * @return vraća trenutne bodove s lab. vježbi
	 */
	public double getLabVjezbe() {
		return labVjezbe;
	}
	
	/**
	 * equals funkcija.Gleda jesuu li jmbagovi 2 studnenta isti.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentRecord other = (StudentRecord) obj;
		if (jmbag != other.jmbag)
			return false;
	
		return true;
	}
	
	
	
	
}
