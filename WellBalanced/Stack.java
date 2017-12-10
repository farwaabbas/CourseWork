/**
 * <p>Title: Stack Interface</p>
 *
 * <p>Description: This interface will define the abstract methods for a DynamicStackArray class. </p>
 * 
 * @author  Joseph Svitak 
 */

public interface Stack<AnyType>
{
  int size();

  boolean isEmpty();

  void push(AnyType newValue);

  AnyType top();

  AnyType pop();
}

