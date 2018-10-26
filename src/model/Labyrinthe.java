package model;

import java.util.Random;

public class Labyrinthe {

	private Random deterministicRandom;
	private boolean[][] murs; 
	
	public Labyrinthe(int w, int h, long seed) {
		this.deterministicRandom=new Random(seed);
		this.murs = new boolean[h][w];
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				//Cette méthode de hasard permet les tests (proba 1/5)
				this.murs[i][j] = (deterministicRandom.nextInt(5) == 0);
			}
		}
	}
	
	public boolean open(int x, int y) {
		if(x>=this.murs.length  || y>=this.murs[0].length) {
			return false;
		}
		return !this.murs[x][y];
	}
	
	
	
}
