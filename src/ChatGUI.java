import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;



public class ChatGUI extends Thread
{
  protected static Socket client;
  protected static JTextField inputbox = new JTextField(10); //Declare it here for Data Passing
  protected static JTextArea  field = new JTextArea(); //Declare it here for Data Passing
  protected static boolean running  = true;
  
  public void run(){
    while(running){
         /* Receive data from the ServerSocket */
            try{
                InputStream inFromServer = client.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                field.append(in.readUTF());
               
            }catch(Exception e){
            }
    }
  }
 
   public static void main(String [] args)
   {
        ClientGUI a = new ClientGUI(args);

    }
   
   
}

class ClientGUI{
      JPanel mainpanel = new JPanel();
    //  JFrame mainframe = new JFrame();
      int counter = 0;
        
        public ClientGUI(String[] args){
          mainpanel.setPreferredSize(new Dimension(300,768));
          mainpanel.setBackground(Color.GRAY);

          ChatGUI.field.setPreferredSize(new Dimension(300,500));
          ChatGUI.inputbox.setSize(new Dimension(300,30));
          mainpanel.add(ChatGUI.field);
          mainpanel.add(ChatGUI.inputbox);
//          mainframe.setContentPane(mainpanel);
//          mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//          mainframe.setVisible(true);
//          mainframe.pack();
//          mainframe.setResizable(true);
          
        try{
        	
   	  String serverName = args[0]; //get IP address of server from first param
          int port = Integer.parseInt(args[1]); //get port from second param
          /* Open a ClientSocket and connect to ServerSocket */
         System.out.println("Connecting to " + serverName + " on port " + port);
         //insert missing line here for creating a new socket for client and binding it to a port
	  ChatGUI.client = new Socket(serverName, port);

	      
         System.out.println("Just connected to " + ChatGUI.client.getRemoteSocketAddress());
         /* Send data to the ServerSocket */
         ChatGUI.field.append("========================================\nWELCOME TO CLASH OF CLASS v1.0\nPLEASE INPUT YOUR CHAT NICKNAME \n ON THE TEXT BOX");
        
         
         Thread t = new ChatGUI();
        t.start();
        
        Action submit = new AbstractAction()
        {
          /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override 
          public void actionPerformed(ActionEvent e){
				if(counter == 0){
	         		 ChatGUI.field.append("\n\nWelcome "+ ChatGUI.inputbox.getText() +"!\n");
	         		 counter++;
	         	}else{
            ChatGUI.field.append("Me: "+ChatGUI.inputbox.getText()+"\n");
	         	}
            OutputStream outToServer;
			try {
				outToServer = ChatGUI.client.getOutputStream();
				DataOutputStream out = new DataOutputStream(outToServer);
	         	out.writeUTF(ChatGUI.inputbox.getText());
	         	
	         
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
         
          	ChatGUI.inputbox.setText("");
          }

        };
        ChatGUI.inputbox.addActionListener(submit);
        
        
         //insert missing line for closing the socket from the client side
	//ChatGUI.client.close(); 
	//ChatGUI.running = false;

      }catch(IOException e)
      {
        
      	ChatGUI.field.append("ERROR: Cannot Connect to Server. \nPlease Restart the Game.\n");
      }catch(ArrayIndexOutOfBoundsException e)
      {
         System.out.println("Usage: java GreetingClient <server ip> <port no.> '<your message to the server>'");
            }
        }
   }
   
