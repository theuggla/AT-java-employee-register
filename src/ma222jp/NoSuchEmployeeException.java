package ma222jp;

/**
 * A customized NoEmployeeException
 * 
 * @author Molly Arhammar
 * @ version 1.0
 */

public class NoSuchEmployeeException  extends Exception
{
	public NoSuchEmployeeException()
	{
		super("No such Employee Exception!");
	}
	
	/**
	 * @param message the message to be displayed when the exception is thrown
	 */
	public NoSuchEmployeeException(String message)
	{
		super(message);
	}

}
