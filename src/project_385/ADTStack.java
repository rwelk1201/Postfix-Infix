package project_385;

public class ADTStack<T> {

	private Node<T> top;
	
	public ADTStack() {
		top=null;
	}

	public boolean isEmpty() {	//returns true if the list is empty, false if otherwise
		if(top==null){
			return true;
		}else{
			return false;
		}
	}
	
	public void push(T newItem){
		if(!isEmpty()){
			top=new Node<T>(newItem, top);
		}else{
			top=new Node<T>(newItem);
		}
	}
	
	public T pop(){
		Node<T>tmp=top;
		if(tmp!=null){
			top=top.getNext();
			return(tmp.getItem());
		}else{
			return(null);
		}	
	}
	
	public T peek(){
		if(!isEmpty()){
			return top.getItem();
		}else{
			return(null);
		}
	}
	
	public void clear(){
		top=null;
	}
	
	public String toString(){
		String output="[top:"+top.getItem();
		Node<T>curNode=top.getNext();
		
		while(curNode!=null){
			output+=","+curNode.getItem();
			curNode=curNode.getNext();
		}
		output+="]";
		return(output);
	}
}
