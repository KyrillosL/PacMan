package com.pacman.modele;


import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.pacman.agent.Agent;
import com.pacman.agent.AgentAction;
import com.pacman.agent.AgentAction.EnumAction;
import com.pacman.agent.PacmanAgent;
import com.pacman.agent.PositionAgent;
import com.pacman.vue.Maze;
import com.pacman.vue.PanelPacmanGame;
import com.pacman.*;

public class PlayerGame extends Game {
	

	
	EtatJeu capsuleNonActive; 
	EtatJeu capsuleActive;
	EtatJeu etatJeu = capsuleNonActive; 
	
	
	
	EtatPacman etatPacmanNormal; 
	EtatPacman etatPacmanBoost;
	EtatPacman etatPacman = etatPacmanNormal; 
	
	
	EtatFantomes etatFantomesNormal; 
	EtatFantomes etatFantomesApeure;
	EtatFantomes etatFantomes = etatFantomesNormal; 
	
	Strategie strategieRandom = new StrategieRandom();
	Strategie strategiePlayer = new StrategiePlayer();
	
	Strategie strategiePacman; 
	Strategie strategieFantome; 
	
	AgentAction actionPrecedente; 
	EnumAction enumAction = EnumAction.vide;

	String gameMode ="com"; 
	
	PanelPacmanGame ppg ;
	public ArrayList<Agent> pacmans; 
	ArrayList<Agent> fantomes;
	int timer=0; 
	
	
	
	public PlayerGame(int mt) {
		super(mt);
		/*
		capsuleNonActive = new CapsuleNonActive(this); 
		capsuleActive = new CapsuleActive(this); 
		
		etatPacmanNormal = new EtatPacmanNormal(this); 
		etatPacmanBoost = new EtatPacmanBoost(this); 
		
		etatFantomesNormal = new EtatFantomeNormal(this); 
		etatFantomesApeure = new EtatFantomeApeure(this); 
		*/
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
		// TODO Auto-generated method stub

		
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
		
		

	}


	@Override
	void takeTurn() {
		
		
		if (gameMode == "com") {
			strategiePacman = strategieRandom;
		}
		else if (gameMode == "player"){
			strategiePacman = strategiePlayer;
		}
		

		
		//System.out.println("AVANT TAKE TURN");
		// TODO Auto-generated method stub
		
		if (etatJeu == capsuleNonActive) {
			etatFantomes = etatFantomesNormal; 
			etatPacman = etatPacmanNormal; 
		}
		else {
			etatPacman = etatPacmanBoost; 
			etatFantomes = etatFantomesApeure;  
		}
		
		
		if (fantomes.size()==0 || pacmans.size()==0) {
			System.out.println("P: "+ pacmans.size()+ ", F: "+fantomes.size());
			gameOver(); 
		}
		
		ArrayList<PositionAgent> apa = new ArrayList<PositionAgent>();
		ArrayList<PositionAgent> paf = new ArrayList<PositionAgent>();
	
		//timer--; 
		if (timer <=0) {
			etatJeu = capsuleNonActive; 
		}
		
		
		for ( Agent f : fantomes) {
			moveAgent(f,strategieFantome.getAction(f,maze) );

			if (etatJeu == capsuleNonActive) {

				if (pacmans.removeIf(p -> (p.position.getX()==f.position.getX() && p.position.getY()==f.position.getY()))){
					System.out.println("J'ai mangé pacman");
				}

			}

			paf.add(f.position);
		}
		
		
		
		
		
		
		for ( Agent p : pacmans) {
			//System.out.println("position pacman avant: " + p.position.getY());
			

			if (gameMode == "player") {

				((StrategiePlayer) strategiePacman).setEnumAction(enumAction);
			}
			

			
			
			moveAgent(p,strategiePacman.getAction(p,maze) );
			
	
			if (maze.isFood(p.position.getX(), p.position.getY())) {
				maze.setFood(p.position.getX(), p.position.getY(), false); 
			}
			
			if (maze.isCapsule(p.position.getX(), p.position.getY())){
				timer=500;
				etatJeu = capsuleActive; 

				maze.setCapsule(p.position.getX(), p.position.getY(), false); 
			}		
			if (etatJeu == capsuleActive) {


				if (fantomes.removeIf(f -> (p.position.getX()==f.position.getX() && p.position.getY()==f.position.getY()))) {
					System.out.println("J'ai mangé un fantome");
				}

				
			}
			//etatPacman.deplacer(p);

			apa.add(p.position);
			
		}
		


		ppg.setPacmans_pos(apa);
		
		ppg.setGhosts_pos(paf);
		
		if (timer < 0) {
			timer =0; 
		}
		System.out.println("Timer: " + timer);
		
		
	}

	@Override
	void gameOver() {
		stop(); 
		
		// TODO Auto-generated method stub
		//notifyObserver(); 
	}
	
	
	public Maze getMaze() {
		// TODO Auto-generated method stub
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
				case "haut" : a.position.setY(a.position.getY() -1 );  break; 	
				case "bas" : a.position.setY( a.position.getY()+ 1 );	break; 
				case "droite" : a.position.setX(a.position.getX() +1 );	  break;
				case "gauche" : a.position.setX(a.position.getX() -1 );	 break; 
				case "vide": break; 
			
			}
		
		}
			
	}

}
