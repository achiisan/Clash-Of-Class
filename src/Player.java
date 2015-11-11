import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Player extends JPanel implements Runnable{
	/**
	 * This class is quite confusing, as this represent BOTH the Generic Class for every character (Initialized in Characters)
	 * and the individual component that is deployed in the game
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//GENERAL ATTRIBUTES
	String name;
	String[] images = new String[2];
	String[] imgholder = new String[2];
	String[] imgholderinv = new String[2];
	String k= new String();
	
	
	
	//ELEMENT-SPECIFIC ATTRIBUTES (PER OBJECT DEPLOY)
	float xcoor = 0;
	float ycoor = 0;
	Player target = null;
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
	
	//CHARACTER PARENT CONSTRUCTOR (Generates the button, cloned by the specific character construction on click)
	//Used in Characters.java file
	public Player(String name, String imgloc, String imgloc2, int max_troops, float hp,float atkstrength, float range){
		
	
		this.name = name;
		this.max_troops = max_troops;
		this.hp = hp;
		this.atkstrength = atkstrength;
		this.range = range;
		images[0] = imgloc;
		images[1] = imgloc2;
			
		
		//this.updateContent();
	}
	
	//SPECFIC CHARACTER CONSTRUCTOR
	//Used in spawining elements in the game itself
	//Cloning the active character preset declared above
	public Player(String name, String imgloc, String imgloc2, int xcoor, int ycoor,float hp, float atkstrength, float range){
	
		Random r = new Random();
		this.name = name;
		this.xcoor = xcoor;
		this.ycoor = ycoor;
		this.hp = hp;
		this.atkstrength = atkstrength;
		this.range = range;
		this.gameID = r.nextInt(10000);
		
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(200,200));
		imgholder[0] = imgloc;
		imgholder[1] = imgloc2;
		imgholderinv[0] = imgloc+"inv";
		imgholderinv[1] = imgloc2+"inv";
		images = imgholder;
		
		try {
			if(!name.equals("Crystal Tower"))
			gc = new GameCommunicator(InetAddress.getByName("127.0.0.1"), 10224);
		} catch (UnknownHostException e) {
			e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
		
	}

	@Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	        g.drawImage(this.loadImage(k), 0, 0, null);
	    	
	}
	
	public BufferedImage loadImage(String file){

		BufferedImage img = null;
		try {
			
		    img = ImageIO.read(new File(file+".png"));
		//    this.repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	public JButton genButton() {
		ImageIcon icon = new ImageIcon(images[0]+".png");
	   charbutton = new JButton(icon);
	    charbutton.setText(name);
		return charbutton;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//gc.sendMessage(""+GameWindow.id);
		if(!name.equals("Crystal Tower"))
		gc.sendMessage("PL#"+GameWindow.id+"#"+this.name+"#"+this.xcoor+"#"+this.ycoor+"#"+this.gameID);
		
		while(true){
//		if(!name.equals("Crystal Tower")){
//			if(this.target == null){
//				//target = this.getTarget();
//				//GameWindow.gc.sendMessage("YOLO");
//			}
//		}
//		
//	
		for(String o:images){	
		
			try {
			
				k = o;
				this.revalidate();
				this.repaint();
//				if(!name.equals("Crystal Tower")){
//						if(this.action.equals("NONE")){
//						this.computepos();
//						}
//						else if (this.action.equals("ATTACK")){
//							this.attack();
//							Thread.sleep((long) (this.atkstrength * 5));
//						}else if(this.action.equals("DEFEND")){
//						
//						}
//				}
				Thread.sleep(20);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	}
	
	public void updateContent(){
		counter = new JLabel(this.name+": "+(this.max_troops - this.troop_count));
	}
	
	//compute the position relative to the target
	public void computepos(){	
		try{
		float slope=(target.ycoor-this.ycoor)/(target.xcoor-this.xcoor);
		float b=target.ycoor-(target.xcoor*slope);
		if (target.xcoor>this.xcoor){
			this.xcoor++;
			images = imgholder;
		}else{ 
			this.xcoor--;
			images = imgholderinv;	
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
	
public Player getTarget() {
	
	Player shortestTarget = GameWindow.ctowers[0];
	
	for(int i=1; i<GameWindow.ctowers.length; i++){
		//get the distance between the towers' points and the players points
	
			if( Math.sqrt((GameWindow.ctowers[i].xcoor - this.xcoor)*(GameWindow.ctowers[i].xcoor - this.xcoor) + (GameWindow.ctowers[i].ycoor - this.ycoor)*(GameWindow.ctowers[i].ycoor - this.ycoor)) < Math.sqrt((shortestTarget.xcoor - this.xcoor)*(shortestTarget.xcoor - this.xcoor) +(shortestTarget.ycoor - this.ycoor)*(shortestTarget.ycoor - this.ycoor))){
				//shortest tower becomes the target
			
				System.out.println(GameWindow.ctowers[i].xcoor);
				shortestTarget = GameWindow.ctowers[i];
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
