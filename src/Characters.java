import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
/*
 * 
 * Characters Class
 * This class contains all characters in the game as well as their attributes. 
 * This is done to minimize clutter on the main game window.
 * 
 */
public class Characters{
	static Map<String, Player> charlist = new HashMap<String, Player>();

	
	//Total player count
	static int totaltroopCount = 0;
	static int allowedPlayers = 250;
	
	static JPanel troopcount = new JPanel();
	//Active Character
	static String aktvName;
	static String aktvImgLink;
	static String aktvImgLink2;
	static Player aktvplayer;
	
	 //Button list for all character types
    static LinkedList <JButton> chars = new LinkedList<JButton>();
	//
	
	public static void initializeCharacters() {
		System.out.println("INITIALIZING CHARACTER LIST...");
		charlist.put("barbarian", new Player("Barbarian", "Images/Barbarian/l0_sprite_1", "Images/Barbarian/l1_sprite_1",100,40.0f,2.0f, 10.0f));
	    charlist.put("bomb", new Player("Bomb", "Images/bomb/l0_sprite_1","Images/bomb/l1_sprite_1",10,10.0f, 50.0f, 10.0f));
		charlist.put("range", new Player("Range", "Images/range/l0_sprite_1","Images/range/l1_sprite_1",100,30.0f,5.0f, 50.0f));
		charlist.put("dragon", new Player("Dragon", "Images/dragon/dragon","Images/dragon/dragon2",10,100.0f, 100.0f, 70.0f));
		charlist.put("giant", new Player("Giant", "Images/giant/giant", "Images/giant/giant2",30,90.0f, 30.0f, 10.0f));
		charlist.put("goblin", new Player("Goblin", "Images/goblin/goblin", "Images/goblin/goblin2",50,50.0f, 10.0f, 10.0f));
		
		ActionListener wee = new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String selectedname = ((JButton) arg0.getSource()).getText();
				
				selectedname = selectedname.toLowerCase();
				aktvplayer = charlist.get(selectedname);
				System.out.println(aktvplayer.name);
				
				}
		};
			
		   //Button list of characters
  //      chars.add(barbarian.genButton());
   //     chars.getLast().addActionListener(wee);
		for(Entry<String, Player> entry:charlist.entrySet()){
			chars.add(entry.getValue().genButton());
			chars.getLast().addActionListener(wee);
		}
       
	}

	

}


