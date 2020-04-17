import java.util.*;
public class Suurballe {
	/**
	 * Creates a list of edges from source to destination
	 * @param path: a list of nodes creating the path from the source to a destination.
	 * @return a 2D array containing the edges of the shortest path
	 */
	public static int[][] getP1(ArrayList<Integer> path)	{
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
	 * Builds an adjacency matrix where edges in P1 have swapped direction and their weights negative
	 * @param graph: the adjacency matrix built in GraphBuilder class
	 * @param path: a list of nodes creating the path from the source to a destination.
	 * @return a copy of graph but the edges in path have swapped direction and their weights negative
	 */
	public static double[][] gPrime(double[][] graph, ArrayList<Integer> path)	{
		double[][] gPrime = graph;
		for(int i = 0; i < path.size()-1; i++)	{
			gPrime[path.get(i+1)][path.get(i)] = -graph[path.get(i)][path.get(i+1)];	// Sets the weight of the reverse direction to negative
			gPrime[path.get(i)][path.get(i+1)] = Double.MAX_VALUE;	// Closes the direction the path goes
		}
		return gPrime;
	}
	
	/**
	 * Creates a list of edges from source to destination based on modifications defined in Suurballe's algorithm
	 * @param gPrime: an adjacency graph modified by gPrime method
	 * @return a 2D array containing the edges of the shortest path
	 */
	public static int[][] getP2(double[][] gPrime, String src, String dest)	{
		int source = GraphBuilder.getCities().indexOf(src);
		int destination = GraphBuilder.getCities().indexOf(dest);
		ArrayList<Integer> p2Nodes = new ArrayList<Integer>();	// List of nodes from src to dest
		Dijkstra.dijkstra(gPrime, source);
		p2Nodes = Dijkstra.getPath(source, destination);
		
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
	 * 		defined in Suurballe's algorithm
	 * @return a binary adjacency matrix which merges P1 and P2 and removes common edges
	 */
	public static int[][] survivablePath(double[][] graph, int[][] p1, int[][] p2)	{
		int[][] survivablePath = new int[graph.length][graph.length];
		for(int i = 0; i < p1.length; i++)	{
			survivablePath[p1[i][0]] [p1[i][1]] = 1;
		}
		for(int i = 0; i < p2.length; i++)	{
			survivablePath[p2[i][0]] [p2[i][1]] = (survivablePath[p2[i][0]] [p2[i][1]] == 1 || survivablePath[p2[i][1]] [p2[i][0]] == 1) ? 0 : 1;
		}
		return survivablePath;
	}
}
