import java.util.*;
import java.io.*;
public class GraphBuilder {
	static ArrayList<String> cityList = new ArrayList<String>();	// sorted list of names of nodes
	
	/**
	 * Builds an adjacency matrix where each elements contains the weight between the nodes.
	 * A weight of 0 means the two nodes are the same and a weight of MAX_VALUE means there is no edge connecting the nodes
	 * @param file	A file containing space-separated names of nodes followed by the weight between the two
	 * Assume the graph is bidirectional. For example, if one line reads A B 2, it is not necessary to put B A 2
	 * @return An bidirectional adjacency matrix
	 * @throws FileNotFoundException
	 */
	public static double[][] graph(File file)	throws FileNotFoundException {
		Scanner console = new Scanner(file);						// First pass with scanner obtains the names of the nodes
		SortedSet<String> rawCities = new TreeSet<String>();		// Ordered set of the names of the nodes
		while(console.hasNextLine())	{							// Fills the list
			String[] lineSplit = console.nextLine().split("\\s++");
			rawCities.add(lineSplit[0]);
			rawCities.add(lineSplit[1]);
		}
		console.close();
		
		ArrayList<String> cities = new ArrayList<String>(rawCities);	// Arraylist being used in future operations
		double[][] edges = new double[cities.size()][cities.size()];
		cityList = cities;
		
		Scanner sc = new Scanner(file);									// Second pass with scanner fills the adjacency matrix
		while(sc.hasNextLine())	{
			String[] lineSplit = sc.nextLine().split("\\s++");
			edges[cities.indexOf(lineSplit[0])][cities.indexOf(lineSplit[1])] = Double.parseDouble(lineSplit[2]);
			edges[cities.indexOf(lineSplit[1])][cities.indexOf(lineSplit[0])] = Double.parseDouble(lineSplit[2]);
		}
		
		for(int i = 0; i < edges.length; i++)	{	// Makes the weight between unconnected nodes infinity
			for(int j = 0; j < edges.length; j++)	{
				if(edges[i][j] == 0 && i != j)	edges[i][j] = Double.MAX_VALUE;
			}
		}
		sc.close();
		return edges;
	}
	
	/**
	 * list of names of nodes for debugging
	 * @return sorted list of the names of nodes
	 */
	public static ArrayList<String> getCities()	{
		return cityList;
	}
}
