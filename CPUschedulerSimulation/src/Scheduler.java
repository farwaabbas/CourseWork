import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Scheduler {

	private CircularLinkedList<Process> readyQueue;

	public Scheduler()
	{// initializing an empty queue
		readyQueue = new CircularLinkedList<Process>();
	}

	 
	// will read the process from file assignment2.txt and will store in RQ
	
	@SuppressWarnings("rawtypes")
	public CircularLinkedList getReadyQueue()
	{
		return readyQueue ;
	}
	
	/*
	public void add(Process val) 
	{
		readyQueue.add(val);
		
		
	}

	public Process remove(int index)
	{
		return readyQueue.remove(index);
	}
	
	
	public int getSize()
	{
		return readyQueue.size();
	}
	
	
	// begin implementing algorithms 
	private void FCFS() // first come first serve algorithm 
	{
		int sysTime = 0 ;
		for(int i = 0 ; i < readyQueue.size() ; i++)
		{
			int cpuBurst  = readyQueue.get(i).getCPUburst();
			findWaitTime(i);
			while(cpuBurst!= 0)
			{
				System.out.println("<system time    " + sysTime +"> process   " + (i+1) +" is running");
				sysTime+=1 ;
				cpuBurst-=1 ;
			}
			System.out.println("<system time    " + sysTime +"> process   " + (i+1) +" is finished...");
		}
		printAvgTimes(readyQueue.size());
		
	}


	private void findWaitTime(int index)
	{
		// if this is the first process in the q then wait time = 0
		int sum = 0 ;
		Process process = readyQueue.get(index);
		if(index == 0) process.setWaitTime(0);
        // else 
		for(int i = 0 ; i < index ; i++) 
		{
			if(readyQueue.get(i).getId() != process.getId()) 
			 sum = sum + readyQueue.get(i).getCPUburst(); // processes before the current process	
		}
		// set the new wait time 
		process.setWaitTime(sum - process.getArrivalTime());
		
	}

	
	private void printAvgTimes(int numOfProcesses)
	{
		double sum = 0 ;
		for(int i = 0 ; i < readyQueue.size() ; i++)
			sum = sum + readyQueue.get(i).getWaitTime();
		double avg = sum/numOfProcesses;
		DecimalFormat df = new DecimalFormat("#.###");
			System.out.println("Average Waiting Time: "+ df.format(avg));
			
	}
	// implementing round robin which is the extension of fcfs algo 
*/
















}
