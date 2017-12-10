import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
/**
 * <p>Title: Project3 Class</p>
 *
 * <p>Description:This class converts a graph into an adjacency list of a every Vertex and 
 * also Topological Sort is performed on the graph and verteces are displayed in topologocal order.   </p>
 * @author Ume Abbas
 */
public class Project3 {

	public static void main(String []args) throws FileNotFoundException 
	{  
		try
		{
		// Creating a Conversion object 
		System.out.println("The Adjacency List of a Graph from the File project3: ");
		Conversion myFile = new Conversion("project3.txt");
		myFile.GraphConversion();
		System.out.println();
		TopologicalSort sort = new TopologicalSort(myFile.getVertices());
		sort.Sort();
		
		}
		
		catch(FileNotFoundException error)
		{
			System.out.println(error.getMessage());
		}
		
		catch(IllegalStateException ex)
		{
			System.out.println(ex.getMessage());
		} 
		
		catch(NoSuchElementException e)
		{
			System.out.println("you are missing either '0' or '1' for one of the vertex: " + e.getMessage());
		}
	}
	
	 }

