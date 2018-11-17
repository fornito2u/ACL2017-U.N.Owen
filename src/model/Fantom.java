package model;

import java.util.ArrayList;

public class Fantom extends Monstre {

    public Fantom(MazeGame j)
    {
        super(j);
    }

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

    public boolean positionPossibleLabyrinthe(int x,int y) {
        if (!collisionHero(x,y) && !collisionMonstres(x,y)) {
            return true;
        } else {
            return false;
        }
    }
}
