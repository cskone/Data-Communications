import java.io.*;
import java.net.*;

// I could not get the Client to print both the server's welcome statement AND the confirmation
// message, so I just printed the latter
public class ClientTest {
	    private static String fileToSend = 
	    		"C:\\Programming\\CSCI3401\\Socket Programming\\Client Folder\\ClientFile.txt";
	    public static void main(String[] args) throws IOException	{
	    	Socket sock = new Socket("127.0.0.1", 1234);
	    	BufferedReader in = new BufferedReader(new InputStreamReader(
                    sock.getInputStream()));
	    	BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
	    	PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
	    	
	    	String userInput = null;
	    	while((userInput = console.readLine()) != null)	{
	    		out.println(userInput);
	    		
	    		break;
	    	}
	    	File file = new File(fileToSend);
	    	byte[] myByteArray = new byte[(int) file.length()];
	    	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
	    	bis.read(myByteArray, 0, myByteArray.length);
	    	OutputStream os = sock.getOutputStream();
	    	try	{
	    		os.write(myByteArray, 0, myByteArray.length);
	    	} catch (SocketException e)	{
	    		System.out.println("Error");
	    	}
	    	os.flush();
	    	System.out.println(in.readLine());
	    	bis.close();
	    	sock.close();
	    }
}
