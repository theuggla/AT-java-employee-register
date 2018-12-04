package io.github.theuggla.EmployeeRegister.models;

import io.github.theuggla.EmployeeRegister.exceptions.*;

/**
 * Objects of the class represents hourly employees with
 * a name, an age, a position and a wage.
 * @author Molly Arhammar
 * @version 1.0
 */
public class HourlyEmployee extends Employee
{
	private static final long serialVersionUID = 1L;

	public enum Rank {UNKNOWN, JUNIOR, SENIOR, DUTY}; //the possible positions for an hourly employee
	
	private int maxHours;
	private int minHours;
	private Rank position;
	
	/**
	 * Creates an object of type hourly employee with a first and last name
	 * and sets the minimum and maximum hours to 0, and the position to unknown
	 * @param firstName the employee's first name
	 * @param lastName the employees last name
	 */
	public HourlyEmployee(String firstName, String lastName)
	{
		super(firstName, lastName);
		setMaxHours(0);
		setMinHours(0);
		
		try
		{	
			setPosition("UNKNOWN");
		}
		catch (NoSuchPositionException e)
		{
			System.out.print(e.getMessage());
		}
	}
	
	/**
	 * Creates an independent copy of another HourlyEmployee object.
	 * @param otherEmployee the employee to copy
	 */
	public HourlyEmployee(HourlyEmployee otherHourlyEmployee)
	{
		super(otherHourlyEmployee);
		this.maxHours = otherHourlyEmployee.maxHours;
		this.minHours = otherHourlyEmployee.minHours;
		this.position = otherHourlyEmployee.position;
	}
	
	/**
	 * Creates an hourly employee object with no name.
	 */
	public HourlyEmployee()
	{
		this("No", "Name");
	}
	
	/**
	 * Returns the minimum hours
	 * @return the minimum hours as an int
	 */
	public int getMinHours() 
	{
		return minHours; 
	}
	
	/**
	 * Sets the minimum hours. If minimum hours are
	 * less than 0, sets them to 0.
	 * @param newMinHours the minimum hours to set
	 */
	public void setMinHours(int newMinHours) 
	{
		if (newMinHours > 0)
		{
			this.minHours = newMinHours;
		}
		else
		{
			this.minHours = 0;
		}
	}
	
	/**
	 * Returns the maximum hours
	 * @return the maximum hours as an int
	 */
	public int getMaxHours() 
	{
		return maxHours;
	}

	/**
	 * Sets the maximum hours. If maximum hours are
	 * less than 0, sets them to 0.
	 * If they are less than minimum hours, sets them to
	 * minimum hours.
	 * @param newMaxHours the maximum hours to set
	 */
	public void setMaxHours(int newMaxHours) 
	{
		if (newMaxHours < minHours)
		{
			this.maxHours = minHours;
		}
		else if (newMaxHours > 0)
		{
			this.maxHours = newMaxHours;
		}
		else 
		{
			this.maxHours = 0;
		}
	}
	
	/**
	 * Returns the employee's wage.
	 * @returns the wage as a double
	 */
	public double getWage()
	{
		return super.getWage();
	}
	
	/**
	 * Sets the employee's wage according to a fixed
	 * rate depending on their position.
	 */
	public void setWage()
	{
		switch (getPosition())
		{
			case "UNKNOWN":
			{	
				super.setWage(0);
				break;
			}
			case "JUNIOR":
			{
				if (getAge() >= 25)
				{
					super.setWage(7.30); //hourly wage in pounds
				}
				else
				{
					super.setWage(7.15); //hourly wage in pounds
				}
				break;
			}
			case "SENIOR":
			{
				if (getAge() >= 25)
				{
					super.setWage(7.90); //hourly wage in pounds
				}
				else
				{
					super.setWage(7.75); //hourly wage in pounds
				}
				break;
			}
			case "DUTY":
			{
				super.setWage(8.85); //hourly wage in pounds
				break;
			}
		}
	}
	
	/**
	 * Returns the employees position
	 * @return the position as a String
	 */
	public String getPosition()
	{
		return position.toString();
	}
	
	/**
	 * Sets the employees position after a given String parameter.
	 * Updates the wage according to the new position.
	 * @param newPosition the position to set.
	 * @throws NoSuchPositionException if the position does not exist.
	 */
	public void setPosition(String newPosition) throws NoSuchPositionException
	{
		try
		{
			newPosition = newPosition.toUpperCase();
			this.position = Rank.valueOf(newPosition);
		}
		catch(IllegalArgumentException e)
		{
			throw new NoSuchPositionException("The position does not exist. ");
		}
		
		setWage(); //make sure to update the wage if the position has been changed
	}
	
	/**
	 * Returns the name, position, age, wage, minimum and maximum hours
	 * of the employee, formatted as a string.
	 * @return the employee information as a String
	 */
	public String toString()
	{
		return String.format("Name: %s%nPosition: %s%nAge: %d%nHourly pay: �%.2f%nMinimum hours/week: %d%nMaximum hours/week: %d%n", getName(), getPosition(), getAge(), getWage(), getMinHours(), getMaxHours());
	}
	
	/**
	 * Compares two employee objects.
	 * @return true if the employees have the same name
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
			Employee otherHourlyEmployee = (Employee)otherObject;
			return ((super.equals(otherHourlyEmployee)) );
		}
	}
}
