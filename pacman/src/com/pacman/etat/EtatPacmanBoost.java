package com.pacman.etat;

import com.pacman.agent.Agent;
import com.pacman.modele.PacmanGame;
import com.pacman.strategies.Strategie;
import com.pacman.strategies.StrategieAttaquePacman;

public class EtatPacmanBoost implements EtatPacman {

	Strategie g; 

	public EtatPacmanBoost() {
		g= new StrategieAttaquePacman(); 	
	}



	@Override
	public Strategie getStrategie() {
		// TODO Auto-generated method stub
		
		return g;
	}

}
