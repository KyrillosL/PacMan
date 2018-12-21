package com.pacman.agent;

import java.util.ArrayList;

import com.pacman.vue.Maze;
import java.util.Objects;

public class PositionAgent {
	
	public static final int HAUT=0;
	public static final int BAS=1;
	public static final int GAUCHE=2;
	public static final int DROITE=3;

	
	private int x;
	private int y;
	private int dir;
	
	public PositionAgent(int x, int y, int dir) {
		this.x=x;
		this.y=y;
		this.dir=dir;
	}

	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}
	public String toString() {
		return  this.x + "-" + this.y + "dir: " + this.dir;
	}
	public boolean coordonneesEgales(PositionAgent posAgent) {
		return (this.x==posAgent.x)&&(this.y==posAgent.y);
		
	}
	public ArrayList<PositionAgent> getVoisins(Maze maze){
		ArrayList<PositionAgent> voisins = new ArrayList<>();
		
				//test si coordonnée dans labyrinthe et si ce n'est pas un mur--> si case accessible
				if(( this.x-1 >= 0 ) && ( this.y>=0 ) && ( this.x-1 < maze.getSizeX() ) && ( this.y < maze.getSizeY()) && ( !maze.isWall(this.x-1, this.y) ))	
					voisins.add(new PositionAgent(this.x-1, this.y, GAUCHE));
				if(( this.x >= 0 ) && ( this.y-1>=0 ) && ( this.x < maze.getSizeX() ) && ( this.y-1 < maze.getSizeY()) && ( !maze.isWall(this.x, this.y-1) ))	
					voisins.add(new PositionAgent(this.x, this.y-1, HAUT));
				if(( this.x+1 >= 0 ) && ( this.y>=0 ) && ( this.x+1-1 < maze.getSizeX() ) && ( this.y < maze.getSizeY()) && ( !maze.isWall(this.x+1, this.y) ))	
					voisins.add(new PositionAgent(this.x+1, this.y, DROITE));
				if(( this.x >= 0 ) && ( this.y+1 >=0 ) && ( this.x < maze.getSizeX() ) && ( this.y+1 < maze.getSizeY()) && ( !maze.isWall(this.x, this.y+1) ))	
					voisins.add(new PositionAgent(this.x, this.y+1, BAS));
			
		return voisins;
		
}
	
	@Override
	public boolean equals(Object obs) {
		PositionAgent pos = (PositionAgent) obs;
		return ( (this.x == pos.x ) && ( this.y == pos.y ) );
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x,y);
		
	}
	
	
}