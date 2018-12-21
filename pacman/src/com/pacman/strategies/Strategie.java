package com.pacman.strategies;

import com.pacman.agent.Agent;
import com.pacman.agent.AgentAction;
import com.pacman.vue.Maze;

public interface Strategie {
	
	AgentAction getAction(Agent a, Maze m);
	
}
