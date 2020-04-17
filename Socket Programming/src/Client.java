import java.io.*;
import java.net.*;
public class Client {
    public static void main(String[] args) throws IOException{
        String serverHostname = new String ("127.0.0.1");
        String fileToSend = "C:\\Programming\\CSCI3401\\Socket Programming\\Client Folder\\ClientFile.txt";
        
           if (args.length > 0)
              serverHostname = args[0];
           System.out.println ("Attemping to connect to host " +
   		serverHostname + " on port 12345.");

           Socket echoSocket = null;
           PrintWriter out = null;
           BufferedReader in = null;
           FileInputStream fis = null;

           try {
               // echoSocket = new Socket("taranis", 7);
               echoSocket = new Socket(serverHostname,12345);
               out = new PrintWriter(echoSocket.getOutputStream(), true);
               in = new BufferedReader(new InputStreamReader(
                                           echoSocket.getInputStream()));
           } catch (UnknownHostException e) {
               System.err.println("Don't know about host: " + serverHostname);
               System.exit(1);
           } catch (IOException e) {
               System.err.println("Couldn't get I/O for "
                                  + "the connection to: " + serverHostname);
               System.exit(1);
           }

   	BufferedReader stdIn = new BufferedReader(
                                      new InputStreamReader(System.in));
   	String userInput = null;

           System.out.print ("input: ");
           
   	while ((userInput = stdIn.readLine()) !=  null ) {
   	    out.println(userInput);
   	    try{
   	    			if(in.readLine().equals("ALOHA!"))	{
   	    				System.out.println("Received");
   	    			}
   	    			else if(userInput.equalsIgnoreCase("Bye")) 
                   {System.out.println("Socket will be closed!");break;}
               }
               catch (Exception e)
               {
                   System.out.println("Socket Closed!");
                   break;
               }
               System.out.print ("input: ");
               
   	}

   	out.close();
   	in.close();
   	stdIn.close();
   	echoSocket.close();
       }
}
