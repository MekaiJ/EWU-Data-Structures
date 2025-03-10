import org.w3c.dom.Node;

import java.util.NoSuchElementException;

public class MyLinkedList {
	
	private ListNode head;
	private int size;





	//inner class for ListNode
	private class ListNode {
		private Object data;
		private ListNode next;
		private ListNode(Object d) {
			this.data = d;
			this.next = null;
		}
	}
	
	public MyLinkedList() {
		this.head = new ListNode(null); //with a dummy head node
		this.size = 0;
	}
	
	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	// Add Object e to start of this LinkedList
	// Please DO NOT change this addFirst() method.
	// You must keep and include this provided addFirst() method in your source code.
	public void addFirst(Object e)
	{
		ListNode node = new ListNode(e);
		node.next = head.next;
		head.next = node;
		size++;
	}
	
	// Remove(cut out) the first data node(the node succeeding the dummy node) 
	//       in this list, then returns the data in the node removed.
	// If the size of this list is zero, throws an Exception.
	/**
	 * @author Mekai Johnson
	 */
	public Object removeFirst() throws Exception {
		//This works
		if(size == 0) {
			throw new Exception("List is empty");
		}
		//setting the dummy head's next to be the first index, removing the first data node
		Object temp = head.next.data;
		head.next = head.next.next;
		size--;

		return temp; //change this as you need.
	}
	
	// Returns true if this list contains the specified element o. 
	// More formally, returns true if and only if this list contains at least one element e 
	// such that (o==null ? e==null : o.equals(e)).
	// Note: you have to handle the case where a list node stores null data element.
	/**
	 * @author Mekai Johnson
	 */
	public boolean contains(Object o) {
		//this works
		ListNode cur = head.next;
		//if the list is empty there's no way we can find the element we are searching for
		if(size == 0) {
			return false;
		}

		//check if the first data node's data is null if the target data is null
		if(cur == null && o == null) {
			return true;
		}

		//iterate through our list while checking if current node's data is equal to our target data
		while(cur != null) {
			if(cur.data == null) {
				if(o == null) {
					return true;
				}else {
					return false;
				}
			}
            if(cur.data.equals(o)) {
				return true;
			}else {
                cur = cur.next;
            }
		}
		return false; //change this as you need.
	}
	
	// Removes the first occurrence of the specified element o from this list and returns true, if it is present. 
	// If this list does not contain the element o, it is unchanged and the method returns false.
	// More formally, removes the element o with the lowest index i such that 
	//     (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists).
	// Note: you have to handle the case where a list node stores null data element.
	/**
	 * @author Mekai Johnson
	 */
	public boolean remove(Object o) {
		//this works
		if(size == 0) {
			return false;
		}
		ListNode cur = head.next, prev = head;

		//loop through our list until we find our target data
		while(cur != null && cur.data != o) {
			prev = cur;
			cur = cur.next;
		}

		//once we are at our target data we can remove that node and return true
		if(cur != null) {
			prev.next = cur.next;
			size--;
			return true;
		}
		return false;//change this as you need.
    }

	// Removes all copies of o from this linked list.
	// You have to handle the cases where Object o may 
	//        have zero, one or multiple copies in this list.
	// If any element has been removed from this list, returns true. 
	//        Otherwise returns false.
	// Note: be careful when multiple copies of Object o are stored
	//        in consecutive(adjacent) list nodes.
	//        E.g. []->["A"]->["A"]->["B"]. 
	//        Be careful when removing all "A"s in this example.
	// Note: This list may contains zero, one or multiple copies of null data elements.
	/**
	 * @author Mekai Johnson
	 */
	public boolean removeAllCopies( Object o ) { //passed test
		//this works
		boolean removed = false;
		if(size == 0) {
			return removed;
		}
		ListNode cur = head.next, prev = head;

		//looop through the list while checking if our target data is the current node's data
		while(cur != null) {
			//if it is, we remove it
			if(cur.data == o) {
				prev.next = cur.next;
				cur = prev.next;
				size--;
				removed = true;
			//if it isnt, we continue our walk
			}else if(cur.data != o) {
				prev = cur;
				cur = cur.next;
			}
		}
		return removed;
	}
	
