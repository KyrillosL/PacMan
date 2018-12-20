package com.pacman.agent;

import java.util.ArrayList;

import com.pacman.vue.Maze;

public class PositionAgent {
	
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
	public boolean coordonneesEgales(PositionAgent posAgent) {
		return (this.x==posAgent.x)&&(this.y==posAgent.y);
		
	}
	public ArrayList<PositionAgent> getVoisins(Maze maze){
		ArrayList<PositionAgent> voisins = new ArrayList<>();
		
				//test si coordonnée dans labyrinthe et si ce n'est pas un mur--> si case accessible
				if(( this.x-1 >= 0 ) && ( this.y>=0 ) && ( this.x-1 < maze.getSizeX() ) && ( this.y < maze.getSizeY()) && ( !maze.isWall(this.x-1, this.y) ))	
					voisins.add(new PositionAgent(this.x-1, this.y, this.dir));
				if(( this.x >= 0 ) && ( this.y-1>=0 ) && ( this.x < maze.getSizeX() ) && ( this.y-1 < maze.getSizeY()) && ( !maze.isWall(this.x, this.y-1) ))	
					voisins.add(new PositionAgent(this.x, this.y-1, this.dir));
				if(( this.x+1 >= 0 ) && ( this.y>=0 ) && ( this.x+1-1 < maze.getSizeX() ) && ( this.y < maze.getSizeY()) && ( !maze.isWall(this.x+1, this.y) ))	
					voisins.add(new PositionAgent(this.x+1, this.y, this.dir));
				if(( this.x >= 0 ) && ( this.y+1 >=0 ) && ( this.x < maze.getSizeX() ) && ( this.y+1 < maze.getSizeY()) && ( !maze.isWall(this.x, this.y+1) ))	
					voisins.add(new PositionAgent(this.x, this.y+1, this.dir));
			
		return voisins;
		
}
	
}