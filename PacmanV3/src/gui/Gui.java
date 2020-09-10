package gui;

import java.awt.Color;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Coordinate;
import model.Direction;
import model.Game;
import model.Ghost;
import model.GhostState;
import model.GhostState.StateGhost;
import model.Pacgomme;
import model.Pacgomme.Gomme;
import model.Pacman;
import model.PacmanEvent;
import model.PacmanObserver;
import model.PacmanState;
import model.PacmanState.State;

public class Gui implements PacmanObserver{

	private final static int NORMAL_PACGOMME_SIZE = 3;
	private final static int INVISIBLE_PACGOMME_SIZE = 10;
	private final static int SUPER_PACGOMME_SIZE = 10;
	private final static int CHANGELAB_PACGOMME_SIZE = 10;
	private final static int SCORE_WIDTH = 200;
	
	private Game game;
	private Pacman pacman;
//	private static final int SCALE = 10;
	private JFrame frame = new JFrame("Pacman");
	private static Map<State, Color> colorMapPacman = new HashMap<PacmanState.State, Color>();
	private static Map<StateGhost, Color> colorMapGhost = new HashMap<GhostState.StateGhost, Color>();
	private static Map<Gomme, Color> colorMapPacgomme = new HashMap<Pacgomme.Gomme, Color>();
	private static Map<Gomme, Integer> sizeMapPacgomme = new HashMap<Pacgomme.Gomme, Integer>();
	
	static {
		colorMapPacman.put(State.NORMAL, Color.yellow);
		colorMapPacman.put(State.SUPERPACMAN, Color.orange);
		colorMapPacman.put(State.INVISIBLE, new Color(255,244,141));
	}
	static {
		colorMapGhost.put(StateGhost.NORMAL, new Color(204,0,0));
		colorMapGhost.put(StateGhost.DEAD, Color.blue);
		colorMapGhost.put(StateGhost.WEAK, Color.cyan);
	}
	static {
		colorMapPacgomme.put(Gomme.NORMAL, Color.blue);
		colorMapPacgomme.put(Gomme.BEINVISIBLE, Color.magenta);
		colorMapPacgomme.put(Gomme.BESUPER, Color.orange);
		colorMapPacgomme.put(Gomme.CHANGELAB, Color.green);
	}
	static {
		sizeMapPacgomme.put(Gomme.NORMAL, NORMAL_PACGOMME_SIZE);
		sizeMapPacgomme.put(Gomme.BEINVISIBLE, INVISIBLE_PACGOMME_SIZE);
		sizeMapPacgomme.put(Gomme.BESUPER, SUPER_PACGOMME_SIZE);
		sizeMapPacgomme.put(Gomme.CHANGELAB, CHANGELAB_PACGOMME_SIZE);
	}
	private Color labColor = Color.black;
	private Color roadColor = new Color(0, 0, 153);
	private Color writeColor = Color.white;
	
	private JComponent component = new JComponent() {
		private static final long serialVersionUID = 4237746179597757563L;
		
		@Override
		protected void paintComponent(Graphics g) {	
			super.paintComponent(g);
			int width = getSize().width - SCORE_WIDTH ;
			int height= getSize().height;
			drawLab(g, width, height);
			drawPacgomme(g,width, height);
			drawScore(g,width+SCORE_WIDTH, height);
			drawPacman(g, width, height);
			drawGhosts(g,width, height);
			if(game.Defaite())
			{
				JOptionPane.showMessageDialog(frame, "Vous avez perdu...");
				System.exit(0);
			}
			if(game.Victoire()) {
				JOptionPane.showMessageDialog(frame, "Victoire avec "+game.getPoints()+"points !");
				System.exit(0);
			}
				
		}
		
		private void drawLab(Graphics g, int width, int height) {
			g.fillRect(0, 0, width, height);
			g.setColor(labColor);
			g.fillRect(width+1, 0, width+SCORE_WIDTH, height);
			for(int i = 0; i<game.getSizeLab(); i++) {
				for(int j =0; j<game.getSizeLab(); j++) {
					if(game.isMur(new Coordinate(i,j))) {
						g.setColor(Color.BLACK);
						g.fillRect(i*width/game.getSizeLab(), j*height/game.getSizeLab(), width/game.getSizeLab()+1, height/game.getSizeLab()+1);
					}else {
						g.setColor(roadColor);
						g.fillRect(i*width/game.getSizeLab(), j*height/game.getSizeLab(), width/game.getSizeLab()+1, height/game.getSizeLab()+1);
					}
				}
			}
		}
		

		private void drawPacgomme(Graphics g, int width, int height) {
			int i; 
			int j;
			int size = game.getSizeLab();
			for(Pacgomme p: game.getPacgomme()) {
				if(p.isActif()) {
					g.setColor(colorMapPacgomme.get(p.getPacgomme()));
					i = p.getCoordinate().getX();
					j = p.getCoordinate().getY();
					g.fillOval(i*width/size+width/(2*2*size), j*height/size+height/(2*2*size), sizeMapPacgomme.get(p.getPacgomme()), sizeMapPacgomme.get(p.getPacgomme()));
				}
			}
		}
		
		private void drawScore(Graphics g, int width, int height) {
			g.setColor(writeColor);
		    g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		    //score
			g.drawString("Vies :", width-195, 20);
			for (int i = 0; i < pacman.getVies(); i++) {
				g.setColor(Color.yellow);
				g.fillOval(width-195+i*20, 30, 20, 20);
			}
			//points
			g.setColor(writeColor);
			g.drawString("Points :", width-195, 60);
			g.drawString(String.valueOf(game.getPoints()), width-195, 80);
		}
		
		private void drawPacman(Graphics g, int width, int height) {
			int i = pacman.getX();
			int j = pacman.getY();
			g.setColor(colorMapPacman.get(pacman.getState()));
			g.fillOval(i*width/game.getSizeLab(), j*height/game.getSizeLab(), width/game.getSizeLab(), height/game.getSizeLab());	
		}
		
		private void drawGhosts(Graphics g, int width, int height)
		{
			for(int i =0; i<game.nbGhost(); i++) {
				drawFantome(game.getGhost(i), g,width, height);
			}
		}
		
		private void drawFantome(Ghost f, Graphics g, int width, int height)
		{
			int i = f.getX();
			int j = f.getY();
			g.setColor(colorMapGhost.get(f.getState()));
			g.fillOval(i*width/game.getSizeLab(), j*height/game.getSizeLab(), width/game.getSizeLab(), height/game.getSizeLab());
		}
	
	};
	
	public Gui(Game game) {
		this.game = game;
		this.pacman = game.getPacman();
		frame.setContentPane(component);
//		frame.setSize(game.getWidth() * SCALE, game.getHeight() * SCALE);
		frame.setSize(game.getWidth(), game.getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					pacman.setDirection(Direction.Left);
					break;
				case KeyEvent.VK_RIGHT:
					pacman.setDirection(Direction.Right);
					break;
				case KeyEvent.VK_UP:
					pacman.setDirection(Direction.Up);
					break;
				case KeyEvent.VK_DOWN:
					pacman.setDirection(Direction.Down);
					break;
				default:
					break;
				}

			}
		});
	}
	
	@Override
	public void notify(List<PacmanEvent> events) {
		component.repaint();
	}
	
	@Override
	public JComponent getComponent()
	{
		return component;
	}
}


