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
		if ((this.x+x>=0) && (this.x+x<=this.jeu.getLimiteX()-diameter) && jeu.getLabyrinthe().open((int)Math.floor((this.x+x)/10),(int)Math.floor((this.y+y)/10))) {
			this.x += x;
			System.out.println("X : "+this.x);
		}

		if ((this.y+y>=0) && (this.y+y<=this.jeu.getLimiteY()-diameter) && jeu.getLabyrinthe().open((int)Math.floor((this.x+x)/10),(int)Math.floor((this.y+y)/10))) {
			this.y += y;
			System.out.println("Y : "+this.y);
		}
		
	}
	

	@Override
	public void attaquer(Personnage p) {
		p.setPv(p.getPv()-1);
	}

	public static int getDiameter() {
		return diameter ;
	}
	
	

}
