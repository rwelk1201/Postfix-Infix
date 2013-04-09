package project_385;

public class ADTStack<T> {

	private Node top;
	
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
			Node tmp=top;
			top=new Node(newItem, top);
		}else{
			top=new Node(newItem);
		}
	}
	
	public Node pop(){
		Node tmp=top;
		top=top.getNext();
		return(tmp);		
	}
	
	public Object peek(){
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
		Node curNode=top.getNext();
		
		while(curNode!=null){
			output+=","+curNode.getItem();
			curNode=curNode.getNext();
		}
		output+="]";
		return(output);
	}
}
