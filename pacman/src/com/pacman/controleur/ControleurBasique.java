package com.pacman.controleur;

import com.pacman.*;
import com.pacman.modele.Game;

public class ControleurBasique implements ControleurGame {

	
	Game game; 
	
	public ControleurBasique(Game g) {
		game = g; 
		
		
	}
	
	@Override
	public void launch() {
		// TODO Auto-generated method stub
		game.launch(); 
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		try {
			game.step();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		game.init();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		game.stop();
	}

}
