import java.io.*;
import java.net.*;
public class ClientTest {
	    private static String fileToSend = 
	    		"C:\\Programming\\CSCI3401\\Socket Programming\\Client Folder\\ClientFile.txt";
	    public static void main(String[] args) throws IOException	{
	    	Socket sock = new Socket("127.0.0.1", 12345);
	    	File file = new File(fileToSend);
	    	while(true)	{
	    		byte[] myByteArray = new byte[(int) file.length()];
	    		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
	    	    bis.read(myByteArray, 0, myByteArray.length);
	    	    OutputStream os = sock.getOutputStream();
	    	    os.write(myByteArray, 0, myByteArray.length);
	    	    os.flush();
	    	    sock.close();
	    	}
	    }
}
