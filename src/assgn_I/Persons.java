package assgn_I;
import java.util.*;
public class Persons {
	ArrayList<String> persons =new ArrayList<String>();
	public Persons() {
		this.persons = null;
	}
	public Persons(ArrayList<String> new_person_list) {
		this.persons = new_person_list; 
	}
	protected int searchByName(String search_name) {
		if(persons.contains(search_name)) {
			return persons.indexOf(search_name);
		}
		else return -1;
	}

	protected void sortList() {
		persons.sort(null);
	}

	protected void addNewPerson(String person_name) {
		persons.add(person_name);
	}
	
	protected String getPersonName(int index) {
		return persons.get(index);
	}

}
