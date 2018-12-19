package com.pacman.agent;

import java.util.ArrayList;

import javax.swing.text.Position;

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
		for( int i = this.x-1 ; i <= this.x+1 ; i = i+2 ) {
			for( int j= this.y-1 ; j <= this.y+1 ; j = j+2 ) {
				//test si coordonnée dans labyrinthe et si ce n'est pas un mur--> si case accessible
				if(( i >= 0 ) && ( j>=0 ) && ( i < maze.getSizeX() ) && ( j < maze.getSizeY()) && ( !maze.isWall(i, j) ))	
					voisins.add(new PositionAgent(i, j, this.dir));
			}
		}
		return voisins;
		
	}
	
	
}