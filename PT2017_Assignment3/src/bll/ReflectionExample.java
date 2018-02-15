package bll;

import java.lang.reflect.Field;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ReflectionExample {
	//Reflection is a feature in the Java programming language. 
	//It allows an executing Java program to examine or "introspect" upon itself, and manipulate internal properties of the program. 
	//For example, it's possible for a Java class to obtain the names of all its members and display them.
	
	//Java Generic methods and generic classes enable programmers to specify, with a single method declaration, 
	//a set of related methods, or with a single class declaration, a set of related types, respectively.

	public static void retrieveProperties(Object object) {

		for (Field field : object.getClass().getDeclaredFields()) {//obtain the class of the object and request the declared fields of that class
			field.setAccessible(true); // Set the field accessible ( most of them are private => need to change permissions)
			Object value;
			try {
				value = field.get(object);//Return the value of the current field contained in the specified object
				System.out.println(field.getName() + "=" + value);//Print the field’s name and its value

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}
	}
	
	public static JTable createT (List<Object> objects) {
		String[] cols = new String[objects.get(0).getClass().getDeclaredFields().length];
		Object o = objects.get(1);
		int i = 0;
		for (Field field : o.getClass().getDeclaredFields()) {
			//A Field provides information about, and dynamic access to, a single field of a class or an interface. 
			//The reflected field may be a class (static) field or an instance field.
			field.setAccessible(true);//A value of true indicates that the reflected object should suppress Java language access checking when it is used.
			try{
				cols[i] = field.getName();
				i++;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		DefaultTableModel defaultTable = new DefaultTableModel(cols, 0);//uses a vector of vectors
		//Constructs a DefaultTableModel with as many columns as there are elements in columns and rowCount (which is 0) of null object values. 
		//Each column's name will be taken from the columns array.
		
		for (i = 0; i < objects.size(); i++) {
			Object[] row = new Object[objects.get(i).getClass().getDeclaredFields().length];
			int j = 0;
			for (Field field : objects.get(i).getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object obj;
				try{
					obj = field.get(objects.get(i));//Returns the value of the field represented by this Field, on the specified object.
					//The value is automatically wrapped in an object if it has a primitive type.
					row[j] = obj;
					j++;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			defaultTable.addRow(row);
		}
		JTable table = new JTable(defaultTable);
		
		return table;
	}
}