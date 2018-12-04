package io.github.theuggla.EmployeeRegister;

import io.github.theuggla.EmployeeRegister.models.*;
import io.github.theuggla.EmployeeRegister.persistance.*;
import io.github.theuggla.EmployeeRegister.exceptions.*;
import io.github.theuggla.EmployeeRegister.ui.*;

import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Name: Main
 * Date: 2016-07-10
 * 
 * The program lets the user make a list of Hourly or Salaried
 * employees and store information about those employees.
 * The list can be sorted after certain parameters, saved,
 * and loaded again at a later date.
 * 
 * @author Molly Arhammar
 * @version 1.0
 */

public class Main
{
	private static Scanner sc = new Scanner(System.in);
	private static EmployeeRegister employeeList = new EmployeeRegister();
	private static Menu mainMenu = new Menu(new String [] {"Show Employees", "Add Employee", "Remove Employee", 
															"Find Employee", "Show Employee Details", "Update Employee Details", 
															"Sort List", "Save List", "Load List", "Clear List", "Exit Program"});
	
	/*
	 * Runs the main menu method until the user chooses to exit the program.
	 */
	public static void main(String[] args) 
	{
		System.out.println("Hi! I am a program that lists employees.");
		
		while (true)
		{
			showMainMenu();
		}
	}
	
	/*
	 * Asks the user if they want to show the menu. If they do, lets them make a choice.
	 * If they don't show the menu, ask them if they want to exit the program. If they
	 * don't, run the method again.
	 */
	private static void showMainMenu()
	{
			System.out.println();
			if (YOrN("Show menu?"))
			{
				System.out.println();
				System.out.println(mainMenu);

				do
				{
					sc.nextLine(); //to empty the scanner
					System.out.print("Please choose the number that corresponds to your desired action: ");
					
				}while (!sc.hasNextInt()); //make sure choice is an int
				
				int choice = sc.nextInt();
				choose(choice);
			}
			else if(YOrN("Exit program?"))
			{
				exitProgram();
			}
			else
			{
				showMainMenu();
			}	
	}
	
	/*
	 * Sends the user to a method corresponding to the number of their choice of the menu.
	 */
	private static void choose(int choice)
	{
		switch (choice)
		{
			case 1:
				showList();
				break;
			case 2:
				addEmployee();
				break;
			case 3:
				removeEmployee();
				break;
			case 4:
				findEmployee();
				break;
			case 5:
				showEmployee();
				break;
			case 6:
				updateEmployee();
				break;
			case 7:
				sortList();
				break;
			case 8:
				saveList();
				break;
			case 9:
				loadList();
				break;
			case 10:
				clearList();
				break;
			case 11:
				exitProgram();
				break;
			default:
				System.out.println("That number is not on the menu.");
				showMainMenu();
				break;
		}
	}
	
	/**
	 * Prints out the current list of employees to the screen.
	 */
	public static void showList()
	{
		if (employeeList.theRegister.size() > 0)
		{
			System.out.println(employeeList);
		}
		else
		{
			System.out.println("There are no items in the list!");
		}
	}
	
	/**
	 * Adds an employee to the list. Gets information about the employee from the user.
	 */
	public static void addEmployee()
	{
		String answer = "";
		
		do
		{
			System.out.print("Do you want to add an HOURLY or a SALARIED employee? ");
			answer = sc.next();
		}while (!(answer.equalsIgnoreCase("hourly") || answer.equalsIgnoreCase("salaried")));
			
		if (answer.equalsIgnoreCase("Hourly"))
		{
			HourlyEmployee toAdd = new HourlyEmployee();
			setHourlyEmployee(toAdd);
			employeeList.addEmployee(toAdd);
		}
		else if (answer.equalsIgnoreCase("Salaried"))
		{
			SalariedEmployee toAdd = new SalariedEmployee();
			setSalariedEmployee(toAdd);
			employeeList.addEmployee(toAdd);
		}
		
		System.out.println("Employee successfully added!");
	}
	
