package model;

import static view.LabyrinthePainter.UNITE_DEPLACEMENT;

public class Hero extends Personnage {

	private MazeGame jeu;
	private static int diameter = 20;
	private int direction = 1;
	private int compteur = 0;
	
	
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
		if(x==0) {
			if(y==1) {
				this.direction = 0;
			} else {
				this.direction = 2;
			}
		} else if(x==1) {
			this.direction = 1;
		} else {
			this.direction = 3;
		}
		if(compteur == 7) {
			compteur = 0;
		} else {
			compteur++;
		}
	}
	
	public boolean accessible(int x, int y) {
		
		int destX = this.x+x;
		int destY = this.y+y;

		//On teste les bordures
		if ((destX<0) || (destX>this.jeu.getLimiteX()-diameter)) {
			return false;
		} else if ((destY<0) || (destY>this.jeu.getLimiteY()-diameter)) {
			return false;
		}
		//On teste le labyrinthe
		Labyrinthe lab = this.jeu.getLabyrinthe();
		if (!lab.open(destX,destY)) {
			return false;
		}
		//Test de colision avec les monstres
		for(Monstre m : jeu.getMonstreList()) {
			if(this.x+x >= m.getX() && this.x+x <= m.getX() &&
					this.y+y >= m.getY() && this.y+y <= m.getY())
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

	public void reInit() {
		this.x = this.jeu.getPosX();
		this.y = this.jeu.getPosY();
	}

	public int getDirection() {
		return this.direction;
	}
	
	public int getCompteur() {
		return compteur;
	}

}
