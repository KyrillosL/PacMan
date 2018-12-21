package com.pacman.strategies;

import java.util.ArrayList;
import java.util.Map;

import com.pacman.agent.Agent;
import com.pacman.agent.AgentAction;
import com.pacman.agent.PositionAgent;
import com.pacman.vue.Maze;

import ia.IA;

public class StrategieGommePacman implements Strategie{

	@Override
	public AgentAction getAction(Agent pacman, Maze m, ArrayList<Agent> inutile) {
		int cout = 0;
		int cout_min = 100000000; 		//INFINI
		PositionAgent g_min = null;
		Map<PositionAgent, PositionAgent> chemins;
		PositionAgent temp;


		
		for (PositionAgent g : m.getFoodList()) {
			cout = IA.A_Star_cout(m,pacman.position,g);
			if( cout < cout_min ) {
				cout_min = cout;
				g_min = g;
			}			
		}
		//c_min : gomme la plus proche
		
		
		chemins = IA.A_Star_chemin(m, pacman.position, g_min);
		temp = g_min;
		
		
		//Cas spécial ou gommes sur case à côté
		if(cout_min == 1) {
			if( m.isFood(pacman.position.getX()+1,pacman.position.getY()))
					return new AgentAction(AgentAction.EnumAction.droite);
			if( m.isFood(pacman.position.getX(),pacman.position.getY()+1))
					return new AgentAction(AgentAction.EnumAction.bas);
			if( m.isFood(pacman.position.getX()-1,pacman.position.getY()))
					return new AgentAction(AgentAction.EnumAction.gauche);
			if( m.isFood(pacman.position.getX(),pacman.position.getY()-1))
					return new AgentAction(AgentAction.EnumAction.haut);
		}
					
		try {
			do {
				temp = chemins.get(temp);
			}
			while (! chemins.get(temp).coordonneesEgales(pacman.position));		
				//tant que qu'on arrive pas au depart, on retrace le chemin
				
		

		}catch(Exception e) {
			
		}
		//temp est maintenant la première case où aller
		
		
		switch (temp.getDir()) {
			case PositionAgent.HAUT: return new AgentAction(AgentAction.EnumAction.haut);
			case PositionAgent.BAS: return new AgentAction(AgentAction.EnumAction.bas);
			case PositionAgent.GAUCHE: return new AgentAction(AgentAction.EnumAction.gauche);
			case PositionAgent.DROITE: return new AgentAction(AgentAction.EnumAction.droite);

		}
		return new AgentAction(AgentAction.EnumAction.gauche);		//random
	}

}
