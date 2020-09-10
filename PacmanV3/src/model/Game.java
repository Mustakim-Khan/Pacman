package model;

import java.util.ArrayList;
import java.util.List;


public class Game {
	
	private final static int NBGHOST = 4;
	private final static int NBINVISIBLEPACGOMME =2;
	private final static int NBSUPERPACGOMME =2;
	private final static int NBLABPACGOMME =2;
	
	private final static int VIESUP = 5000;
	private int height;
	private int width;
	private int points;
	private Pacman pacman;
	private Ghost[] ghosts;
	private Labyrinthe labyrinthe;
	private List<Pacgomme> pacgomme;
	
	public Game(int width, int height) {
		this.height = height;
		this.width = width;
		this.points = 0;
		this.labyrinthe = new Labyrinthe();
		this.pacgomme = new ArrayList<Pacgomme>();
		addPacgomme();
		this.pacman = new Pacman(this);
		ghosts = new Ghost [NBGHOST];
		for(int i =0; i<NBGHOST; i++) ghosts[i] = new Ghost(this);
	}
	
	private void addPacgomme() {
		for(int i =0; i<NBINVISIBLEPACGOMME; i++) pacgomme.add(new InvisiblePacgomme(this));
		for(int i =0; i<NBSUPERPACGOMME; i++) pacgomme.add(new SuperPacgomme(this));
		for(int i = 0; i<NBLABPACGOMME; i++)pacgomme.add(new LabPacgomme(this));
		for(int i =0; i<getSizeLab(); i++) {
			for(int j = 0; j<getSizeLab(); j++) {
				Coordinate c = new Coordinate(i,j);
				if(!isMur(c) && !isPacgomme(c)) {
					Pacgomme p = new NormalPacgomme(this);
					p.setCoordinate(c);
					pacgomme.add(p);
				}
			}
		}
	}
	
	private boolean isPacgomme(Coordinate c) {
		for (Pacgomme p : pacgomme) {
			if(c.equals(p.getCoordinate())) return true;
		}
		return false;
	}
	
	 void resetState() {
		pacman.changeState(new NormalPacmanState(pacman));
		for(int i =0; i<NBGHOST; i++)ghosts[i].setState(new NormalGhostState(ghosts[i]));
	}
	
	public List<Pacgomme> getPacgomme() {
		return this.pacgomme;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public Pacman getPacman() {
		return pacman;
	}
	public int getPoints() {
		return points;
	}
	public int nbGhost() {
		return NBGHOST;
	}
	public Ghost getGhost(int i) {
		return ghosts[i];
	}
	
	public int getSizeLab() {
		return labyrinthe.getSize();
	}
	
	public boolean isMur(Coordinate c) {
		return labyrinthe.isMur(c);
	}
	
	void AddPoints(int points) {
		if(this.points<VIESUP && this.points+points>= VIESUP) pacman.gagnerVie();
		this.points = this.points+points;
	}
	void ChangeLab() {
		labyrinthe.labChange();
		ChangePacgomme();
	}
	private void ChangePacgomme() {
		for(Pacgomme p: pacgomme) {
			int x = p.getCoordinate().getX();
			int y = p.getCoordinate().getY();
			if(LabPlein()) p.die();
			else if(p.isActif() && labyrinthe.isMur(p.getCoordinate())) {
				while(labyrinthe.isMur(new Coordinate(x,y)) || isPacgomme(new Coordinate(x,y))) {
					x = (int) (Math.random()*getSizeLab());
					y = (int) (Math.random()*getSizeLab());
				}
				p.setCoordinate(new Coordinate(x,y));
			} 
		}
	}
	private boolean LabPlein() {
		for(int i =0; i<getSizeLab(); i++) {
			for(int j =0; j<getSizeLab(); j++) {
				Coordinate test = new Coordinate(i, j);
				if(!labyrinthe.isMur(test) && !isPacgomme(test)) return false;
			}
		}
		return true;
	}
	void SuperPacman() {
		pacman.changeState(new SuperPacmanState(pacman));
		for (Ghost ghost : ghosts) {
			ghost.setState(new WeakGhostState(ghost));
		}
	}
	void Invisible() {
		for(int i =0; i<NBGHOST; i++)ghosts[i].setState(new NormalGhostState(ghosts[i]));
		pacman.changeState(new InvisiblePacmanState(pacman));
	}

	 private void ghostMove() {
		 for(int i = 0; i<NBGHOST; i++) ghosts[i].move();
	 }
	 
	 private void GhostReaction() {
		 for (Ghost ghost : ghosts) {
			if(ghost.getBody().equals(pacman.getBody())) pacman.action(ghost);
			if(ghost.movement().equals(pacman.getBody()) && pacman.movement().equals(ghost.getBody())) pacman.action(ghost);
		}
	 }
	 
	 private void PacgommeReaction() {
		 for (Pacgomme p : pacgomme) {
				if(pacman.getBody().equals(p.getCoordinate()) && p.isActif()) p.action();
			}
	 }
	 
	public boolean Victoire() {
			for (Pacgomme p : pacgomme) {
				if(p.isActif() && !isMur(p.getCoordinate())) return false;
			}
			pacman.kill();
			return true;
		}
	
	public boolean Defaite() {
		return !pacman.isAlive();
	}
	 
	 public void step() {
		 GhostReaction();
		 PacgommeReaction();
		 pacman.move();
		 ghostMove();
	 }
	
}
