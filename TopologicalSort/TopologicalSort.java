/**
 * <p>Title: Topological Sort  Class</p>
 *
 * <p>Description: This class performs topogical sort on the adjacencyList converted graph. It can only perform this
 * sorting on acyclic graphs hence the user will be notified if the graph is cyclic.</p>
 * 
 * @author Ume Abbas
 */
public class TopologicalSort {
	
	private DoublyLinkedList<Vertex> verticesToSort ;
	private LinkedQueue<Vertex> q ;
	
	/** Topological Sort--
	 * creates a doubly linked List that contains the vertex of a graph. Also creates an empty queue.
	 * @param-- l is the list taken from the user.
	 */
	public TopologicalSort(DoublyLinkedList<Vertex> l)
	{
		verticesToSort = l ;
		q = new LinkedQueue<>(); 
	}
	
	/** storeVerticesOfIndegreeZero--
	 * counts the number of vertices with an indegree of 0 and add them on the queue q
	 */
	private void storeVerticesOfIndegreeZero()
	{
		Vertex theVertex ;
		for(int i = 0 ; i < verticesToSort.size(); i++)
		{
			theVertex = verticesToSort.get(i) ;
			if(theVertex.getIndegree() == 0)
				q.enqueue(theVertex);
		}
	}
	
	/** Sort--
	 * Topologicaly sorts the vertices of a grapf if and only if the graph is acyclic,
	 * otherwise will throw an exception 
	 * @throws -- IllegalStateException if the graph is cyclic.
	 */
	@SuppressWarnings("unchecked")
	public void Sort()
	{
		if(checkIfGraphIsCyclic(verticesToSort))
			throw new IllegalStateException("The Graph is cyclic");
		storeVerticesOfIndegreeZero() ;
		System.out.println("Topological Sort: ");
		while( !q.isEmpty())
		{
			Vertex sortedVertex  = q.dequeue() ;
			System.out.print(sortedVertex.getVertexName() + " ");
			DoublyLinkedList<Vertex> SortedVList = sortedVertex.getAdjacencyList() ;
			ModifyIndegreesOfAdjacentVertex(SortedVList);			
		}
		}
	
	/** --ModifyIndegreesOfAdjacentVertex
	 * This method will decrement the indegree of the vertex that has been dequeued from
	 * the queue q and no more has to be visited. if on decrementing one of the indegree becomes 0 
	 * the next vetrtex of indegree 0 is enqueued in the sorting.
	 */
	private void ModifyIndegreesOfAdjacentVertex(DoublyLinkedList<Vertex> SortedVList)
	{
		for(int i = 0 ; i < SortedVList.size() ; i++ )
		{
			Vertex adjVer = SortedVList.get(i) ;
			int adjIndeg = (adjVer.getIndegree())-1 ;
			adjVer.setIndegree(adjIndeg);
			int newIndeg = adjVer.getIndegree();
			if( newIndeg == 0 )
				q.enqueue(adjVer);
		}	
	}
	
	/** --checkIfGraphIsCyclic
	 * checks if the graph is not cyclic 
	 * @ returns true if the graph is cyclic else returns false.
	 */
	 private boolean checkIfGraphIsCyclic(DoublyLinkedList<Vertex> l)
	 {
		 for(int i = 0 ; i < l.size(); i++)
		 {
			 if(l.get(i).getIndegree() == 0)
				 return false ;
		 }
		 
		 return true ;
	 }
	}
	
	

