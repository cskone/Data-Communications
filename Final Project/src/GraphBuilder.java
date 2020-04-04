import java.util.*;
import java.io.*;
public class GraphBuilder {

	public static void main(String[] args)	throws FileNotFoundException {
		File nsfNet = new File("C:\\Programming\\CSCI3401\\Final Project\\src txt files\\nsfnet.txt");
		Scanner console = new Scanner(nsfNet);
		SortedSet<String> rawCities = new TreeSet<String>();
		while(console.hasNextLine())	{
			String[] lineSplit = console.nextLine().split("\\s++");
			rawCities.add(lineSplit[0]);
			rawCities.add(lineSplit[1]);
		}
		console.close();
		ArrayList<String> cities = new ArrayList<String>(rawCities);
//		System.out.println(Arrays.toString(cities.toArray()));
		Scanner sc = new Scanner(nsfNet);
		double[][] edges = new double[cities.size()][cities.size()];
		while(sc.hasNextLine())	{
			String[] lineSplit = sc.nextLine().split("\\s++");
			System.out.println(Arrays.toString(lineSplit));
			edges[cities.indexOf(lineSplit[0])][cities.indexOf(lineSplit[1])] = Double.parseDouble(lineSplit[2]);
		}
		for(int i = 0; i < edges.length; i++)	{
			for(int j = 0; j < edges.length; j++)	{
				if(edges[i][j] == 0 && i != j)	edges[i][j] = Double.MAX_VALUE;
			}
			System.out.println(Arrays.toString(edges[i]));
		}
		
	}
	

}
