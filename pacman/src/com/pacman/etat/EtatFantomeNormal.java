package com.pacman.etat;

import com.pacman.agent.Agent;
import com.pacman.modele.PacmanGame;
import com.pacman.strategies.Strategie;
import com.pacman.strategies.StrategieAttaqueFantome;

public class EtatFantomeNormal implements EtatFantomes {

	Strategie g; 

	public EtatFantomeNormal() {
		this.g = new StrategieAttaqueFantome(); 	
	}

	@Override
	public Strategie getStrategie() {
		// TODO Auto-generated method stub
		return g;
	}


}
