import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ServerPlayer extends JPanel implements Runnable{
	/**
	 * This class is quite confusing, as this represent BOTH the Generic Class for every character (Initialized in Characters)
	 * and the individual component that is deployed in the game
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//GENERAL ATTRIBUTES
	String name;
	int playerid;
	String k= new String();
	
	
	
	//ELEMENT-SPECIFIC ATTRIBUTES (PER OBJECT DEPLOY)
	float xcoor = 0;
	float ycoor = 0;
	ServerPlayer target = null;
	String action = "NONE";
	int gameID = 0; //temporary game id	
	char charID = 0;
	GameCommunicator gc;
	
	//CHARACTER-SPECIFIC ATTRIBUTES(CHARACTER-TYPE BASED)
	int max_troops = 100;
	
	int troop_count = 0;
	float hp = 100;
	float atkstrength = 2;
	float range = 10;
	JButton charbutton;
	JLabel counter;
	
	
	//SPECFIC CHARACTER CONSTRUCTOR
	//Used in spawining elements in the game itself
	//Cloning the active character preset declared above
	public ServerPlayer(int playerid, String name, int xcoor, int ycoor,float hp, float atkstrength, float range, int gameID){
		this.gameID = gameID;
		this.playerid = playerid;
		this.name = name;
		this.xcoor = xcoor;
		this.ycoor = ycoor;
		this.hp = hp;
		this.atkstrength = atkstrength;
		this.range = range;
		
		
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(200,200));
	
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//gc.sendMessage(""+GameWindow.id);
		while(true){
		if(!name.equals("Crystal Tower")){
			if(this.target == null){
				target = this.getTarget();
				//GameWindow.gc.sendMessage("YOLO");
			}
		}
			
			try {
				this.revalidate();
				this.repaint();
				if(!name.equals("Crystal Tower")){
						if(this.action.equals("NONE")){
						this.computepos();
						System.out.println("MOVING "+this.name+"#"+this.playerid+" "+this.xcoor+" "+this.ycoor);
						}
						else if (this.action.equals("ATTACK")){
							this.attack();
							Thread.sleep((long) (this.atkstrength * 5));
						}else if(this.action.equals("DEFEND")){
							
						}
				}
				Thread.sleep(20);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	
	
	
	
	//compute the position relative to the target
	public void computepos(){	
		try{
		float slope=(target.ycoor-this.ycoor)/(target.xcoor-this.xcoor);
		float b=target.ycoor-(target.xcoor*slope);
		if (target.xcoor>this.xcoor){
			this.xcoor++;
			
		}else{ 
			this.xcoor--;
			
		}
		this.ycoor=(((slope)*this.xcoor)+b);
		
		
		//System.out.println(this.name+": ("+this.xcoor+","+this.ycoor+")");
		if(Math.sqrt((target.xcoor-this.xcoor)*(target.xcoor-this.xcoor)+(target.ycoor-this.ycoor)*(target.ycoor-this.ycoor))<range+20 && !this.target.equals(GameWindow.ctowers[gameID])){
				//atttack the base if enemy base
				this.action = "ATTACK";
		}else if(Math.sqrt((target.xcoor-this.xcoor)*(target.xcoor-this.xcoor)+(target.ycoor-this.ycoor)*(target.ycoor-this.ycoor))<range+150 && this.target.equals(GameWindow.ctowers[gameID])){
			//if the nearest "target" is the home base, defend the base
			this.action = "DEFEND";
		}
		
			
		}catch(Exception e){
			
		}
		
//			Thread.sleep(100);
			

}
	
public ServerPlayer getTarget() {
	
	ServerPlayer shortestTarget = GameServer.ctowers[0];
	
	for(int i=1; i<GameServer.ctowers.length; i++){
		//get the distance between the towers' points and the players points
	
			if( Math.sqrt((GameServer.ctowers[i].xcoor - this.xcoor)*(GameServer.ctowers[i].xcoor - this.xcoor) + (GameServer.ctowers[i].ycoor - this.ycoor)*(GameServer.ctowers[i].ycoor - this.ycoor)) < Math.sqrt((shortestTarget.xcoor - this.xcoor)*(shortestTarget.xcoor - this.xcoor) +(shortestTarget.ycoor - this.ycoor)*(shortestTarget.ycoor - this.ycoor))){
				//shortest tower becomes the target
				shortestTarget = GameServer.ctowers[i];
			}
	}
	System.out.println("SHORTEST TARGET: "+shortestTarget.xcoor);
	return shortestTarget;
	
}

public void attack() {
	if(target.hp != 0){
		System.out.println(this.atkstrength);
			target.hp = target.hp -  this.atkstrength;
			System.out.println("ATTACKING BASE: LIFE="+target.hp);
			
				// TODO Auto-generated catch block
			
	}else{
		target.action = "REMOVE";
		
	}
	
}
}
