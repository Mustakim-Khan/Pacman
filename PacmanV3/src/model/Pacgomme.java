package model;

public abstract class Pacgomme {
	
	public static enum Gomme{NORMAL, BEINVISIBLE, BESUPER, CHANGELAB};
	
	protected Coordinate coordinate;
	protected Game game;
	protected boolean isActif;
	
	protected Pacgomme(Game game) {
		this.game = game;
		int size = game.getSizeLab();
		int x = (int) (size*Math.random());
		int y = (int) (size*Math.random());
		while(game.isMur(new Coordinate(x,y))) {
			x = (int) (size*Math.random());
			y = (int) (size*Math.random());
		}
		this.coordinate = new Coordinate(x,y);
		this.isActif = true;
	}
	
	protected void die() {
		this.isActif = false;
	}
	public boolean isActif() {
		return this.isActif;
	}
	public Coordinate getCoordinate() {
		return this.coordinate;
	}
	protected void setCoordinate(Coordinate c) {
		this.coordinate = c;
	}
	public abstract Gomme getPacgomme();
	public abstract void action();

}
