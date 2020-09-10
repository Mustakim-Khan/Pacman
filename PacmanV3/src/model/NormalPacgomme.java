package model;

public class NormalPacgomme extends Pacgomme {

	protected NormalPacgomme(Game game) {
		super(game);
	}

	@Override
	public Gomme getPacgomme() {
		return Gomme.NORMAL;
	}

	@Override
	public void action() {
		game.AddPoints(100);
		die();
	}

}
