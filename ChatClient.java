import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.io.*;
 
public class ChatClient {
	public static void main (String[] argv) {
	    try {
		    	File myFileReference = new File("C:\\DEMO\\JAVA\\rmi1\\Chat\\security.policy");
			System.setProperty("java.security.policy", myFileReference.getAbsolutePath());

			System.setSecurityManager(new RMISecurityManager());
		    	Scanner s=new Scanner(System.in);
		    	System.out.println("Enter Your name and press Enter:");
		    	String name=s.nextLine().trim();		    		    	
		    	ChatInterface client = new Chat(name);
 
		    	ChatInterface server = (ChatInterface)Naming.lookup("rmi://localhost/ABC");
		    	String msg="["+client.getName()+"] got connected";
			
			System.out.println("**********Hello " + client.getName() + ",  WELCOME TO CALL CENTER*******");
			System.out.println("How may I help you ?");
		    	server.send(msg);
		    	System.out.println("[System] Chat Remote Object is ready:");
		    	server.setClient(client);

String str2="exit";
 
		    	while(true){
		    		msg=s.nextLine().trim();
if(msg.equalsIgnoreCase(str2)){
					server.send(msg);

					break;
					}
				else{
		    		msg="["+client.getName()+"] "+msg;		    		
	    			server.send(msg);
		}

		    		
		    	}
 
	    	}catch (Exception e) {
	    		System.out.println("[System] Server failed: " + e);
	    	}
		}
}