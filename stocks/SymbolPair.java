package project4;
/**
 * <p>Title: SymbolPair Class</p>
 *
 * <p>Description:This class is capable of storing the ticker Symbol and the company name sent by the user.
 *   It is capable of retrieving the value of the tickerSym and the companyName.
 *
 *  @author Ume Abbas
 */

public class SymbolPair {
	
	private String tickerSym; 
	private String companyName;
/**
 * parameterized constructor --
 * initializes the companyName and tickersym from the values sent by the user  
 * @param -- symbol stores the ticker symbol sent by the user for the company.
 * @param -- name stores the reference of the company name sent by the user    
 */
	
 public SymbolPair(String symbol, String name)
 {
	 tickerSym = symbol ;
	 companyName = name ;
 }
 /**
  * getTickerSym method -- gets the ticker symbol of the company from tickerSym 
  * @return-- tickerSym
  */

 public String getTickerSym()
 {
	 return tickerSym ;
 }
 /**
  * getcompanyName method -- gets the company name from the companyName 
  * @return-- the companyName storing the name of the company
  */

 public String getCompanyName()
 {
	 return companyName ;
 }
 /**
  * toString method --
  * creates and returns a String representing the state of SymbolPair.
  * @return a String containing the current values of tickerSym AND CompanyName.
  */
 public String toString()
 {
	 return  tickerSym +  " " + companyName ;
 }
}
