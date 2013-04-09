package project_385;
public class Node<T> {

	  private T item;
	  private Node<T> nextNode;

	  public Node() {
		item = null;
		nextNode = null;
	  }

	  public Node(T item) {
		this.item = item;
		nextNode = null;
	  }

	  public Node(T item, Node<T> nextNode) {
		this.item = item;
		this.nextNode = nextNode;
	  }

	  public void setItem(T item) {
		this.item = item;
	  }

	  public T getItem() {
		return item;
	  }

	  public void setNext(Node<T> nextNode) {
		this.nextNode = nextNode;
	  }

	  public Node<T> getNext() {
		return nextNode;
	  }

}


