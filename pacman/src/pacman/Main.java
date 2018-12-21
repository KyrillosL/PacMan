package pacman;

import com.pacman.controleur.ControleurAvance;
import com.pacman.modele.Game;
import com.pacman.modele.PacmanGame;
import com.pacman.modele.PacmanGame;

public class Main {
	
	
	  public static void main(String[] args) {
	       //Game jeu = new SimpleGame(10); 
	       //jeu.initializeGame();
	       //jeu.run();
		  
		  //Game jeu = new PacmanGame(100000000); 
		  Game jeu = new PacmanGame(100000000); 
		  jeu.init(3);
		  ControleurAvance controleur = new ControleurAvance(jeu);
		  

		  jeu.registerObserver(controleur.view);
	  }


}
