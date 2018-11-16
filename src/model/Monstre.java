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
		}while(!positionPossibleApparition(newx,newy));
		this.x=newx;
		this.y=newy;
	}

	//Pour cette fonction x et y sont les coordonnées du héro
	@Override
	public void deplacer(int x, int y) {
		double distance=Math.hypot(this.x-x, this.y-y);
		if(positionPossibleLabyrinthe(this.x+1, this.y) && Math.hypot((this.x+1-x), this.y-y)<distance){
			this.x++;
		}else if(positionPossibleLabyrinthe(this.x-1, this.y) && Math.hypot((this.x-1-x), this.y-y)<distance){
			this.x--;
		}else if(positionPossibleLabyrinthe(this.x, this.y+1) && Math.hypot((this.x-x), this.y-y+1)<distance){
			this.y++;
		}else if(positionPossibleLabyrinthe(this.x, this.y-1) && Math.hypot((this.x-x), this.y-y-1)<distance){
			this.y--;
		}
	}
	

	@Override
	public void attaquer(Personnage p) {
		p.setPv(p.getPv()-1);
	}
	
	public static int getDiameter() {
		return diameter;
	}
	
	public boolean positionPossibleApparition(int x, int y) {
		//On teste si la position est possible dans le labyrinthe
		Labyrinthe lab = this.jeu.getLabyrinthe();
		if (!lab.open(x,y)){
			return false;
		}
		//On test la position du hero et un peu autour
		int[] tabPosition= {0,1,-1,2,-2,3,-3,4,-4,5,-5,6,-6,7,-7};
		for(int i : tabPosition) {
			for(int j : tabPosition) {
				int a=(this.jeu.getHero().getX())+i;
				int b=(this.jeu.getHero().getY())+j;
				if(a==x && b==y) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean positionPossibleLabyrinthe(int x,int y){
		Labyrinthe lab = this.jeu.getLabyrinthe();
		if (!lab.open(x,y)){
			return false;
		}
		if(this.jeu.getHero().getX()==x && this.jeu.getHero().getY()==y) {
			return false;
		}
		return true;
		
	}
	
}
