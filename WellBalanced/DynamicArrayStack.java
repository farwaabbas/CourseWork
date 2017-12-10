/**
 * <p>Title: DynamicArrayStack Class</p>
 *
 * <p>Description: This class is responsible for checking the format of the file sent by the user. It will count the number
 * of left with right parentheses, curly braces, square brackets and will make sure that htey are equal. Meaning the 
 * program will check their proper pairing. If the user have not properly paired these right parentheses, curly braces, square brackets
 * then the user will get an error message. This class will also print the text so that the user is able to know his/her mistake. </p>
 * 
 * @author  Joseph Svitak and Ume Abbas
 */

public class DynamicArrayStack<AnyType> implements Stack<AnyType>
{
	public static final int DEFAULT_CAPACITY = 1024;
	AnyType[] data;
	int topOfStack;

	/** DynamicArrayStack--
	 * The default constructor assigns a default_Capacity as a length for the array data.
	 */
	public DynamicArrayStack() { this(DEFAULT_CAPACITY); }

	/** DynamicArrayStack--
	 * The parameterized constructor assigns the capacity for the array data provided by the user.
	 * @param-- capacity accepts the size for an array data.
	 */
	@SuppressWarnings("unchecked")
	public DynamicArrayStack(int capacity)
	{
		topOfStack = -1;
		data = (AnyType[]) new Object[capacity];
	}

	/**size--
	 * returns the number of elements in the array named data.
	 * @return-- number of elements.
	 */
	public int size()
	{
		return topOfStack + 1 ;

	}

	/**isEmpty--
	 * Check if the stack is empty
	 * @return-- true if there is no element in data else returns false.
	 */
	public boolean isEmpty()
	{
		return(size() == 0) ;
	}

	/**push--
	 * push the new value at the top of the stack.
	 * @param-- newValue accepts the value to be inserted.
	 */
	@SuppressWarnings("unchecked")
	public void push(AnyType newValue)
	{
		if(topOfStack == (data.length-1))
		{
			int n = size();
			AnyType[] temp = (AnyType[]) new Object[topOfStack * 2]; 
			for (int i=0; i < n; i++) 
				temp[i] = data[i]; 
			data = temp;
		}
		data[++topOfStack] = newValue ;
	}
	/**top--
	 * returns the value at the top of the stack.
	 * @return-- the top most element.
	 * @throws-- IllegalStateException if the stack is empty.
	 */
	public AnyType top()
	{
		if(isEmpty())
			throw new IllegalStateException("The Stack is Empty");
		else
			return data[topOfStack];
	}
	/**pop--
	 * removes the top most element in the stack.
	 * @return-- top removed item.
	 * @throws-- IllegalStateException if the stack is empty.
	 */
	public AnyType pop()
	{
		if(isEmpty()) throw new IllegalStateException("The Stack is Empty");
		else
			if(size() <= (data.length / 4))
			{
				int n = size();
				AnyType[] temp = (AnyType[]) new Object[data.length / 2]; 
				for (int i=0; i < n; i++) 
					temp[i] = data[i]; 
				data = temp;
			}
		AnyType item = data[topOfStack];
		data[topOfStack] = null ;
		topOfStack-- ;
		return item ;

	}
}

