package pacman;

import java.util.ArrayList;



import pacman.AgentAction.EnumAction;

public class PacmanGame extends Game {
	
	
	
	PanelPacmanGame ppg ;
	public ArrayList<Agent> pacmans; 
	ArrayList<Agent> fantomes;
	int timer=0; 
	
	
	
	public PacmanGame(int mt) {
		super(mt);
		// TODO Auto-generated constructor stub
	}

	

	
	ArrayList<Agent>  getPositionPacmans() {
		return pacmans;
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
		
		//ArrayList<PositionAgent> f =  new ArrayList<PositionAgent>();
		//ArrayList<PositionAgent> f = maze.getGhosts_start();
		ArrayList<PositionAgent> f = ppg.getGhosts_pos();
		for ( PositionAgent fap : f) {
			fantomes.add( new PacmanAgent(fap));
		}	
		
		

	}


	@Override
	void takeTurn() {
		//System.out.println("AVANT TAKE TURN");
		// TODO Auto-generated method stub
		
		if (fantomes.size()==0 || pacmans.size()==0) {
			System.out.println("P: "+ pacmans.size()+ ", F: "+fantomes.size());
			gameOver(); 
		}
		
		ArrayList<PositionAgent> apa = new ArrayList<PositionAgent>();
		ArrayList<PositionAgent> paf = new ArrayList<PositionAgent>();
	
		timer--; 

		
		for ( Agent f : fantomes) {
			moveAgent(f,getAction(f,maze) );
			if (0>=timer) {
				
				if (pacmans.removeIf(p -> (p.position.getX()==f.position.getX() && p.position.getY()==f.position.getY()))){
					System.out.println("J'ai mangé pacman");
				}
			}
			
			paf.add(f.position);
		}
		
		for ( Agent p : pacmans) {
			//System.out.println("position pacman avant: " + p.position.getY());
			moveAgent(p,getAction(p,maze) );
			
			if (maze.isFood(p.position.getX(), p.position.getY())) {
				maze.setFood(p.position.getX(), p.position.getY(), false); 
			}
			
			if (maze.isCapsule(p.position.getX(), p.position.getY())){
				timer=500; 
				maze.setCapsule(p.position.getX(), p.position.getY(), false); 
			}
			
			if (timer >0) {
				
				if (fantomes.removeIf(f -> (p.position.getX()==f.position.getX() && p.position.getY()==f.position.getY()))) {
					System.out.println("J'ai mangé un fantome");
				}
				
			}

			
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
	
	
	AgentAction getAction(Agent a, Maze m) {
		EnumAction test =  EnumAction.haut;
		int n = (int)(Math.random() * 4);
		

		
		//System.out.println("Nombre random: "+n);
		switch (n) {
		
			case 0 : test = EnumAction.haut; break; 
			case 1 : test = EnumAction.bas; break;
			case 2 : test = EnumAction.gauche; break;
			case 3 : test = EnumAction.droite; break;
		}
		//System.out.println("Actions : " +test.toString());
		AgentAction aa = new AgentAction(test);
		return aa;
		//MAIS ON AURAIT AUSSI PU METTRE UN TRUC PLUS COMPLIQUE EN FONCTION DE L'AGENT ETC. PAR EXEMPLE : RAPPROCHER FANTOME PACMAN
		
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
		//System.out.println(" IS MOVE LEGAL");
		if (isLegalMove(a, aa) ){
			//System.out.println("MOVE LEGAL");
			//System.out.println(aa.getAction());
			
			switch (aa.getAction()) {
				/*
				case "haut" : a.position.setY(a.position.getY() -1 );  break; 	
				case "bas" : a.position.setY( a.position.getY()+ 1 );	break; 
				case "droite" : a.position.setX(a.position.getX() +1 );	  break;
				case "gauche" : a.position.setX(a.position.getX() -1 );	 break; 
				*/
				
				case "haut" : a.position.setY(a.position.getY() -1 );  break; 	
				case "bas" : a.position.setY( a.position.getY()+ 1 );	break; 
				case "droite" : a.position.setX(a.position.getX() +1 );	  break;
				case "gauche" : a.position.setX(a.position.getX() -1 );	 break; 
			
			}
			
			
		}
		

		
	}

}
