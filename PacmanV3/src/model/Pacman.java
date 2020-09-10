package model;

import java.util.ArrayList;
import java.util.List;

import model.PacmanEvent.ChangeType;
import model.PacmanState.State;

public class Pacman{
	
	private static final int NBVIES = 3;
	private Coordinate INITPACMAN;
	
	private Coordinate body;
	private Game game;
	private Direction dir;
	private List<PacmanObserver> observers;
	private int vies;
	private PacmanState state;
	
	public Pacman(Game game) {
		INITPACMAN = new Coordinate(0, (game.getSizeLab()-1)/2);
		body = INITPACMAN;
		dir = Direction.Null;
		this.game = game;
		this.observers = new ArrayList<>();
		this.vies = NBVIES;
		this.state = new NormalPacmanState(this);
	}

	public void register(PacmanObserver o) {
		observers.add(o);
	}

	public void unregister(PacmanObserver o) {
		observers.remove(o);
	}
	
	PacmanObserver getObserver(int n)
	{
		return observers.get(n);
	}

	void notifyObserver(List<PacmanEvent> events) {
		for (PacmanObserver pacmanObserver : observers) {
			pacmanObserver.notify(events);
		}
	}
	
	public int getX() {
		return body.getX();
	}
	public int getY() {
		return body.getY();
	}

	public void setDirection(Direction dir) {
		this.dir = dir;
	}	
	public Direction getDirection()
	{
		return dir;
	}
	public State getState() {
		return state.getState();
	}
	
	void changeState(PacmanState state) {
		this.state = state;
	}
	
	public int getVies()
	{
		return vies;
	}
	
	void perdreVie() { 
		vies--; 
		body = INITPACMAN;
		dir = Direction.Null;
	}
	void gagnerVie() { 
		vies++; 
	}

	public Coordinate getBody() {
		return body;
	}
	
	void action(Ghost ghost) {
		state.action(ghost);
	}

	void move() {
		state.move(game);
	}
	
	Coordinate movement() {
		int x = body.getX()+dir.getX();
		int y = body.getY()+dir.getY();
		int size =game.getSizeLab();
		if(x == -1) x = size-1;
		else if(x == size) x = 0;
		else if(y == -1) y = size-1;
		else if(y == size) y = 0;
		return new Coordinate(x,y);
	}
	
	boolean sortirMur() {
		boolean estSorti = true;
		while(game.isMur(body)) {
			body = movement();
			estSorti = false;
		}
		return estSorti;
	}
	
	void notifyMove(Coordinate next) {
		body = next;
		List<PacmanEvent> events = new ArrayList<>();
		events.add(new PacmanEvent(next, ChangeType.ENTER));
		notifyObserver(events);
	}
	
	public boolean isAlive() {
		return this.vies>0;
	}
	
	void kill() {
		vies =0;
	}
}

