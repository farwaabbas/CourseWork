
/**
 * <p>Title: Vertex Class</p>
 *
 * <p>Description:This class is capable of creating a queue.The user will be able to queue, dequeue, and look 
 * the front item in the queue</p>
 * 
 * @author Ume Abbas
 */
public class LinkedQueue <AnyType> implements Queue<AnyType>{

	private DoublyLinkedList<AnyType> data;

	/** LinkedQueue()--
	 * creates an empty queue
	 */

	public LinkedQueue() 
	{
		data = new DoublyLinkedList<>();
	}
	/** size--
	 * returns the size of queue
	 */
	public int size() 
	{
		return data.size(); 
	}
	
	/** isempty--
	 *@returns true if the queue is empty else returns false 
	 */
	public boolean isEmpty() 
	{
		return data.isEmpty(); 
	}
	/** enqueue--
	 * adds new item in the end od the queue
	 * @param-- newValue that needs to be added.
	 */
	public void enqueue(AnyType newValue) 
	{
		data.add(newValue); 
	}
	/** first--
	 * returns the first item in the queue 
	 * @return-- first item in the queue
	 */
	public AnyType first() 
	{ 
		return data.get(0); 
	}
	/** dequeue--
	 * returns and deletes the first item in the queue
	 * @return-- first item in the queue
	 */
	public AnyType dequeue() 
	{ 
		return data.remove(0); 
	}


}
