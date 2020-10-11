package hr.fer.zemris.java.hw05.db;

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
	 * ocjena studenta
	 */
	private double finalGrade;
	/**
	 * ime studenta
	 */
	private String firstName;
	/**
	 * prezime studenta
	 */
	private String lastName;
	/**
	 * Kostruktor koji prima 4 arguumenta , i sprema ih na definirana mjesta.
	 * @param jmbag jmbag studenta
	 * @param finalGrade ocjena studenta
	 * @param firstName ime studenta
	 * @param lastName prezime studenta
	 */
	public StudentRecord(String jmbag, double finalGrade, String firstName, String lastName) {
		super();
		this.jmbag = jmbag;
		this.finalGrade = finalGrade;
		this.firstName = firstName;
		this.lastName = lastName;
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
	public double getFinalGrade() {
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
	 * Stvara jedinstveni hashCode za određenog studenta.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(finalGrade);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((jmbag == null) ? 0 : jmbag.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
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
