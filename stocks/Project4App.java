package project4;
import java.io.File;
import java.util.Scanner;

/**
* <p>Title: Proj4App</p>
*
* <p>Description: This class is capable of creating a porfolio that contains a stock of different companies with their purchased
* price and the shares they owned. If the user decides to sell some of its stocks products(company shares ) then
* the transaction occurs only if the shares that the user wants to sell are more then he/she actually have in the stock.
* Otherwise, an error message is displayed to let the user know that his stock doesn't have enough shares to sell. Every time 
* the user sells or buy a company's shares the portfolio containing the stocks left and the gain or loss
* the user have received after ever transaction is represented.The program is also capable of detecting and 
* returning an error message if the user has sent the wrong ticker symbol for the company he/she wants to find in his stocks.
* Also displays the message if the stock is empty or gets empty.</p>
*
* @author  Ume Abbas 
*/
public class Project4App {
	public static void main(String[] args) throws Exception
	{
		// testing SymbolTable constructor 
		SymbolTable table = new SymbolTable(new File("symboldata.txt"));
		System.out.println("Testing SymbolTable constructor:\n" + table.toString());
		// testing findCompany method 
		System.out.println("The company is found: " + table.findCompany("CSCO"));
		try{
			System.out.println("The company is found: " + table.findCompany("CVCO"));
		}
		catch(ElementNotFoundException ex)
		{
			System.out.println(ex.getMessage());
		}

		// Stock class: testing all the methods
		Stock myStock = new Stock(100, 23.5,"JCP");
		System.out.println("\nmyStock is: " + myStock.toString()+ "\ngetting sharesOwned: " + myStock.getSharesOwened() + "\ngetting purchasePrice: " + myStock.getPurchasePrice() + "\ngetting tickerSym: " + myStock.getTickerSym());

		// Portfolio class  
		// new Portfolio object and SymbolTable object is instantiated 
		Portfolio portfolio = new Portfolio(new SymbolTable(new File("symboldata.txt")));
		// displays when the portfolio is empty :
		System.out.println("\nWhen the portfolio is empty:");
		try{
		System.out.println("\nWhen the portfolio is empty:\n" + portfolio.toString());
		}
		catch(EmptyCollectionException error)
		{
			System.out.println(error.getMessage());
		}
		//Instantiating a scanner object stockData that will store the data read from the file stockdata.txt
		System.out.println("\nThe transaction in the stock begins:");
		Scanner stockData = new Scanner(new File("stockdata.txt"));
		while(stockData.hasNext())
		{
			try
			{
				portfolio.processTransaction(stockData.next().charAt(0),stockData.nextInt(),stockData.nextDouble(), stockData.next());
				System.out.println("\nTransaction:"  + "\n" + portfolio.toString());

			}
			catch(InvalidSaleException error) // if the shares sold are more than the shares in the stock
			{
				System.out.println(error.getMessage());
			}
			catch(ElementNotFoundException error) // if the findCompany method did not find the company
			{
				System.out.println(error.getMessage());
			}
			catch(EmptyCollectionException error) // if the stock is empty
			{
				System.out.println(error.getMessage());
			}

		}













	}

}

