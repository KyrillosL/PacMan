package pacman;

import java.util.ArrayList;

public abstract class Game implements Runnable, Sujet {
	
	ArrayList<Observer> obs= new ArrayList<Observer> ();
	
	 Thread thread;  
	 
		public Maze maze; 
	 
    public void launch(){  
        thread = new Thread(this);    
        thread.start();
        
    }
	public Maze getMaze() {
		// TODO Auto-generated method stub
		return null;
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

        try {
			//maze = new Maze("layouts/originalClassic.lay");
        	//maze = new Maze("layouts/capsuleClassic.lay");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void init() {
		
		
		isRunning=false; 
		nbTour =0; 
		
		try {
			//maze = new Maze("layouts/originalClassic.lay");
			maze = null; 
        	maze = new Maze("layouts/capsuleClassic.lay");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		initializeGame(); 
		notifyObserver(); 
		
		 
	}
	
	protected void step() throws InterruptedException {
		//System.out.println("Step Game ");
		isRunning=true; 
		if (!termine) {
			takeTurn(); 
			nbTour++;
			//notifyObserver(); 
			Thread.sleep(2);
			
		}
		else {
			gameOver(); 
		}
		notifyObserver(); 
		
	}
	public void run() {
		isRunning=true; 
		while( !termine && isRunning && nbTour != maxTour) {
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
		System.out.println("STOP");
		notifyObserver(); 
	}
	
	protected int nbTour;
	protected int maxTour; 
	protected boolean termine=false;
	protected boolean isRunning; 
	abstract void initializeGame();
	abstract void takeTurn();
	abstract void gameOver();
	public PanelPacmanGame getPpg() {
		// TODO Auto-generated method stub
		return null;
	}



}
