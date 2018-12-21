package com.pacman.modele;

import java.util.ArrayList;
import java.util.Map;

import com.pacman.agent.Agent;
import com.pacman.agent.AgentAction;
import com.pacman.agent.FantomeAgent;
import com.pacman.agent.PacmanAgent;
import com.pacman.agent.PositionAgent;
import com.pacman.vue.Maze;

import ia.IA;

public class StrategieAttaqueFantome implements Strategie{

	private Agent fantome;
	private ArrayList<Agent> pacmans;
	
	public StrategieAttaqueFantome(Agent fantome, ArrayList<Agent> pacmans) {
		this.fantome = fantome;
		this.pacmans = pacmans;
	}
	
	@Override
	public AgentAction getAction(Agent a, Maze m) {

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

		chemins = IA.A_Star_chemin(m, fantome.position, p_min.position);
		temp = p_min.position;
		

		try {
		while ( ! chemins.get(temp).coordonneesEgales(fantome.position)) {		//tant que qu'on arrive pas au depart, on retrace le chemin
			temp = chemins.get(temp);

		}
		}catch (Exception e) {
			
		}
		
		//temp est maintenant la première case où aller
		
		
		switch (temp.getDir()) {
			case PositionAgent.HAUT: return new AgentAction(AgentAction.EnumAction.haut);
			case PositionAgent.BAS: return new AgentAction(AgentAction.EnumAction.bas);
			case PositionAgent.GAUCHE: return new AgentAction(AgentAction.EnumAction.gauche);
			case PositionAgent.DROITE: return new AgentAction(AgentAction.EnumAction.droite);

		}
		
		
		} else 		System.out.println("Liste de pacmans vides");

		
		
		return new AgentAction(AgentAction.EnumAction.haut);
		
	}
		

}
