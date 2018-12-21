package com.pacman.etat;

import com.pacman.agent.Agent;
import com.pacman.modele.PacmanGame;
import com.pacman.strategies.Strategie;
import com.pacman.strategies.StrategieAttaquePacman;
import com.pacman.strategies.StrategieGommePacman;

public class EtatPacmanNormal implements EtatPacman {


	Strategie g; 

	public EtatPacmanNormal() {
		g= new StrategieGommePacman(); 		
	}

	@Override
	public Strategie getStrategie() {
		// TODO Auto-generated method stub
		return g;
	}



}
