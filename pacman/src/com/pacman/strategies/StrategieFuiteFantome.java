package com.pacman.strategies;

import java.util.ArrayList;
import java.util.Map;

import com.pacman.agent.Agent;
import com.pacman.agent.AgentAction;
import com.pacman.agent.PositionAgent;
import com.pacman.vue.Maze;

import ia.IA;

public class StrategieFuiteFantome implements Strategie{

	// s'eloigne du pacman le plus près
	@Override
	public AgentAction getAction(Agent fantome, Maze m, ArrayList<Agent> pacmans) {
		int cout = 0;
		int cout_min = 100000000; 		//INFINI
		Agent p_min = null;
		Map<PositionAgent, PositionAgent> chemins;
		PositionAgent temp;
		


		
		if( ! pacmans.isEmpty()) {
			
			for (Agent p : pacmans) {
				cout = IA.A_Star_cout(m,fantome.position,p.position);
				if( cout < cout_min ) {
					cout_min = cout;
					p_min = p;
				}
			}
			//p_min : pacman le plus pres
			
			int voisin_cout = 0;
			int voisin_cout_max = 0;		//INFINI
			PositionAgent voisin_max = null;
			
			for (PositionAgent v : fantome.position.getVoisins(m)) {
				voisin_cout = IA.A_Star_cout(m,v,p_min.position);
				if( voisin_cout > voisin_cout_max) {
					voisin_cout_max = voisin_cout;
					voisin_max = v;
					
				}
			}
			//voisin_max : case voisine la plus éloignée du pacman
			
			switch (voisin_max.getDir()) {
				case PositionAgent.HAUT: return new AgentAction(AgentAction.EnumAction.haut);
				case PositionAgent.BAS: return new AgentAction(AgentAction.EnumAction.bas);
				case PositionAgent.GAUCHE: return new AgentAction(AgentAction.EnumAction.gauche);
				case PositionAgent.DROITE: return new AgentAction(AgentAction.EnumAction.droite);
	
			}
			
		
		} 
		else 		
			System.out.println("Liste de pacmans vides");
		return new AgentAction(AgentAction.EnumAction.haut);
		
	}

	
	

}
