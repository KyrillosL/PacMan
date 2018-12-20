package com.pacman.vue;
import com.pacman.*;
import com.pacman.controleur.ControleurGame;
import com.pacman.modele.Game;

import event.KeyEvent;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class View implements Observer {
	
	public JButton restart; 
	public JButton pause; 
	protected JButton step;
	public JButton run; 
	protected JFrame fenetreJeu;
	PanelPacmanGame ppg;
	Game game; 
	JTextArea tour;
	JCheckBox bCom;
	JCheckBox bPlayer;
	JComboBox listeTerrains;
	
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
		
		
				
		

		JPanel sliderAndLabel = new JPanel();
				
				
		JPanel bottom = new JPanel();
		JSlider tourParSeconde = new JSlider(0, 0, 50, 2);
		tourParSeconde.setMajorTickSpacing(10);
		tourParSeconde.setMinorTickSpacing(1);
		tourParSeconde.setPaintTicks(true);
		tourParSeconde.setSnapToTicks(true);
		tourParSeconde.setPaintLabels(true);
		
		JLabel vitesse = new JLabel("Vitesse du jeu", SwingConstants.CENTER);
		
		Color c = bottom.getBackground();
		
		tour = new JTextArea("Tour"); 
		tour.setBackground(c);
		
		
		

		
		
		JTextArea player = new JTextArea("Mode de Jeu");
		player.setBackground(c);
		
		
		JPanel gameMode = new JPanel();
		ButtonGroup bGameMode = new ButtonGroup();
		
		bCom = new JCheckBox("Com");
		bPlayer = new JCheckBox("Player");
		bCom.setSelected(true);
		 

		bGameMode.add(bCom);
		bGameMode.add(bPlayer);
		
		gameMode.setLayout(new GridLayout(3,1));
		

		
		gameMode.add(player);
		
		gameMode.add(bCom);
		gameMode.add(bPlayer);
		bottom.add(gameMode);
		
		
		String[] petStrings = { "capsuleClassic", "bigCorners"};
		
		
		listeTerrains = new JComboBox(petStrings);
		listeTerrains.setSelectedIndex(0);
		//listeTerrains.addActionListener(this);
		bottom.add(listeTerrains);
		
		
		
		
		sliderAndLabel.add(tour);
		sliderAndLabel.add(tourParSeconde); 
		sliderAndLabel.add(vitesse);
		sliderAndLabel.setLayout(new GridLayout(3,1));
		
		bottom.add(sliderAndLabel);

		bottom.setLayout(new GridLayout(1,1));

		

		
		
		GridLayout all = new GridLayout(2,1);
		commande.add(panelBoutons);
		commande.add(bottom);
		commande.setLayout(all);		
        commande.setVisible(true);
        
        
        
        
	    fenetreJeu = new JFrame();
		KeyEvent ke = new KeyEvent(game); 
		fenetreJeu.addKeyListener(ke);

		
	    
	    fenetreJeu.setTitle("JEU");
	    fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //fenetreJeu.setFocusable(true);
	    //fenetreJeu.requestFocus();
        
        //ke.addWindowListener(commande.getWindowStateListeners());
	    
	    
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
	    
	    bCom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.setGameMode("com");
				//System.out.println("STEP");
				
			}
		});
	    
	    bPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controleur.setGameMode("player");
				//System.out.println("STEP");
				
			}
		});

	    tourParSeconde.addChangeListener(new ChangeListener(){
	          public void stateChanged(ChangeEvent e) {
	        	  try {
	        		  controleur.setTourParSeconde(1000/tourParSeconde.getValue()) ;
	        	  }
	        	  catch(Exception exception) {
	        		  
	        	  }
	          }
		});
	    
     listeTerrains.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(listeTerrains.getSelectedItem().toString());
				controleur.setMap(listeTerrains.getSelectedItem().toString()+".lay");
				//System.out.println("STEP");
				
				fenetreJeu.remove(ppg);

				controleur.init();
				ppg = game.getPpg(); 
				System.out.println(ppg.toString());
				fenetreJeu.setSize(game.getMaze().getSizeX()*50, game.getMaze().getSizeY()*50);
		        fenetreJeu.add(ppg);
		        fenetreJeu.setVisible(true);
				
			}
		});
        
	    
		
	
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
		tour.setText("Tour "+game.getNbTour());
		fenetreJeu.repaint();
	}
	


}
