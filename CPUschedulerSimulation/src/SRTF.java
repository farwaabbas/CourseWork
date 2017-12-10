import java.text.DecimalFormat;
import java.util.ArrayList;

public class SRTF extends Scheduler{

	private CircularLinkedList<Process> scheduler ;
	private ArrayList<Process> list;
	private int[] cpuUsage ; // we need this since we will decrement cpu burst length in future 

	@SuppressWarnings("unchecked")
	public SRTF(ArrayList<Process> list)
	{
		this.list = list ;
		scheduler = this.getReadyQueue();
		cpuUsage = new int[list.size()];
		insertProcess(list);
		srtf();

	}

	private void insertProcess(ArrayList<Process> list)
	{
		for(int i = 0 ; i < list.size() ; i++)
		{
			scheduler.add(list.get(i));
			cpuUsage[i] = list.get(i).getCPUburst();
		}
	}
	
	public void srtf()
	{
		System.out.println("Scheduling algorithm: SRTF");
		System.out.println("==============================================================");
		int sysTime = 0 ;
		int cpuIdle = 0 ;
		int [] response = new int[list.size()];
		fillResponse(response);
		int pid = scheduler.get(0).getId();
		int arr[] = {0} ; // arr[1] will keep track of the next pid with the shortest cpuBurst and at index = 0 will store its postion in the arrayList
		int res = 0 ;
		while(scheduler.size() > 0)
		{
			Process p  = scheduler.remove(arr[0]); // strting with the first process in the queue
			if(p.getArrivalTime() > sysTime) // if cpu is idle 
			{	
				cpuIdle = p.getArrivalTime() ;
				CpuisIdle(sysTime,cpuIdle,p);
				sysTime = cpuIdle ;
			}
			res = sysTime ; // find response time 
			if(response[p.getId() -1 ] == -1) response[p.getId() -1] = res ;
			System.out.println("<system time    " + sysTime +"> process   " + p.getId() +" is running");
			p.setCPUburst(p.getCPUburst() - 1); // decrement cpuBurst by 1 after every '1' time lapse 
			p.setCompletionTime(sysTime + 1); // increment the completion time
			Process prev = p ; // keep track of the current process before getting the new one 
			if(prev.getCPUburst() != 0) 
				scheduler.add(p); // add the current process back to the queue
			arr = findMinProcess(sysTime + 1); // now find the other min process and store in arr it pid and index position
			pid = arr[1]; // find the next min cpu burst
			sysTime+=1 ;
			if(pid != prev.getId()) // if the min pid is the same as current then again execute the current process 
				System.out.println("<system time    " + sysTime +"> process   " + prev.getId() +" is finished...");
		}
		System.out.println("==============================================================");
		CPUusage(cpuIdle);
		getWaitTime();
		responseTime(response);
		TurnaroundTime();
		System.out.println("==============================================================");
	}
	
	private void CPUusage(int idle)
	{ double sum = 0 ;
		for(int i = 0 ; i < list.size() ; i++)
			sum = sum + cpuUsage[i];
		double avg = sum/(sum + idle) * 100;
		DecimalFormat df = new DecimalFormat("#.###");
		System.out.println("Cpu Usage: "+ df.format(avg) + "%");
	
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
	
	private void fillResponse(int[] response)
	{
		for(int i = 0 ; i < response.length ; i++)
		{
			response[i] = -1 ;
		}
	}
	private void TurnaroundTime()
	{
		double tt = 0 ;
		int bt = 0 ;
		double wt = 0;
		double sum =0 ;
		for(int i = 0 ; i < list.size() ; i++)
		{
			bt = cpuUsage[i] ;
			wt = list.get(i).getWaitTime();
			tt = bt + wt ;
			list.get(i).setTurnaroundTime(tt);// setting turnaround time 
			sum = sum + tt ;
		}
		DecimalFormat df = new DecimalFormat("#.####");
		System.out.println("Average Turnaround Time: "+ df.format(sum/list.size()));	
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
			bt = cpuUsage[i] ;
			at = list.get(i).getArrivalTime() ;
			w = ct - (bt + at) ; // completion time - burst time + arrival Time
			list.get(i).setWaitTime(w); // also setting the wait time for each process 
			wait = wait + (w) ;
		}
		DecimalFormat df = new DecimalFormat("#.####");
		System.out.println("Average Waiting Time: "+ df.format(wait/list.size()));	
	}
	
	private void CpuisIdle(int t , int idle, Process p)
	{
		for(int i = t ; i <= idle ; i++)	
			System.out.println("<system time    " + i +"> CPU is idle.. " + p.getId() );	
	}

	private int[] findMinProcess(int t)
	{
		int minBurst = 1000 ;
		int pid = 0 ; int index =0;
		int arr[] = new int[2];
		for(int i = 0 ; i < scheduler.size() ; i++)
		{
			Process p = scheduler.get(i);
			if(p.getCPUburst() > 0 && p.getCPUburst() < minBurst && p.getArrivalTime() <= t)
			{   
			    int bt = p.getCPUburst() ;
				minBurst = bt ;
				pid = p.getId() ;
				index = i ;		
			}	
		}
		arr[0] = index;
		arr[1] = pid ;
		
		return arr ;
	}


}
