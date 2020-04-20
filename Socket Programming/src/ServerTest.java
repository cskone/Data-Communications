import java.util.concurrent.ThreadLocalRandom;
import java.io.*;
import java.net.*;
public class ServerTest {
	
	public static void main(String[] args)	throws IOException	{
		int num = ThreadLocalRandom.current().nextInt(100, 999);
		String fileName = "ServerFile" + num + ".txt";
		String fileOutput = 
				"C:\\Programming\\CSCI3401\\Socket Programming\\Server Folder\\" + fileName;
		
		ServerSocket servSock = new ServerSocket(1234);
		Socket sock = servSock.accept();
		
		InputStream is = sock.getInputStream();
		FileOutputStream fos = new FileOutputStream(fileOutput);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
	    BufferedReader in = new BufferedReader( 
	            new InputStreamReader(sock.getInputStream())); 
		PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
		
		String inputLine;
		while ((inputLine = in.readLine()) != null)	{
	         if(inputLine.equalsIgnoreCase("aloha"))	{
	        	byte[] myByteArray = new byte[4096];
	     		int bytesRead = is.read(myByteArray, 0, myByteArray.length);
	    	    bos.write(myByteArray, 0, bytesRead);
	    	    out.println("Your file named '" + fileName + "' with a size of " 
	    	    		+ bytesRead + " Bytes has been uploaded");
	    	    bos.close();
	         } 
		}
		sock.close();
		servSock.close();
	}
}
