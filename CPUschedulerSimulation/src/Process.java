
/**
 * A class called Process which stores the ID, arrival time, 
 * and CPU burst length of a process, all are integers.  
 * You can also add data members to keep track of information 
 * in order to compute the statistics about the process such as 
 * its wait time, response time, and turnaround time.  
 * The methods of the Process class are the get and set methods 
 * for each data member, the constructor
 * for the class, and any method needed to compute the statistics for that process.  
 */

public class Process {
	private int id, arrivalTime, cpuBurst, completionTime;
	private double waitTime, responseTime, turnaroundTime ;
	
	public Process(int id, int arrivalTime, int cpuBurst)
	{
		// initializing the data members 
		this.id = id ;
		this.arrivalTime = arrivalTime ;
		this.cpuBurst = cpuBurst ;
		this.completionTime = 0 ;
		waitTime = responseTime = turnaroundTime = 0 ;
	}
	
	public int getId()
	{
		return id ;
	}
	
	public int getArrivalTime()
	{
		return arrivalTime ;
	}
	
	public int getCPUburst()
	{
		return cpuBurst ;
	}
	
	public double getWaitTime()
	{
		return waitTime ;
	}
	
	public double getTurnaroundTime()
	{
		return turnaroundTime ;
	}
	
	public double getResponseTime()
	{
		return responseTime ;
	}
	
	public void setTurnaroundTime(double tt)
	{
		turnaroundTime = tt;
	}
	
	public void setId(int id)
	{
		this.id = id ;
	}
	public void setArrivalTime(int at)
	{
		this.arrivalTime = at ;
	}
	
	public void setCPUburst(int burst)
	{
		this.cpuBurst = burst ;
	}
	
	public void setWaitTime(double val)
	{
			waitTime = val ;
	}
	
	public int getCompletionTime()
	{
		return completionTime ;
	}
	
	public void setCompletionTime(int at)
	{
		completionTime = at ;
		
	}
	public String toString()
	{
		return "Process ID: " + id  + "\nArrival Time: " + arrivalTime + "\nCPU Burst Length: " + cpuBurst ;
	}
	
}
