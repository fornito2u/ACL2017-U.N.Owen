package model;


public class Fantom extends Monstre {

	private int direction = 1;
	private int compteur = 0;
	private int points = 30;
	
    public Fantom(MazeGame j)
    {
        super(j);
    }

    public void deplacer(int x, int y) {
    	double distance=Math.hypot(this.x-x, this.y-y);
		if(positionPossibleLabyrinthe(this.x+1, this.y) && Math.hypot((this.x+1-x), this.y-y)<distance){
			this.x++;
			this.direction = 3;
		}else if(positionPossibleLabyrinthe(this.x-1, this.y) && Math.hypot((this.x-1-x), this.y-y)<distance){
			this.x--;
			this.direction = 1;
		}else if(positionPossibleLabyrinthe(this.x, this.y+1) && Math.hypot((this.x-x), this.y-y+1)<distance){
			this.y++;
			this.direction = 2;
		}else if(positionPossibleLabyrinthe(this.x, this.y-1) && Math.hypot((this.x-x), this.y-y-1)<distance){
			this.y--;
			this.direction = 0;
		}
		if(compteur == 8) {
			compteur = 0;
		} else {
			compteur++;
		}
    }

    public boolean positionPossibleLabyrinthe(int x,int y) {
        if (!collisionHero(x,y) && !collisionMonstres(x,y)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFantom() {
        return true;
    }
    
    public int getDirection() {
		return this.direction;
	}
	
	public int getCompteur() {
		return compteur;
	}
}
