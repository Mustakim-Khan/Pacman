package model;

public class InvisiblePacgomme extends Pacgomme {

	protected InvisiblePacgomme(Game game) {
		super(game);
	}


	@Override
	public Gomme getPacgomme() {
		return Gomme.BEINVISIBLE;
	}

	@Override
	public void action() {
		game.AddPoints(300);
		game.Invisible();
		die();
	}

}
