package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Labyrinthe {

	//Utilisé par les labyrintes aléatoire
	private Random deterministicRandom;
	
	private boolean[][] murs;
	private int width;
	private int height;
	
	public Labyrinthe(int w, int h, long seed) {
		this.width=w;
		this.height=h;
		this.deterministicRandom=new Random(seed);
		this.murs = new boolean[this.width][this.height];
		for(int i=0; i<w; i++) {
			for(int j=0; j<h; j++) {
				//Les cotés
				if ((i == 0) || (j == 0)|| (i == w-1) || (j==h-1)) {
					this.murs[i][j]=true;
				} else {
					//Cette méthode de hasard permet les tests (proba 1/5)
					this.murs[i][j] = (deterministicRandom.nextInt(5) == 0);
				}
			}
		}
	}
	
	public Labyrinthe(File f) throws IOException {
		FileReader fr;
		try {
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader (fr);
			
			int i=0;
			String line =  br.readLine();
			this.width = Integer.parseInt(line);
			line =  br.readLine();
			this.height = Integer.parseInt(line);
			this.murs = new boolean[this.width][this.height];
			line =  br.readLine();
			while(line != null) {
				for(int j=0; j<line.length(); j++) {
					this.murs[j][i] = (line.charAt(j) == '█');
				}
				i++;
				line =  br.readLine();
			}
			//System.out.println(murs[2][4]);
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean open(int x, int y) {
		if(x>=this.murs.length  || y>=this.murs[0].length) {
			return false;
		}
		return !this.murs[x][y];
	}

	public int getWidth() {
		return this.width;
	}


	public int getHeight() {
		return this.height;
	}
	
	
}
