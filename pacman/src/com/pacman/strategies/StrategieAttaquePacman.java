package com.pacman.strategies;

import java.util.ArrayList;
import java.util.Map;

import com.pacman.agent.Agent;
import com.pacman.agent.AgentAction;
import com.pacman.agent.FantomeAgent;
import com.pacman.agent.PacmanAgent;
import com.pacman.agent.PositionAgent;
import com.pacman.vue.Maze;

import ia.IA;

public class StrategieAttaquePacman implements Strategie{
	private Agent pacman;
	private ArrayList<Agent> fantomes;
	
	public StrategieAttaquePacman(Agent pacman, ArrayList<Agent> fantomes) {
		this.fantomes = fantomes;
		this.pacman = pacman;
	}
	

	@Override
	public AgentAction getAction(Agent a, Maze m) {
		int cout = 0;
		int cout_min = 100000000; 		//INFINI
		Agent f_min = null;
		Map<PositionAgent, PositionAgent> chemins;
		PositionAgent temp;
		
		if( ! fantomes.isEmpty()) {
			
		for (Agent f : fantomes) {
			cout = IA.A_Star_cout(m,pacman.position,f.position);
			if( cout < cout_min ) {
				cout_min = cout;
				f_min = f;
			}
		}
		chemins = IA.A_Star_chemin(m, pacman.position, f_min.position);
		temp = f_min.position;


		try {
			while (! chemins.get(temp).coordonneesEgales(pacman.position)) {			//tant que qu'on arrive pas au depart, on retrace le chemin
				temp = chemins.get(temp);
			}
		}catch(Exception e) {
			
		}
		//temp est maintenant la première case où aller
		
		
		switch (temp.getDir()) {
			case PositionAgent.HAUT: return new AgentAction(AgentAction.EnumAction.haut);
			case PositionAgent.BAS: return new AgentAction(AgentAction.EnumAction.bas);
			case PositionAgent.GAUCHE: return new AgentAction(AgentAction.EnumAction.gauche);
			case PositionAgent.DROITE: return new AgentAction(AgentAction.EnumAction.droite);

		}
		
		
		} else 		System.out.println("Liste de fantômes vides");

		
		
		return new AgentAction(AgentAction.EnumAction.gauche);
	}
		

}
