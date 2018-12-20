package com.pacman.controleur;

import com.pacman.*;
import com.pacman.modele.Game;
import com.pacman.vue.View;

public class ControleurAvance implements ControleurGame {


	public Game game; 
	public View view; 
	
	public ControleurAvance(Game g) {
		game = g; 
		try {
			view = new View(this, g);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//view.setPanel(g.getMaze());
		

        
		

	}
	
	@Override
	public void launch() {
		// TODO Auto-generated method stub
		game.launch(); 
		view.run.setEnabled(false);
		view.pause.setEnabled(true);
		view.restart.setEnabled(true);
		

	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		game.init(); 
		
		view.restart.setEnabled(false);
		view.pause.setEnabled(true);
		view.run.setEnabled(true);

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		game.stop(); 
		view.pause.setEnabled(false);
		view.run.setEnabled(true);

	}
	
	@Override
	public void step() {
		// TODO Auto-generated method stub
		try {
			game.step();
			//view.update(); //update quand le jeu change. CHANGER ICI !!!! La vue est un observer, elle est updaté automatiquement dès qu'il se passe quelque chose; 
			//System.out.println("STEP CONTROLEUR ");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	@Override
	public void setTourParSeconde(int valeur) {
		// TODO Auto-generated method stub
		game.setVitesse(valeur);
		System.out.println("valeur: " +valeur);
		
	}
	
	public void setGameMode(String gm) {
		game.setGameMode(gm);
	}

	@Override
	public void setMap(String valeur) {
		// TODO Auto-generated method stub
		game.setMaze(valeur);
	}


}


