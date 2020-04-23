import java.util.*;
/**
 * A Priority Queue that sorts its contents based on values in a separate location 
 * which are pointed to by the contents of the queue
 * @author Collin Skone
 */
public class PriorityQueue	{
	/**
	 * constructs the priority queue
	 * @param keys: A list of the values of the nodes in the queue.  The indices
	 * 		of the values are node's names in the queue
	 * @return an arraylist representing a priority queue
	 */
	public static ArrayList<Integer> priorityQueue(double[] keys)	{
		ArrayList<Integer> queue = new ArrayList<Integer>();
		for(int i = 0; i < keys.length; i++)	{
			queue.add(i);
		}
		buildMinHeap(queue, keys);
		return queue;
	}
	
	/**
	 * Sets the index of the left child
	 * @param i: index of the parent
	 * @return the index of the left child
	 */
	public static int left(int i)	{	// Left child
		return 2*i+1;
	}
	/**
	 * Sets the index of the right child
	 * @param i: index of the parent
	 * @return the index of the right child
	 */
	public static int right(int i)	{	// Right Child
		return 2*i+2;
	}
	
	/**
	 * creates the minHeap property
	 * @param heap: the queue being minHeapified
	 * @param keys: A list of the values of the nodes in the queue.  The indices
	 * 		of the values are node's names in the queue
	 * @param i: index of the starting node
	 */
	public static void minHeapify(ArrayList<Integer> heap, double[] keys, int i)	{
		if(2*i+1 > heap.size())	{
			return;
		}
		int l = left(i);	// Gets left child
		int r = right(i);	// Gets right child
		int smallest;
		
		// Finds the smallest value in the subtree
		if(l < heap.size() && keys[heap.get(l)] < keys[heap.get(i)])	{	
			smallest = l;
		}else	{
			smallest = i;
		}
		if(r < heap.size() && keys[heap.get(r)] < keys[heap.get(smallest)])	{
			smallest = r;
		}
		if(smallest != i)	{	// Bubble's up the specified node
			int temp = heap.get(i);
			heap.set(i, heap.get(smallest));
			heap.set(smallest, temp);
			minHeapify(heap, keys, smallest);
		}
	}
	
	/**
	 * Sorts the queue
	 * @param heap: the queue being sorted
	 * @param keys: A list of the values of the nodes in the queue. The indices
	 * 		of the values are node's names in the queue
	 */
	public static void buildMinHeap(ArrayList<Integer> heap, double[] keys)	{
		for(int i = heap.size()/2; i >= 0; i--)	{
			minHeapify(heap, keys, i);
		}
	}
	
	/**
	 * Reorganizes the queue when there is a change in the value of a node
	 * @param heap: the queue being sorted
	 * @param v: the name of the node being sorted (not the index)
	 * @param keys: A list of the values of the nodes in the queue. The indices
	 * 		of the values are node's names in the queue
	 */
	public static void decreaseKey(ArrayList<Integer> heap, int v, double[] keys)	{
		int child = heap.indexOf(v);	// Index of child
		int parent = (child-1)/2;	// Index of parent
		
		while(child != 0 && keys[heap.get(child)] < keys[heap.get(parent)])	{
			int temp = heap.get(parent);
			heap.set(parent, heap.get(child));
			heap.set(child, temp);
			child = parent;
			parent = (child-1)/2;
			
		}
	}
	
	/**
	 * Removes the smallest item in the queue based on its value in the keys array
	 * @param heap: queue being affected
	 * @param keys: A list of the values of the nodes in the queue. The indices
	 * 		of the values are node's names in the queue
	 * @return the name/value of smallest node in the queue
	 */
	public static int extractMin(ArrayList<Integer> heap, double[] keys)	{
		int min = heap.get(0);
		heap.set(0, heap.get(heap.size()-1));
		heap.remove(heap.size()-1);
		minHeapify(heap, keys, 0);
		return min;
	}
}