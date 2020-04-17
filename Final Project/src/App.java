import java.util.*;
import java.io.*;
public class App {
	public static void main(String[] args)	throws FileNotFoundException	{
		File nsfNet = new File("C:\\Programming\\CSCI3401\\Final Project\\src txt files\\nsfnet.txt");
		File test = new File("C:\\Programming\\CSCI3401\\Final Project\\src txt files\\test.txt");
		double[][] suurballeGraph = GraphBuilder.graph(test);
		double[][] tsspGraph = GraphBuilder.graph(test);
		runSuurballe(suurballeGraph, "t", "z");
		System.out.println();
		runTSSP(tsspGraph, "t", "z");
		
	}
	
	public static void runSuurballe(double[][] graph, String src, String dest)	{
		runDijkstra(graph, getCityIndex(src));
		ArrayList<Integer> path = Dijkstra.getPath(getCityIndex(src), getCityIndex(dest));
		int[][] p1 = Suurballe.getP1(path);
		try	{
			int[][] p2 = Suurballe.getP2(Suurballe.gPrime(graph, path), src, dest);
			int[][] newPaths = Suurballe.survivablePath(graph, p1, p2);
			for(int[] x : newPaths)	System.out.println(Arrays.toString(x));
		} catch (Exception e)	{
			System.out.println("P2 cannot be built");
		}
	}
	
	public static void runTSSP(double[][] graph, String src, String dest)	{
		runDijkstra(graph, getCityIndex(src));
		ArrayList<Integer> path = Dijkstra.getPath(getCityIndex(src), getCityIndex(dest));
		int[][] p1 = TSSP.getP1(path);
		try	{
			int[][] p2 = TSSP.getP2(TSSP.gPrime(graph, p1), src, dest);
			int[][] newPaths = TSSP.survivablePath(graph, p1, p2);
			for(int[] x : newPaths)	System.out.println(Arrays.toString(x));
		}	catch (Exception e)	{
			System.out.println("P2 cannot be built");
		}
	}
	
	public static void runDijkstra(double[][] graph, int s)	{
		Dijkstra.dijkstra(graph, s);
	}
	
	public static int getCityIndex(String city)	{
		return GraphBuilder.getCities().indexOf(city);
	}
}
