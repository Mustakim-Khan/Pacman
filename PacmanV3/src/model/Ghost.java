package model;

import model.GhostState.StateGhost;

public class Ghost {
	
	private Coordinate INITGHOST;
	private Direction dir;
	private Coordinate body;
	private Game game;
	private GhostState state;
	
	public Ghost(Game game)
	{
		INITGHOST = new Coordinate((game.getSizeLab()-1)/2,(game.getSizeLab()-1)/2);
		this.game = game;
		state = new NormalGhostState(this);
		this.dir = Direction.Null;
		this.dir = dir.randomDirection();
		body = INITGHOST;
	}

	public int getX() {
		return body.getX();
	}
	public int getY() {
		return body.getY();
	}
	Coordinate getReset() {
		return INITGHOST;
	}
	boolean isReset() {
		return body.equals(INITGHOST);
	}

	public void move() {
		if(game.isMur(body)) body = INITGHOST;
		state.move(movement());
	}
	
	Coordinate movement() {
		int x = body.getX()+dir.getX();
		int y = body.getY()+dir.getY();
		int size = game.getSizeLab();
		while(x == -1 || x ==size || y == -1 || y ==size || game.isMur(new Coordinate(x,y))) {
			dir = dir.randomDirection();
			x = body.getX()+dir.getX();
			y = body.getY()+dir.getY();
		}
		return new Coordinate(x,y);
	}
	
	Direction getDirection() {
		return dir;
	}
	
	void setDirection(Direction direction) {
		this.dir = direction;
	}
	
	Game getGame() {
		return this.game;
	}
	
	public StateGhost getState() {
		return this.state.getState();
	}
	void setState(GhostState state) {
		this.state = state;
	}

	public Coordinate getBody() {
		return body;
	}
	
	void setBody(Coordinate body) {
		this.body = body;
	}

}