	/*
	 * Gets the information for an Hourly Employee  from the user.
	 */
	private static HourlyEmployee setHourlyEmployee(HourlyEmployee emp)
	{
		getEmployeeInformation(emp);
		
		int minHours = 0;
		int maxHours = 0;
		String position = "";
		
		boolean correctAnswer = false;
		while (!correctAnswer)
		{
			try
			{
				System.out.print("Enter the employee's position (JUNIOR, SENIOR, DUTY)");
				position = sc.next();
				emp.setPosition(position);
				correctAnswer = true;
			}
			catch (NoSuchPositionException e)
			{
				System.out.print(e.getMessage());
			}	
		}
			
		System.out.print("Enter the employee's minimum hours per week: ");
		
		while (!sc.hasNextInt()) //make sure the hours are entered as an int
		{
			sc.nextLine(); //empty the scanner
			System.out.print("Enter the hours as an integer: ");
		}

		minHours = sc.nextInt();
		emp.setMinHours(minHours);
			
		System.out.print("Enter the employee's maximum hours per week: ");
		
		while (!sc.hasNextInt()) //make sure the hours are entered as an int
		{
			sc.nextLine(); //empty the scanner
			System.out.print("Enter the hours as an integer: ");
		}
			
		maxHours = sc.nextInt();
		emp.setMaxHours(maxHours);
		
		return emp;	
	}
	
	/*
	 * Gets the information for a Salaried Employee from the user.
	 */
	private static SalariedEmployee setSalariedEmployee(SalariedEmployee emp)
	{
		String position = "";
		
		getEmployeeInformation(emp);
		
		boolean correctAnswer = false;
		while (!correctAnswer)
		{
			try
			{
				System.out.print("Enter the employee's position (VENUE, ASSISTANT)");
				position = sc.next();
				emp.setPosition(position);
				correctAnswer = true;
			}
			catch (NoSuchPositionException e)
			{
				System.out.print(e.getMessage());
			}	
		}
		
		return emp;
	}
	
	/*
	 * Gets generic Employee information from the user.
	 */
	private static Employee getEmployeeInformation(Employee emp)
	{
		String firstName = "";
		String lastName = "";
		int age = 0;
		
		sc.nextLine(); // empty the scanner
		
		System.out.print("Enter the first and last name of the employee, separated by a space: ");
		String employee = sc.nextLine();
		int separator = employee.indexOf(" ");
		
		if (separator != -1)// extract first and last name
		{
			firstName = employee.substring(0, separator);
			lastName = employee.substring((separator + 1), employee.length());
		}
		else //if there is only one name
		{
			firstName = employee;
			lastName = "Unknown";
		}
		
		emp.setName(firstName, lastName);
		
		System.out.print("Enter the employee's age: ");
		while (!sc.hasNextInt()) //make sure the age is an int
		{
			sc.nextLine(); //empty the scanner
			System.out.print("That's not the age. Try again: ");
		}

		age = sc.nextInt();
		emp.setAge(age);
		
		return emp;
	}
	
	/**
	 * Removes an employee from the list. Gets the information
	 * about what employee to remove from the user.
	 */
	public static void removeEmployee()
	{
		//create target employee to use as template for the search
		Employee targetEmployee = makeTargetEmployee("Please enter the first and last name of the employee you would like to remove, separated by a space: ");

		try 
		{
			employeeList.removeEmployee(targetEmployee);
			System.out.println("Employee successfully removed!");
		} 
		catch (NoSuchEmployeeException e) 
		{
			System.out.println("The employee you are trying to remove is already not in the list. Congratulations!");
		}
		
	}
	
