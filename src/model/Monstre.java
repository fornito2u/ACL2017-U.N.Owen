package model;

import java.util.ArrayList;
import java.util.Random;

public class Monstre extends Personnage {

	protected MazeGame jeu;
	private final static int diameter = 20;
	private int direction = 1;
	private int compteur = 0;
	private static int DISTANCE_DETECTION=7;
	private int points = 10;
	
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

	//Pour cette fonction x et y sont les coordonnées du hero
	@Override
	public void deplacer(int x, int y) {
		double distance=Math.hypot(this.x-x, this.y-y);
		if(distance <= DISTANCE_DETECTION) {
			if(positionPossibleLabyrinthe(this.x+1, this.y) && Math.hypot((this.x-x+1), this.y-y)<distance){
				this.x++;
				this.direction = 3;
			}else if(positionPossibleLabyrinthe(this.x-1, this.y) && Math.hypot((this.x-x-1), this.y-y-1)<distance){
				this.x--;
				this.direction = 1;
			}else if(positionPossibleLabyrinthe(this.x, this.y+1) && Math.hypot((this.x-x), this.y-y+1)<distance){
				this.y++;
				this.direction = 2;
			}else if(positionPossibleLabyrinthe(this.x, this.y-1) && Math.hypot((this.x-x), this.y-y-1)<distance){
				this.y--;
				this.direction = 0;
			}
		}else {
			Random r=new Random();
			int alea=r.nextInt(4);
			if(alea==0 && positionPossibleLabyrinthe(this.x+1, this.y)){
				this.x++;
				this.direction = 3;
			}else if(alea==1 && positionPossibleLabyrinthe(this.x-1, this.y)){
				this.x--;
				this.direction = 1;
			}else if(alea==2 && positionPossibleLabyrinthe(this.x, this.y+1)){
				this.y++;
				this.direction = 2;
			}else if(alea==3 && positionPossibleLabyrinthe(this.x, this.y-1) ){
				this.y--;
				this.direction = 0;
			}
		}
		
		if(compteur == 8) {
			compteur = 0;
		} else {
			compteur++;
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
	
	public int getDirection() {
		return this.direction;
	}
	
	public int getCompteur() {
		return compteur;
	}

	@Override
	protected int getPoints() {
		return this.points;
	}
	
}
