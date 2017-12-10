import java.text.DecimalFormat;
import java.util.ArrayList;

public class RoundRobin  extends Scheduler {

	private CircularLinkedList<Process> scheduler ;
	private ArrayList<Process> list;
	private int quantum ;
	
	@SuppressWarnings("unchecked")
	public RoundRobin(int quantum , ArrayList<Process> list)
	{
		 this.quantum =  quantum ;
		 this.list = list ;
		 scheduler = this.getReadyQueue();
		 insertProcess(list);
		 RR();
		
	}
	private void insertProcess(ArrayList<Process> list)
	{
		for(int i = 0 ; i < list.size() ; i++)
			scheduler.add(list.get(i));
	}
	
	@SuppressWarnings("unused")
	private void RR()
	{
		System.out.println("Scheduling algorithm: Round Robin");
		System.out.println("==============================================================");
		int [] response = new int[list.size()];
		fillResponse(response);
		int sysTime = 0 ;
		int index = 0 ;
		int cpuIdle = 0 ;
		double sumCpuBurst=0 ;
		int res = 0 ;
		while(scheduler.size() > 0)
		{
			
			Process process = scheduler.remove(0);
			if(process.getArrivalTime() > sysTime) 
			{	
				cpuIdle = cpuIdle + process.getArrivalTime() ;
				int t = CpuisIdle(sysTime,cpuIdle,process);
				sysTime = cpuIdle ;
			}
			//sysTime = cpuIdle ;
			res = sysTime ;
			if(response[process.getId() -1 ] == -1) response[process.getId() -1] = res ;
			int cpuBurst = process.getCPUburst() ;
			int id = process.getId();
			sumCpuBurst = sumCpuBurst + cpuBurst ; // to get the cpu busy time 
			int time = 1;
			int sum = 0;
			while(time <= cpuBurst) // executing the processes till cpuBurstLength if burst < quantum value
			{						// else will only run until quantum value 
				if(time > quantum)
					break ;
				System.out.println("<system time    " + sysTime +"> process   " + (id) +" is running");
				sysTime+=1 ;
				time++;
			}
			cpuBurst = cpuBurst - quantum; // decrementing the cpuBurst length
			if(!(cpuBurst <= 0) )
			{
			process.setCPUburst(cpuBurst) ; // set the new value of cpuBurst
			scheduler.add(process); //add the process back to the queue
			}
			System.out.println("<system time    " + sysTime +"> process   " + (id) +" is finished...");
			process.setCompletionTime(Math.abs(sysTime + 1));
			
		}
		
		System.out.println("==============================================================");
		CPUusage(cpuIdle);
		getWaitTime();
		responseTime(response);
		TurnaroundTime();
		//System.out.println("cpu sitting idle " + cpuIdle);
		System.out.println("==============================================================");
		
	}
	
	
	private void CPUusage(int idle)
	{ double sum = 0 ;
		for(int i = 0 ; i < list.size() ; i++)
			sum = sum + list.get(i).getCPUburst();
		double avg = sum/(sum + idle) * 100;
		DecimalFormat df = new DecimalFormat("#.###");
		System.out.println("Cpu Usage: "+ df.format(avg) + "%");	
	
	}
	private int CpuisIdle(int t ,int idle, Process p)
	{
		for(int i = 0 ; i < idle ; i++)
		{	
	    System.out.println("<system time    " + i +"> CPU is idle..");	
	    t++ ;
		}
		return t ;
	}
	private void fillResponse(int[] response)
	{
		for(int i = 0 ; i < response.length ; i++)
		{
			response[i] = -1 ;
		}
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
	
	private void TurnaroundTime()
	{
		double tt = 0 ;
		int bt = 0 ;
		double wt = 0;
		double sum =0 ;
		for(int i = 0 ; i < list.size() ; i++)
		{
			bt = list.get(i).getCPUburst() ;
			wt = list.get(i).getWaitTime();
			tt = bt + wt ;
			list.get(i).setTurnaroundTime(tt);// setting turnaround time 
			sum = sum + tt ;
		}
		DecimalFormat df = new DecimalFormat("#.####");
		System.out.println("Average Turnaround Time: "+ df.format(sum/list.size()));	
	}
	
	private void responseTime(int[] response) //is the sum of the waiting time when the first time th process went into the cpu 
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
	
}
	
	