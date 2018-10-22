package pacman;

public class SimpleGame extends Game {

	SimpleGame(int mt) {
		super(mt);
		// TODO Auto-generated constructor stub
	}

	@Override
	void initializeGame() {
		// TODO Auto-generated method stub
		System.out.println("Init");
		
	}

	@Override
	void takeTurn() {
		// TODO Auto-generated method stub
		System.out.println("takeTurn");
	}

	@Override
	void gameOver() {
		// TODO Auto-generated method stub
		System.out.println("gameOver");
	}

	@Override
	public Maze getMaze() {
		// TODO Auto-generated method stub
		return null;
	}



}
