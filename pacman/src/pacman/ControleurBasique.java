package pacman;

public class ControleurBasique implements ControleurGame {

	
	Game game; 
	
	public ControleurBasique(Game g) {
		game = g; 
	}
	
	@Override
	public void launch() {
		// TODO Auto-generated method stub
		game.launch(); 
	}

}
