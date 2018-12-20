package event;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;


import com.pacman.agent.AgentAction.EnumAction;
import com.pacman.modele.Game;
import com.pacman.modele.PacmanGame;
 
public class KeyEvent extends JFrame implements KeyListener, ActionListener
{

	PacmanGame pg; 
	EnumAction action ;
    JTextArea displayArea;
    JTextField typingArea;
    static final String newline = System.getProperty("line.separator");
     
    
    public KeyEvent(Game game) {
		this.pg =(PacmanGame) game; 
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		
	}

	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub

		
	}
	
	public EnumAction getAction() {
		return action; 
	}

	@Override
	public void keyPressed(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(e.getKeyCode());
		
	    switch(e.getKeyCode())
	    {
	      case 37:
	    	  action = EnumAction.gauche;
	    	  break;

	      case 39:
	    	  action = EnumAction.droite;
	    	  break;     
	      case 38:
	    	  action = EnumAction.haut; 
	    	  break; 

	      case 40:
	    	  action = EnumAction.bas; 
	    	  break;
	    } 
	    pg.setAction(action);
	    
		
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub

		
	}

    
}