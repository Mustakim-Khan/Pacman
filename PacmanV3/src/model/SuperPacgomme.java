package model;

public class SuperPacgomme extends Pacgomme {

	protected SuperPacgomme(Game game) {
		super(game);
	}

	@Override
	public Gomme getPacgomme() {
		return Gomme.BESUPER;
	}

	@Override
	public void action() {
		game.AddPoints(500);
		game.SuperPacman();
		die();
	}

}
