package project4;
/**
 * <p>Title: Portfolio Class</p>
 *
 * <p>Description: This class contains an UnOrdered list of stocks stored, containing the stock object in the list
 *  This class is capable of processing transaction on every stock that is bought and the stock of the same company 
 *  sold and it also calculate the gin and loss that occurs while selling the item in the stocks.If the number of shares in
 *  the stock are more comparatively to the number of shares to be sold then the transaction can take place,
 *  otherwise it will display an InvalidSaleException.The class is also capable of representing the state of the instance
 *  variables to show the current state of Portfolio.</p>
 *
 *  @author Ume Abbas 
 */

import java.text.DecimalFormat;
import java.util.Scanner;
public class Portfolio {

	private ArrayUnorderedList<Stock> stocks ;
	private SymbolTable symbols;
	private double gainLoss;
	/**
	 * parameterized constructor --
	 * initializes the symbols ,gainLoss, and stocks for a portfolio to an 
	 * mySymbol,0.0, and ArrayUnorderedList respectively.
	 *  @param -- symbolTable stores the SymbolTable object.
	 */

	public Portfolio(SymbolTable mySymbol) throws Exception
	{
		symbols = mySymbol;
		gainLoss = 0.0 ;
		stocks = new ArrayUnorderedList<Stock>() ;

	}
	/**
	 * processTransaction method --
	 * sells the oldest item in the stock first, only if the item to be sold matches with the ticker symbol of the selling item
	 * and then also calculates its gain and loss value. 
	 * The method also removes the items from the stock if there are no more shares available.
	 * If the stocked item doesn't have enough share to sell then it throws an InvalidSaleException
	 * @param boughtSold stores the character read from the file which indicates if the item is bought or sold
	 * @param shares -- stores the number of shares read from the file for a particular stock item.
	 * @param price --  stores the price for the stock item in the file read at a particular time
	 * @param tickerSym -- stores the reference of the ticker symbol read from the file.
	 * @throws InvalidSaleException -- if the selling shares are more than the share of a items with
	 *  the same ticker symbol in the stock
	 */

	public void processTransaction(char boughtSold ,int shares, double price, String tickerSym) throws Exception
	{
		int isBought;
		isBought = boughtSold ;
		if(isBought == 'b')
		{
			stocks.addToRear(new Stock(shares, price, tickerSym));
			symbols.findCompany(tickerSym);
		}
		else
		{
			//boolean found = false ;
			int sharesOwned = -1 ; // -1 represents that we havn't entered the stock list 
			int count = 0 ;
			int totalShares = 0 ;

			for(int i = count ; i< stocks.size() ; i++)
			{
				if((stocks.get(i).getTickerSym().equals(tickerSym)))
					totalShares = totalShares + stocks.get(i).getSharesOwened();
			}

			if(totalShares >= shares)
			{

				while(count < stocks.size())// && found == false)
				{
					sharesOwned = stocks.get(count).getSharesOwened();
					if(stocks.get(count).getTickerSym().equals(tickerSym))
					{
						if(sharesOwned >= shares && shares!= 0)
						{
							stocks.get(count).setSharesOwened(sharesOwned - shares) ;
							sharesOwned = stocks.get(count).getSharesOwened() ;
							gainLoss = gainLoss + (shares * (price - (stocks.get(count).getPurchasePrice())));
							shares = 0 ;
							if(sharesOwned == 0)
								stocks.remove(stocks.get(count));
						}
						else
							if(shares >= sharesOwned)
							{
								gainLoss = gainLoss + (sharesOwned * (price - (stocks.get(count).getPurchasePrice())));
								shares = shares - sharesOwned ;
								stocks.remove(stocks.get(count));
							}
					}

					count++ ;

				}
			}
			else
				throw new InvalidSaleException("\nThis transaction for company: " + symbols.findCompany(tickerSym));
		}

	}
	/**
	 * setCompanyName method --
	 * creates a string object that represents the state of every Stock object with its full company name 
	 * @return-- str returns the new state of the stocks in the portfolio.
	 */

	public String setCompanyName()
	{  
		DecimalFormat format = new DecimalFormat("###,###.###");

		String str = new String() ;
		for(int i = 0 ; i< stocks.size(); i++)
			str = str +	stocks.get(i).getSharesOwened() + " $"+ format.format(stocks.get(i).getPurchasePrice()) + symbols.findCompany(stocks.get(i).getTickerSym()) + "\n";
		return str ;
	}
	/**
	 * toString method --
	 * creates and returns a String representing the state of the Portfolio.
	 * @return a String containing the current values of stocks,GainLoss, and symbols(company name )
	 */

	public String toString()
	{
		if(stocks.isEmpty())
			throw new EmptyCollectionException("Portfolio's "); 
		else
		{			
			DecimalFormat format = new DecimalFormat("###,###.###");
			return "The stock contains:\n" + setCompanyName() + "\n" + "GainLoss: $" + format.format(gainLoss) ;

		}
	}
}
