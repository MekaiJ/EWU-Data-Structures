public class CDoublyLinkedList {

	private class Node {
		private Object data;   //Assume data implemented Comparable
		private Node next, prev;
		private Node(Object data, Node pref, Node next)
		{
			this.data = data;
			this.prev = pref;
			this.next = next;
		}
	}

	private Node head;
	private int size;

	public CDoublyLinkedList() {
		this.head = new Node(null, null, null );
		this.head.next = this.head;
		this.head.prev=this.head;
		this.size = 0;
	}

	public boolean isEmpty() {
		return this.head == this.head.next;
	} 
	
	// Add Object data to start of this LinkedList
	// Please DO NOT change this addFirst() method.
	// You must keep and include this provided addFirst() method
	//      in your source code.
	public void addFirst(Object data) {
		Node nn = new Node(data, this.head, this.head.next);
		this.head.next.prev = nn;
		this.head.next = nn;
		this.size ++;
	}

	// write a method void addLast(Object data) that will insert 
	// the piece of data at the end of the current list.
	// Note: this list is allowed to store null data element in its list node.

	/**
	 *
	 * @author Mekai Johnson
	 */
	public void addLast(Object data) {
		//This works
		//create a new node setting its respective pointers
		Node nn = new Node(data, this.head.prev, this.head);
		//set the existing node's pointers so no links are broken or mixed up
		this.head.prev.next = nn;
		this.head.prev = nn;
		this.size ++;
	}
	
	// Write the subListOfSmallerValues method.  
	// It should return a CDoublyLinkedList object 
	//     containing data that is smaller than the value passed to the method.
        // If a null data element in this list is encountered, you can skip it.
        // You need to use the CompareTo() method to determine which object is smaller.
        // If list A contains {9, 11, 14, 6, 4, 7} and you call  A.subListOfSmallerValues(10),
        // the method call returns a list that contains data in A that is smaller than 10, the passed-in argument.
        // That is, the returned list contains { 9, 6, 4, 7}.
	/**
	 *
	 * @author Mekai Johnson
	 */
	public CDoublyLinkedList subListOfSmallerValues(Comparable data) {
		//if(data == null){walk list} else if(data in sublist){add to sublist} else{walk list}
		//compare to < 0? - need to typecast Object to comparable type?
		CDoublyLinkedList sub = new CDoublyLinkedList();
		Node cur = this.head.next;
		while(cur != head) {
			if(cur.data == null) {
				cur = cur.next;
			}else if(data.compareTo(cur.data) > 0) {
				sub.addLast(cur.data);
			}
			cur = cur.next;
		}
		return sub; //change this as needed.
	}
	
	// This method should remove the first occurrence of the data from the list, 
        //      starting at the *BACK* of the list. 
        // It scans the list from back to the front by following the prev links. 
	// The method should return true if successful, false otherwise. 
	// Note that list node may contain null data element. Please handle this edge case.
	/**
	 *
	 * @author Mekai Johnson
	 */
	public boolean removeStartingAtBack(Object dataToRemove) {
		//this works
		Node cur = this.head.prev, prev = this.head;
		//backward traversal
		while(cur != this.head) {
			//first check if they are both null because .equals() doesnt like the data to be null for some reason
			if(dataToRemove == null && cur.data == null) {
				cur.prev.next = prev;
				prev.prev = cur.prev;
				this.size--;
				return true;
			//then we can check if our data is equal
			} else if (dataToRemove != null && dataToRemove.equals(cur.data)) {
				cur.prev.next = prev;
				prev.prev = cur.prev;
				this.size--;
				return true;
			}
			//if they are not equal traverse list
			prev = prev.prev;
			cur = cur.prev;
		}
		//if nothing was found return false
		return false;//change this as needed.
	}
	
	// Returns the index of the last occurrence of the specified element in this list, 
	//     or -1 if this list does not contain the element. 
	// More formally, returns the highest index i 
	//     such that (o==null ? get(i)==null : o.equals(get(i))), 
	//     or -1 if there is no such index.
	// Note: a list node may store a null data element. Please handle this edge case.
	/**
	 *
	 * @author Mekai Johnson
	 */
	public int lastIndexOf(Object o) {
		//this works
		Node cur = this.head.prev;
		//thinking of index backwards
		int index = size-1;
		while(cur != this.head) {
			//checking for target data equals current data
			if(cur.data == null && o == null) {
				return index;
			}else if(cur.data != null && cur.data.equals(o)) {
				return index;
			}
			//traverse the list backward
			cur = cur.prev;
			index--;
		}
		return -1; //change this as needed.
	}
	
	
	// Removes from this list all of its elements that 
	//    are NOT contained in the specified linkedlist other.
	// If any element has been removed from this list,
	//    returns true. Otherwise returns false.
	// If other list is null, throws NullPointerException.
        // Helper methods are allowed.
	/**
	 *
	 * @author Mekai Johnson
	 */
	public boolean retainAll(CDoublyLinkedList other) throws NullPointerException {
		if(other == null) {
			throw new NullPointerException();
		}
		Node cur = this.head.next;
		boolean removed = false;
		while(cur != this.head) {
			//save the next node BEFORE removal so links do not get messed up
			Node nextNode = cur.next;
			//remove all instances if not in the other list
			if(!other.contains(cur.data)) {
				removeAll(cur.data);
				removed = true;
			}
			cur = nextNode;
		}

		return removed; //change this as needed.
	}

	/**
	 *
	 * @author Mekai Johnson
	 */
	//healper method for retain all
	public void removeAll(Object data) {
		if(isEmpty()) {
			return;
		}
		Node cur = this.head.next;
		while(cur != this.head) {
			if(cur.data == null && data == null) {
				cur.prev.next = cur.next;
				cur.next.prev = cur.prev;
				this.size--;
			}
			if(cur.data != null && cur.data.equals(data)) {
				cur.prev.next = cur.next;
				cur.next.prev = cur.prev;
				this.size--;
			}
			cur = cur.next;
		}
	}

	/**
	 *
	 * @author Mekai Johnson
	 */
	//helper contains() method for retainall
	public boolean contains(Object o) {
		//if the list is empty there's no way we can find the element we are searching for
		if(isEmpty()) {
			return false;
		}
		Node cur = this.head.next;
		//traverse the list until we find our data
		while(cur != this.head) {
			if((cur.data == null && o == null) || cur.data != null && cur.data.equals(o)) {
				return true;
			}
			cur = cur.next;
		}
		//if we get here that means nothing was found so return false
		return false; //change this as you need.
	}

        // Write this method to sort this list using insertion sort algorithm, 
        //      as we have learned in the classroom.
	/**
	 *
	 * @author Mekai Johnson
	 */
	public void insertionSort() {
		//this works
		Node lastSorted, sortedWalker;
		Comparable firstUnsortedData;
		for(lastSorted = this.head.next; lastSorted != this.head.prev; lastSorted = lastSorted.next) {
			firstUnsortedData = (Comparable)lastSorted.next.data;
			for(sortedWalker = lastSorted; sortedWalker != this.head && ((Comparable)sortedWalker.data).compareTo(firstUnsortedData) > 0; sortedWalker = sortedWalker.prev) {
				sortedWalker.next.data = sortedWalker.data;
			}
			sortedWalker.next.data = firstUnsortedData;
		}
	}
	
	@Override
	public String toString() {
		String result = "{";
	    for (Node node = this.head.next; node != this.head; node = node.next) {
	    		if(node.next != this.head)
	    			result += node.data + "->"; 
	    		else
	    			result += node.data;
	    }
	    return result + "}";
	  }
}
			