package compexample;

import java.util.Comparator;
import java.awt.Color;

/**
 * Comparator is an interface imposing a total ordering on some collection of objects.
 * */

public class PeopleComparator implements Comparator{
    private String sortOrder;
    private int sortColumn;

	/*Your Job (*hint* you probabaly want to store the two arguments)
	The first argument takes a column that matches up with the
	constants Person.COL_LASTNAME, Person.COL_FIRSTNAME,
	Person.COL_PHONE, Person.COL_EMAIL, and Person.COL_COLOR
	The second matches ComparatorExample.ASCENDING and
	ComparatorExample.DESCENDING (two string constants).
	*/
	public PeopleComparator(int sortColumn, String sortOrder){
		this.sortOrder = sortOrder;
		this.sortColumn = sortColumn;

	}
	/*	Your Job (you must cast o1 and o2 to Person, but
	    make sure to check that they are indeed instanceof Person)
	    This MUST follow the Comparator interface.
	    To compare the colors, try using getColor() in
	    class Person. To compare the other fields, note
	    that many things are already comparable, using
	    someObj1.compareTo(someObj2)

	    It must follows Comparator specification.

	    compare takes in 2 objects and return int
	    negative number if o1 is "less" than o2
	    and 0 if they are equal and positive if o1
	    is greater than o2.
	*/
	public int compare(Object o1,Object o2){
   		//throw new RuntimeException("implement me");
		System.out.println("enter the compare with " + this.sortColumn);
		try {
			System.out.println("enter the try clause");
			Person p1 = (Person)o1;
			Person p2 = (Person)o2;
		} catch (Exception e) {
			System.out.println("exception: some objects not instance of Person");
			if (!(o1 instanceof Person)){
				System.out.println("o1 is not an instance of Person");
			}
			else if (!(o2 instanceof Person)) {
				System.out.println("o2 is not an instance of Person");
			}
			else {
				System.out.println("some other errors");
			}
		}
		System.out.println("pass the try clause");
		Person p1 = (Person)o1;
		Person p2 = (Person)o2;
		int multiplier;
		if (sortOrder == "ascending"){
			multiplier = 1;
		}
		else if (sortOrder == "descending"){
			multiplier = -1;
		}
		else {
			System.out.println("something unexpected");
			multiplier = 2;
		}
		int toReturn;
		if (sortColumn == 5){
			String c1 = p1.getColor().toString();
			String c2 = p2.getColor().toString();
			toReturn = multiplier * c1.compareTo(c2);
		}
		else {
			Object f1 = p1.getField(sortColumn);
			Object f2 = p2.getField(sortColumn);
			if (sortColumn == 2){
				String s1 = (String)f1;
				String s2 = (String)f2;
				String ss1 = s1.replaceAll("[^0-9.]", "");
				String ss2 = s2.replaceAll("[^0-9.]", "");
				int n1 =  Integer.parseInt(ss1);
				int n2 =  Integer.parseInt(ss2);
				System.out.println("n1 int = " + n1);
				System.out.println("n2 int = " + n2);
				toReturn = multiplier * compareInt(n1, n2);
			}
			else {
				String s1 = (String)f1;
				String s2 = (String)f2;
				System.out.println("s1 string = " + s1);
				System.out.println("s2 string = " + s2);
				toReturn = multiplier * s1.compareTo(s2);
			}
		}
		System.out.println(toReturn);
		return toReturn;
	}
	private int compareInt(int n1, int n2) {
		if (n1 > n2){
			return 1;
		}
		else if (n1 < n2){
			return -1;
		}
		else {
			return 0;
		}
	}
}
