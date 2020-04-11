import java.util.*;
public class Suurballe {
	public static void main(String[] args) {

	}
	
	public static double[][] gPrime(double[][] graph, ArrayList<Integer> path)	{
		double[][] gPrime = graph;
		for(int i = 0; i < path.size()-1; i++)	{
			graph[path.get(i+1)][path.get(i)] = -graph[path.get(i)][path.get(i+1)];
			graph[path.get(i)][path.get(i+1)] = Double.MAX_VALUE;
		}
		return gPrime;
	}
}
