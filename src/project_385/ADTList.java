package project_385;
/**  Abstract Data Type List implementation*/
@SuppressWarnings(value = { "unchecked" })
public class ADTList<T>{
	
	private T array[];
	private int numItems;
	private int curSize;
	
	
	/**Standard constructor that generates an empty array
	 * 
	 */
	public ADTList(){	//initialize the underlying structures with default constructor
		initArray();
	}
	
	/** Constructor that allows for user-defined initial length
	 * @param newMax User-defined array length
	 */
	public ADTList(int newMax){	//secondary constructor in the case that an array of greater than 10 items is desired
		curSize=newMax;
		initArray();
	}
	
	/** Constructor that allows for the building of an ADTList from a pre-existing array.
	 * @param items User-defined array to serve as the ADTList array
	 */
	public ADTList(T[] items){
		curSize = items.length;
		for(T item:items){
			add(item);
		}
	}
	
	/** Initializes the array to the predefined maximum size.
	 * 
	 */
	public void initArray(){	//establishes a new array of predefined size and sets the number of items to 0
		array = (T[]) new Object[curSize];
		numItems = 0;
	}
	
	/** Function to determine if the array is empty
	 * @return Returns true if there are 0 items in the array, otherwise return false
	 */
	public boolean isEmpty() {	//returns true if the list is empty, false if otherwise
		if(numItems==0){
			return true;
		}else{
			return false;
		}
	}

	/** Function to determine if the array is full
	 * @return Returns true if the number of elements is equal to the max size
	 */
	public boolean isFull() {	//returns true if list is full, false otherwise
		if(numItems==curSize){
			return true;
		}else{
			return false;
		}
	}

	/** Function to return the current number of elements
	 * @return Returns the number of elements
	 */
	public int size() {	//returns numItems
		return numItems;
	}
	
	/** Allows for extension of array max size
	 * @param length Guarantee that array is at least this size
	 * @return Returns the size of array after any changes
	 */
	public int ensureSize(int length){
		if(length>curSize){
			T[] tmp=(T[])new Object[length];
			System.arraycopy(array, 0, tmp, 0, array.length);
			array=tmp;
			curSize=length;
		}
		return(curSize);
	}

	/** Function to add a new object at a certain position
	 * @param position Place to store new object, must be between 0 and the size
	 * @param item Object to insert
	 * @return Returns true if operation is successful, otherwise returns false
	 */
	public boolean add(int position, Object item) {			//inserts [item] at index [position]
		if(isFull()){
			ensureSize(curSize+1);
		}
		if(position>size()-1||position<0){					//return false if list is full or index is out of range
			return(false);
		}else{
			for(int i=size()+1;i>position;i--){				//loops through array in reverse, shifting each element to the right one position
				array[i]=array[i-1];						//down to the provided index
			}
			array[position]=(T)item;
			numItems++;										//sets the new array value and increments numItems
			return(true);
		}
		
	}

	/** Function to add an object to the end of the list
	 * @param item Object to add to list
	 * @return Returns true is operations is successful, otherwise returns false
	 */
	public boolean add(T item) {						//inserts object [item] at end of list
		if(isFull()){
			ensureSize(curSize+1);
		}
		array[size()]=item;
		numItems++;
		return(true);
	}

	/** Replaces an object at specified position with provided object
	 * @param position Position to replace, must be between 0 and max size of array (including 0)
	 * @param item Object for replacement
	 * @return Returns true if constraints are met, otherwise returns false
	 */
	public boolean replace(int position, Object item) {	//replaces object at index [position] with object [item]
		if(position<=size()+1 && position>0){			//check if index is within bounds
			array[position]=(T)item;					//replace item
			return true;
		}else{
			return false;								//otherwise, return false
		}
	}

	/** Function to retrieve a value
	 * @param position Position in array to retrieve, must be between 0 and max size of array (including 0)
	 * @return Returns an object if position is valid, otherwise returns null
	 */
	public T get(int position) {						//return item at index [position]
		if(position<=size() && position>=0){			//check if index is within bounds
			return array[position];						//return item
		}else{
			return null;								//otherwise, return nothing
		}
	}

	/** Function to check if a specific object is in the list
	 * @param item Object to find
	 * @return Returns true if item is found, otherwise returns false
	 */
	public boolean contains(Object item) {				//determine if [item] is in array
		for(T contents:array){							//loop through items in the array
			if(contents==(T)item){						//check for equivalence
				return true;							//return true if found
			}
		}
		return false;									//otherwise, return false
	}

	/** Function to remove object at specified position by shifting all higher-indexed objects down 1 position
	 * @param position Position for removal, must be between 0 and max size (0 included)
	 * @return Returns true if constraints are met and object at position is not null, otherwise returns false
	 */
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

	/** Function to clear array, calls initArray to create an empty array of the current maximum size
	 * 
	 */
	public void clear() {		//clear array
		initArray();			//resets array and numItems
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String output="[";
		for(T item:array){
			output+=item+",";
		}
		output=output.substring(0,output.length()-1)+"]";
		return(output);
	}
}
