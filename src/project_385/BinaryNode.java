package project_385;

public class BinaryNode<T> {
	private T item;
	private BinaryNode<T> left,right;

	public BinaryNode() {
		this.item = null;
		this.left = null;
		this.right = null;
	}

	public BinaryNode(T item) {
		this.item = item;
		this.left=null;
		this.right=null;
	}

	public BinaryNode(T item, BinaryNode<T> newLeft, BinaryNode<T> newRight) {
		this.item = item;
		this.left = newLeft;
		this.right = newRight;
	}

	public void setItem(T item) {
		this.item = item;
	}

	public T getItem() {
		return this.item;
	}

	public BinaryNode<T> getRightChild() {
		return this.right;
	}

	public void setRightChild(BinaryNode<T> right) {
		this.right = right;
	}
	
	public void setRightItem(T right){
		this.right = new BinaryNode<T>(right);
	}

	public BinaryNode<T> getLeftChild() {
		return this.left;
	}

	public void setLeftChild(BinaryNode<T> left) {
		this.left = left;
	}
	
	public void setLeftItem(T left){
		this.left = new BinaryNode<T>(left);
	}
	
	public boolean hasLeftChild(){
		if(this.left==null){
			return(false);
		} else{
			return(true);
		}
	}
	
	public boolean hasRightChild(){
		if(this.right==null){
			return(false);
		} else{
			return(true);
		}
	}
	
	public boolean isLeaf(){
		if(this.left==null && this.right==null){
			return(true);
		} else {
			return(false);
		}
	}
}
