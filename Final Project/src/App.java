import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.io.*;
public class App {
	public static void main(String[] args)	throws FileNotFoundException	{
		File nsfNet = new File("C:\\Programming\\CSCI3401\\Final Project\\src txt files\\nsfnet.txt");
		File test = new File("C:\\Programming\\CSCI3401\\Final Project\\src txt files\\test.txt");
		File coroNet = new File("C:\\Programming\\CSCI3401\\Final Project\\src txt files\\coronet.txt");
		
		int[][] randNSFPath = assignPaths(1000, 14);
		int[][] randCOROPath = assignPaths(1000, 75);
		int[][] randTrapPath = assignPaths(1000, 6);
		
		suurballeTimeTest(randNSFPath, coroNet);
	}

	/**
	 * Assigns the source and destination nodes randomly
	 * @param testCount: the number of tests wanted
	 * @param nodeCount: the number of nodes in the graph
	 * @return a 2D array with each row containing the given source and destination nodes
	 */
	public static int[][] assignPaths(int testCount, int nodeCount)	{
		int[][] randPaths = new int[testCount][2];
		for(int i = 0; i < randPaths.length; i++)	{
			int testSrcNode = ThreadLocalRandom.current().nextInt(0, nodeCount+1);
			int testDestNode = testSrcNode;
			while(testDestNode == testSrcNode)	testDestNode = ThreadLocalRandom.current().nextInt(0, 5);
			randPaths[i][0] = testSrcNode;
			randPaths[i][1] = testDestNode;
		}
		return randPaths;
	}
	
	/**
	 * Runs Suurballe to find the running time
	 * @param randPath: 2D array of source and destination nodes
	 * @param file: document containing names of nodes and the weight between them
	 * @throws FileNotFoundException
	 */
	public static void suurballeTimeTest(int[][] randPath, File file) throws FileNotFoundException	{
		for(int i = 0; i < randPath.length; i++)	{
			double[][] suurballeGraph = GraphBuilder.graph(file);
			long startTime = System.nanoTime();
			Suurballe.runSuurballe(suurballeGraph, randPath[i][0], randPath[i][1]);
			long endTime = System.nanoTime();
			long timeElapsed = endTime - startTime;
			System.out.println(timeElapsed);
		}
	}
	
	/**
	 * Runs TSSP to find the running time
	 * @param randPath: 2D array of source and destination nodes
	 * @param file: document containing names of nodes and the weight between them
	 * @throws FileNotFoundException
	 */
	public static void tsspTimeTest(int[][] randPath, File file) throws FileNotFoundException	{
		for(int i = 0; i < randPath.length; i++)	{
			double[][] tsspGraph = GraphBuilder.graph(file);
			long startTime = System.nanoTime();
			TSSP.runTSSP(tsspGraph, randPath[i][0], randPath[i][1]);
			long endTime = System.nanoTime();
			long timeElapsed = endTime - startTime;
			System.out.println(timeElapsed);
		}
	}
	
	/**
	 * Runs Suurballe to find the success rate of correctly built paths
	 * @param randPath: 2D array of source and destination nodes
	 * @param file: document containing names of nodes and the weight between them
	 * @return the number of pairs of disjoint paths successfully created
	 * @throws FileNotFoundException
	 */
	public static int suurballeSuccessTest(int[][] randPath, File file) throws FileNotFoundException	{
		int success = 0;
		for(int i = 0; i < randPath.length; i++)	{
			double[][] suurballeGraph = GraphBuilder.graph(file);
			success += Suurballe.runSuurballe(suurballeGraph, randPath[i][0], randPath[i][1]);
		}
		return success;
	}
	
	/**
	 * Runs TSSP to find the success rate of correctly built paths
	 * @param randPath: 2D array of source and destination nodes
	 * @param file: document containing names of nodes and the weight between them
	 * @return the number of pairs of disjoint paths successfully created
	 * @throws FileNotFoundException
	 */
	public static int tsspSuccessTest(int[][] randPath, File file) throws FileNotFoundException	{
		int success = 0;
		for(int i = 0; i < randPath.length; i++)	{
			double[][] tsspGraph = GraphBuilder.graph(file);
			success += TSSP.runTSSP(tsspGraph, randPath[i][0], randPath[i][1]);
		}
		return success;
	}
}
