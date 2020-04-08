import java.util.*;
import java.io.*;
public class Dijkstra {
	static double[][] G;
	static double[] d;
	static int[] p;
	
	public static void main(String[] args)	throws FileNotFoundException {
		G = App.graph();
		dijkstra(G, GraphBuilder.getCities().indexOf("t"));
		System.out.println(Arrays.toString(d));
	}
		
	public static void initialize(double[][] graph, int s)	{
		d = new double[graph.length];
		p = new int[graph.length];
		
		for(int v = 0; v < graph.length; v++)	{
			d[v] = Double.MAX_VALUE;
			p[v] = Integer.MAX_VALUE;
		}
		
		d[s] = 0;
	}
	
	public static void relax(int u, int v, double[][] graph)	{
		if(d[v] > d[u] + graph[u][v])	{
			d[v] = d[u] + graph[u][v];
			p[v] = u;
		}
	}
	
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
	
}
