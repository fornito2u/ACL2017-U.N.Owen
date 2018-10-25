package model;

import view.MazeGame;

public class Hero extends Personnage {

	
	public Hero() {
		super();
		this.x = 0;
		this.y = 0;
	}
	
	@Override
	public void deplacer(int x,int y) {
		if ((this.x+x>=0) && (this.x+x<MazeGame.LIMITE_X)) {
			this.x += x;
			System.out.println("X : "+this.x);
		}

		if ((this.y+y>=0) && (this.y+y<MazeGame.LIMITE_Y)) {
			this.y += y;
			System.out.println("Y : "+this.y);
		}
		
	}
	

	@Override
	public void attaquer() {
		// TODO Auto-generated method stub
		
	}
	
	

}
