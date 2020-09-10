package model;

import java.util.ArrayList;
import java.util.List;

public class DeadGhostState extends GhostState {

	protected DeadGhostState(Ghost ghost) {
		super(ghost);
	}

	@Override
	StateGhost getState() {
		return StateGhost.DEAD;
	}
	
	class Sommet{
		private Coordinate c;
		private boolean vu;
		
		public Sommet(Coordinate c) {
			this.vu = false;
			this.c = c;
		}
		
		public int getX(){
			return c.getX();
		}
		
		public int getY() {
			return c.getY();
		}
		
		public boolean isVu() {
			return this.vu;
		}
		public void vu() {
			vu = true;
		}
		
		@Override
		public String toString() {
			return "["+this.c+","+this.vu+"]";
		}
		
	}
	@Override
	public void move(Coordinate next) {
		if(ghost.isReset()) return;
		else {
			Sommet reset = new Sommet(ghost.getReset());
			List<Sommet> list = new ArrayList<Sommet>();
			list.add(reset);
			Sommet g = new Sommet(ghost.getBody());
			System.out.println(g);
			while(!Contains(list, g)){
				List<Sommet> list2 = new ArrayList<Sommet>();
				System.out.println("list"+list);
				for (Sommet coordinate : list) {
					if(coordinate.isVu() ==false) {
						if((ghost.getX()<=reset.getX() && coordinate.getX()<=reset.getX() && ghost.getY()<=reset.getY() && coordinate.getY()<=reset.getY())
							||(ghost.getX()<=reset.getX() && coordinate.getX()<=reset.getX() && ghost.getY()>=reset.getY() && coordinate.getY()>=reset.getY())
							||(ghost.getX()>=reset.getX() && coordinate.getX()>=reset.getX() && ghost.getY()<=reset.getY() && coordinate.getY()<=reset.getY())
							||(ghost.getX()>=reset.getX() && coordinate.getX()>=reset.getX() && ghost.getY()>=reset.getY() && coordinate.getY()>=reset.getY())) {
							list2.add(Ajout(Direction.Down, coordinate));
							list2.add(Ajout(Direction.Left, coordinate));
							list2.add(Ajout(Direction.Right, coordinate));
							list2.add(Ajout(Direction.Up, coordinate));
						}
						coordinate.vu();
					}
		
				}
				for (Sommet sommet : list2) {
					if(!Contains(list, sommet)) list.add(sommet);
				}
			}
			Avancer(list);
		}
	}
	
	private boolean Contains(List<Sommet> l, Sommet s) {
		if(s == null)return true;
		Coordinate c = new Coordinate(s.getX(), s.getY());
		for (Sommet sommet : l) {
			if(c.equals(new Coordinate(sommet.getX(), sommet.getY()))) return true;
		}
		return false;
	}
	
	private Sommet Ajout(Direction dir, Sommet test){
		if(test ==null)return null;
		int size = ghost.getGame().getSizeLab();
		Coordinate c = new Coordinate(test.getX()+dir.getX(), test.getY()+dir.getY());
		if(c.getX()<size && c.getX()>=0 && c.getY()<size && c.getY()>=0 && !ghost.getGame().isMur(c)) return new Sommet(c);
		else return null;
	}
		
		private void Avancer(List<Sommet>  l) {
			ghost.setDirection(Direction.Left);
			Sommet test = new Sommet(ghost.movement());
			if(Contains(l,test)) {
				ghost.setBody(ghost.movement());
				return;
			}
			ghost.setDirection(Direction.Right);
			test = new Sommet(ghost.movement());
			if(Contains(l,test)) {
				ghost.setBody(ghost.movement());
				return;
			}
			ghost.setDirection(Direction.Up);
			test = new Sommet(ghost.movement());
			if(Contains(l,test)) {
				ghost.setBody(ghost.movement());
				return;
			}
			ghost.setDirection(Direction.Down);
			test = new Sommet(ghost.movement());
			if(Contains(l,test)) {
				ghost.setBody(ghost.movement());
				return;
			}
		}
}
		
		
