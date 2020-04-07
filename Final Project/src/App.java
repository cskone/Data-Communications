import java.util.*;
import java.io.*;
public class App {
	public static double[][] graph()	throws FileNotFoundException	{
		File nsfNet = new File("C:\\Programming\\CSCI3401\\Final Project\\src txt files\\nsfnet.txt");
		File test = new File("C:\\Programming\\CSCI3401\\Final Project\\src txt files\\test.txt");
		double[][] g = GraphBuilder.graph(test);
		return g;
	}
}
