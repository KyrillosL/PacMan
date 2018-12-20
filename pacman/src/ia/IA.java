package ia;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.Element;

import com.pacman.agent.PositionAgent;
import com.pacman.vue.Maze;


public class IA {
	private final static int DISTANCE_ENTRE_CASES=1;

	
	public static int heuristic(PositionAgent noeud, PositionAgent but) {
		    int dx = Math.abs(noeud.getX() - but.getX());
		    int dy = Math.abs(noeud.getY() - but.getY());
		    return (dx + dy);
	}
	public static boolean contient(ArrayList<PositionAgent> set,PositionAgent pos) {
		for( PositionAgent element : set) {
			if (element.coordonneesEgales(pos))
				return true;
		}
		return false;
	}
	
	public static int A_Star(Maze maze,PositionAgent depart,PositionAgent but) {
	    // The set of nodes already evaluated
	    ArrayList<PositionAgent> closedSet = new ArrayList<PositionAgent>();
	
	    // The set of currently discovered nodes that are not evaluated yet.
	    // Initially, only the start node is known.
	    ArrayList<PositionAgent> openSet = new ArrayList<PositionAgent>();
	    openSet.add(depart);
	    
	
	    // For each node, which node it can most efficiently be reached from.
	    // If a node can be reached from many nodes, cameFrom will eventually contain the
	    // most efficient previous step.
	    Map<PositionAgent, PositionAgent> cameFrom = new HashMap<PositionAgent, PositionAgent>();
	    
	
	    // For each node, the cost of getting from the start node to that node.
	    Map<PositionAgent, Integer> gScore = new HashMap<PositionAgent, Integer>();
	
	    //gScore := map with default value of Infinity
	    
	
	    // The cost of going from start to start is zero.
	    gScore.put(depart,0);
	
	    // For each node, the total cost of getting from the start node to the goal
	    // by passing by that node. That value is partly known, partly heuristic.
	    Map<PositionAgent, Integer> fScore = new HashMap<PositionAgent, Integer>();
	
	    //fScore := map with default value of Infinity
	
	    // For the first node, that value is completely heuristic.
	    fScore.put(depart, heuristic(depart, but));


	
	    while (!openSet.isEmpty()) {					//Tant que openSet n'est pas vide
	    	
	    	PositionAgent posMin=null;
	    	int fscoreMin = 1000000000;					//valeur infinie
	    	int positionDansOpenset=-1;
	    	
	    	for(PositionAgent pos: openSet) {
	    		if(fScore.get(pos)<fscoreMin) {
	    			posMin=pos;
	    			fscoreMin = fScore.get(pos);

	    			positionDansOpenset=openSet.indexOf(pos);

	    		}
	    			
	    	}
	    	//PositionAgent posCourante = posMin;			//besoin de faire d'autres variables ?
	    	//fScore.put(posCourante,fscoreMin);			//PositionAgent traités par référence ?
	    	
	    	
	        //current := the node in openSet having the lowest fScore[] value
	    	if(( posMin !=null ) && ( positionDansOpenset != -1 ) && ( posMin.coordonneesEgales(but) ) )
	    		return fscoreMin;
	       // if current = goal
	        	//retourne le cout du chemin le plus court
	           // return fScore[current];
	
	        openSet.remove(positionDansOpenset);
	        closedSet.add(posMin);
			System.out.println("ERREUR ICI");

	        ArrayList<PositionAgent> voisins =posMin.getVoisins(maze);

	        int gscoreTentative;
	        
	        for(PositionAgent voisin : voisins) {

	        	if(contient(closedSet,voisin))
	        		continue;
	        	
	        	gscoreTentative= gScore.get(posMin) + DISTANCE_ENTRE_CASES;		
	        	
	        	if(! contient(openSet,voisin))
	        		openSet.add(voisin);		// ajout dans positions découvertes à évaluer
	        	
	        	cameFrom.put(voisin, posMin);
	        	gScore.put(voisin,gscoreTentative);
	        	fScore.put(voisin, gscoreTentative + heuristic(voisin, but));	
	        }
	        
	    }

		return -1;
	}
	   public static void main(String[] args) {

    	Maze maze=null;
		try {
			maze = new Maze("layouts/testMaze.lay");
		} catch (Exception e) {

			e.printStackTrace();
		}

		PositionAgent depart= new PositionAgent(1, 1, 1);
		PositionAgent but= new PositionAgent(1, 2, 1);
		System.out.println("TEST IA: " +IA.A_Star(maze, depart, but));


	}
	
}

