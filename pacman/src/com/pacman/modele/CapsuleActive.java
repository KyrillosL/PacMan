package com.pacman.modele;

import java.util.ArrayList;

import com.pacman.agent.Agent;

public class CapsuleActive implements EtatJeu {

	PacmanGame pacmanGame; 
	

	public CapsuleActive(PacmanGame pc) {
		
		this.pacmanGame = pc; 
	}
	
	@Override
	public void manger(ArrayList <Agent> fantomes, Agent f,  ArrayList <Agent> pacmans, Agent p) {

		fantomes.remove(f);

	}
	


}