	// Insert data elements from linkedlist A and B alternately into 
	//    a new linkedlist C, then returns C.
        // Follow the pattern to pick items in list A and B, 
        //        linkedlist A->linkedlist B->linkedlist A->linkedlist B …
	// If A is longer than B, append remaining items in A to C
	//     when the end of B is first reached.
	// If B is longer than A, append remaining items in B to C
	//     when the end of A is first reached.
	// E.g1 A = {1, 3, 5, 7, 9} and B = {2, 4, 6}; and 
	//       C will be {1, 2, 3, 4, 5, 6, 7, 9}.
        //
	// E.g2 A = {1, 3, 5} and B = {2, 4, 6, 8, 10}; and 
	//       C will be {1, 2, 3, 4, 5, 6, 8, 10}.
	// Note: after this method is called, both list A and B are UNCHANGED.
	/**
	 * @author Mekai Johnson
	 */
	public static MyLinkedList interleave(MyLinkedList A, MyLinkedList B) {
		//this works
		MyLinkedList C = new MyLinkedList();
		ListNode curA = A.head.next;
		ListNode curB = B.head.next;
		ListNode curC = C.head;

		//interchanging the new node's data we are creating between a and b
		while (curA != null && curB != null) {
			curC.next = C.new ListNode(curA.data);
			curC = curC.next;
			curA = curA.next;
			curC.next = C.new ListNode(curB.data);
			curC = curC.next;
			curB = curB.next;
		}

		//solve for the edge case that either list is larger than the other
		//if list A is longer, we append list A to list C
		while (curA != null) {
			curC.next = C.new ListNode(curA.data);
			curC = curC.next;
			curA = curA.next;
		}
		//if list B is longer, we append list B to list C
		while (curB != null) {
			curC.next = C.new ListNode(curB.data);
			curC = curC.next;
			curB = curB.next;
		}
		return C;//change this as you need.
	}
	
	// Inserts the specified element at the specified position in this list. 
	// Shifts the element currently at that position (if any) and any subsequent
	//     elements to the right (adds one to their indices).
	// if(index < 0 or index > this.size), throws IndexOutOfBoundsException.
	
	// E.g, if this list is [dummy]->["A"]->["B"]->["C"] with size = 3.
	//   add(0,D) will result in [dummy]->["D"]->["A"]->["B"]->["C"].
	//   Continuing on the previous add() call, add(1,"E") will
	//   change the existing list to [dummy]->["D"]->["E"]->["A"]->["B"]->["C"].
	/**
	 * @author Mekai Johnson
	 */
	public void add(int index, Object o) {
		//this works
		ListNode cur = head.next, prev = head;
		int i = 0;
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		//walk through the list
		while (i < index) {
			prev = cur;
			cur = cur.next;
			i++;
		}
		//when we are at our index, we can insert our new node and increase the size
		ListNode newNode = new ListNode(o);
		prev.next = newNode;
		newNode.next = cur;
		size++;
	}
	

	// Returns the element at the specified index in this list.
	// Be noted that the listnode at head.next has index 0 and 
	//      the last list node has index of size()-1.
	// if index < 0 or index >= this.size, throws IndexOutOfBoundsException.
	/**
	 * @author Mekai Johnson
	 */
	public Object get(int index) throws IndexOutOfBoundsException{
		//this works
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		ListNode cur = head.next;
		int i = 0;
		//traverse entire list if the index is the size of the list
		if(index == size()-1) {
			ListNode prev = head;
			while(cur != null) {
				prev = cur;
				cur = cur.next;
			}
			return prev.data;
		}
		//otherwise, we traverse up to the passed in index, check if cur is null to return null
		while (i < index) {
			cur = cur.next;
			if(cur == null) {
				return null;
			}
			i++;
		}
		//we can now return cur's data because cur is at the desired index.
		return cur.data;//change this as you need.
	}
	
	// Removes (cuts out) the list node at the specified index in this list. 
	// Returns the data element in the node that is removed.
	// Be noted that the list node at head.next has index 0 and 
	//      the last list node has index of size()-1.
	// if index < 0 or index >= this.size, throws IndexOutOfBoundsException.
	/**
	 * @author Mekai Johnson
	 */
	public Object remove(int index) throws IndexOutOfBoundsException {
		//this works
		ListNode cur = head.next, prev = head;
		int i = 0;
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		//traverse the list up to our index
		while (i < index) {
			prev = cur;
			cur = cur.next;
			i++;
		}
		//once our current node is at our desired index, we can remove it
		Object temp = cur.data;
		prev.next = cur.next;
		size--;
		return temp; //change this as you need.

	}

	
	//Add the object e to the end of this list.
	// it returns true, after e is successfully added.
	/**
	 * @author Mekai Johnson
	 */
	public boolean add(Object e) {
		//this works
		ListNode cur = head.next, prev = head;
		//traverse to the end of our list
		while(cur != null) {
			prev = cur;
			cur = cur.next;
		}
		//once at the end, create a new node
		prev.next = new ListNode(e);
		return true; //change this as you need.
	}
	
        //Please DO NOT Change the following toString() method!!!
        //You must include the following toString() method in your source code.
	@Override
	public String toString() {
		String result = "{";
	    for (ListNode node = this.head.next; node != null; node = node.next) {
	    		if(node.next != null)
	    			result += node.data + "->"; 
	    		else
	    			result += node.data;
	    }
	    return result + "}";
	  }
}
