package com.pacman.vue;
import com.pacman.*;
import com.pacman.controleur.ControleurGame;
import com.pacman.modele.Game;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JButton;


public class View implements Observer {
	
	public JButton restart; 
	public JButton pause; 
	protected JButton step;
	public JButton run; 
	protected JFrame fenetreJeu;
	PanelPacmanGame ppg;
	Game game; 
	
	public View(ControleurGame controleur, Game g ) throws Exception{
		
		game = g; 
		
		JFrame commande = new JFrame();
		commande.setTitle("Commande");
		commande.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		commande.setSize(new Dimension(700, 300));
        
        Dimension windowSize = commande.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2 ;
        int dy = centerPoint.y - windowSize.height / 2 - 300; 
        commande.setLocation(dx, dy);
        

        
		
		GridLayout boutons = new GridLayout(1,4);
		JPanel panelBoutons = new JPanel();

		Icon icon_restart = new ImageIcon("Icones/icon_restart.png");
		restart = new JButton(icon_restart); 
		panelBoutons.add(restart); 
		
		Icon icon_run = new ImageIcon("Icones/icon_run.png");
		run = new JButton(icon_run); 
		panelBoutons.add(run); 
		
		Icon icon_step = new ImageIcon("Icones/icon_step.png");
		step = new JButton(icon_step); 
		panelBoutons.add(step); 
		
		Icon icon_pause = new ImageIcon("Icones/icon_pause.png");
		pause = new JButton(icon_pause); 
		panelBoutons.add(pause); 
		
		panelBoutons.setLayout(boutons);
		
		
				
		
		GridLayout sliderAndText = new GridLayout(1,2);
		JPanel panelSliderAndText = new JPanel();
		JSlider tourParSeconde = new JSlider(0, 1, 10, 5);
		JTextArea tour = new JTextArea("Tour"); 
		panelSliderAndText.add(tourParSeconde);
		panelSliderAndText.add(tour);
		panelSliderAndText.setLayout(sliderAndText);
		
		
		
		
		
		GridLayout all = new GridLayout(2,1);
		commande.add(panelBoutons);
		commande.add(panelSliderAndText);
		commande.setLayout(all);		
        commande.setVisible(true);
        
        
        
        
	    fenetreJeu = new JFrame();
	    fenetreJeu.setTitle("JEU");
	    fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    
	    fenetreJeu.setSize(new Dimension(1000, 1000));
        
	    
	    
        Dimension windowSizeFenetreJeu = fenetreJeu.getSize();
        GraphicsEnvironment geFJ = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPointFJ = geFJ.getCenterPoint();
        int dxFJ = centerPointFJ.x - windowSizeFenetreJeu.width / 2 ;
        int dyFJ = centerPointFJ.y - windowSizeFenetreJeu.height / 2+100 ; 
        fenetreJeu.setLocation(dxFJ, dyFJ);
        fenetreJeu.setVisible(true);
        
        
        
/*
        ppg = new PanelPacmanGame(game.maze); 
		
		fenetreJeu.setSize(  game.maze.getSizeX()*50,game.maze.getSizeY()*50);
		//fenetreJeu.setBounds(0,0, m.getSizeX(),m.getSizeY());
		
        fenetreJeu.add(ppg);
        
        System.out.println("size X: "+ fenetreJeu.getWidth() +", size Y : "+fenetreJeu.getHeight());
        
*/
        
		ppg = game.getPpg(); 

		fenetreJeu.setSize(game.getMaze().getSizeX()*50, game.getMaze().getSizeY()*50);

		
        fenetreJeu.add(ppg);
        

        
	    run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.launch();
				
			}
		});
	    restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//ppg = null; 
				fenetreJeu.remove(ppg);

				controleur.init();
				ppg = game.getPpg(); 
				System.out.println(ppg.toString());
				fenetreJeu.setSize(game.getMaze().getSizeX()*50, game.getMaze().getSizeY()*50);
		        fenetreJeu.add(ppg);
		        fenetreJeu.setVisible(true);
		        
				
			}
		});
	    pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.stop();
				
			}
		});
	    step.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.step();
				//System.out.println("STEP");
				
			}
		});

	  
	   // game.setPositionPacmans(ppg.getPacmans_pos());
	    
	    
	    

        
	    
		
	
	}

	public void setPanel(PanelPacmanGame p ) {
		
		/*
		ppg = p; 
		
		
		//ppg = new PanelPacmanGame(m); 
		//System.out.println("size X: "+ m.getSizeX()+", size Y : "+m.getSizeY());
		fenetreJeu.setSize(  ppg.getWidth()*50,ppg.getHeight()*50);
		//fenetreJeu.setBounds(0,0, m.getSizeX(),m.getSizeY());
		
        fenetreJeu.add(ppg);
        
        System.out.println("size X: "+ fenetreJeu.getWidth() +", size Y : "+fenetreJeu.getHeight());
        */
        
       
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		//System.out.println("+++++++++++++UPDATE+++++++++++++++");
			
		fenetreJeu.repaint();
	}
	


}
