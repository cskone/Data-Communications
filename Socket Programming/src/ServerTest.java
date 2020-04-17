import java.io.*;
import java.net.*;
public class ServerTest {
	private final static String fileOutput = 
			"C:\\Programming\\CSCI3401\\Socket Programming\\Server Folder\\ServerFile.txt";
	
	public static void main(String[] args)	throws IOException	{
		ServerSocket servsock = new ServerSocket(12345);
		Socket sock = servsock.accept();
		byte[] myByteArray = new byte[4096];
		InputStream is = sock.getInputStream();
		FileOutputStream fos = new FileOutputStream(fileOutput);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		int bytesRead = is.read(myByteArray, 0, myByteArray.length);
	    bos.write(myByteArray, 0, bytesRead);
	    bos.close();
	    sock.close();
	}
}
