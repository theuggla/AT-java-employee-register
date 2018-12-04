package io.github.theuggla.EmployeeRegister.models;

import java.io.Serializable;

/**
 * Objects of the class represents employees with
 * a name, an age and a wage.
 * @author Molly Arhammar
 * @version 1.0
 */

public class Employee implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	private String firstName = "";
	private String lastName = "";
	private double wage;
	private int age;
	
	/**
	 * Creates an Employee with a first and a last name
	 * and wage and age set to 0
	 * @param firstName the employees first name
	 * @param lastName the employees last name
	 */
	public Employee(String firstName, String lastName)
	{
		setName(firstName, lastName);
		setAge(0);
		setWage(0);
	}
	
	/**
	 * Creates an independent copy of another Employee object.
	 * @param otherEmployee the employee to copy
	 */
	public Employee(Employee otherEmployee)
	{
		this.firstName = otherEmployee.firstName;
		this.lastName = otherEmployee.lastName;
		this.wage = otherEmployee.wage;
		this.age = otherEmployee.age;
	}
	
	/**
	 * Creates an employee object with no name.
	 */
	public Employee()
	{
		this("No", "Name");
	}
	
	/**
	 * Returns the wage.
	 * @return the wage as a double
	 */
	public double getWage()
	{
		return wage;
	}
	
	/**
	 * Sets the wage.
	 * @param wage the wage to set
	 */
	public void setWage(double wage)
	{
		this.wage = wage;
	}
	
	/**
	 * Returns the employee's full name.
	 * @return first name and last name as a String;
	 */
	public String getName()
	{
		return (firstName + " " + lastName);
	}
	
	/**
	 * Returns the employees first name.
	 * @return the employees first name as a String
	 */
	public String getFirstName()
	{
		return this.firstName;
	}
	
	/**
	 * Returns the employees last name.
	 * @return the employees last name as a String
	 */
	public String getLastName()
	{
		return this.lastName;
	}
	
	/**
	 * Sets the employees first name and last name.
	 * @param firstName the employees desired first name
	 * @param lastName the employees desired last name
	 */
	public void setName(String firstName, String lastName)
	{
		//sets the first letter of the first name to upper case and the rest to lower case
		this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1, firstName.length()).toLowerCase();
		//sets the first letter of the last name to upper case and the rest to lower case
		this.lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1, lastName.length()).toLowerCase();
	}
	
	/**
	 * Returns the employees age
	 * @return the employees age as an int
	 */
	public int getAge()
	{
		return this.age;
	}
	
	/**
	 * Sets the employees age. If the age is less than 0, sets it to 0.
	 * @param newAge the age to set
	 */
	public void setAge(int newAge)
	{
		if (newAge > 0)
		{
			this.age = newAge;
		}
		else 
		{
			this.age = 0;
		}
	}
	
	/**
	 * Returns the employee's name as a String.
	 * @return the employees name as a String
	 */
	public String toString()
	{
		return this.getName();
	}
	
	/**
	 * Compares two employee objects.
	 * @returns true if the objects names are the same.
	 */
	public boolean equals(Object otherObject)
	{
		if (otherObject == null)
		{
			return false;
		}
		else if (!(otherObject instanceof Employee))
		{
			return false;
		}
		else
		{
			Employee otherEmployee = (Employee)otherObject;
			return ((this.firstName.equals(otherEmployee.firstName)) && (this.lastName.equals(otherEmployee.lastName)));
		}
	}
}

