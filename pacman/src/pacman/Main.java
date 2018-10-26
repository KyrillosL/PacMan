package pacman;

public class Main {
	
	
	  public static void main(String[] args) {
	       //Game jeu = new SimpleGame(10); 
	       //jeu.initializeGame();
	       //jeu.run();
		  
		  Game jeu = new PacmanGame(100000000); 
		  jeu.init();
		  ControleurAvance controleur = new ControleurAvance(jeu);
		  

		  jeu.registerObserver(controleur.view);
	  }


}
