package model;


public abstract class GhostState {
	
	public static enum StateGhost {NORMAL, WEAK, DEAD};

	protected Ghost ghost;

	protected GhostState(Ghost ghost) {
		this.ghost = ghost;
	}
	
	abstract StateGhost getState();

	public abstract void move(Coordinate next);

}
