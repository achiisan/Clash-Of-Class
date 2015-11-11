import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GameWindow extends JPanel implements MouseListener, Runnable {

	private static final long serialVersionUID = 1L;
	 JLayeredPane defaultpanel = new JLayeredPane();
	JFrame mainframe = new JFrame("Clash of Class");
	JPanel elementholder = new JPanel(); 
	JPanel graphicsoverlay = new JPanel();
	JScrollPane scrollFrame = new JScrollPane(this);
	  
	//Overlay Graphics
	JPanel minimap ;
	
	//Additional panels/graphics
	JPanel tpc;
	int xcoor = 0;
	int ycoor = 0;
	
	//ADD NON-MOVABLE ELEMENTS
	static Player bg = new Player("Wall", "Images/tile2","Images/tile2", 0,0,0,0,0);
	static Player minimapbg = new Player("Mini Map", "Images/minimap", "Images/minimap",0,0,0,0,0);
	static Player[] ctowers = new Player[3];
	
  
   //All Objects deployed by the player on the screen
    static LinkedList <Player> objects = new LinkedList<Player>();
   
    
    Insets insets;
    
    //UDP Commpuncator
    static GameCommunicator gc;
    static int id;
    
    
public GameWindow(String[] args){
		//
	Random r = new Random();
	id = r.nextInt(10000);
	try {
		System.out.println("id:"+id);
		gc = new GameCommunicator(InetAddress.getByName("127.0.0.1"), 10224);
		gc.sendMessage("CONN#"+id);
		
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		//Initialize all characters
		Characters.initializeCharacters();
		
		this.setPreferredSize(new Dimension(1914,1555));
		 this.addMouseListener(this);
		 this.setLayout(null);
		
         mainframe.setResizable(false);
       
         this.setAutoscrolls(true);
         scrollFrame.setPreferredSize(new Dimension( 800,600));
         
         //Character list JPanel
         elementholder.setPreferredSize(new Dimension(800,168));
          
         for(JButton i: Characters.chars){
        	 elementholder.add(i);
         }
     
         defaultpanel.setLayout(null);
         defaultpanel.setPreferredSize(new Dimension(1150,768));
         graphicsoverlay.setPreferredSize(new Dimension(800,768));
         graphicsoverlay.setOpaque(false);
         graphicsoverlay.setLayout(null);
         
     	ClientGUI g = new ClientGUI(args);
		g.mainpanel.setBounds(800,0,300,768);
		defaultpanel.add(g.mainpanel,JLayeredPane.POPUP_LAYER);
		
         //Put them on the main panel
         scrollFrame.setBounds(0,0,800,600);
         graphicsoverlay.setBounds(0,0,800,768);
         elementholder.setBounds(0,600,800,168);
         
         
         defaultpanel.add(scrollFrame,JLayeredPane.DEFAULT_LAYER);
         defaultpanel.add(elementholder,JLayeredPane.DEFAULT_LAYER);
         defaultpanel.add(graphicsoverlay,JLayeredPane.POPUP_LAYER);
        
         
       //Declare the crystal towers
         for(int i=0; i<3; i++){
        	 ctowers[i] = new Player("Crystal Tower",  "Images/diamond/l0_diamond1", "Images/diamond/l0_diamond2",10,10,100*10,0,0);
        	 objects.add(ctowers[i]);
        	 this.add(ctowers[i]); 
        		Thread t = new Thread(ctowers[i],"Crystal Tower");
        		t.start();
         }
   //declare coordinates of towers
         ctowers[0].setBounds(10,20 ,200,200);
         ctowers[0].xcoor = 10;
         ctowers[0].ycoor = 20;
         ctowers[1].setBounds(1700,20 ,200,200);
         ctowers[1].xcoor = 1700;
         ctowers[1].ycoor = 20;
         ctowers[2].setBounds(1100,1300 ,200,200);
         ctowers[2].xcoor = 1100;
         ctowers[2].ycoor = 1300;
         //Add elements on the overlay
         minimap = new JPanel(){
			private static final long serialVersionUID = 1L;
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				//g.drawImage(minimapbg.loadImage(minimapbg.images[0]),0,0,null);
			}
        	 
         };
         minimap.setPreferredSize(new Dimension(300,200));
         minimap.setBounds(450,350,300,200);
         minimap.setOpaque(true);
         minimap.setBackground(Color.GREEN);
         graphicsoverlay.add(minimap);
         minimapconfig();
        
 
         mainframe.setContentPane(defaultpanel);
         mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         mainframe.setVisible(true);
         mainframe.pack();
         mainframe.setResizable(true);
         
      
         Thread t = new Thread(this, "Thread");
         t.run();
         
         
}
	
	public static void main(String[] args){
		GameWindow gw = new GameWindow(args);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//initially there are no clicked characters
		if(Characters.aktvplayer  != null){
			if(Characters.aktvplayer.max_troops != Characters.aktvplayer.troop_count && Characters.totaltroopCount != Characters.allowedPlayers){
			
				objects.add(new Player(Characters.aktvplayer.name, Characters.aktvplayer.images[0] ,Characters.aktvplayer.images[1], arg0.getX(),arg0.getY(), Characters.aktvplayer.hp, Characters.aktvplayer.atkstrength, Characters.aktvplayer.range));
				this.add(objects.getLast());
				Thread t = new Thread(objects.getLast(), Characters.aktvplayer.name);
				Characters.aktvplayer.troop_count++;
				Characters.totaltroopCount++;	
				t.start();
			}else{
				Characters.aktvplayer.charbutton.setEnabled(false);
				if(Characters.totaltroopCount == Characters.allowedPlayers){
					for(JButton i: Characters.chars){
						i.setEnabled(false);
					}
				}
			}
		}
		}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//repaint for the background image
	  protected void paintComponent(Graphics g) {
		    super.paintComponent(g);
		        g.drawImage(bg.loadImage(bg.images[0]), 0, 0, null);
		}
		

	@Override
	//This thread paints the characters onto the JPanel
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			   this.revalidate();
			this.repaint();
			try{
			for(Player o:objects){
				if(!o.action.equals("REMOVE")){
				 o.setBounds((int)o.xcoor,(int)o.ycoor ,200,200);
				}else{
					this.remove(o);
					objects.remove(o);
				}
			}
			}catch(Exception e){
				
			}
		}
	}
	
		public void minimapconfig() {
			minimap.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					scrollFrame.getVerticalScrollBar().setValue(arg0.getY()* 10);
					scrollFrame.getHorizontalScrollBar().setValue(arg0.getX()* 8);
					
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				
			});
			
				
		
		}

}
	