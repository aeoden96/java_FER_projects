package hr.fer.zemris.java.hw06.demo4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Class demonsrates use of stream() objects that are used for easier workflow when we use Collections.
 
 * @author Mateo
 *
 */
public class StudentDemo {
	/**
	 * Function reads list of student records and return a number using stream workflow
	 * that represents the number of students that have more than 25 points .
	 * A filter is used for that,and after that fuction count() count all elements 
	 * left in the pipleline.
	 * @param records list of all student records
	 * @return returns number of students with number of points bigger than 25
	 */
	public static long  vratiBodovaViseOd25(List<StudentRecord> records){
		
		return records
				.stream()
				.filter(s->s.getMeduIspit()+s.getZavrsniIspit()+s.getLabVjezbe()>=25)
				.count();
	}
	
	/**
	 * Function recieves a list of student records and returns number of students who have 
	 * grade equal to 5. A simle filter is used here to filter if sdudent has grade 5 or not,
	 * and elements that remainded in the pipeline get the counted.
	 * @param records list of all student records
	 * @return returns number of students with grade equal to 5
	 */
	public static long  vratiBrojOdlikasa(List<StudentRecord> records){
		
		return records
				.stream()
				.filter(s->s.getFinalGrade()>=5)
				.count();
	}
	
	/**
	 * Function recieves list of student records and returnes a list of ones that have 
	 * grade equal to 5
	 * @param records list of all student records
	 * @return returns a list of all students taht have grade equal to 5
	 */
	public static List<StudentRecord>  vratiListuOdlikasa(List<StudentRecord> records){
		
		return records
				.stream()
				.filter(s->s.getFinalGrade()>=5)
				.collect(Collectors.toList());
	}
	
	
	/**
	 * A function that creates and returns a sorted list of students that have grade of 5 .
	 * For sorting,we had to create a new comaprator object, where we define by which 
	 * criteria we want to sort them. 
	 * Then in a stream, that comaprator object is passed to sorted fnc. and form that ,a new 
	 * list is created.
	 * @param records list of all stdent records
	 * @return returns a list of sorted student records
	 */
	public static List<StudentRecord> vratiSortiranuListuOdlikasa(List<StudentRecord> records){
		final class MyComparator implements Comparator<StudentRecord> {
	        @Override
	        public int compare(StudentRecord first ,StudentRecord second) {
	            double firstSum= first.getLabVjezbe()+first.getMeduIspit()+first.getZavrsniIspit() ;
	            double secondSum= second.getLabVjezbe()+second.getMeduIspit()+second.getZavrsniIspit() ;
	            if(firstSum>secondSum) return -1;
	            else if (firstSum<secondSum) return 1;
	            else return 0;
	        }
	    }
		return records
				.stream()
				.filter(s->s.getFinalGrade()>=5)
				.sorted(new MyComparator())
				.collect(Collectors.toList());
	}
	
	/**
	 * Function returns a list of all JMBAGs of students that have a negative grade.
	 * Here is filter used to get just those who failed ,than a map is used to 
	 * take just JMBAG from each record, and then we want to return it as al list,
	 * so we send Collectors.toList() to collect function to make a list of elements 
	 * we defined.
	 * @param records list of all student records
	 * @return returns a list of JMBAGs of students that have a negative grade
	 */
	public static List<String> vratiPopisNepolozenih(List<StudentRecord> records){
		
		return records
				.stream()
				.filter(s->s.getFinalGrade()<=1)
				.map(s->s.getJmbag())
				.collect(Collectors.toList());
	}
	
	/**
	 * Fuction creates a map that links a grade and a list of students  with that grade.
	 * Here,groupingBy() is used to diferentiate which students have which grade, as 
	 * argument to this function ,a function getFinalGrade is passed, so that function is 
	 * used to generate keys in this map.
	 * @param records list of all student records
	 * @return returns a map
	 */
	public static Map<Integer, List<StudentRecord>> razvrstajStudentePoOcjenama(List<StudentRecord> records) {
		return records
				.stream()
				.collect(Collectors.groupingBy(StudentRecord::getFinalGrade));
	}
	
	
	/**
	 * Function creates a map that links a grade to number of students that have that grade.
	 * Here, toMap() is used to map a grade to a single integer of value 1,so every time 
	 * another value is linked to same grade,a concat. function Integer::sum adds the 
	 * valuue of 1 to sum of already added 1's.
	 * @param records list of all student records
	 * @return returns a map
	 */
	public static Map<Integer, Integer> vratiBrojStudenataPoOcjenama(List<StudentRecord> records) {
		
		return records
				.stream()
				.map(StudentRecord::getFinalGrade)
				.collect(Collectors.toMap(	s->s,s->1,Integer::sum ));
		
	}
	
