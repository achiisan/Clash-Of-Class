import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.io.*;
public class GameServer implements Runnable{
	CopyOnWriteArrayList<DatagramPacket> clientlist = new CopyOnWriteArrayList<DatagramPacket>();
	LinkedList<String> allplayers = new LinkedList<String>();
	CopyOnWriteArrayList<ServerPlayer> playerlist = new CopyOnWriteArrayList<ServerPlayer>();
	static ServerPlayer[] ctowers = new ServerPlayer[3];
	
   
 
    public GameServer() throws IOException {
    System.out.println("INITIALIZING GAME SERVER...");
    FrontEnd monitor = new FrontEnd();
    DatagramSocket datagramSocket = new DatagramSocket(10224);
    

    byte[] buffer = new byte[1000];
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

    System.out.println("SETTING UP GAME ENVIRONMENT...");
    //Declare the crystal towers
    for(int i=0; i<3; i++){
   	 ctowers[i] = new ServerPlayer(0,"Crystal Tower",10,10,100*10,0,0,0);
   	 playerlist.add(ctowers[i]);
   	 
   		//Thread t = new Thread(ctowers[i],"Crystal Tower");
   		//t.start();
    }
//declare coordinates of towers
    ctowers[0].xcoor = 10;
    ctowers[0].ycoor = 20;
    ctowers[1].xcoor = 1700;
    ctowers[1].ycoor = 20;
    ctowers[2].xcoor = 1100;
    ctowers[2].ycoor = 1300;
    
    //intialize character blueprint
    Characters.initializeCharacters();
   Thread d = new Thread(this);
   d.start();
   System.out.println("GAME SERVER STARTED.. \n WAITING FOR USERS");
    while(true){
    	
        datagramSocket.receive(packet);
        buffer = packet.getData();
        
        String message = new String(buffer);
        System.out.println(message);
        monitor.getBytesReceived2(message.length(),"Test");
        String [] messages = message.split("#");
        
        System.out.println(message);
        if(messages[0].equals("CONN")){
        	//Client paramdam sa server
        	allplayers.add(messages[1]);
        	System.out.println(messages[1]+" Joined the game.");
        	clientlist.add(packet);
        	
        }else if(messages[0].equals("PL")){
        	//Character galaw
        	System.out.println("RECEIVED A PLAYER INPUT");
        	String character = messages[2].toLowerCase();
        	ServerPlayer temp = new ServerPlayer(Integer.parseInt(messages[1]), messages[2],Math.round(Float.parseFloat(messages[3])), Math.round(Float.parseFloat(messages[4])), Characters.charlist.get(character).hp,Characters.charlist.get(character).atkstrength,Characters.charlist.get(character).range, 0); 
        	playerlist.add(temp);
        	Thread t = new Thread(playerlist.get(playerlist.size() -1));
        	//t.start();
        }
        System.out.println("RECEIVED FROM:" +packet.getSocketAddress().toString()+"CONTENT:"+ new String(buffer));      
    }   
}  
    
   public void broadcast(byte[] buffer) {
	   for(DatagramPacket i: clientlist){
		   	try {
				GameCommunicator gc = new GameCommunicator(i.getAddress(), i.getPort());
				gc.sendMessage(new String(buffer));
		   	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
	   
   }
   //send the data of the player status over udp
@Override
public void run() {
	while(true){
		String mainstring = "";
		for(ServerPlayer s: playerlist){
			String temp = s.playerid+"#"+s.name+"#"+s.xcoor+"#"+s.ycoor+"#"+s.action;
			mainstring = mainstring+"##"+temp;
		}
		broadcast(mainstring.getBytes());
		
	}
	
} 
    
}