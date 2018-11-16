package model;

public class Fantom extends Monstre {

    public Fantom(MazeGame j)
    {
        super(j);
    }

    public void deplacer (int x, int y)
    {
        double distance=Math.hypot(this.x-x, this.y-y);

        if(Math.hypot((this.x+1-x), this.y-y)<distance){
            this.x++;
        }else if(Math.hypot((this.x-1-x), this.y-y)<distance){
            this.x--;
        }else if(Math.hypot((this.x-x), this.y-y+1)<distance){
            this.y++;
        }else if(Math.hypot((this.x-x), this.y-y-1)<distance){
            this.y--;
        }
    }
}
