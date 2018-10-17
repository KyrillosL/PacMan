package pacman;

public interface Sujet {
	
	public void registerObserver(Observer obs );
	public void removeObserver(Observer obs);
	public void notifyObserver(); 

}