	/**
	 * Search for an employee in the list. Gets the information
	 * about what employee to search for from the user.
	 * If the employee is found, displays their details on the screen.
	 * If the employee can't be found in the list, asks if the
	 * user wants to add them.
	 */
	public static void findEmployee()
	{
		//create target employee to use as template for the search
		Employee targetEmployee = makeTargetEmployee("Please enter the first and last name of the employee you would like to find, separated by a space: ");

		try 
		{
			Employee desiredEmployee = employeeList.findEmployee(targetEmployee);
			System.out.println(desiredEmployee);
		} 
		catch (NoSuchEmployeeException e) 
		{
			if(YOrN("The employee you are looking for is not in the list. Would you like to add them?"))
			{
				addEmployee();
			}
		}
	}
	
	/**
	 * Displays a chosen employees details to the screen. Gets the information
	 * about what employee to display from the user. If the employee can't be ford, asks if the
	 * user wants to try the search again.
	 */
	public static void showEmployee()
	{
		//create target employee to use as template for the search
		Employee targetEmployee = makeTargetEmployee("Please enter the first and last name of the employee you would like to show, separated by a space: ");

		try 
		{
			Employee desiredEmployee = employeeList.findEmployee(targetEmployee);
			System.out.println(desiredEmployee);
		} 
		catch (NoSuchEmployeeException e) 
		{
			if(YOrN("The employee you are looking for is not in the list. Try again?"))
			{
				showEmployee();
			}
		}
	}
	
	/**
	 * Lets the user update an employee that's already in the list.
	 * Prints the employees details to the screen before and after the update.
	 * Gets the information about what employee to update from the user.
	 * If the employee can't be found in the list, asks if the user wants
	 * to try the search again.
	 */
	public static void updateEmployee()
	{
		//create target employee to use as template for the search
		Employee targetEmployee = makeTargetEmployee("Please enter the first and last name of the employee you would like to update, separated by a space: ");
		
		try 
		{
			Employee desiredEmployee = employeeList.findEmployee(targetEmployee);
			System.out.println(desiredEmployee);
			System.out.println("Press enter to add the new details.");
			
			//updates the employee
			if (desiredEmployee instanceof HourlyEmployee)
			{
				setHourlyEmployee((HourlyEmployee)desiredEmployee);
			}
			else if (desiredEmployee instanceof SalariedEmployee)
			{
				setSalariedEmployee((SalariedEmployee)desiredEmployee);
			}
			System.out.println("New employee details: ");
			System.out.println(desiredEmployee);
			System.out.println("Employee successfully updated!");
		} 
		catch (NoSuchEmployeeException e) 
		{
			if(YOrN("The employee you are looking for is not in the list. Sorry. Search again?"))
			{
				updateEmployee();
			}
		}
		
	}
	
	/**
	 * Sorts the list according to the sorting parameter
	 * the user chooses. Asks if the user wants to display the sorted list.
	 */
	public static void sortList()
	{
		//create menu to display the sorting parameters
		Menu sortMenu = new Menu(new String[] {"Age (low to high)", "Name (alphabetical by first name)", "Wage (low to high)"});
		
		System.out.println();
		System.out.println(sortMenu);
		
		System.out.print("What would you like to sort after? Choose the corresponding number: ");
		
		while (!sc.hasNextInt()) //make sure the number is entered as an int
		{
			sc.nextLine(); //empty the scanner
			System.out.print("That's not a proper number. Try again: ");
		}
		
		int choice = sc.nextInt();
		
		switch (choice)
		{
			case 1: //make an age comparator to send as an argument
				employeeList.sortRegister(new Comparator<Employee>() {public int compare(Employee emp1, Employee emp2) {return emp1.getAge() - emp2.getAge();}});
				break;
			case 2: //make an alphabetical comparator to send as an argument
				employeeList.sortRegister(new Comparator<Employee>() {public int compare(Employee emp1, Employee emp2) {return emp1.getName().compareTo(emp2.getName());}});
				break;
			case 3: //make an wage comparator to send as an argument
				employeeList.sortRegister(new Comparator<Employee>() {public int compare(Employee emp1, Employee emp2) {return (int) (emp1.getWage() - emp2.getWage());}});
				break;
			default:
				System.out.println("That number is not on the list!");
				sortList();
				break;
		}

		if (YOrN("Would you like to show the sorted list?"))
		{
			showList();
		}
		else
		{
			showMainMenu();
		}
	}
	
