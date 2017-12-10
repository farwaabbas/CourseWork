import java.util.Scanner;
/**
 * <p>Title: WellBalnaced Class</p>
 *
 * <p>Description: This class is responsible for checking the format of the file sent by the user. It will count the number
 * of left with right parentheses, curly braces, square brackets and will make sure that htey are equal. Meaning the 
 * program will check their proper pairing. If the user have not properly paired these right parentheses, curly braces, square brackets
 * then the user will get an error message. This class will also print the text so that the user is able to know his/her mistake. </p>
 * 
 * @author Ume Abbas
 */
public class WellBalanced {

	private DynamicArrayStack<Character> stack;

	/** WellBallanced--
	 * creates an empty stack of type Character.
	 */
	public WellBalanced()
	{
		stack = new DynamicArrayStack<>();
	}

	/** WellBallanced--
	 * creates an empty stack of type Character of length provided by the user.
	 * @param-- capacity accepts the length of a stack. 
	 */

	public WellBalanced(int capacity)
	{
		stack = new DynamicArrayStack<>(capacity);
	}
	
	/**checkCharacter-- 
	 * read each character from a file one by one, prints the character and make sure that the parentheses,
	 * curly braces and square brackets are : equal and paired. 
	 * @param-- file will accept a scanner object containg the file sent by the user. 
	 * @throws--IllegalStateException if the format is improper.
	 */
	public void checkCharacter(Scanner file)
	{
		char poppedCharacter ;
		int right = 0; // counts the number of ( { [
		int left  = 0; // counts the number of ) } ]
		String character = ""; // will read one line at a time from a file.
		int length = 0 ; // will store the length of character
		char eachChar = 'a'; // will store one character from the character variable holding a String of characters.

		while(file.hasNextLine())
		{
			character = file.nextLine();
			length = character.length();
			for(int i = 0 ; i < length ; i++)	
			{
				eachChar = character.charAt(i);
				Print(eachChar);
				if(eachChar == '(' || eachChar == '[' || eachChar == '{')
				{
					stack.push(eachChar);
					left = stack.size();
				}
				else 
					if(eachChar == ')' || eachChar == ']' || eachChar == '}')
					{
						checkIfRightEqualsLeft();
						poppedCharacter = stack.pop();
						if(!(compareCharacters(poppedCharacter,eachChar)))
							throw new IllegalStateException("\nThe format does not have well balanced parenthesis");
						right++ ;
					}	
			}
		}checkIfLeftEqualsRight(left,right);
	}

	/**checkIfRightEqualsLeft--  
	 * This method makes sure that after popping all left parentheses and braces, there are no extra right ones left.
	 * @throws--IllegalStateException if the format is improper.
	 */
	private void checkIfRightEqualsLeft() // will keep track of the number of extra right parenthesis,braces
	{
		if(stack.isEmpty())
			throw new IllegalStateException("\nThere are extra right parenthesis, curly braces or square brackets");
	}
	/**checkIfLeftEqualsRight--  
	 * This method makes sure that after pairing with all right parentheses and braces, there are no extra left ones left.
	 * @throws--IllegalStateException if the format is improper.
	 */
	private void checkIfLeftEqualsRight(int left, int right )
	{
		if(left != right)
			throw new IllegalStateException("\nThere are extra left parenthesis, curly braces or square brackets");
	}
	/**compareCharacters--  
	 * This method makes sure that the popped and the read parentheses or braces(curly and squared)
	 * from the file are of the same type.
	 * @return-- true if they are same and false otherwise.
	 */
	private boolean compareCharacters(char poppedCharacter , char readCharacter)
	{
		if(poppedCharacter == '(' && readCharacter == ')')
			return true;
		else 
			if(poppedCharacter == '[' && readCharacter == ']')
				return true ;
			else 
				if(poppedCharacter == '{' && readCharacter == '}')
					return true ;

		return false ;

	}

	/**Print--  
	 * Prints a character 
	 * @return-- c stores the character that has to be printed.
	 */
	private void Print(char c)
	{
		System.out.print(c);
	}

}
