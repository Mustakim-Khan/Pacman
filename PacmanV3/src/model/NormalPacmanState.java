package model;

public class NormalPacmanState extends PacmanState{

	protected NormalPacmanState(Pacman pacman) {
		super(pacman);
	}

	@Override
	State getState() {
		return State.NORMAL;
	}

	@Override
	public void action(Ghost ghost) {
		pacman.perdreVie();
	}

	@Override
	public void move(Game game) {
		if(!pacman.sortirMur()) return;
		Coordinate next = pacman.movement();
		if (!game.isMur(next)) pacman.notifyMove(next);
		else pacman.notifyMove(pacman.getBody());
	}

}
