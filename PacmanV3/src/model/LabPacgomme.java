package model;

public class LabPacgomme extends Pacgomme {

	protected LabPacgomme(Game game) {
		super(game);
	}

	@Override
	public Gomme getPacgomme() {
		return Gomme.CHANGELAB;
	}

	@Override
	public void action() {
		game.AddPoints(1000);
		game.ChangeLab();
		die();
	}

}
