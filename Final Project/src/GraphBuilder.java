import java.util.*;
import java.io.*;
public class GraphBuilder {

	public static double[][] graph(File file)	throws FileNotFoundException {
		Scanner console = new Scanner(file);
		SortedSet<String> rawCities = new TreeSet<String>();
		while(console.hasNextLine())	{
			String[] lineSplit = console.nextLine().split("\\s++");
			rawCities.add(lineSplit[0]);
			rawCities.add(lineSplit[1]);
		}
		console.close();
		
		ArrayList<String> cities = new ArrayList<String>(rawCities);
		Scanner sc = new Scanner(file);
		double[][] edges = new double[cities.size()][cities.size()];
		while(sc.hasNextLine())	{
			String[] lineSplit = sc.nextLine().split("\\s++");
			edges[cities.indexOf(lineSplit[0])][cities.indexOf(lineSplit[1])] = Double.parseDouble(lineSplit[2]);
		}
		for(int i = 0; i < edges.length; i++)	{
			for(int j = 0; j < edges.length; j++)	{
				if(edges[i][j] == 0 && i != j)	edges[i][j] = Double.MAX_VALUE;
			}
		}
		sc.close();
		return edges;
	}
}
