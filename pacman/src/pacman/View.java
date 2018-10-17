package pacman;
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
	
	protected JButton restart; 
	protected JButton pause; 
	protected JButton step;
	protected JButton run; 
	public View(ControleurGame controleur ) throws Exception{
		
		
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
        
        
        
        
	    JFrame fenetreJeu = new JFrame();
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
        Maze maze = new Maze("layouts/bigMaze.lay");
        PanelPacmanGame ppg = new PanelPacmanGame(maze); 
        
        fenetreJeu.add(ppg);
        
        

        
	    run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.launch();
				
			}
		});
	    restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.init();
				
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
				
			}
		});

	    
	    

        
        
	    
		
	
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
	


}
