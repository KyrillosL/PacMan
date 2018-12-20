package com.pacman.modele;

import java.util.ArrayList;

import com.pacman.agent.Agent;

public class CapsuleNonActive implements EtatJeu {


	PacmanGame pacmanGame; 
	
	public CapsuleNonActive(PacmanGame pc) {
		
		this.pacmanGame = pc; 
	}
	
	@Override
	public void manger(ArrayList <Agent> fantomes, Agent f,  ArrayList <Agent> pacmans, Agent p) {

		pacmans.remove(p);
		// TODO Auto-generated method stub
		
	}

}