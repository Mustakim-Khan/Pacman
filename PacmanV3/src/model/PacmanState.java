package model;


public abstract class PacmanState {
	
	public static enum State {NORMAL, SUPERPACMAN, INVISIBLE};

	protected Pacman pacman;

	protected PacmanState(Pacman pacman) {
		this.pacman = pacman;
	}
	
	abstract State getState();

	public abstract void action(Ghost ghost);
	public abstract void move(Game game);

}
