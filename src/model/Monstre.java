package model;

import java.util.Random;

public class Monstre extends Personnage {
	private MazeGame jeu;
	private final static int diameter = 10;
	public Monstre(MazeGame j)
	{
		super();
		this.jeu=j;
		Random r=new Random();
		int newx, newy;
		do {
			newx=r.nextInt(this.jeu.getLabyrinthe().getWidth());
			newy=r.nextInt(this.jeu.getLabyrinthe().getHeight());
		}while(!positionPossible(newx,newy));
		this.x=(newx*10)+20;
		this.y=(newy*10)+40;
		
	}

	@Override
	public void deplacer(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attaquer(Personnage p) {
		p.setPv(p.getPv()-1);
	}
	
	public static int getDiameter() {
		return diameter;
	}
	
	public boolean positionPossible(int x, int y) {
		//On teste si la position est possible dans le labyrinthe
		Labyrinthe lab = this.jeu.getLabyrinthe();
		if (!lab.open((int)Math.floor(x),(int)Math.floor(y))) {
			return false;
		}
		//On test la position du hero et un peu autour
		int[] tabPosition= {0,1,-1,2,-2,3,-3,4,-4,5,-5,6,-6,7,-7};
		for(int i : tabPosition) {
			for(int j : tabPosition) {
				int a=(this.jeu.getHero().getX()/10)+i;
				int b=(this.jeu.getHero().getY()/10)+j;
				if(a==x && b==y) {
					return false;
				}
			}
		}
		return true;
	}
	
}
