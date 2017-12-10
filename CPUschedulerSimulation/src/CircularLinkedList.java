import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class CircularLinkedList<AnyType> implements List<AnyType>
{
	private static class Node<AnyType>
	{
		private AnyType data;
		private Node<AnyType> next;

		public Node(AnyType d, Node<AnyType> n)
		{
			setData(d);
			setNext(n);
		}

		public AnyType getData() { return data; }

		public void setData(AnyType d) { data = d; }

		public Node<AnyType> getNext() { return next; }

		public void setNext(Node<AnyType> n) { next = n; }
	}

	private int theSize;
	private int modCount;
	private Node<AnyType> tail;

	public CircularLinkedList()
	{
		this.clear();
		modCount = 0 ;

	}

	public void clear()
	{
		theSize = 0 ;
		tail = null ;

	}

	public int size()
	{
		return theSize ;
	}

	public boolean isEmpty()
	{
		return (theSize == 0); 
	}

	public AnyType get(int index)
	{
		return getNode(index).getData() ;
		
	}
	
	public AnyType set(int index, AnyType newValue)
	{
		 Node<AnyType> setNode = getNode(index);
		 AnyType data = setNode.getData() ;
		 setNode.setData(newValue);
		 return data ;
	}

	public boolean add(AnyType newValue)
	{
		add(size(), newValue);
		return true;
	}

	public void add(int index, AnyType newValue)
	{
		if(invalidIndex(index))
			throw new IndexOutOfBoundsException();

		Node<AnyType> newNode = new Node<AnyType>(newValue, null);

		if(isEmpty()) // if the list is initially empty 
		{
			newNode.setNext(newNode); // if the list is empty 
			tail = newNode ;
		}

		if(index == size()) //special case: if the list is full 
		{
			Node<AnyType> first = tail.getNext(); // pointer to first node 
			tail.setNext(newNode); // adding new node to the end
			tail = newNode; // making the new Node the tail 
			tail.setNext(first); // connecting the new tail to the first element in the list 
		}

		else 
		{  
			Node<AnyType> curr = tail.getNext();
			Node<AnyType> prev = tail ;
			for(int i = 0  ; i < index ; i++ )
			{
				prev = curr ;
				curr = curr.getNext();
				// by the end of loop curr points to the node at specified index  
			}
			prev.setNext(newNode);
			newNode.setNext(curr);
		}
		theSize++ ; 
		// check to see if we are adding at theSize
	}


	private boolean invalidIndex(int index)
	{
		if(index > size() || index < 0)
			return true ;
		return false ;
	}
	public AnyType remove(int index)
	{
		return removeNode(index,0,size()-1).getData();
	}
	
	
	private Node<AnyType> removeNode(int index, int lower, int upper)
	{
		if(isEmpty())
			throw new IllegalStateException("The list is empty");
		
		if(index > upper || index < lower) throw new IndexOutOfBoundsException();
		
		Node<AnyType> node;
		
		
		//if removed node is the only element left then update tail = null
		  if(theSize == 1) { //index == lower && index == upper
			  node  = tail ; 
			  //clear();
		  }
		  // removing the mid values 
			Node<AnyType> curr = tail.getNext();
			Node<AnyType> prev = tail ;
			for(int i = 0 ; i < index ; i++)
			{
				prev = curr ;
				curr = curr.getNext();	
			}
			
			node = curr ;
			prev.setNext(curr.getNext());
			curr.setNext(null);
			if(index == upper) // if the removed node is the last one update the tail 
				tail = prev ;
		  
		
		theSize-- ;
		return node ;
		
	}
	
	public void rotate() 
	{
		if(isEmpty())
			throw new IllegalStateException("The list is empty");
		Node<AnyType> head = tail.getNext() ;
		tail = head ;
		
	}

	public Iterator<AnyType> iterator()
	{
		return new LinkedListIterator();    
	}

	private Node<AnyType> getNode(int index)
	{
		return (getNode(index, 0, size()-1));
	}

	private Node<AnyType> getNode(int index, int lower, int upper)
	{
		if(index < lower || index > upper) throw new IndexOutOfBoundsException("Invalid Index");
		if(isEmpty()) throw new IllegalStateException("The list is empty");
		
		Node<AnyType> curr = tail.getNext();
		for(int i = 0 ; i < index ; i++)
		{
			curr = curr.getNext();
			
		}
		return curr ;
	}

	
	private class LinkedListIterator implements Iterator<AnyType>
	{
		private Node<AnyType> previous;
		private Node<AnyType> current;
		private int expectedModCount;
		private boolean okToRemove;

		LinkedListIterator()
		{
			current = tail.getNext();
			previous = current ;
			expectedModCount = modCount ;
			okToRemove = false ;
		}

		public boolean hasNext()
		{
			
			return (previous != tail);
		}

		public AnyType next()
		{
			AnyType data;
			if(modCount != expectedModCount)
				throw new ConcurrentModificationException();
			if(!hasNext() )
				throw new NoSuchElementException();
			
			data = current.getData();
			previous = current;
			current = current.getNext();
			okToRemove = true ;
			return data ;

		}

		public void remove()
		{
			if (modCount != expectedModCount)
		        throw new ConcurrentModificationException();
		      if (!okToRemove)
		        throw new IllegalStateException();
		      //getting a node before previous
		      Node<AnyType> node = tail.getNext();
		      while(node.getNext() != previous)
		      {
		    	  node = node.getNext();
		      }
		      node.setNext(current);
		      expectedModCount++;
		      okToRemove = false;
		      theSize--;

		}
	}
}