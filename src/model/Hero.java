package model;

public class Hero extends Personnage {

	private MazeGame jeu;
	private static int diameter = 10;
	
	public Hero(MazeGame jeu) {
		super();
		this.jeu = jeu;
		this.x = 10;
		this.y = 250;
	}
	
	@Override
	public void deplacer(int x,int y) {
		if(this.accessible(x, y)) {
			this.x += x;
			this.y += y;
		}
	}
	
	public boolean accessible(int x, int y) {
		boolean res = true;
		int destX = this.x+x;
		int destY = this.y+y;
		
		//On teste les bordures
		if ((destX<0) || (destX>this.jeu.getLimiteX()-diameter)) {
			res = false;
		} else if ((destY<0) || (destY>this.jeu.getLimiteY()-diameter)) {
			res = false;
		}
		
		//On teste le labyrinthe
		Labyrinthe lab = this.jeu.getLabyrinthe();
		if (!lab.open((int)Math.floor(destX/10),(int)Math.floor(destY/10))) {
			res = false; 
		}
		if(destY > this.y && !lab.open((int)Math.floor(destX/10),(int)Math.floor((destY-1)/10+1))) {
			res = false; 
		}
		if(destX > this.x && !lab.open((int)Math.floor((destX-1)/10+1),(int)Math.floor(destY/10))) {
			res = false; 
		}
		if(destY < this.y && !lab.open((int)Math.floor((destX-1)/10+1),(int)Math.floor((destY)/10))) {
			res = false; 
		}
		if(destX < this.x && !lab.open((int)Math.floor((destX)/10),(int)Math.floor((destY-1)/10+1))) {
			res = false; 
		}
		if((destX > this.x ||  destY > this.y) && !lab.open((int)Math.floor((destX-1)/10+1),(int)Math.floor((destY-1)/10+1))) {
			res = false; 
		}
		return res;
	}
	

	@Override
	public void attaquer(Personnage p) {
		p.setPv(p.getPv()-1);
	}

	public static int getDiameter() {
		return diameter ;
	}
	
	

}
