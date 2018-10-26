package model;

public class Labyrinthe {

	private boolean[][] murs; 
	
	public Labyrinthe(int w, int h) {
		this.murs = new boolean[h][w];
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				this.murs[i][j] = (Math.random()<0.2);
			}
		}
	}
	
	public boolean open(int x, int y) {
		return !this.murs[x][y];
	}
	
	
	
}
