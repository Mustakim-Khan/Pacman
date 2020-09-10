package model;

public class NormalGhostState extends GhostState {

	protected NormalGhostState(Ghost ghost) {
		super(ghost);
	}

	@Override
	StateGhost getState() {
		return StateGhost.NORMAL;
	}

	@Override
	public void move(Coordinate next) {
		ghost.setBody(next);
	}

}
