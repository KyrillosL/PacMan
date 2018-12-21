package com.pacman.etat;

import com.pacman.agent.Agent;
import com.pacman.modele.PacmanGame;
import com.pacman.strategies.Strategie;
import com.pacman.strategies.StrategieAttaqueFantome;
import com.pacman.strategies.StrategieFuiteFantome;

public class EtatFantomeApeure implements EtatFantomes {

	Strategie g; 

	public EtatFantomeApeure() {
		this.g = new StrategieFuiteFantome(); 	
	}

	@Override
	public Strategie getStrategie() {
		// TODO Auto-generated method stub
		return g;
	}

}
