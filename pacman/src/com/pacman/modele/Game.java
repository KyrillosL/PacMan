package com.pacman.modele;
import com.pacman.agent.AgentAction.EnumAction;
import com.pacman.vue.*;
import java.util.ArrayList;

public abstract class Game implements Runnable, Sujet {
	
	ArrayList<Observer> obs= new ArrayList<Observer> ();
	
	Thread thread;  
	int vitesseJeu=200; 

	 
	public Maze maze;
	private String mazeString;
	
	
	 
    public void launch(){  
        thread = new Thread(this);    
        thread.start();
        
    }
	public Maze getMaze() {
		// TODO Auto-generated method stub
		
		return maze;
	}
	
	

	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub
		obs.add(o);
	}


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
        	maze = new Maze("layouts/capsuleClassic.lay");
        	mazeString= "layouts/capsuleClassic.lay";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void init() {
		
		
		isRunning=false; 
		nbTour =0; 
		
		try {
			//maze = new Maze("layouts/originalClassic.lay");
			maze = null; 
        	maze = new Maze(mazeString);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		initializeGame(); 
		notifyObserver(); 
		
		 
	}
	
	public void step() throws InterruptedException {
		//System.out.println("Step Game ");
		isRunning=true; 
		if (!termine) {
			takeTurn(); 
			nbTour++;
			//notifyObserver(); 
			Thread.sleep(vitesseJeu);
			
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
	public void stop() {
		isRunning=false; 
		System.out.println("STOP");
		notifyObserver(); 
	}
	
	public void setVitesse(int value) {
		this.vitesseJeu = value; 
	}
	
	public int getNbTour() {
		return this.nbTour;
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
		System.out.println("dans le mauvais ppg");
		return null;
	}
	
	public abstract void setGameMode(String gm);
	public  void setMaze(String name) {
		try {
			//maze = new Maze("layouts/" + name);
			mazeString = "layouts/" + name;
			System.out.println(maze.toString());
			init();
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	



}
