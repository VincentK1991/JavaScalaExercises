package compexample;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
/**
 * the Person Data Type stores all the information for a person
 * 
 * @author rayhe
 *
 */
class Person{
	/**
	 *  constants that can be altered to store new fields
	 */
	public static final int COL_LASTNAME = 0;
	public static final int COL_FIRSTNAME = 1;
	public static final int COL_PHONE = 2;
	public static final int COL_EMAIL = 3;
	public static final int COL_COLOR = 4;
	public static final int NUM_FIELDS = 5;
	/**
	 * varibles that preserve field information with the class
	 */
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String eMail;
	private Color favoriteColor;
	/**
	 * ComparatorExample compExample is for callbacks, particularly so
	 * we can keep only 1 instance of the resource-intensive JDialog
	 * that chooses a color
	 */
	
	private ComparatorExample compExample = null; // for callbacks
	/**
	 * Just the simple constructor, the fields are filled in
	 * as people edit
	 * @param compExample
	 */
	public Person(ComparatorExample compExample){
		this.firstName = "new_entry";
		this.lastName = "";
		this.phoneNumber = "";
		this.eMail = "";
		this.favoriteColor = Color.RED;
		this.compExample=compExample;
	}
	/**
	 * heavier constructor to allow test-tables with code-initialized people
	 * @param firstName
	 * @param lastName
	 * @param phoneNumber
	 * @param eMail
	 * @param favoriteColor
	 * @param compExample
	 */
	public Person(String firstName,String lastName, String phoneNumber,String eMail, Color favoriteColor,ComparatorExample compExample){
		if (firstName == null || lastName == null || phoneNumber == null || eMail == null || favoriteColor == null)
			throw new IllegalArgumentException("Null element not accepted");
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.eMail = eMail;
		this.favoriteColor = favoriteColor;
		this.compExample=compExample;
	}
	/**
	 * the favorite color of the person
	 * @return a copy of a color for protection
	 */
	public Color getColor(){
		return new Color(favoriteColor.getRGB());
	}
	/**
	 * sets the favorite color to a safe copy of the color passed in
	 * @param c
	 */
	protected void setColor(Color c){
		favoriteColor = new Color(c.getRGB());
	}
	/** allows the manipulation of (currentlY) string fields
	 * 
	 * @param index to set
	 * @param value to modify index to
	 */
	public void setField(int index,Object value){
		if (index == COL_FIRSTNAME)
			firstName = (String)value;
		if (index == COL_LASTNAME)
			lastName = (String)value;
		if (index == COL_PHONE)
			phoneNumber = (String)value;
		if (index == COL_EMAIL)
			eMail = (String)value;
	}
	/** same as above but returns the current value
	 * 
	 * @param index to set
	 * @return the field as an object
	 */
	public Object getField(int index){
		if (index == COL_FIRSTNAME)
			return firstName;
		if (index == COL_LASTNAME)
			return lastName;
		if (index == COL_PHONE)
			return phoneNumber;
		if (index == COL_EMAIL)
			return eMail;
		if (index == COL_COLOR){
			JButton myButton = new JButton();
			myButton.setBorder(BorderFactory.createEmptyBorder());
			myButton.setBackground(favoriteColor);
			myButton.addMouseListener(new MouseAdapter(){
				 public void mouseClicked(MouseEvent e) {
					 setColor();
				 }
			});
			return myButton;
		}
		return null;
	}
	private void setColor(){
		compExample.setColor(this,favoriteColor);
	}
	/**
	 * matches a name to a specified column, useful in
	 * adding new fields
	 * @param index - the field column
	 * @return - the name associated with it (Strings)
	 */
	public static String getFieldColumn(int index){
		if (index == COL_FIRSTNAME)
			return "First Name";
		if (index == COL_LASTNAME)
			return "Last Name";
		if (index == COL_PHONE)
			return "Phone Number";
		if (index == COL_EMAIL)
			return "Email Address";
		if (index == COL_COLOR)
			return "Favorite Color";
		return null;
	}
	/**
	 * an arrayList of all of the fields in getFieldColumn,
	 * limited by NUM_FIELDS
	 * @return - a list of fields
	 */
	public static List<String> getAllFields(){
		List <String> myList = new ArrayList<String>();
		for (int i = 0;i < NUM_FIELDS;i++)
			myList.add(getFieldColumn(i));
		return myList;
	}
	public static Class getClass(int index){
		if (index == COL_COLOR)
			return JButton.class;
		return String.class;
	}
}
