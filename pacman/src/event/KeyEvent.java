package event;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;


import com.pacman.agent.AgentAction.EnumAction;
import com.pacman.controleur.ControleurGame;
import com.pacman.modele.Game;
import com.pacman.modele.PacmanGame;
import com.pacman.vue.View;
 
public class KeyEvent extends JFrame implements KeyListener, ActionListener
{

	View view; 
	PacmanGame pg; 
	EnumAction action ;
    JTextArea displayArea;
    JTextField typingArea;
    ControleurGame cg; 
    static final String newline = System.getProperty("line.separator");
     
    
    public KeyEvent(Game game, View v, ControleurGame cg) {
    	this.cg = cg; 
    	//System.out.println("Constructeur key event");
		this.pg =(PacmanGame) game; 
		this.view = v; 
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
		System.out.println(e.getKeyCode());
		
	    switch(e.getKeyCode())
	    {
	      case 37:
	    	  pg.setAction(EnumAction.gauche); 
	    	  break;

	      case 39:
	    	  pg.setAction(EnumAction.droite); 
	    	  break;     
	      case 38:
	    	  pg.setAction(EnumAction.haut); 
	    	  break; 

	      case 40:
	    	  pg.setAction(EnumAction.bas); 
	    	  break;
	    	  
	      case 32:
	    	  cg.launch();
	    	  break;
	    } 
	    
	    
		
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub

		
	}

    
}