package model;

public class InvisiblePacmanState extends PacmanState {
	
	private static final int TMPINVISIBLE = 30;
	private int timer;

	protected InvisiblePacmanState(Pacman pacman) {
		super(pacman);
		timer = TMPINVISIBLE;
	}

	@Override
	State getState() {
		return State.INVISIBLE;
	}

	@Override
	public void action(Ghost ghost) {
	}

	@Override
	public void move(Game game) {
		if(timer ==0) game.resetState();
		else timer--;
		if(!pacman.sortirMur()) return;
		Coordinate next = pacman.movement();
		if (!game.isMur(next)) pacman.notifyMove(next);
		else pacman.notifyMove(pacman.getBody());
	}

}
