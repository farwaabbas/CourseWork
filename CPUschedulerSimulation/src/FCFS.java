import java.text.DecimalFormat;
import java.util.ArrayList;

public class FCFS extends Scheduler {

	
	private CircularLinkedList<Process> scheduler ;
	private ArrayList<Process> list;
	@SuppressWarnings("unchecked")
	public FCFS(ArrayList<Process> list) 
	{
	 this.list = list ;	
	 scheduler = this.getReadyQueue(); // calling the parent method to get the list 
	 insertProcess(list);
	 FCFS();
	 
	}
	
	// insert the processes in the readyQueue
	private void insertProcess(ArrayList<Process> list)
	{
		for(int i = 0 ; i < list.size() ; i++)
			scheduler.add(list.get(i));
	}
	
	// perform FCFS on the readyQueue
	private void FCFS()
	{
		System.out.println("Scheduling algorithm: First Come First Serve"); 
		System.out.println("==============================================================");
		int sysTime = 0 ;
		int cpuIdle = 0 ; // will count the time cpu was idle 
		double sumCpuBurst= 0 ;
		int [] response = new int[list.size()]; // will store the response time for each process
		fillResponse(response); // initially fills the array with -1 
		int res = 0 ;
		while(scheduler.size() > 0)
		{
			Process process = scheduler.remove(0); // remove the first process form readyQueue
			int cpuBurst = process.getCPUburst() ;
			int id = process.getId();
			sumCpuBurst = sumCpuBurst + cpuBurst ; // to get the cpu busy time 
			if(process.getArrivalTime() > sysTime)  // if there is no process in the readyQueue
			{	
				cpuIdle = process.getArrivalTime() ;
				CpuisIdle(sysTime,cpuIdle,process);
				sysTime = cpuIdle ;
			}
			res = sysTime ; 
			if(response[process.getId() -1 ] == -1) response[process.getId() -1] = res ;
			while(cpuBurst != 0) // when process is executing in the CPU
			{
				System.out.println("<system time    " + sysTime +"> process   " + (id) +" is running");
				sysTime+=1 ;
				cpuBurst-=1 ;
			}
			System.out.println("<system time    " + sysTime +"> process   " + (id) +" is finished...");
			process.setCompletionTime(Math.abs(sysTime)); // getting the completion time 
		}
		
		System.out.println("==============================================================");
		getCPUusage(cpuIdle,sumCpuBurst);
		getWaitTime();
		responseTime(response);
		printAvgTurnaroundTime();
		System.out.println("==============================================================");
	}
	

	private void CpuisIdle(int t , int idle, Process p)
	{
		for(int i = t ; i <= idle ; i++)	
			System.out.println("<system time    " + i +"> CPU is idle.. " + p.getId() );	
	}
	
	private void getWaitTime()
	{
		double wait = 0 ;
		int at = 0;
		int ct = 0 ;
		int bt = 0 ;
		double w = 0;
		for(int i = 0 ; i < list.size() ; i++)
		{
			ct = list.get(i).getCompletionTime();
			bt = list.get(i).getCPUburst() ;
			at = list.get(i).getArrivalTime() ;
			w = ct - (bt + at) ; // completion time - burst time + arrival Time
			list.get(i).setWaitTime(w); // also setting the wait time for each process 
			wait = wait + (w) ;
		}
		DecimalFormat df = new DecimalFormat("#.####");
		System.out.println("Average Waiting Time: "+ df.format(wait/list.size()));	
	}
	
	
	private void printAvgTurnaroundTime()
	{  // turnaround time = burstTime + wait time
		double sum = 0 ;
		for(int i = 0 ; i < list.size() ; i++)
			sum = sum + (list.get(i).getWaitTime() + list.get(i).getCPUburst());
		double avg = sum/list.size();
		DecimalFormat df = new DecimalFormat("#.####");
		System.out.println("Average Turnaround Time: "+ df.format(avg));
	
	}
	private void fillResponse(int[] response)
	{
		for(int i = 0 ; i < response.length ; i++)
		{
			response[i] = -1 ;
		}
	}
	
	private void responseTime(int[] response) //is the sum of the waiting time when the first time the process went into the cpu 
	{ // waiting time - arrival time 
		double sumrt = 0 ;
		double rt= 0;
		for(int i = 0 ; i < list.size() ; i++)
		{
			rt = response[i] - list.get(i).getArrivalTime();
			sumrt = sumrt + rt ;
		}
		DecimalFormat df = new DecimalFormat("#.####");
		System.out.println("Average Response Time: "+ df.format(sumrt/list.size()));	
	}
	
	private void getCPUusage(double cpuIdle, double sumCpuBurst)
	{   // cpuUsage = cpuBusy / cpuBusy + cpuIdle
		double sum = 0 ;
		for(int i = 0 ; i < list.size() ; i++)
			sum = sum + list.get(i).getCPUburst();
		double avg = sum/(sum + cpuIdle) * 100;
		DecimalFormat df = new DecimalFormat("#.###");
		System.out.println("Cpu Usage: "+ df.format(avg) + "%");
		
	}
	
	
}
