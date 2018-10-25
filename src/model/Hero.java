package model;

public class Hero extends Personnage {

	private MazeGame jeu;
	
	public Hero(MazeGame jeu) {
		super();
		this.jeu = jeu;
		this.x = 0;
		this.y = 0;
	}
	
	@Override
	public void deplacer(int x,int y) {
		if ((this.x+x>=0) && (this.x+x<this.jeu.getLimiteX())) {
			this.x += x;
			System.out.println("X : "+this.x);
		}

		if ((this.y+y>=0) && (this.y+y<this.jeu.getLimiteY())) {
			this.y += y;
			System.out.println("Y : "+this.y);
		}
		
	}
	

	@Override
	public void attaquer() {
		
	}
	
	

}
