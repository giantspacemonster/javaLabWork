package assgn_I;
import java.io.*;
import java.util.*;
public class ColourPallets {
	public static void main(String[] args) throws IOException {
		LinkedList<String> colour_pallets = new LinkedList<String>();
		colour_pallets.add("Red");
		colour_pallets.add("Blue");
		colour_pallets.add("Yellow");
		colour_pallets.add("Orange");
		int choice = 0;
		BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.print("\nWelcome to Colour Pallets.\nWe provide the following services : \n"
					+ "\t1. Display Colour Pallets\n"
					+ "\t2. Display Colour Pallets in Reverse Order\n"
					+ "\t3. Insert Elements Between\n"
					+ "\t4. EXIT\n"
					+ "\tEnter Menu Option : ");
			try {
				choice = Integer.parseInt(inputStream.readLine());
			}
			catch(Exception e) {
				System.err.println(e);
			}
			switch(choice) {
			case 1:
				displayColourPallet(colour_pallets);
				break;
			case 2:
				displayColourPalletReverse(colour_pallets);
				break;
			case 3:
				LinkedList<String> new_colour_pallets = new LinkedList<String>();
				new_colour_pallets.add("Pink");
				new_colour_pallets.add("Green");
				displayColourPallet(insertElementsBetween(colour_pallets, new_colour_pallets));
				break;
			case 4:
				System.exit(0);
				break;
				default:
					System.out.println("Pease Enter Valid Choice!!!");
			}
		}
	}


	private static LinkedList<String> insertElementsBetween(LinkedList<String> colour_pallets,LinkedList<String> new_colour_pallets) {
		LinkedList<String> temp_colour_pallets = new LinkedList<String>();
		LinkedList<String> original_colour_pallets = colour_pallets;
		ListIterator<String> list = original_colour_pallets.listIterator();
		ListIterator<String> new_list = new_colour_pallets.listIterator();
		ListIterator<String> temp_list = temp_colour_pallets.listIterator();
		int initial_index = 0;
		while(list.hasNext()) {
			if(list.next() == "Blue"){
				initial_index = list.nextIndex();
				System.out.print("\nTouched 1 : initial_index : "+initial_index);
			}
		}
		list = original_colour_pallets.listIterator(original_colour_pallets.size());
		while(list.hasPrevious() && list.previousIndex() >= initial_index){
			temp_list.add(list.previous());
		}
		while(new_list.hasNext()) {
			original_colour_pallets.set(initial_index++,new_list.next());
		}
		temp_list = temp_colour_pallets.listIterator(temp_colour_pallets.size());
		while(temp_list.hasPrevious()) {
			original_colour_pallets.add(temp_list.previous());
		}
		return original_colour_pallets;
	}

	private static void displayColourPalletReverse(LinkedList<String> colour_pallets) {
		int sizeOfList = colour_pallets.size();
		ListIterator<String> list = colour_pallets.listIterator(sizeOfList);
		while(list.hasPrevious()) {
			System.out.println("\n Clour Name : "+list.previous());
		}
	}

	private static void displayColourPallet(LinkedList<String> colour_pallets) {
		ListIterator<String> list = colour_pallets.listIterator();
		while(list.hasNext()) {
			System.out.println("\n Clour Name : "+list.next());
		}
	}
}
