package project4;
import java.io.File;
/**
 * <p>Title: SymbolTable Class</p>
 *
 * <p>Description:This class is capable of creating an unordered list of data type SymbolPairs that
 * is capable of storing SymbolPair objects. The unordered list will accept the data from the user in
 * a form of a file containing the data for symbolPair objects.<p>
 *   @author Ume Abbas
 */
import java.util.Scanner;
public class SymbolTable {
	// instance variable	
	private ArrayUnorderedList<SymbolPair> symbolPairs;
	/**
	 * parameterized constructor --
	 * Initializes the instance variable symbolPairs by accepting the data from the file sent by the user.
	 * @param -- file containing the data to be stored in SymbolPair object in SymbolPairs 
	 */
	public SymbolTable(File file) throws Exception
	{
		@SuppressWarnings("resource")
		Scanner symbolData = new Scanner(file);
		symbolPairs = new ArrayUnorderedList<SymbolPair>();
		while(symbolData.hasNext())
			symbolPairs.addToRear(new SymbolPair(symbolData.next() , symbolData.nextLine()));	

	}
	/**
     * findCompany method --
     * This method accepts the tickerSymbol from the user and search for the company in the SymbolTable object
     * symbolPairs. On finding the company it returns the full company name else it throws ElementNotFoundException 
     * and EmptyCollectionException if the symbolTable is empty. 
     * @param -- tickerSymbol storing reference to the storing reference of ticker symbol sent from the file.
     * @throws -- ElementNotFoundException if the company is not found in the symbolPairs list.
     * @throws-- EmptyCollectionException if the symbolTable list is empty
     */
	public String findCompany(String tickerSymbol)
	{
		if(symbolPairs.isEmpty())
			throw new EmptyCollectionException("symbolPair list");
		else
		{
		int count = 0 ;
		while(count < symbolPairs.size())
		{
			if(symbolPairs.get(count).getTickerSym().equals(tickerSymbol))
				return symbolPairs.get(count).getCompanyName() ;
			else
				count++ ;
		}

		throw new ElementNotFoundException("SymbolTable") ;
		}


	}
	/**
     * toString method --
     * creates and returns a String representing the state of SymbolTable.
     * @return a String containing the current values of symbolPairs
     */

	public String toString()
	{
		return symbolPairs.toString() ;
	}

}