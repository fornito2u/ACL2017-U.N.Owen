package model;

import java.util.ArrayList;
import java.util.Random;

public class Monstre extends Personnage {

	protected MazeGame jeu;
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

	//Pour cette fonction x et y sont les coordonnées du monstres
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
	
	public boolean explore(int x,int y){
		boolean[][] visite=new boolean[jeu.getLabyrinthe().getHeight()][jeu.getLabyrinthe().getWidth()];
		double distance=Math.hypot(this.x-x, this.y-y);
		if(x==jeu.getHero().getX() && y==jeu.getHero().getY()){
			return true;
		}
		if(positionPossibleLabyrinthe(this.x+1, this.y) && Math.hypot((this.x+1-x), this.y-y)<distance && visite[this.x+1][this.y]){
			visite[this.x+1][this.y]=true;
			return explore(this.x+1,this.y);
		}else if(positionPossibleLabyrinthe(this.x-1, this.y) && Math.hypot((this.x-1-x), this.y-y)<distance && !visite[this.x-1][this.y]){
			visite[this.x-1][this.y]=true;
			return explore(this.x-1,this.y);
		}else if(positionPossibleLabyrinthe(this.x, this.y+1) && Math.hypot((this.x-x), this.y-y+1)<distance && !visite[this.x][this.y+1]){
			visite[this.x][this.y+1]=true;
			return explore(this.x,this.y+1);
		}else if(positionPossibleLabyrinthe(this.x, this.y-1) && Math.hypot((this.x-x), this.y-y-1)<distance && !visite[this.x][this.y-1]){
			visite[this.x][this.y-1]=true;
			return explore(this.x,this.y-1);
		}
		return false;
		
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

	public boolean isFantom() {
		return false;
	}
	
	public boolean positionPossibleLabyrinthe(int x,int y) {
		if (!collisionMur(x,y) && !collisionHero(x,y) && !collisionMonstres(x,y)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean collisionMur(int x, int y) {
		Labyrinthe lab = this.jeu.getLabyrinthe();
		if (!lab.open(x, y)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean collisionHero(int x, int y) {
		Hero hero=this.jeu.getHero();
		if (hero.getX() == x && hero.getY() == y) {
			return true;
		} else {
			return false;
		}
	}

	public boolean collisionMonstres(int x, int y) {
		ArrayList<Monstre> list_monstres=this.jeu.getMonstreList();
		for (int i=0;i<list_monstres.size();i++) {
			Monstre m=list_monstres.get(i);
			if (m != this) {
				if (m.getX() == x && m.getY() == y) {
					return true;
				}
			}
		}
		return false;
	}
	
}
