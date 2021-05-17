package assgn_I;
import java.util.*;
import java.io.*;
public class StudentRecord {
	private static void addNewStudentRecord(Hashtable<String, Float> student_records,String student_name,Float student_percentage) {
		student_records.put(student_name, student_percentage);
	}
	public static void displayStudentRecords(Hashtable<String,Float> student_records) {
		System.out.println("\n--------------------------------Student Record Details--------------------------------\n");
		Enumeration<String> student_names = student_records.keys();
		while(student_names.hasMoreElements()) {
			String name = (String)student_names.nextElement();
			System.out.println("\n--------------------------------\n" 
			+ "Student Name : "+name
			+"\nStudent Percentage : "+student_records.get(name)
			+" % \n"
			+ "--------------------------------\n");
		}
	}
	public static void searchStudentRecords(Hashtable<String,Float> student_records, String search_name) {
		if(student_records.containsKey(search_name)) {
			System.out.println("\nStudent Record Found :\n"
					+ "Student Name : " + search_name
					+"\nStudent Percentage : "+student_records.get(search_name)+" %");
		}
		else {
			System.out.println("\nStudent Record Not Found!!!");
		}
	}
	public static String showHighestScorer(Hashtable<String,Float> student_records) {
		Enumeration<String> student_names = student_records.keys();
		String high_percentage_student_name = "-1";
		while(student_names.hasMoreElements()) {
			Float max_percentage = 0.0f;
			String student_name = student_names.nextElement();
			if(student_records.get(student_name) > max_percentage){
				max_percentage = student_records.get(student_name);
				high_percentage_student_name = student_name;
			}
		}
		return high_percentage_student_name;
	}
	public static void main(String[] args) throws IOException{
		Hashtable<String,Float> student_records = new Hashtable<String,Float>();
		BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.print("\n--------------------------------Student Module--------------------------------\n"
					+ "\t\t\t1. Add Student record\n"
					+ "\t\t\t2. Display All Records\n"
					+ "\t\t\t3. Search By Student Name\n"
					+ "\t\t\t4. Display  Details Highest Percentile\n"
					+ "\t\t\t5. Exit Program\nEnter Your Choice : ");
			int choice = Integer.parseInt(inputStream.readLine());
			switch(choice) {
			case 1:
				System.out.println("Enter Student Name : ");
				String student_name = inputStream.readLine();
				System.out.println("Enter Student Percentage : ");
				Float student_percentage = Float.parseFloat(inputStream.readLine());
				addNewStudentRecord(student_records,student_name,student_percentage);
				break;
			case 2:
				displayStudentRecords(student_records);
				break;
			case 3:
				System.out.print("\n--------------------------------Search By Name--------------------------------\n"
						+ "Enter Student Name : ");
				String search_name = inputStream.readLine();
				searchStudentRecords(student_records,search_name);
				break;
			case 4:
				searchStudentRecords(student_records,showHighestScorer(student_records));
				break;
			case 5:
				System.exit(0);
				default:
					System.out.println("Please Enter Valid Choice");
			}
		}
	}	
}
