package project_385;

// ----- ADTList.java										-----
// ----- Ryan Welker										-----
// ----- CSC 385											-----
// ----- February 4, 2013									-----
// ----- Implementation of the ListInterface				-----
// ----- Variables:											-----
// ----- 	array: 		underlying array structure			-----
// -----	numItems:	tracker for current size of array	-----
// -----	MAXSIZE:	pseudo-constant for size of array	-----
@SuppressWarnings(value = { "unchecked" })
public class ADTList<T>{

	private T array[];
	private int numItems;
	private int curSize;
	
	
	public ADTList(){	//initialize the underlying structures with default constructor
		initArray();
	}
	
	public ADTList(int newMax){	//secondary constructor in the case that an array of greater than 10 items is desired
		curSize=newMax;
		initArray();
	}
	
	public ADTList(T[] items){
		curSize = items.length;
		for(T item:items){
			add(item);
		}
	}
	
	public void initArray(){	//establishes a new array of predefined size and sets the number of items to 0
		array = (T[]) new Object[curSize];
		numItems = 0;
	}
	
	public boolean isEmpty() {	//returns true if the list is empty, false if otherwise
		if(numItems==0){
			return true;
		}else{
			return false;
		}
	}

	public boolean isFull() {	//returns true if list is full, false otherwise
		if(numItems==curSize){
			return true;
		}else{
			return false;
		}
	}

	public int size() {	//returns numItems
		return numItems;
	}
	
	public int ensureSize(int length){
		if(length>curSize){
			T[] tmp=(T[])new Object[length];
			System.arraycopy(array, 0, tmp, 0, array.length);
			array=tmp;
			curSize=length;
		}
		return(curSize);
	}

	public boolean add(int position, Object item) {	//inserts [item] at index [position]
		if(isFull()){
			ensureSize(curSize+1);
		}
		if(position>size()-1||position<0){	//return false if list is full or index is out of range
			return(false);
		}else{
			for(int i=size()+1;i>position;i--){		//loops through array in reverse, shifting each element to the right one position
				array[i]=array[i-1];				//down to the provided index
			}
			array[position]=(T)item;
			numItems++;								//sets the new array value and increments numItems
			return(true);
		}
		
	}

	public boolean add(T item) {				//inserts object [item] at end of list
		if(isFull()){
			ensureSize(curSize+1);
		}
		array[size()]=item;
		numItems++;
		return(true);
	}

	public boolean replace(int position, Object item) {	//replaces object at index [position] with object [item]
		if(position<=size()+1 && position>0){			//check if index is within bounds
			array[position]=(T)item;					//replace item
			return true;
		}else{
			return false;								//otherwise, return false
		}
	}

	public T get(int position) {						//return item at index [position]
		if(position<=size() && position>=0){			//check if index is within bounds
			return array[position];					//return item
		}else{
			return null;								//otherwise, return nothing
		}
	}

	public boolean contains(Object item) {				//determine if [item] is in array
		for(T contents:array){							//loop through items in the array
			if(contents==(T)item){						//check for equivalence
				return true;							//return true if found
			}
		}
		return false;									//otherwise, return false
	}

	public boolean remove(int position) {									//remove item at index [position]
		if(position>=0 && position<=size() && array[position]!=null){		//check if index is within bounds
			for(int i=position;i<size();i++){								//loop through array, shifting each value
				array[i]=array[i+1];										//after position one place to the left
			}
			numItems--;														//decrement numItems
			return true;													//return success
		}
		return false;														//otherwise, return false
	}

	public void clear() {		//clear array
		initArray();			//resets array and numItems
	}
	
	public String toString(){
		String output="[";
		for(T item:array){
			output+=item+",";
		}
		output=output.substring(0,output.length()-1)+"]";
		return(output);
	}
}
