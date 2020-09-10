package model;

public class WeakGhostState extends GhostState {
	
	private int compteur;

	protected WeakGhostState(Ghost ghost) {
		super(ghost);
		
	}

	@Override
	StateGhost getState() {
		return StateGhost.WEAK;
	}


	@Override
	public void move(Coordinate next) {
		compteur++;
		if(compteur%2 ==0)return;
		else ghost.setBody(next);
	}

}
