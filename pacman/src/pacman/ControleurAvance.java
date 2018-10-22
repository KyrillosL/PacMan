package pacman;

public class ControleurAvance implements ControleurGame {


	Game game; 
	View view; 
	
	public ControleurAvance(Game g) {
		game = g; 
		try {
			view = new View(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		view.setPanel(g.getMaze());
        
		view.fenetreJeu.setVisible(true);

	}
	
	@Override
	public void launch() {
		// TODO Auto-generated method stub
		game.launch(); 
		view.run.setEnabled(false);
		view.pause.setEnabled(true);
		view.restart.setEnabled(true);
		

	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		game.init(); 
		view.restart.setEnabled(false);
		view.pause.setEnabled(true);

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		game.stop(); 
		view.pause.setEnabled(false);
		view.run.setEnabled(true);

	}
	
	@Override
	public void step() {
		// TODO Auto-generated method stub
		try {
			game.step();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}


