package com.pacman.agent;

public class AgentAction {

	public enum EnumAction {haut, bas, droite, gauche, vide};

	EnumAction action;

	
	
	public AgentAction( EnumAction a) {
		action = a; 
		
	}
	
	public String getAction() {
		
		return action.name(); 
	}
	
	void setAction(EnumAction a) {
		
		this.action = a; 
	}
	
}
