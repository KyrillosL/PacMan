package com.pacman.modele;


import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.pacman.agent.Agent;
import com.pacman.agent.AgentAction;
import com.pacman.agent.AgentAction.EnumAction;
import com.pacman.strategies.Strategie;
import com.pacman.strategies.StrategieAttaqueFantome;
import com.pacman.strategies.StrategieAttaquePacman;
import com.pacman.strategies.StrategieFuiteFantome;
import com.pacman.strategies.StrategieGommePacman;
import com.pacman.strategies.StrategiePlayer;
import com.pacman.strategies.StrategieRandom;
import com.pacman.agent.PacmanAgent;
import com.pacman.agent.PositionAgent;
import com.pacman.vue.Maze;
import com.pacman.vue.PanelPacmanGame;
import com.pacman.*;
import java.awt.Color;

public class PacmanGame extends Game {
	

	

	boolean capsuleActive = false;
	
	
	EtatPacman etatPacmanNormal; 
	EtatPacman etatPacmanBoost;
	EtatPacman etatPacman = etatPacmanNormal; 
	
	
	EtatFantomes etatFantomesNormal; 
	EtatFantomes etatFantomesApeure;
	EtatFantomes etatFantomes = etatFantomesNormal; 
	
	Strategie strategieRandom = new StrategieRandom();
	Strategie strategiePlayer = new StrategiePlayer();
	
	Strategie strategiePacman; 
	Strategie strategieGommePacman = new StrategieGommePacman();
	Strategie strategieAttaquePacman = new StrategieAttaquePacman();
	
	Strategie strategieFantome; 
	Strategie strategieFuiteFantome = new StrategieFuiteFantome();
	Strategie strategieAttaqueFantome = new StrategieAttaqueFantome();
	
	AgentAction actionPrecedente; 
	EnumAction enumAction = EnumAction.vide;

	String gameMode ="com"; 
	
	PanelPacmanGame ppg ;
	public ArrayList<Agent> pacmans; 
	ArrayList<Agent> fantomes;
	int timer=0; 
	
	
	
	public PacmanGame(int mt) {
		super(mt);
		

		
		etatPacmanNormal = new EtatPacmanNormal(this); 
		etatPacmanBoost = new EtatPacmanBoost(this); 
		
		etatFantomesNormal = new EtatFantomeNormal(this); 
		etatFantomesApeure = new EtatFantomeApeure(this); 
		
		strategiePacman = strategiePlayer; 
		strategieFantome = strategieRandom; 
		
	}

	

	
	ArrayList<Agent>  getPositionPacmans() {
		return pacmans;
	}
	

	public void setGameMode(String gm) {
		this.gameMode= gm;
		
	}
	
	public PanelPacmanGame getPpg() {
		
		return ppg; 
	}
	
	public void initializeGame() {

		System.out.println("INIT");
		ppg=null; 
		ppg= new PanelPacmanGame(maze);
		
		
		
		pacmans = new ArrayList <Agent>(); 
		pacmans.clear();
		//ArrayList<PositionAgent> pa = maze.getPacman_start();
		//ArrayList<PositionAgent> pa = new ArrayList<PositionAgent>();
		
		ArrayList<PositionAgent> pa = ppg.getPacmans_pos();
		
		
		for ( PositionAgent papa : pa) {
			pacmans.add( new PacmanAgent(papa));
		}		
		fantomes = new ArrayList <Agent>(); 
		fantomes.clear();
		
		ArrayList<PositionAgent> f = ppg.getGhosts_pos();
		for ( PositionAgent fap : f) {
			fantomes.add( new PacmanAgent(fap));
		}	
		
		etatPacman = etatPacmanNormal;
		etatFantomes = etatFantomesNormal;

		strategieFantome = new StrategieAttaqueFantome();
		strategiePacman= new StrategieGommePacman();
		
		

	}
	
	private boolean plusDeGomme() {
		boolean fin = true; 
		for (int x = 0; x<maze.getSizeX(); x++) {
			for (int y = 0; y<maze.getSizeY(); y++) {
				if (maze.isFood(x, y))
					fin = false; 
				
			}
		}
		return fin; 
	}


