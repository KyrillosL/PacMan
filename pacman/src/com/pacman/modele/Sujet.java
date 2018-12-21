package com.pacman.modele;
import com.pacman.*;
import com.pacman.vue.Observer;
public interface Sujet {
	
	public void registerObserver(Observer obs );
	public void removeObserver(Observer obs);
	public void notifyObserver(); 

}
