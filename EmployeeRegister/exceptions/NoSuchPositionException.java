package io.github.theuggla.EmployeeRegister.exceptions;

/**
 * A customized NoSuchPositionException
 * 
 * @author Molly Arhammar
 * @ version 1.0
 */

public class NoSuchPositionException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public NoSuchPositionException()
	{
		super("No Such Position Exception!");
	}
	
	/**
	 * @param message the message to be displayed when the exception is thrown
	 */
	public NoSuchPositionException(String message)
	{
		super(message);
	}

}
