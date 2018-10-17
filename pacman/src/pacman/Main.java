package pacman;

public class Main {
	
	
	  public static void main(String[] args) {
	       Game jeu = new SimpleGame(10); 
	       jeu.initializeGame();
	       //jeu.run();
		   ControleurGame controleur = new ControleurAvance(jeu);
	        
	    }

}
