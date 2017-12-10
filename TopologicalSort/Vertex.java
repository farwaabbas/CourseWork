/**
 * <p>Title: Vertex Class</p>
 *
 * <p>Description: This class is capable of creating an object named Vertex that contains String variable. 
 * The funtion of the class is to obtain the name of a vertex, create an adjacency list from a file. 
 * The user will be able to set and get the name of a vertex through get and set methods.</p>
 * 
 * @author Ume Abbas
 */
public class Vertex {

	private String vertexName;
	private DoublyLinkedList<Vertex> adjacencyList ;
	private int indegree ;

	
	
	/** Vertex--
	 * Parameterized constructor accepts a vertex name from the file or a user and gets stored in the the 
	 * vertex data member. Creates an emty list of type object, and sets indegree to 0 
	 * @param-- name accepts the vertex name of type String.
	 */
	public Vertex (String name)
	{
	    vertexName = name ;
	    adjacencyList = new DoublyLinkedList<>();
	    indegree = 0;
		
	}
	
	/** updateIndegree--
	 * to this Vertex object if a vertex is added into an adjacencyList then the indegree of the adjacent vertex
	 * increases by 1
	 */
	public void updateIndegree()
	{
		int deg = 0 ;
		int numOfVertex = getAdjacencyList().size();
		deg = adjacencyList.get(numOfVertex-1).getIndegree();
		adjacencyList.get(numOfVertex-1).setIndegree(deg + 1);			
	}
	
	/** setIndegree--
	 * sets indegree to new value 
	 * @param-- num accepts the new value 
	 */
	public void setIndegree(int num)
	{
		indegree = num ;
	}
	/** getIndegree--
	 * returns indegree of a vertex
	 * @returns-- indegree 
	 */
	public int getIndegree()
	{
		return indegree ; 
	}
	
	/** addToList--
	 * accepts a new adjacent Vertex and add in the end od the list.
	 
	 */
	public void addToList(Vertex newVertex)
	{
		adjacencyList.add(newVertex);
		updateIndegree();
	}
	
	/** getAdjacencyList--
	 * returns AdjacencyList of a vertex
	 * @returns-- AdjacencyList 
	 */
   public DoublyLinkedList getAdjacencyList()
   	{
	   return adjacencyList ;
   	}
   
	/** get--
	 * retrieves the name stored in vertex and return it.
	 * @return-- String object containing the name of a vertex.
	 */
	public String getVertexName()
	{
		return vertexName ;
	}
	
	/** set--
	 * set method sets a newName specified by the user by replacing the old name stored in the data member vertex.
	 * @param-- newName accepts the name of type String that has to be set in the vertex data member.
	 */
	public void setVertexName(String newName)
	{
		vertexName = newName ;
	}
	
	/**toString--
	 * this method represents the name of the vertex stored in a vertex object.
	 * @return-- String object containing the name of a vertex.
	 */
	public String toString()
	{
	    
		String str = vertexName + ": " ;
		for(int i = 0 ; i < adjacencyList.size(); i++)
			str  = str + adjacencyList.get(i).vertexName + " ";
		return str ;
		
	}
}
