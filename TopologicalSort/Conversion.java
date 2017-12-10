import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 * <p>Title: Conversion Class </p>
 *
 * <p>Description: This class performs all major operation that converts the graph matrix in a given file to an 
 * adjacency matrix. The Conversion class contains a data member called graphFile that is a File object.If th user calls 
 * default contructor, the contructor will assign a default file name present already in the project, otherwise by calling a 
 * parameterized contructor the user can send the another file name containg a graph matrix. This class will access 
 * the file and will start the conversion of the graph by calling the GraphConversion method. This class will also
 * print an adjacency list for every vertex by using the Vertex class and will also throw an exception if the file
 *  is not found or the file has an improper format</p>
 * @author Ume Abbas
 */
public class Conversion {
	// data member that store a File object 
	private File graphFile;
	private DoublyLinkedList<Vertex> vertices ;

	/** Conversion--
	 * The default constructor assigns a default file name already present in the project to the File object.
	 */
	public Conversion()
	{
		// instantiating a new File object and paasing the default file name called sample.txt
		graphFile = new File("sample.txt");
		vertices = new DoublyLinkedList<>();
	}
	/** Conversion--
	 * The parameterized constructor assigns a file name provided by the user and instantiates a File object 
	 * that is assigned to a graphFile.
	 * @param-- userFile accepts a file name provided by the user.
	 */
	public Conversion(String userFile)
	{ 
		graphFile = new File(userFile);
		vertices = new DoublyLinkedList<>();
	}

	/** getVertices--
	 * returns the doublyLinkedList of vertices
	 * @ returns list of vertices.
	 */
	
	public DoublyLinkedList getVertices()
	{
		return vertices ;
	}

	/** GraphConversion--
	 * This method uses the the data member graphFile(File Object) and assigns it to the Scanner object named scan.
	 * Scan reads the data from the file. There are two 2 processes of this method:
	 * First: It creates a link list of Vertex objects that contains the name of the vertices present in the file.
	 * Also it counts how many vertices are there which helps to know that to how many vertices will one vertex will be 
	 * compared to in a file or how many rows and columns will a matrix in a file would have.
	 * Second: two nested for loops helps to iterate through the matrix. In each Iteration of a 'i' loop a new link list 
	 * is created that will serve as an adjacency list for one vertex in the file. After creation of the list, the list is
	 * immedietly printed by calling a private print method before creating another link list. 
	 * @throws FileNotFoundException if the File is not found.
	 */
	public void GraphConversion() throws FileNotFoundException
	{
		@SuppressWarnings("resource")
		Scanner scan1 = new Scanner(graphFile);
		String name = "";
		while(!(scan1.hasNextInt()))
		{ 
			name = scan1.next();
			vertices.add(new Vertex(name));
		}
		for(int i = 0 ; i < vertices.size() ; i++)
		{ 	
			int num = 0;
			for(int j = 0 ; j < vertices.size() ; j++)
			{ 
				num = scan1.nextInt();
				if(num!=0 && num!=1) throw new IllegalStateException("one of the digit is not '0' or '1' or there is no proper spacing among the digits");
				else
				if(num == 1)
					vertices.get(i).addToList(vertices.get(j));
			}
			print(vertices.get(i));
			System.out.println();
		}
	}

	
	
	/** print--
	 * This method accepts a Link list. It iterate through the list and will print every vertex in the list.
	 * @param-- theList containg a pointer to the adjacency list of a vertex.
	 */
	private void print(Vertex theList)
	{
		System.out.println(theList.toString()) ;
	}	
	

}

