package project_385;
/** Abstract Data Type Node; Standard node class */
public class Node<T> {

	private T item;
	private Node<T> nextNode;

	  /** Standard constructor; provides a node with no next node and no item
	 * 
	 */
	public Node() {
		item = null;
		nextNode = null;
	  }

	  /** Constructor to set the item of the node
	 * @param item Object to be assigned to node
	 */
	public Node(T item) {
		this.item = item;
		nextNode = null;
	  }

	  /** Constructor that accepts both an item and a node
	 * @param item Object to be assigned to node
	 * @param nextNode Node to be set as next node in sequence
	 */
	public Node(T item, Node<T> nextNode) {
		this.item = item;
		this.nextNode = nextNode;
	  }

	  /** Function to set item of node
	 * @param item Object to be assigned to node
	 */
	public void setItem(T item) {
		this.item = item;
	  }

	  /** Function to retrieve item of node
	 * @return Returns the item of the node, including null if it is not set
	 */
	public T getItem() {
		return item;
	  }

	  /** Function to set next node of the node
	 * @param nextNode Node representing the next node is sequence
	 */
	public void setNext(Node<T> nextNode) {
		this.nextNode = nextNode;
	  }

	  /** Function to retrieve next node of node
	 * @return Returns the next node in sequence, including null if it is not set
	 */
	public Node<T> getNext() {
		return nextNode;
	  }

}


