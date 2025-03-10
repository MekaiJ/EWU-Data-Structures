
/**
  * Please change this file in the places only when it is directed 
  * by the comments.
  * 
*/
import java.util.Arrays;

public class Tester {
	
	public static MyLinkedList list1 = new MyLinkedList();
	public static MyLinkedList list2 = new MyLinkedList();
	public static MyLinkedList list3 = new MyLinkedList();
	
	//init() method is NOT allowed to change
	public void init(){
		list2.addFirst("C");
		list2.addFirst("B");
		list2.addFirst("A");
		list2.addFirst("D");
		list3.addFirst("A");
		list3.addFirst("B");
	}
	
	// Implement the following method using recursion
	// The method returns the number of white spaces in the input string str.
	// Write this method below.
	/**
	 * @author Mekai Johnson
	 */
	public int countSpace(String str) {
		//this works
		if(str.isEmpty()) {
			return 0;
		}
		String sub = str.substring(1); //make a substring from start location to the end
		int preCount = countSpace(sub);
		if(str.charAt(0) == ' ') {
			return 1 + preCount;
		}
		return preCount;
	}
	
	//Check whether s1 is a substring in s2.
	//For example, if s1="an", and s2="banana", it returns true.
	//For another example, if s1="bb" and s2="good", it returns false.
	//If either s1 or s2 is null, return false. 
	//If either s1 or s2 is empty, return false.
	//You are NOT allowed to use the contains() method in Java String class.
	//Write the method below using recursion.
	/**
	 * @author Mekai Johnson
	 */
	public boolean myContains(String s1, String s2){
		if(s1.isEmpty() || s2.isEmpty()) {
			return false;
		}
		//checking if the beginning of each subarray is the same as the array we need to compare
		if(s2.startsWith(s1)) {
			return true;
		}
		return myContains(s1, s2.substring(1)); //change this line of code as needed.
	}

	// Compute the quotient of the integer division of (m / n) using recursion, 
	// you are NOT allowed to use mathematics operation '/'.
	// Look the problem from another perspective. What does the division mean?
	// Division (m/n) also means how many n (s) is contained in the number m.
	// If n == 0, throw an IllegalArgumentException object.
	// Write this method below using recursion.
	/**
	 * @author Mekai Johnson
	 */
	public int div(int m, int n) throws IllegalArgumentException {
		//this works
		if(n == 0) { //we cannot divide by 0
			throw new IllegalArgumentException();
		}
		if(m < n) { //add zero to what we return because this means n cannot be contained in m.
			return 0;
		}
		int division = 1 + div(m-n, n);
		return division; //change this line of code as needed.
	}
	
	//Check whether the sum of array arr is 24 or not.
	//You are required to implement the *private* helper method in the space below this method.
	//You are NOT allowed to change the provided code in the *public*
	//    method isSum24(int arr[]).
	public boolean isSum24(int arr[]) {
		
		return isSum24(arr, 24);
	}

	// Implement the private helper method below using recursion.
	// The method checks whether the sum of all elements in arr 
	//       equals to the targetSum or not.
	// Hint:the subproblem is about a subarray.
	/**
	 * @author Mekai Johnson
	 */
	private boolean isSum24(int arr[], int targetSum) {
		//this works
		if (arr.length == 0) {
			return targetSum == 0;
		}
		int[] subArray = Arrays.copyOfRange(arr, 1, arr.length); // make this subarray and remove the first element from the passed in array
		return isSum24(subArray, targetSum - arr[0]); //change this line of code as needed.
	}
	
	//You are NOT allowed to change the provided code in the *public* 
	//    reverseArray(int a[]) method below.
	public void reverseArray(int a[]) {
		reverseArray(a, 0, a.length - 1);
	}
	
