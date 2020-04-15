import java.util.*;

public class TSSP {
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
	
	public static double[][] gPrime(double[][] graph, int[][] p1)	{
		double[][] gPrime = graph;
		for(int i = 0; i < p1.length; i++)	{
			gPrime[p1[i][0]][p1[i][1]] = Double.MAX_VALUE;
			gPrime[p1[i][1]][p1[i][0]] = Double.MAX_VALUE;
		}

		return gPrime;
	}
	
	public static int[][] getP2(double[][] gPrime, String src, String dest)	{
		int source = GraphBuilder.getCities().indexOf(src);
		int destination = GraphBuilder.getCities().indexOf(dest);
		ArrayList<Integer> p2Nodes = new ArrayList<Integer>();
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
	
	public static int[][] survivablePath(double[][] graph, int[][] p1, int[][] p2)	{
		int[][] survivablePath = new int[graph.length][graph.length];
		for(int i = 0; i < p1.length; i++)	{
			survivablePath[p1[i][0]][p1[i][1]] = 1;
		}
		for(int i = 0; i < p2.length; i++)	{
			survivablePath[p2[i][0]][p2[i][1]] = 1;
		}
		return survivablePath;
	}
}
