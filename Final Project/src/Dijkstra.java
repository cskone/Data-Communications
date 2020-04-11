import java.util.*;
public class Dijkstra {
	static double[][] G;
	static double[] d;
	static int[] p;
	
	/**
	 * getter to retrieve the distance array
	 * @return an array of distances from source
	 */
	public static double[] getD()	{
		return d;
	}
	
	/**
	 * getter to retrieve the parent array
	 * @return an array of the parents of each node.  Element with max int is the source node
	 */
	public static int[] getP()	{
		return p;
	}
	/**
	 * Prepares the distance and parent arrays	
	 * @param graph: the adjacency matrix built in GraphBuilder class
	 * @param s: Source node
	 */
	public static void initialize(double[][] graph, int s)	{
		d = new double[graph.length];
		p = new int[graph.length];
		
		for(int v = 0; v < graph.length; v++)	{
			d[v] = Double.MAX_VALUE;
			p[v] = Integer.MAX_VALUE;
		}
		
		d[s] = 0;
	}
	/**
	 * Decreases the weight of v if the new path is shorter and sets the parent of v to u
	 * @param u: the node at center of this iteration of checks
	 * @param v: adjacent node being checked
	 * @param graph: the adjacency matrix built in GraphBuilder class
	 */
	public static void relax(int u, int v, double[][] graph)	{
		if(d[v] > d[u] + graph[u][v])	{
			d[v] = d[u] + graph[u][v];
			p[v] = u;
		}
	}
	/**
	 * Runs the Dijkstra algorithm 
	 * @param graph: the adjacency matrix built in GraphBuilder class
	 * @param s: Source node
	 */
	public static void dijkstra(double[][] graph, int s)	{
		initialize(graph, s);
		ArrayList<Integer> S = new ArrayList<Integer>();
		ArrayList<Integer> Q = PriorityQueue.priorityQueue(d);
		while(Q.size() != 0)	{
			int u = PriorityQueue.extractMin(Q, d);
			S.add(u);
			for(int v : Q)	{
				relax(u, v, graph);
				PriorityQueue.decreaseKey(Q, v, d);
			}
		}
		
	}
	/**
	 * @param src: Source node
	 * @param dest: destination
	 * @return a list of nodes that create the path from src to dest. Element with max int value is the src node
	 */
	public static ArrayList<Integer> getPath(int src, int dest)	{
		ArrayList<Integer> path = new ArrayList<Integer>();
		int i = dest;
		while(i != src)	{
			path.add(i);
			i = p[i];
		}
		path.add(src);
		Collections.reverse(path);
		return path;
	}
	
}
