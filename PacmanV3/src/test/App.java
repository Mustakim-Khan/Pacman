package test;

import gui.Gui;
import model.Game;
import model.PacmanObserver;

public class App {

	private static final int SPEED = 100;

	public static void main(String[] args) {
		Game game = new Game(700, 500);
		PacmanObserver gui = new Gui(game);
		game.getPacman().register(gui);
		while (game.getPacman().isAlive()) {
			game.step();
			try {
				Thread.sleep(SPEED);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		game.getPacman().unregister(gui);
	}
}
