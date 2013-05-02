package project_385;

/** A simple stack class for pushing, peeking, and popping items
 * @author Matthew Dean
 *
 * @param <T>
 */
public class ADTStack<T> {

	private Node<T> top;

	/** Instantiates an empty stack
	 */
	public ADTStack() {
		top = null;
	}

	/** Returns the empty status of the stack
	 * @return A boolean value indicating whether or not the stack is empty
	 */
	public boolean isEmpty() { // returns true if the list is empty, false if
								// otherwise
		return top == null;
	}

	/** Pushes a given item to the top of the stack
	 * 
	 * @param newItem
	 *            The item
	 */
	public void push(T newItem) {
		// now top.getNext points to the former top
		top = new Node<T>(newItem, top);
	}

	/** Removes the item from the top of the stack and returns it
	 * 
	 * @return The item which was at the top of the stack, otherwise null
	 */
	public T pop() {
		Node<T> tmp = top;
		if (tmp != null) {
			top = top.getNext();
			return tmp.getItem();
		} else {
			// the stack is empty, so do nothing and return null
			return null;
		}
	}

	/** Returns the item currently at the top of the stack
	 * 
	 * @return The item currently at the top of the stack
	 */
	public T peek() {
		if (isEmpty()) {
			return null;
		}
		return top.getItem();
	}

	/** Clears the stack
	 */
	public void clear() {
		top = null;
	}

	/** Creates a visual display of the stack
	 * 
	 * @return A string representation of the stack
	 */
	public String toString() {
		// so we don't have memory problems with concatenating many strings
		StringBuilder buffer = new StringBuilder(']');
		buffer.append('[');
		// loop through all nodes and print the values, separated by commas
		for (Node<T> curNode = top; curNode != null; curNode = curNode
				.getNext()) {
			buffer.append(curNode.getItem());
			if (curNode.getNext() != null)
				buffer.append(',');
		}
		buffer.append(']');
		return buffer.toString();
	}
}
