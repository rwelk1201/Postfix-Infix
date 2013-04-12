package project_385;

public class ADTBinaryTree<T> {
	private BinaryNode<T> root;
	
	public ADTBinaryTree(){
		this.root=null;
	}
	
	public ADTBinaryTree(T item){
		this.root=new BinaryNode<T>(item);
	}
	
	public void setRootItem(T item){
		this.root=new BinaryNode<T>(item);
	}
	
	public void setRoot(BinaryNode<T> node){
		this.root=node;
	}
	
	public BinaryNode<T> getRoot(){
		return(this.root);
	}
	
	public String processPreOrder(){
		return(preorder(root));
	}
	
	public String processInOrder(){
		return(inorder(root));
	}
	
	public String processPostOrder(){
		return(postorder(root));
	}
	
	public String preorder(BinaryNode<T> root){
		if(root.isLeaf()){
			return(root.getItem().toString());
		} else {
			return(root.getItem()+((root.getLeftChild()!=null)?" " + preorder(root.getLeftChild()):"")+((root.getRightChild()!=null)?" " + preorder(root.getRightChild()):""));
		}
	}
	
	public String inorder(BinaryNode<T> root){
		if(root.isLeaf()){
			return(root.getItem().toString());
		} else {
			return(((root.getLeftChild()!=null)?inorder(root.getLeftChild()):"") + " " + root.getItem().toString()+((root.getRightChild()!=null)?" " + inorder(root.getRightChild()):""));
		}
	}
	
	public String postorder(BinaryNode<T> root){
		if(root.isLeaf()){
			return(root.getItem().toString());
		} else {
			return(((root.getLeftChild()!=null)?postorder(root.getLeftChild()):"")+((root.getRightChild()!=null)?" " + postorder(root.getRightChild()):"")+" "+root.getItem().toString());
		}
	}
	
	public void clear(){
		root=null;
	}
}
