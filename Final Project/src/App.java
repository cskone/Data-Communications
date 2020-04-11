import java.util.*;
import java.io.*;
public class App {
	public static void main(String[] args)	throws FileNotFoundException	{
		File nsfNet = new File("C:\\Programming\\CSCI3401\\Final Project\\src txt files\\nsfnet.txt");
		File test = new File("C:\\Programming\\CSCI3401\\Final Project\\src txt files\\test.txt");
		double[][] g = GraphBuilder.graph(test);
		Dijkstra.dijkstra(g, GraphBuilder.getCities().indexOf("t"));
		System.out.println(Arrays.toString(GraphBuilder.getCities().toArray()));
		ArrayList<Integer> path = Dijkstra.getPath(GraphBuilder.getCities().indexOf("t"), GraphBuilder.getCities().indexOf("z"));
		System.out.println(Arrays.toString(path.toArray()));
		double[][] check = Suurballe.gPrime(g, path);
		for(double[] x : check) System.out.println(Arrays.toString(x));
	}
}
