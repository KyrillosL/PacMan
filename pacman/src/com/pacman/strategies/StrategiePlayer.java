package com.pacman.strategies;

import com.pacman.agent.Agent;
import com.pacman.agent.AgentAction;
import com.pacman.agent.PacmanAgent;
import com.pacman.agent.AgentAction.EnumAction;
import com.pacman.vue.Maze;

public class StrategiePlayer implements Strategie {

	EnumAction ea; 
	
	public StrategiePlayer() {
		this.ea = null; 
	}
	
	public void setEnumAction(EnumAction ea) {
		this.ea = ea; 
	}
	
	@Override
	public AgentAction getAction(Agent a, Maze m) {

		AgentAction aa = new AgentAction(ea );
		return aa;

	}

} 
