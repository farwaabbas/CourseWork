/**
 * <p>Title: DoublyLinkedList Class</p>
 *
 * <p>Description: This class is capable of creating a list of nodes that can add, remove, modify,and retrieve 
 * the data in the list at different locations. The class also contains iterator that can traverse through the list.
 * It also have a private node class that helps intantiaton of a node object. </p>
 * 
 * @authors Prof Joseph Svitak and Ume Abbas
 */
import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class DoublyLinkedList<AnyType> implements List<AnyType>
{
	private static class Node<AnyType>
	{
		private AnyType data;
		private Node<AnyType> prev;
		private Node<AnyType> next;

		public Node(AnyType d, Node<AnyType> p, Node<AnyType> n)
		{
			setData(d);
			setPrev(p);
			setNext(n);
		}

		public AnyType getData() { return data; }

		public void setData(AnyType d) { data = d; }

		public Node<AnyType> getPrev() { return prev; }

		public void setPrev(Node<AnyType> p) { prev = p; }

		public Node<AnyType> getNext() { return next; }

		public void setNext(Node<AnyType> n) { next = n; }
	}

	private int theSize;
	private int modCount;
	private Node<AnyType> header;
	private Node<AnyType> trailer;

	public DoublyLinkedList()
	{
		header = new Node<AnyType>(null, null, null);
		trailer = new Node<AnyType>(null, null, null);
		modCount = 0;
		clear();
	}

	public void clear()
	{
		header.setNext(trailer);
		trailer.setPrev(header);
		theSize = 0;
	}

	public int size()
	{
		return theSize;
	}

	public boolean isEmpty()
	{
		return (size() == 0);
	}
	/** get--
	 * returns the data stored in the node at index specifies by the user by calling another private method getNode.
	 * @param-- index accepts the int value that locates the data to be retrieved.
	 * @return data of type AnyType to the user.
	 */
	public AnyType get(int index) // works 
	{
		return (getNode(index)).getData();
	}
	/** set--
	 * set method sets a newValue specified by the user by replacing the oldValue from the node at given index.
	 * returns the oldValue stored in the node at an index  by calling another private method getNode.
	 * @param-- newValue accepts the data of type AnyType that has to be set in the list.
	 * @param-- index accepts the int value that locates the data to be modified.
	 * @return oldValue of type AnyType to the user.
	 */
	public AnyType set(int index, AnyType newValue) // works 
	{
		AnyType oldValue;
		Node<AnyType> setNode = getNode(index);
		oldValue = setNode.getData();
		setNode.setData(newValue);
		return oldValue; 

	}

	/** add--
	 * this method adds a newValue in the end of the list.
	 * @param-- newValue accepts the data of type AnyType that has to be added in the list.
	 * @return true if the value is added successfully and false other wise
	 */
	public boolean add(AnyType newValue) //this works 
	{
		add(size(), newValue);
		return true;
	}
	/** add--
	 * this method adds a newValue in the list at specified index.
	 * @param-- index accepts the value of type int that locates where the new node has to be added.
	 * @param-- newValue accepts the data of type AnyType that has to be added in thr list.
	 */
	public void add(int index, AnyType newValue) // works
	{
		Node<AnyType> current, nextNode , prevNode ;
		nextNode = getNode(index,0,theSize);
		prevNode = nextNode.getPrev();
		current = new Node<AnyType>(newValue,prevNode,nextNode); 
		prevNode.setNext(current);
		nextNode.setPrev(current);
		theSize++ ;

	}
	/** remove--
	 * this method removes a value from the list at a specified index.
	 * @param-- index accepts the int value that locates the data to be removed.
	 * @return-- returns the removedItem of type AnyType from the list. 
	 */
	public AnyType remove(int index)
	{
		return remove(getNode(index));
	}

	public Iterator<AnyType> iterator()
	{
		return new LinkedListIterator();    
	}

	/** getNode--
	 * this method returns the address to the node at a specified index.
	 * @param-- index accepts the int value that locates the node to retrieve.
	 * @return-- returns the Node object of type AnyType from the list. 
	 */
	private Node<AnyType> getNode(int index)
	{
		return (getNode(index, 0, size()-1));
	}
	/** getNode--
	 * this private method returns the address to the node at a specified index. The methos also accepts lowest and 
	 * highest range that is valid and then it iterates through the list to reach the node at a specified index. 
	 * @param-- index accepts the int value that locates the node to retrieve.
	 * @param-- lower accepts the lowest valid index from the user.
	 * @param-- upper accepts the highest valid index from the user. 
	 * @return-- returns the Node object of type AnyType from the list. 
	 * @throws-- IndexOutOfBoundsException if the user sends an invalid index, even f the user tries to get a node from
	 * an empty list.
	 */
	private Node<AnyType> getNode(int index, int lower, int upper) throws IndexOutOfBoundsException // works 
	{
		if (index < lower || index > upper )
			throw new IndexOutOfBoundsException("Invalid Index: The index should be in the range " + lower +" to " + upper);

		// creating 3 external variables that will help in iterating through the list 
		Node<AnyType> current = header.getNext() ; 
		//int count = 0 ;
		while(lower < index )
		{	  
			current = current.getNext();
			lower++ ;
		}
		return current ;   

	}

	/** remove--
	 * this method removes a value from the list by removing the node containing the value and returns the value back to the 
	 * call from another remove method.
	 * @param currNode -- is the node that has to be removed from the list. 
	 * @return returns the removedItem from the list. 
	 */
	private AnyType remove(Node<AnyType> currNode) // works
	{

		Node<AnyType> nextNode , prevNode ;
		prevNode = currNode.getPrev();
		nextNode = currNode.getNext();
		prevNode.setNext(nextNode);
		nextNode.setPrev(prevNode);
		theSize-- ;
		return currNode.getData();

	}

	private class LinkedListIterator implements Iterator<AnyType>
	{
		private Node<AnyType> current;
		private int expectedModCount;
		private boolean okToRemove;

		LinkedListIterator()
		{
			current = header.getNext();
			expectedModCount = modCount;
			okToRemove = false;
		}

		public boolean hasNext()
		{
			return (current != trailer);
		}

		public AnyType next()
		{
			if (modCount != expectedModCount)
				throw new ConcurrentModificationException();
			if (!hasNext())
				throw new NoSuchElementException();

			AnyType nextValue = current.getData();
			current = current.getNext();
			okToRemove = true;
			return nextValue;
		}

		public void remove()
		{
			if (modCount != expectedModCount)
				throw new ConcurrentModificationException();
			if (!okToRemove)
				throw new IllegalStateException();

			DoublyLinkedList.this.remove(current.getPrev());
			expectedModCount++;
			okToRemove = false;
		}
	}
}

