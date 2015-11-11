import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.*;


public class GameCommunicator implements Runnable{
	  private DatagramPacket packet;
	  private DatagramSocket datagramSocket;
	  private InetAddress receiverAddress;
	  private int port;
    public GameCommunicator(InetAddress address, int port) throws IOException{
        datagramSocket = new DatagramSocket();

        byte[] buffer = "0123456789".getBytes();
        this.port = port;
        this.receiverAddress = address;

    
        
        Thread t = new Thread(this);
        t.start();
    }
    
    public void sendMessage(String message){
    	byte[] buffer = new byte[message.length()];
        packet = new DatagramPacket(
                buffer, buffer.length, receiverAddress, port);
        packet.setData(message.getBytes());
        try {
			datagramSocket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void sendMessage(byte[] message){
        packet.setData(message);
        try {
			datagramSocket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	@Override
	public void run() {
	
		    byte[] buffer = new byte[1000];
		    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

		    
		    while(true){
		    
		        try {
					datagramSocket.receive(packet);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        buffer = packet.getData();
		        //System.out.println("CLIENT:" +datagramSocket.getLocalAddress().toString()+"FROM SERVER CONTENT:"+new String(buffer));
		        

		      
		        
		    }   
	}
	
	public void pingpid() {
		
	    byte[] buffer = new byte[1000];
	    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

	    
	  
	        try {
				datagramSocket.receive(packet);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        buffer = packet.getData();
	        System.out.println("CLIENT:" +datagramSocket.getLocalAddress().toString()+"FROM SERVER CONTENT:"+buffer.toString());
	        
	        String a = new String(buffer);
	        String []acon = a.split("#");
	        
	        System.out.println(acon[2]);
	        
	        
}
}