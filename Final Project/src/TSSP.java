import java.util.*;

public class TSSP {
	/**
	 * Creates a list of edges from source to destination
	 * @param src: the source of the path
	 * @param dest: the destination of the path
	 * @return a 2D array containing the edges of the shortest path
	 */
	public static int[][] getP1(int src, int dest)	{
		ArrayList<Integer> path = Dijkstra.getPath(src, dest);
		int[][] p1 = new int[path.size()-1][2];
		int j = 0;
		for(int i = 0; i < p1.length; i++)	{
			j++;
			if(j == path.size()) break;
			p1[i][0] = path.get(j-1);
			p1[i][1] = path.get(j);
		}
		return p1;
	}
	
	/**
	 * A modified adjacency matrix where the edges in P1 are removed
	 * @param graph: the adjacency matrix built in GraphBuilder class
	 * @param p1: A list of edges which form the shortest path from a specified source to destination
	 * @return an adjacency matrix where edges in P1 are removed
	 */
	public static double[][] gPrime(double[][] graph, int[][] p1)	{
		double[][] gPrime = graph;
		for(int i = 0; i < p1.length; i++)	{	// "Deletes" the edge if it's in P1
			gPrime[p1[i][0]] [p1[i][1]] = Double.MAX_VALUE;
			gPrime[p1[i][1]] [p1[i][0]] = Double.MAX_VALUE;
		}

		return gPrime;
	}
	
	/**
	 * Creates a list of edges that form the shortest path from source to destination based on modifications
	 * to the graph in gPrime method
	 * @param gPrime: A modified adjacency matrix where the edges in P1 are removed
	 * @param src: the name of the source node
	 * @param dest: the name of the destination node
	 * @return
	 */
	public static int[][] getP2(double[][] gPrime, int src, int dest)	{
		ArrayList<Integer> p2Nodes = new ArrayList<Integer>();
		Dijkstra.dijkstra(gPrime, src);	// List of nodes from src to dest
		p2Nodes = Dijkstra.getPath(src, dest);
		
		int[][] p2 = new int[p2Nodes.size()-1][2];
		int j = 0;
		for(int i = 0; i < p2.length; i++)	{
			j++;
			if(j == p2Nodes.size()) break;
			p2[i][0] = p2Nodes.get(j-1);
			p2[i][1] = p2Nodes.get(j);
		}
		return p2;
	}

	/**
	 * Creates a binary, unidirectional adjacency matrix containing two disjoint paths from a specified source to destination
	 * @param graph: An adjacency matrix with each element being the weight of the edge between two nodes
	 * @param p1: A list of edges which form the shortest path from a specified source to destination
	 * @param p2: A list of edges which form the shortest path from a specified source to destination based on modifications
	 * 		to the graph in gPrime method
	 * @return a binary adjacency matrix representing a survivable least lost path from a source to a destination
	 */
	public static int[][] survivablePath(double[][] graph, int[][] p1, int[][] p2)	{
		int[][] survivablePath = new int[graph.length][graph.length];
		for(int i = 0; i < p1.length; i++)	{	// Adds the edges from P1
			survivablePath[p1[i][0]][p1[i][1]] = 1;
		}
		for(int i = 0; i < p2.length; i++)	{	// Adds the edges from P2
			survivablePath[p2[i][0]][p2[i][1]] = 1;
		}
		return survivablePath;
	}
	
	/**
	 * Runs the TSSP algorithm
	 * @param graph: the adjacency matrix built in GraphBuilder class
	 * @param src: source node
	 * @param dest: destination node
	 * @return returns a 1 if the algorithm successfully created a pair of disjoint paths
	 * and 0 if it didn't
	 */
	public static int runTSSP(double[][] graph, int src, int dest)	{
		Dijkstra.dijkstra(graph, src);
		int[][] p1 = TSSP.getP1(src, dest);
		try	{
			int[][] p2 = TSSP.getP2(TSSP.gPrime(graph, p1), src, dest);
			int[][] newPaths = TSSP.survivablePath(graph, p1, p2);
			return 1;
		}	catch (Exception e){return 0;}
	}
}
