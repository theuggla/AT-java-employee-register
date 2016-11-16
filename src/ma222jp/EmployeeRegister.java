package ma222jp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.EOFException;

/**
 * Name: EmployeeRegister
 * Date: 2016-07-10
 * 
 * The class holds an ArrayList that stores objects of type Employee.
 * There are methods to add, remove and search for Employees.
 * The list can be saved and loaded and printed to String.
 * 
 * @author Molly Arhammar
 * @version 1.0
 */

public class EmployeeRegister
{
	ArrayList<Employee> theRegister;
	
	/**
	 * Creates a new EmployeeRegister object and initiates the list to 20.
	 */
	public EmployeeRegister()
	{
		theRegister = new ArrayList<Employee>(20);
	}
	
	/**
	 * Creates a new EmployeeRegister object.
	 * @param size the initial size of the list.
	 */
	public EmployeeRegister(int size)
	{
		theRegister =  new ArrayList<Employee>(size);
	}
	
	/**
	 * Makes an exact independent copy of another EmployeeRegister.
	 * @param otherEmployeeRegister
	 */
	public EmployeeRegister(EmployeeRegister otherEmployeeRegister)
	{
		theRegister = new ArrayList<Employee>(otherEmployeeRegister.theRegister.size());
		
		//makes an independent copy of each of the employees in the other EmployeeRegister
		for (int i = 0; i < theRegister.size(); i++)
		{
			this.theRegister.add(new Employee(otherEmployeeRegister.theRegister.get(i)));
		}
	}
	
	/**
	 * Loads all the objects in a previously saved list and makes a new list that consists of them.
	 * Makes a new EmployeeRegister object with this list.
	 * @return the new EmployeeRegister object
	 * @throws ClassNotFoundException if the objects can't be read from the file
	 * @throws IOException if the file can't be read or found
	 */
	public static EmployeeRegister loadRegister() throws ClassNotFoundException, IOException
	{ 
		EmployeeRegister loadedReg = new EmployeeRegister();
		ObjectInputStream loadingStream = null;
		
		try
		{
			loadingStream = new ObjectInputStream(new FileInputStream("savedregister"));
			
			while(true)
			{
				loadedReg.addEmployee((Employee)loadingStream.readObject());
			}
		}
		catch(EOFException e)
		{
			return loadedReg;
		} 
		finally
		{
			loadingStream.close();
		}
	}
	
	/**
	 * Adds a new Employee to the list.
	 * @param newEmployee the Employee to add to the list.
	 */
	public void addEmployee(Employee newEmployee)
	{
		theRegister.add(newEmployee);
	}

	/**
	 * Removes an Employee from the list.
	 * @param unwantedEmployee the Employee to remove
	 * @throws NoSuchEmployeeException if the Employee is not in the list
	 */
	public void removeEmployee(Employee unwantedEmployee) throws NoSuchEmployeeException
	{ 
		if (theRegister.indexOf(unwantedEmployee) != -1) //the Employee is in the list
		{
			theRegister.remove(theRegister.indexOf(unwantedEmployee));
		}
		else
		{
			throw new NoSuchEmployeeException();
		}
	}
	
	/**
	 * @param targetEmployee the employee to find
	 * @return the Employee matching the target Employee
	 * @throws NoSuchEmployeeException if the Employee is not in the list
	 */
	public Employee findEmployee(Employee targetEmployee) throws NoSuchEmployeeException
	{ 
		if (theRegister.indexOf(targetEmployee) != -1) //the employee is in the list
		{
			return theRegister.get(theRegister.indexOf(targetEmployee));
		}
		else
		{
			throw new NoSuchEmployeeException();
		}
	 }
	
	/**
	 * Returns a String representation of the register, consisting of a numbered list
	 * of the names of all the Employees in it.
	 * @return the names of all the Employees as a String
	 */
	public String toString()
	{ 
		StringBuilder theList = new StringBuilder("");
		
		for (int i = 1; i <= theRegister.size(); i++)
		{
			theList.append(String.format("%d. %s%n", i, (theRegister.get(i-1).getName())));
		}
		
		return theList.toString();
	}
	
	/**
	 * Sorts the list according to a custom comparator.
	 * @param customComparator the comparator to sort by
	 */
	public void sortRegister(Comparator<Employee> customComparator)
	{
		Collections.sort(theRegister, customComparator);
	}
	
	/**
	 * Deletes all the posts in the list.
	 */
	public void emptyRegister()
	{
		theRegister.clear();
	}
	
	/**
	 * Saves all the objects in the list to a binary file.
	 * @throws IOException if the list can't be saved
	 */
	public void saveRegister() throws IOException
	{ 
		ObjectOutputStream saveStream = new ObjectOutputStream(new FileOutputStream("savedregister"));
			
		for (Employee emp : theRegister)
		{
			saveStream.writeObject(emp);
		}
			
		saveStream.close();
	}
}
