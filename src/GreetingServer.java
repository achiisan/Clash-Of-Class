   // File Name GreetingServer.java

import java.net.*;
import java.io.*;
import java.util.LinkedList;

public class GreetingServer extends Thread
{
	
   
   private Socket server;
   private static LinkedList<GreetingServer> users = new LinkedList<GreetingServer>();
   private static ServerSocket serverSocket;
   private DataOutputStream out;  
   public String nickname = "DEFAULT NICKNAME";
   
   
   public GreetingServer(Socket server) throws IOException
   {
         this.server = server ;
         this.out = new DataOutputStream(this.server.getOutputStream());
   }

   public void broadcast(String message, String addr){
   
        for(GreetingServer t: users){
            try{
           
            String addr2 = (String) t.server.getRemoteSocketAddress().toString();     
            if(!addr.equals(addr2)){
                t.out.writeUTF("\n"+this.nickname+": "+message);
            }
            }catch(Exception e){
            }
            
        }
   }
   public void run()
   {
        DataInputStream in;
      boolean connected = true;
      FrontEnd monitor = new FrontEnd();
      while(connected)
      {
         try
         {

            System.out.println("Client Connected: " + this.server.getRemoteSocketAddress().toString());
            //GET THE NICKNAME
              in = new DataInputStream(this.server.getInputStream());
             this.nickname = in.readUTF();
             //broadcast(in.readUTF(), this.server.getRemoteSocketAddress().toString());
            
            broadcast(this.nickname+" Joined the Chat", this.server.getRemoteSocketAddress().toString());
                
            while (true){
            /* Read data from the ClientSocket */
             in = new DataInputStream(this.server.getInputStream());
           // System.out.println(in.readUTF());
             String temp = in.readUTF();
             final byte[] utf8Bytes = temp.getBytes("UTF-8");
             System.out.println(utf8Bytes.length);
               monitor.getBytesReceived(utf8Bytes.length,  this.server.getRemoteSocketAddress().toString());
            broadcast(temp, this.server.getRemoteSocketAddress().toString());
            
           
           }
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e)
         {
            //e.printStackTrace();
            System.out.println("Server ended connection to"+ server.getRemoteSocketAddress());
            break;
         }
      } 
   }
   public static void main(String [] args) throws IOException
   {
	   System.out.println("=======================================\nWELCOME TO CLASH OF CLASS VERSON 1.0\n");
	   Thread st = new Thread(new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			 try {
				GameServer gs = new GameServer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		   
	   });
	   st.start();
	   //FrontEnd.createGUI();
	   	
      try
      {
        int port = Integer.parseInt(args[0]);
        waitConn(port);
      }catch(ArrayIndexOutOfBoundsException e)
      {
         System.out.println("Usage: java GreetingServer <port no.> ");
      }
   }


public static void waitConn(int port){
	System.out.println("CHAT SERVER STARTED...");
	
    while(true){
         try
        {
         serverSocket = new ServerSocket(port);
          System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
         Socket tempserver = serverSocket.accept();
         users.add(new GreetingServer(tempserver));
            users.getLast().start();
            System.out.println("Awaiting for more users...");
        serverSocket.close();
        }catch(IOException e)
        {
          
        }
       
      
        }

}



}


/**
a) Socket server = serverSocket.accept();
b) serverSocket = new ServerSocket(port);
**/
