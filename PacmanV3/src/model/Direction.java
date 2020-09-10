package model;

import java.util.Random;

public enum Direction {
	Null(0,0),Up(0, -1), Down(0, 1), Left(-1, 0), Right(1, 0);

	private int x;
	private int y;

	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
	    return x;
	}

	public int getY() {
	    return y;
	}	
	
	public Direction randomDirection()
	{
		Direction d = Direction.Null;
		Random rand = new Random();
		int n;
		while(d == Direction.Null || d ==this) {
			n = rand.nextInt(5)%4;
			switch (n) {
			case 0:
				d = Direction.Up;
				break;
			case 1:
				d = Direction.Down;
				break;
			case 2:
				d = Direction.Left;
				break;
			case 3:
				d = Direction.Right;
				break;
			}
		}
		return d;
	}
}
