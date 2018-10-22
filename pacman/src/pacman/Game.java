package pacman;

import java.util.ArrayList;

public abstract class Game implements Runnable, Sujet {
	
	ArrayList<Observer> obs= new ArrayList<Observer> ();
	
	 Thread thread;  
	 
    public void launch(){  
        thread = new Thread(this);    
        thread.start();
        
    }
	
	
	@Override
	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub
		obs.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		obs.remove(o);
		
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		for (Observer observer : obs) {
			observer.update();
		}
		
	} 
	
	
	
	Game(int mt){
		maxTour=mt; 
	}
	
	protected void init() {
		nbTour =0; 
		initializeGame(); 
	}
	
	protected void step() throws InterruptedException {
		if (!termine) {
			takeTurn(); 
			nbTour++;
			Thread.sleep(2000);
			
		}
		else {
			gameOver(); 
		}
		
		
	}
	public void run() {
		
		while( !termine && !isRunning && nbTour != maxTour) {
			try {
				step();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			} 
		}
		gameOver();
	}
	protected void stop() {
		isRunning=false; 
	}
	
	protected int nbTour;
	protected int maxTour; 
	protected boolean termine=false;
	protected boolean isRunning; 
	abstract void initializeGame();
	abstract void takeTurn();
	abstract void gameOver();


	public abstract Maze getMaze();

}
