package myjava.homework.part2;
import java.util.ArrayList;

/**
 * A item list to provide controller with adding and removing item name,
 * and displaying all the item names.
 * @author saberLiou
 */
public class ItemList {
	/**
	 * A item list.
	 */
	private ArrayList<String> id;
	
	/**
	 * Constructs a ItemList.
	 */
	public ItemList(){
		id = new ArrayList<String>();
	}
	
	/**
	 * Adds the item name in the item list.
	 * @param item the item name
	 */
	public void addItem(String item){
		id.add(item);
	}
	
	/**
	 * Removes the item name in the item list if the list contains it.
	 * @param item the item name
	 */
	public void remove(String item){
		if (!id.contains(item)){
			System.out.println(item + " isn't in the item list.");
		}
		else{
			id.remove(item);
		}
	}
	
	/**
	 * Prints all the item names in the item list.
	 */
	public void printList(){
		System.out.println("show the list");
		for (int i = 0; i < id.size(); i++){
			System.out.println((i + 1) + " : " + id.get(i));
		}
		/* As below, if I use id.indexOf(item) in for-each loop,
		 * items with same name will have the same index.
		 */
		/*
		for (String s : id){
			System.out.println((id.indexOf(s) + 1) + " : " + s);
		}*/
		
		System.out.println();
	}
}
