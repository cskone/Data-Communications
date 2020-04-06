import java.util.*;
public class PriorityQueue	{

	// Creates the priority queue in the form of a min-heap
	public static ArrayList<Integer> priorityQueue(double[] keys)	{
		ArrayList<Integer> queue = new ArrayList<Integer>();
		for(int i = 0; i < keys.length; i++)	{
			queue.add(i);
		}
		buildMinHeap(queue, keys);
		return queue;
	}
	
	public static int left(int i)	{	// Left child
		return 2*i+1;
	}
	public static int right(int i)	{	// Right Child
		return 2*i+2;
	}
	// Sorts the heap
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
		if(smallest != i)	{
			int temp = heap.get(i);
			heap.set(i, heap.get(smallest));
			heap.set(smallest, temp);
			minHeapify(heap, keys, smallest);
		}
	}
	
	// Builds the heap
	public static void buildMinHeap(ArrayList<Integer> heap, double[] keys)	{
		for(int i = heap.size()/2; i >= 0; i--)	{
			minHeapify(heap, keys, i);
		}
	}
	
	public static void decreaseKey(ArrayList<Integer> heap, int i, double[] keys)	{
		int child = heap.indexOf(i);
		int parent = (child-1)/2;
		while(i != 0 && keys[heap.get(child)] < keys[heap.get(parent)])	{
			int temp = heap.get(parent);
			heap.set(parent, heap.get(child));
			heap.set(child, temp);
			child = parent;
		}
	}
	
	// Finds and removes the smallest value in the heap
	public static int extractMin(ArrayList<Integer> heap, double[] keys)	{
		int min = heap.get(0);
		heap.set(0, heap.get(heap.size()-1));
		heap.remove(heap.size()-1);
		minHeapify(heap, keys, 0);
		return min;
	}
}