	/**
	 * Function creates a map that links bool result to a list of students that passed/failed.
	 * For that, partitioningBy() is used to diferentiate which students failed/passed.
	 * @param records list of all student records
	 * @return returns a map
	 */
	public static Map<Boolean, List<StudentRecord>> razvrstajProlazPad(List<StudentRecord> records) {
		return records
				.stream()
				.collect(Collectors.partitioningBy(s -> s.getFinalGrade() > 1));
		
	}
	
	/**
	 * Main function that calls each of created funtions and prints the results to console line.
	 * @param args it is not used
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Path docPath=Paths.get("src/main/resources/studenti.txt");
		List<String> lines = Files.readAllLines(docPath);
		List<StudentRecord> records = convert(lines);
		
		long broj = vratiBodovaViseOd25(records);
		System.out.println( "Broj studenata s više od 25 bodova: "+  broj);
		long broj5 = vratiBrojOdlikasa(records);
		System.out.println("Broj odlikaša : "+ broj5);
		
		List<StudentRecord> odlikasi = vratiListuOdlikasa(records);
		System.out.println( "LISTA ODLIKAŠA:");
		printList(odlikasi);
		
		List<StudentRecord> odlikasiSortirano = vratiSortiranuListuOdlikasa(records);
		System.out.println("SORTIRANA LISTA ODLIKAŠA: ");
		printList(odlikasiSortirano);
		

		List<String> nepolozeniJMBAGovi=vratiPopisNepolozenih(records);
		System.out.println("NEPOLOŽENI:");
			
		/*for(String i: nepolozeniJMBAGovi) {
			System.out.println(i);
		}*/
		
		Map<Integer, List<StudentRecord>> mapaPoOcjenama =razvrstajStudentePoOcjenama(records);
		System.out.println("MAPA STUDENATA PO OCJENAMA:");
		
		/*for(Integer i :mapaPoOcjenama.keySet()) {
			printList(mapaPoOcjenama.get(i));
		}*/
		
		
		
		Map<Integer, Integer> mapaPoOcjenama2=vratiBrojStudenataPoOcjenama(records);
		System.out.println("BROJ STUDENATA PO OCJENAMA: ");
		
		for(Integer i :mapaPoOcjenama2.keySet()) {
			System.out.println(i.intValue() + " -> " + mapaPoOcjenama2.get(i));
		}
		
		Map<Boolean, List<StudentRecord>> prolazNeprolaz=razvrstajProlazPad(records);
		System.out.println("RAZVRSTANO PROLAZ/PAD:");

		//for(StudentRecord i: prolazNeprolaz.get(false)) {
		//	System.out.println(i.getFirstName() + " "+ i.getLabVjezbe()+ " "+ i.getMeduIspit()
		//	+" "+ i.getZavrsniIspit() + " "+i.getFinalGrade());
		//}
		
		
		
	}
	
	/**
	 * Function that reads list of StudentRecords and prints it.
	 * @param list list of StudentRecord objects
	 */
	public static void printList(List<StudentRecord> list) {
		System.out.println();
		
		for(StudentRecord i : list) {
			System.out.println(i.getFirstName() + " "+ i.getLabVjezbe()+ " "+ i.getMeduIspit()
			+" "+ i.getZavrsniIspit() + " "+i.getFinalGrade());
		}
	}
	/**
	 * Fucntion converts list of lines from input and parses them into StudentRecord objects,
	 * one for each line.
	 * @param lines each line represents one student
	 * @return returns a list of StudentRecord objects
	 */
	public static List<StudentRecord> convert(List<String> lines){
		List<StudentRecord> record= new ArrayList<>();
		StudentRecord temp;
		for(String i: lines) {
			
		
			String[] student= i.split("\\s+");
			if(student.length<7) break;
			temp=new StudentRecord(
					student[0],
					student[1],
					student[2],
					Double.parseDouble(student[3]),
					Double.parseDouble(student[4]),
					Double.parseDouble(student[5]),
					Integer.parseInt(student[6])
					);
			record.add(temp);
		}
		return record;
	}
}
