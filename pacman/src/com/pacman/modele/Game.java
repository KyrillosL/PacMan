package com.pacman.modele;
import com.pacman.agent.AgentAction.EnumAction;
import com.pacman.vue.*;
import java.util.ArrayList;

public abstract class Game implements Runnable, Sujet {
	
	ArrayList<Observer> obs= new ArrayList<Observer> ();
	
	Thread thread;  
	int vitesseJeu=200; 
	int vies; 
	
	private String etat; 


	 
	public Maze maze;
	private String mazeString;
	
	
	 
    public void launch(){  
        thread = new Thread(this);    
        thread.start();
        
    }
    
    public int getVies() {
    	return this.vies;
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
	
	public void init(int nbVies) {
		setEtat("init");
		vies = nbVies; 
		isRunning=false; 
		nbTour =0; 
		gameOver=false;
		
		
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
		
		if (vies <=0) {
			gameOver=true; 
		}
		
		isRunning=true; 
		if (!termine) {
			takeTurn(); 
			nbTour++;
			//notifyObserver(); 
			Thread.sleep(vitesseJeu);
			
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

	}
	public void stop(boolean win) {
		isRunning=false; 
		System.out.println("STOP");
		if (!gameOver)
			init(vies-1);
		
		if (win) {
			setEtat("win");
		}
		else {
			setEtat("stop");
		}

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

	private boolean gameOver; 
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
			init(3);
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
	



}
