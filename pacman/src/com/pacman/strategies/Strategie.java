package com.pacman.strategies;

import java.util.ArrayList;

import com.pacman.agent.Agent;
import com.pacman.agent.AgentAction;
import com.pacman.vue.Maze;

public interface Strategie {
	
	AgentAction getAction(Agent a, Maze m,  ArrayList<Agent> ala);
	
}
