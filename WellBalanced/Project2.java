import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * <p>Title: Project2 Class</p>
 *
 * <p>Description: This class opens a file and hands it over to the checkCharacter method of the WellBallanced class. 
 * The main purpose of this program is to ,ake sure that the text in the file have proper number of parentheses, curly braces 
 * and square brackets. However, if File is not found the user will be informed via an error message</p>
 * 
 * @author Ume Abbas
 */
public class Project2 {
	
	public static void main(String[] args ) throws FileNotFoundException
	{
		try
		{
		WellBalanced balancing = new WellBalanced();
		Scanner scan = new Scanner(new File("project2.txt"));
		balancing.checkCharacter(scan);
		}
		catch(FileNotFoundException error)
		{
			System.out.println(error.getMessage());
		}
		catch(IllegalStateException ex)
		{
			System.out.println(ex.getMessage());
		}
		
	}

}
