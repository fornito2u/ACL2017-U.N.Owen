package model;

import view.MazeGame;

public class Hero extends Personnage {

	private Jeu jeu;
	
	public Hero(Jeu jeu) {
		super();
		this.jeu = jeu;
		this.x = 0;
		this.y = 0;
	}
	
	@Override
	public void deplacer(int x,int y) {
		if ((this.x+x>=0) && (this.x+x<this.jeu.getGame().getLimiteX())) {
			this.x += x;
			System.out.println("X : "+this.x);
		}

		if ((this.y+y>=0) && (this.y+y<this.jeu.getGame().getLimiteY())) {
			this.y += y;
			System.out.println("Y : "+this.y);
		}
		
	}
	

	@Override
	public void attaquer() {
		// TODO Auto-generated method stub
		
	}
	
	

}
