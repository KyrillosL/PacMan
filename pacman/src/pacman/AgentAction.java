package pacman;

public class AgentAction {

	enum EnumAction {haut, bas, droite, gauche};

	EnumAction action;

	
	
	public AgentAction( EnumAction a) {
		action = a; 
		
	}
	
	String getAction() {
		
		return action.name(); 
	}
	
	void setAction(EnumAction a) {
		
		this.action = a; 
	}
	
}