	//Please write the private reverseArray() using recursion.
	//The method will reverse the array elements 
	//      that are located at indices ranging from low to high.
	//E.g. if int a[] ={3, 1, 5, 4}, after reverseArray(a, 0, 3) is called,
	//       array a becomes {4, 5, 1, 3}.
	/**
	 * @author Mekai Johnson
	 */
	private void reverseArray(int a[], int low, int high) {
		//this works
		if(a.length == 0 || a.length == 1 || low == high || high < low) {
			return;
		}
		//swapping nodes
		int placeHolder = a[a.length-1];
		a[a.length-1] = a[0];
		a[0] = placeHolder;
		reverseArray(a, a[1], high-1);
	}
	
	//You are NOT allowed to change the provided code in the *public* 
	//    recursiveSelectionSort(int a[]) method below.
	public void recursiveSelectionSort(int a[]) {
		recursiveSelectionSort(a, 0, a.length - 1); //
	}
	
	//Please write the private helper method recursiveSelectionSort() using recursion.
	//The method will sort in an ascending order the array elements 
	//      that are located at indices ranging from low to high.
	//This is a version of recursive selection sort.
	/**
	 * @author Mekai Johnson
	 */
	private void recursiveSelectionSort(int a[], int low, int high) 
	{
		//this works
		if(a.length == 0 || a.length == 1 || low == high || high < low) {
			return;
		}

		//find the smallest data
		int smallest = low;
		for(int i = low; i <= high; i++) {
			if(a[i] < a[smallest]) {
				smallest = i;
			}
		}
		//swapping smallest with the first element
		int temp  = a[low];
		a[low] = a[smallest];
		a[smallest] = temp;
		recursiveSelectionSort(a, low+1, high); //pass in same array, but starting at the next index over, excluding the data that was sorted just above
	}//end of method
	
	

	//Please do NOT change the main() method below.
	//If you do change, you get a zero for this project.
	public static void main(String[] args) {
		Tester test = new Tester();
		test.init();
		//System.out.println(list1);
		//System.out.println(list2);
		System.out.println("---------Test Reverse()-------");
		System.out.println(list1.reverse());
		System.out.println(list2.reverse());
		System.out.println(list3.reverse());
		System.out.println("-------Test Reverse2()---------");
		list1.reverse2();
		list2.reverse2();
		list3.reverse2();
		System.out.println(list1);
		System.out.println(list2);
		System.out.println(list3);
		System.out.println("-------Test countSpace()----------");
		System.out.println(test.countSpace("g  d  "));
		System.out.println(test.countSpace("good "));
		System.out.println(test.countSpace("  good"));
		System.out.println(test.countSpace("good  mornin g "));
		System.out.println("-----Test myContains()------------");
		System.out.println( test.myContains("an", "banana"));
		System.out.println( test.myContains("bn", "banana"));
		System.out.println( test.myContains("er", "richer"));
		System.out.println( test.myContains("a", "a"));
		System.out.println("-----Test div()------------");
		System.out.println( test.div(11, 3) );
		System.out.println( test.div(12, 5) );
		System.out.println( test.div(4, 4) );
		System.out.println( test.div(3, 7) );
		System.out.println( test.div(16, 4) );
		
		System.out.println("-----Test isSum24()------------");
		int a[] = {6, 3, 8, 3, 4};
		int b[] = {5, 6, 7};
		int c[] = {24};
		int d[] = {10, 14};
		int e[] = {};
		System.out.println( test.isSum24(a) ); //true
		System.out.println( test.isSum24(b) ); //false
		System.out.println( test.isSum24(c) ); //true
		System.out.println( test.isSum24(d) ); //true
		System.out.println( test.isSum24(e) ); //false
		
		System.out.println("-----Test reverseArray()------------");
		test.reverseArray(a);
		System.out.println(Arrays.toString(a));//43836
		test.reverseArray(b);
		System.out.println(Arrays.toString(b));//765
		test.reverseArray(d);
		System.out.println(Arrays.toString(d));//14,10
		
		System.out.println("-----Test recursiveSelectionSort()------------");
		test.recursiveSelectionSort(a, 0, a.length - 1);
		System.out.println(Arrays.toString(a)); //33468
		int f[] = {2, 5, 1, 7, 9, 3, 6, 8};
		test.recursiveSelectionSort(f, 0, f.length - 1);
		System.out.println(Arrays.toString(f));//12356789
		
		
	}

}
