package project4;
/**
 * <p>Title: EmptyCollectionException Class</p>
 *
 * <p>Description: Exception Class for use by all Container Classes</p>
 * 
 * @author - Ume Abbas 
 */
@SuppressWarnings("serial")
public class InvalidSaleException extends RuntimeException {	
	/**
	 * Initializes an InvalidSaleException storing an appropriate message.
	 */
	public InvalidSaleException()
	{
		super(" The transaction cannot perform due to insufficient stock");
	}
	
	/**
     * Initializes an InvalidSaleException storing the type of the
     * collection (as specified by the user) along with an appropriate message.
	 */
	public InvalidSaleException(String collection)
	{
		super(collection + "cannot perform due to insufficient stock");
	}
	
}



