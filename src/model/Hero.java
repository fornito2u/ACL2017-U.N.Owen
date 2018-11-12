package model;

import static view.LabyrinthePainter.UNITE_DEPLACEMENT;

public class Hero extends Personnage {

	private MazeGame jeu;
	private static int diameter = 10;
	
	public Hero(MazeGame jeu) {
		super();
		this.jeu = jeu;
		this.x = jeu.getPosX();
		this.y = jeu.getPosY();
	}
	
	@Override
	public void deplacer(int x,int y) {
		if(this.accessible(x, y)) {
			this.x += x;
			this.y += y;
		}
	}
	
	public boolean accessible(int x, int y) {
		int destX = this.x+x-20;
		int destY = this.y+y-40;
		
		//On teste les bordures
		if ((destX<0) || (destX>this.jeu.getLimiteX()-diameter)) {
			return false;
		} else if ((destY<0) || (destY>this.jeu.getLimiteY()-diameter)) {
			return false;
		}
		
		//On teste le labyrinthe
		Labyrinthe lab = this.jeu.getLabyrinthe();
		if (!lab.open((int)Math.floor(destX/10),(int)Math.floor(destY/10))) {
			return false;
		}
		if(this.y+y > this.y && !lab.open((int)Math.floor(destX/10),(int)Math.floor((destY-1)/10+1))) {
			return false;
		}
		if(this.x+x > this.x && !lab.open((int)Math.floor((destX-1)/10+1),(int)Math.floor(destY/10))) {
			return false;
		}
		if(this.y+y < this.y && !lab.open((int)Math.floor((destX-1)/10+1),(int)Math.floor((destY)/10))) {
			return false;
		}
		if(this.x+x < this.x && !lab.open((int)Math.floor((destX)/10),(int)Math.floor((destY-1)/10+1))) {
			return false;
		}
		if((this.x+x > this.x ||  this.y+y > this.y) && !lab.open((int)Math.floor((destX-1)/10+1),(int)Math.floor((destY-1)/10+1))) {
			return false;
		}

		int monstreX ;
		int monstreY ;
		for(int i = 0; i < jeu.getMonstreList().size(); ++i)
		{
			monstreX = jeu.getMonstreList().get(i).getX();
			monstreY = jeu.getMonstreList().get(i).getY();

			if(this.x+x >= monstreX-5 && this.x+x <= monstreX+5 &&
				this.y+y >= monstreY-5 && this.y+y <= monstreY+5)
			{
				return false;
			}
		}
		return true;
	}
	

	@Override
	public void attaquer(Personnage p) {
		p.setPv(p.getPv()-1);
	}

	public static int getDiameter() {
		return diameter ;
	}
	
	

}