	/**
	 * Saves the current list. Makes sure the uses knows that any other saved lists will
	 * be written over. If the list can't be saved, asks if the user wants to try again.
	 */
	public static void saveList()
	{
		System.out.println("Your previusly saved list, if any, will be written over.");
		
		if (YOrN("Are you sure you would like to save?"))
		{
			try
			{
				employeeList.saveRegister();
				System.out.println("List saved! Have a nice day.");
			}
			catch (IOException e)
			{
				if(YOrN("Something seems to have gone wrong with saving. Apologies. Try again?"))
				{
					saveList();
				}
			}
		}
	}
	
	/**
	 * Loads the most recently saved list. Makes sure the user knows that
	 * the current unsaved list will be written over. If the list can't be loaded
	 * asks, if the user wants to try again.
	 */
	public static void loadList()
	{
		System.out.println("Your current work in progress will be written over.");
		
		if (YOrN("Are you sure you would like to load the old list?"))
		{
			try
			{
				employeeList = EmployeeRegister.loadRegister();
				System.out.println("List successfully loaded.");
			}
			catch (ClassNotFoundException e)
			{
				if(YOrN("Something went wrong while reading the file. Try again?"))
				{
					loadList();
				}
			}
			catch (IOException e)
			{
				if(YOrN("Something went wrong while finding the saved file. Make sure you have a saved file. Try again?"))
				{
					loadList();
				}
			}
		}
	}
	
	/**
	 * Deletes all the items on the list.
	 * Asks if the user is sure before deleting.
	 */
	public static void clearList()
	{
		System.out.println("Your list will be deleted.");
		
		if (YOrN("Are you sure you would like to clear the list?"))
		{
			employeeList.emptyRegister();
			System.out.println("List have been cleared.");
		}
	}
	
	/**
	 * Terminates the program.
	 * Asks if the user wants to save first.
	 */
	public static void exitProgram()
	{
		if (YOrN("Would you like to save before you exit?"))
		{
			saveList();
			System.out.println("Bye bye now.");
			System.exit(0);
		}
		else
		{
			System.out.println("Bye bye now.");
			System.exit(0);
		}
	}
		
	/*
	 * Asks the user a question and prompts them to answer
	 * Y or N. Loops until the user chooses one
	 * of the two.
	 * @param question the question to ask
	 * @return true if the user answers y
	 */
	private static boolean YOrN(String question)
	{	
		while (true)
		{
			System.out.printf("%s (Y/N): ", question);
			String answer = sc.next();
			
			if (answer.equalsIgnoreCase("Y"))
			{
				return true;
			}
			else if (answer.equalsIgnoreCase("N"))
			{
				return false;
			}
		}
	}
	
	/*
	 * Creates a generic target employee of type Employee
	 * with the name of the users choosing to use as a template
	 * for finding an employee of the same name in the list.
	 * @param question the question to prompt the user for a name
	 * @return the target Employee
	 */
	private static Employee makeTargetEmployee(String question)
	{
		System.out.println(question);
		
		String firstName = "";
		String lastName = "";
		
		sc.nextLine(); //to empty the scanner
		
		String employee = sc.nextLine();
		int separator = employee.indexOf(" ");
		
		if (separator != -1) //creates a first name and a last name from the string
		{
			firstName = employee.substring(0, separator);
			lastName = employee.substring((separator + 1), employee.length());
		}
		else //if only one name can be found
		{
			firstName = employee;
			lastName = "Unknown";
		}
		
		Employee targetEmployee = new Employee(firstName, lastName);
		
		return targetEmployee;
	}
}
