import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment2 {

	public static void main(String[] args) throws FileNotFoundException
	{	
		String algoName = args[0];
		int quantum =0;
		if (args.length > 1) { // arg length contains quantum then get the quantum
			try {
				quantum  = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				System.err.println("Argument" + args[1] + " must be an integer.");
				System.exit(1);
			}
		}

		ArrayList<Process> processes = new ArrayList<Process>();
		File file = new File("assignment2.txt");
		Scanner scan = new Scanner(file);
		while(scan.hasNextInt())
		{
			int id = scan.nextInt();
			int at = scan.nextInt();
			int burst = scan.nextInt();
			processes.add(new Process(id,at,burst));
		}

		Scheduler scheduler ;
		// since the parent is scheduler then we can create a sub class from the parent class
		if(algoName.equals("FCFS"))
			scheduler = new FCFS(processes);
		else if (algoName.equals("RR") && quantum!= 0)
			scheduler = new RoundRobin(quantum,processes);	
		else if (algoName.equals("SRTF")) 
			scheduler = new SRTF(processes);

	}



}
