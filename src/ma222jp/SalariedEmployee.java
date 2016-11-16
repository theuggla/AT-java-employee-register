package ma222jp;

public class SalariedEmployee extends Employee
{
	public enum Rank {UNKNOWN, ASSISTANT, VENUE}; //the possible positions for a salaried employee
	
	private Rank position;
	
	/**
	 * Creates an object of type hourly employee with a first and last name
	 * and sets the position to unknown
	 * @param firstName the employee's first name
	 * @param lastName the employees last name
	 */
	public SalariedEmployee(String firstName, String lastName)
	{
		super(firstName, lastName);
		
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
	 * Creates an independent copy of another SalariedEmployee object.
	 * @param otherEmployee the employee to copy
	 */
	public SalariedEmployee(SalariedEmployee otherSalariedEmployee)
	{
		super(otherSalariedEmployee);
		this.position = otherSalariedEmployee.position;
	}
	
	/**
	 * Creates an hourly employee object with no name.
	 */
	public SalariedEmployee()
	{
		this("No", "Name");
	}
	
	/**
	 * Returns the employees estimated hourly wage, if they work 40 hours a week.
	 * @return the wage as a double
	 */
	public double getWage()
	{
		return (super.getWage() / 52 / 40); //the hourly wage of a salaried employee working 40 hours a week
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
			case "ASSISTANT":
			{
				super.setWage(17000); //yearly wage in pounds
				break;
			}
			case "VENUE":
			{
				super.setWage(20000); //yearly wage in pounds
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
	 * Returns the name, position, age and estimated hourly wage
	 * of the employee, formatted as a string.
	 * @return the employee information as a String
	 */
	public String toString()
	{
		return String.format("Name: %s%nPosition: %s%nAge: %d%nEstimated Hourly pay: £%.2f%n", getName(), getPosition(), getAge(), getWage());
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
			Employee otherSalariedEmployee = (Employee)otherObject;
			return (super.equals(otherSalariedEmployee));
		}
	}
}
