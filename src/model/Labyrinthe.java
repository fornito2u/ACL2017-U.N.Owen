package model;

import java.io.*;
import java.util.Random;

public class Labyrinthe {

	//Utilisé par les labyrintes aléatoire
	private Random deterministicRandom;
	
	private int[][] murs;
	private int width;
	private int height;
	private int posX;
	private int posY;
	private int goalX;
	private int goalY;
	
	public Labyrinthe(int w, int h, long seed) {
		this.width=w;
		this.height=h;
		this.deterministicRandom=new Random(seed);
		this.murs = new int[this.width][this.height];
		for(int i=0; i<w; i++) {
			for(int j=0; j<h; j++) {
				//Les cotés
				if ((i == 0) || (j == 0)|| (i == w-1) || (j==h-1)) {
					this.murs[i][j]=1;
				} else {
					//Cette méthode de hasard permet les tests (proba 1/5)
					if(deterministicRandom.nextInt(5) == 0) {
						this.murs[i][j]=1;
					} else {
						this.murs[i][j]=0;
					}
				}
			}
		}

		//Position par defaut en haut à gauche
		this.posX=10+20;
		this.posY=10+40;

		//Par défaut on considèrera que la case du héros est toujours en haut à gauche

		this.murs[1][1]=0;

	}
	
	public Labyrinthe(File f) throws IOException {
		Random r = new Random();
        InputStreamReader fr;
		try {
		    //Rajout de l'encodage (sinon bug avec ant sous Windows)
		    fr = new InputStreamReader(new FileInputStream(f), "UTF-8");
			BufferedReader br = new BufferedReader (fr);
			int i=0;
			String line =  br.readLine();
			this.width = Integer.parseInt(line);
			line =  br.readLine();
			this.height = Integer.parseInt(line);
			line =  br.readLine();
			this.goalX = r.nextInt(this.width);
			this.goalY = r.nextInt(this.height);
			boolean goal = false;
			this.posX = Integer.parseInt(line);
			line =  br.readLine();
			this.posY = Integer.parseInt(line);
			this.murs = new int[this.width][this.height];
			line =  br.readLine();
			while(line != null) {
				for(int j=0; j<this.width; j++) {
					if(j == goalX && i == goalY || goal) {
						if(line.charAt(j) == '█') {
							goal = true;
							if(line.charAt(j) == '█') {
								this.murs[j][i]=1;
							} else {
								this.murs[j][i]=0;
							}
						} else {
							this.murs[j][i] = 2;
							this.goalX = j;
							this.goalY = i;
							goal = false;
						}
					} else {
						if(line.charAt(j) == '█') {
							this.murs[j][i]=1;
						} else {
							this.murs[j][i]=0;
						}
					}
				}
				i++;
				line =  br.readLine();
			}
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean open(int x, int y) {
		if(x>=this.murs.length  || y>=this.murs[0].length) {
			return false;
		}
		return (this.murs[x][y]==0 || this.murs[x][y]==2);
	}

	public int getWidth() {
		return this.width;
	}


	public int getHeight() {
		return this.height;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public boolean isGoal(int x, int y) {
		return (this.murs[x][y] == 2);
	}

	public int getGoalX() {
		return this.goalX;
	}


	public int getGoalY() {
		return this.goalY;
	}
	
	public void setGoalX(int x) {
		this.goalX = x;
	}


	public void setGoalY(int y) {
		this.goalY = y;
	}

	
	
	
}