	@Override
	void takeTurn() {
		
		

		if (gameMode == "player"){
			strategiePacman = strategiePlayer;
		}
				
		if ( capsuleActive) {
			etatPacman = etatPacmanBoost; 
			etatFantomes = etatFantomesApeure;
			
		}
		else {
			etatFantomes = etatFantomesNormal; 
			etatPacman = etatPacmanNormal; 
		}
		
		if(etatPacman == etatPacmanNormal) {
			strategiePacman = strategieGommePacman;
			strategieFantome = strategieAttaqueFantome;
		}
		else if(etatPacman == etatPacmanBoost) {
			strategiePacman = strategieAttaquePacman;
			strategieFantome = strategieFuiteFantome;
			
		}
		
	
		
		
		
		
		if (fantomes.size()==0 ||plusDeGomme() ) {
			System.out.println("Il n'y a plus de fantomes, fin du jeu. P: "+ pacmans.size()+ ", F: "+fantomes.size());
			win(); 
		}
		
		
		if ( pacmans.size()==0) {
			System.out.println("Il n'y a plus de pacmans, fin du jeu. P: "+ pacmans.size()+ ", F: "+fantomes.size());
			gameOver(); 
		}

		
		ArrayList<PositionAgent> apa = new ArrayList<PositionAgent>();
		ArrayList<PositionAgent> paf = new ArrayList<PositionAgent>();
	
		timer--; 
		if (timer <=0) {
			capsuleActive=false; 
		}
		
		
		for ( Agent f : fantomes) {



			moveAgent(f,strategieFantome.getAction(f,maze, pacmans) );

			if (! capsuleActive) {
				
				ppg.setGhostsScarred(false);
				
				//System.out.println("p: " +pacmans.get(0).position.getX()+","+pacmans.get(0).position.getY()+"f: " + f.position.getX()+","+f.position.getY());
				
				if (pacmans.removeIf(p -> (p.position.getX()==f.position.getX() && p.position.getY()==f.position.getY()))){
					System.out.println("J'ai mangé pacman");
				}

			}
			else {
				ppg.setGhostsScarred(true);
			}

			paf.add(f.position);
		}
		
		
		
		
		
		
		for ( Agent p : pacmans) {
			//System.out.println("position pacman avant: " + p.position.getY());
			

			if (gameMode == "player") {

				((StrategiePlayer) strategiePacman).setEnumAction(enumAction);
			}
			


			
			moveAgent(p,strategiePacman.getAction(p,maze, fantomes) );
			
	
			if (maze.isFood(p.position.getX(), p.position.getY())) {
				maze.setFood(p.position.getX(), p.position.getY(), false); 
			}
			
			if (maze.isCapsule(p.position.getX(), p.position.getY())){
				timer=500;
				capsuleActive = true; 

				maze.setCapsule(p.position.getX(), p.position.getY(), false); 
			}		
			if (capsuleActive) {
				Color c= Color.RED ;
				ppg.setPacmanColor(c);
				if (fantomes.removeIf(f -> (p.position.getX()==f.position.getX() && p.position.getY()==f.position.getY()))) {
					System.out.println("J'ai mangé un fantome");
				}
				
			}
			else {
				Color c= Color.YELLOW ;
				ppg.setPacmanColor(c);
			}
			//etatPacman.deplacer(p);

			apa.add(p.position);
			
		}
		


		ppg.setPacmans_pos(apa);
		
		ppg.setGhosts_pos(paf);
		
		if (timer < 0) {
			timer =0; 
		}
		//System.out.println("Timer: " + timer);
		//System.out.println(etatJeu);
		
		
	}

	@Override
	void gameOver() {
		System.out.println("gameover");
		stop(false); 
		//notifyObserver(); 
	}
	void win() {
		System.out.println("win");
		stop(true); 
		
		//notifyObserver(); 
	}
	
	
	public Maze getMaze() {
		// 
		return maze;
	}
	
	
	public void setAction(EnumAction ea) {
		System.out.println("dans le setAction");
		enumAction = ea; 
		try {
			AgentAction aa = new AgentAction(enumAction);
			System.out.println(aa.getAction());
			//moveAgent(pacmans.get(0),aa );
		}
		catch (Exception e) {
			System.out.println("Catch");
		}

		
	}
	
	boolean isLegalMove(Agent a, AgentAction aa) {
		
		//System.out.println("Position Pacman Depart x:"+this.maze.getPacman_start().get(0).getX() + ", y: "+this.maze.getPacman_start().get(0).getY() ); 
		
		switch (aa.getAction()) {
			case "haut" : 
					if  (maze.getSizeY()>= (a.position.getY()+1)  ) {
						int pos  = a.position.getY() - 1; 
						//System.out.println("Est un mur ? " +a.position.getX() +" " +  pos  + " "+maze.isWall(a.position.getX() ,  pos));
						if (!maze.isWall(a.position.getX() ,  pos)) {
							//System.out.println("true");
							return true; 
						}
						else return false; 
					}
					else return false; 
					//System.out.println("X: " + a.position.getX()+  " Y: " + a.position.getY() +" Wall: "+ !maze.isWall(a.position.getX() ,  a.position.getY() +1) ); 
					  
		
			case "bas" : 
				if  (0<= (a.position.getY()-1)  ) {
					int pos  = a.position.getY() + 1; 
					//System.out.println("Est un mur ? " +a.position.getX() +" " +  pos  + " "+maze.isWall(a.position.getX() ,  pos));
					if (!maze.isWall(a.position.getX() ,  pos)) {
						//System.out.println("true");
						return true; 
					}
					else return false; 
				}
				else return false; 
				
				
			case "droite" : 
				if  (maze.getSizeX()>= (a.position.getX()+1)  ) {
					int pos  = a.position.getX() + 1; 
					//System.out.println("Est un mur ? " +pos +" " +  a.position.getY()  + " "+maze.isWall(pos ,  a.position.getY()));
					if (!maze.isWall(pos ,  a.position.getY())) {
						//System.out.println("true");
						return true; 
					}
					else return false; 
				}
				else return false; 
				  
			case "gauche" : 
				
				if  (maze.getSizeX()>= (a.position.getX()-1)  ) {
					int pos  = a.position.getX() - 1; 
					//System.out.println("Est un mur ? " +pos +" " +  a.position.getY()  + " "+maze.isWall(pos ,  a.position.getY()));
					if (!maze.isWall(pos ,  a.position.getY())) {
						//System.out.println("true");
						return true; 
					}
					else return false; 
				}
				else return false; 
		
		}
		return false;

		
	}
	
	void moveAgent(Agent a, AgentAction aa) {
		if (isLegalMove(a, aa) ){
			switch (aa.getAction()) {		
				case "haut" : a.position.setY(a.position.getY() -1 ); a.position.setDir(0);  break; 	
				case "bas" : a.position.setY( a.position.getY()+ 1 );a.position.setDir(1);	break; 
				case "droite" : a.position.setX(a.position.getX() +1 );	a.position.setDir(2);  break;
				case "gauche" : a.position.setX(a.position.getX() -1 );	a.position.setDir(3); break; 
				case "vide": break; 
			
			}
		
		}
			
	}
	
	

}
