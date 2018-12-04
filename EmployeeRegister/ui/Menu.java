package io.github.theuggla.EmployeeRegister.ui;

/**
 * Name: Menu
 * Date: 2016-07-10
 * 
 * The class takes an array of Strings and
 * presents them as a numbered list.
 * 
 * @author Molly Arhammar
 * @version 1.0
 */

public class Menu 
{
	private String[] menuItems;
	
	/**
	 * Makes a Menu object with posts from the array.
	 * @param items the array of Strings to be used as posts on the menu
	 */
	public Menu(String[] items)
	{
		menuItems = new String[items.length];
		
		for (int i = 0; i < items.length; i++)
		{
			menuItems[i] = items[i]; //no risk of privacy leak because objects are of type String
		}
	}
	
	/**
	 * Makes a copy of another Menu.
	 * @param otherMenu the Menu to copy
	 */
	public Menu(Menu otherMenu)
	{
		this(otherMenu.menuItems);
	}
	
	/**
	 * Makes a menu without posts.
	 */
	public Menu()
	{
	}
	
	/**
	 * Returns a String representation of the menu.
	 * 
	 * @return the Menu as a String Object with each post on a new row, 
	 *  numbered from 1 to n.
	 */
	public String print()
	{
		return this.toString();
	}
	
	/**
	 * Returns a String representation of the menu.
	 * 
	 * @return the Menu as a String Object with each post on a new row, 
	 *  numbered from 1 to n.
	 */
	public String toString()
	{
		if (menuItems == null || menuItems.length == 0)
		{
			return "No choices on the menu.";
		}
		else
		{
			StringBuilder printedMenu = new StringBuilder("");
		
			for (int i = 0; i < menuItems.length; i++)
			{
				printedMenu.append(String.format("%d. %s%n", (i + 1), menuItems[i])); //number each post from 1 to n and put on separate row
			}
		
			return printedMenu.toString();
		}
	}
	
	/**
	 * Returns the number of items on the menu.
	 * @return the number of items on the menu as an int
	 */
	public int length()
	{
		return menuItems.length;
	}
	
	/**
	 * Compares two menu objects.
	 * @return true if both objects contains the same items in the same order
	 */
	public boolean equals(Object otherObject)
	{
		if (otherObject == null)
		{
			return false;
		}
		else if (getClass() != otherObject.getClass())
		{
			return false;
		}
		else
		{
			Menu otherMenu = (Menu)otherObject;
			
			if (this.length() != otherMenu.length())
			{
				return false;
			}
			else
			{
				for (int i = 0; i < menuItems.length; i++)
				{
					if (this.menuItems[i] != otherMenu.menuItems[i])
					{
						return false; //as soon as two items don't match
					}
				}	
			}
			
			return true; //if all items matched
		}
	}

}
