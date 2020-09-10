package model;

public class SuperPacmanState extends PacmanState {
	
	private static final int TMPSUPER = 30;
	private int timer;

	protected SuperPacmanState(Pacman pacman) {
		super(pacman);
		timer = TMPSUPER;
	}

	@Override
	State getState() {
		return State.SUPERPACMAN;
	}

	@Override
	public void action(Ghost ghost) {
		ghost.setState(new DeadGhostState(ghost));
//		ghost.reset();
	}

	@Override
	public void move(Game game) {
		if(timer ==0)game.resetState();
		else timer--;
		if(!pacman.sortirMur()) return;
		Coordinate next = pacman.movement();
		if (!game.isMur(next)) pacman.notifyMove(next);
		else pacman.notifyMove(pacman.getBody());
	}

}
