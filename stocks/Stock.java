package project4;
/**
 * <p>Title: Stock Class</p>
 *
 * <p>Description:This class is capable of storing the shares and the price of each company 
 *   represented by it's ticker symbol stored in the tickerSym.It is capable of retrieving the value of
 *   shares,price, and the tickerSym through its accessor methods and it can also set the shares to a new value if some
 *   of them  gets sold.<p>
 *   @author Ume Abbas
 */
public class Stock {
	
	private String tickerSym ;
	private int sharesOwened ;
	private double purchasePrice;
	
    /**
     * parameterized constructor --
     * initializes the sharesOwend,purchasePrice, and tickersym for a Stock to an 
     * symbol,price, and symbol respectively.
     * @param -- shares stores the number of shares stored and taken from the user.
     * @param -- price stores the price sent by the user 
     * @param -- symbol stores the ticker symbol sent by the user to store in the stock.
     */

	public Stock(int shares, double price, String symbol ) throws Exception
	{
		
		sharesOwened = shares ;
		purchasePrice = price;
		tickerSym = symbol ;
			
	}
	/**
     * getSharesOwned method -- gets the shares owned in the stock
     * @return  sharesOwned
     */

	public int getSharesOwened()
	{
		return sharesOwened ;
	}
	/**
     * get PurchasePrice -- gets the the price at which the stocks were purchase 
     * @return the purchasePrice
     */

	public double getPurchasePrice()
	{
		return purchasePrice;
	}
	/**
     * getTickerSym method -- gets the ticker symbol of the company in the stock 
     * @return the tickerSym
     */

	public String getTickerSym()
	{
		return tickerSym ;
	}
	/**
     * setSharesOwned method --
     * stores the user-specified value in sharesOwned.
     * @param shares -- that stores the value of the shares sent by the user 
     */

	public void setSharesOwened(int shares)
	{
		sharesOwened = shares ;
	}
	
	/**
     * toString method --
     * creates and returns a String representing the state of Stock.
     * @return a String containing the current values of sharesOwned,purchasePrice, and tickerSym
     */

	public String toString()
	{	
		return  sharesOwened + " $" + purchasePrice + " " + tickerSym ;
	}
	
}
