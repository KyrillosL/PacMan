package com.pacman.modele;

import com.pacman.agent.Agent;
import com.pacman.agent.AgentAction;
import com.pacman.agent.PacmanAgent;
import com.pacman.agent.AgentAction.EnumAction;
import com.pacman.vue.Maze;

public class StrategieRandom implements Strategie {
	
	@Override
	public AgentAction getAction(Agent a, Maze m) {

		
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
		
		
	}

}
