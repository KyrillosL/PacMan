package com.pacman.controleur;

public interface ControleurGame {

	public void launch();

	void step();

	void init(int nbVies);

	void stop();
	
	
	void setTourParSeconde(int valeur);
	void setGameMode(String valeur);
	void setMap(String valeur);
	
}
