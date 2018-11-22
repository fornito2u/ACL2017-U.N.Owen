package model;

import java.awt.Point;
import java.io.*;
import java.util.ArrayList;
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
		this.posX=1;
		this.posY=1;

		//Par défaut on considèrera que la case du héros est toujours en haut à gauche

		this.murs[1][1]=0;

	}
	
	public Labyrinthe() throws IOException {
		this.init(this.aleatLab(System.nanoTime(), 1));
	}
	
	public boolean open(int x, int y) {
		if(x>=this.murs.length  || y>=this.murs[0].length || x<0 || y<0) {
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
	
	public File aleatLab(long seed, int niveau) throws IOException {
		//Création du fichier
		File file = new File("labyrinthe"+niveau+".txt");
		file.createNewFile();
		
		//Création des variables
		int width = 42+1;
		boolean[][] parcours = new boolean[width/3][width/3];
		boolean[][] labyrinthe = new boolean[width][width];
		Random rand = new Random(seed);
		
		//Création de la base du labyrinthe
		for(int i=0; i<width-3; i+=3) {
			for(int j=0; j<width-3; j+=3) {
				labyrinthe[i][j] = true;
				labyrinthe[i][j+1] = true;
				labyrinthe[i][j+2] = true;
				labyrinthe[i+1][j] = true;
				labyrinthe[i+2][j] = true;
			}
		}
		for(int j=0; j<width; j++) {
			labyrinthe[j][width-1] = true;
			labyrinthe[width-1][j] = true;
		}
		
		//Algorithme de création aléatoire de labyrinthe
		for(int i=0; i<width/3; i++) {
			for(int j=0; j<width/3; j++) {
				parcours[i][j] = false;
			}
		}
		boolean continuer = true;
		ArrayList<Point> parcouru = new ArrayList<Point>();
		int x=0,y=0,temp=0,boucle=0, compteur =0;
		int index = 0;
		parcouru.add(0,new Point(0,0));
		while(continuer) {
			boolean accessible = false;
			boolean back = false;
			compteur++; 
			while(!accessible) {
				switch(temp) {
					case 0:
						if(x<width/3-1 && !parcouru.contains(new Point(x+1, y))) {
							accessible = true;
							labyrinthe = openRoad(labyrinthe, x, y, temp);
							parcours[x][y]=true;
							x++;
						} else {
							temp++;
							if(boucle==temp) {
								x = parcouru.get(index+1).x;
								y = parcouru.get(index+1).y;
								accessible = true;
								back = true;
							}
						}
						break;
					case 1:
						if(y<width/3-1 && !parcouru.contains(new Point(x, y+1))) {
							accessible = true;
							labyrinthe = openRoad(labyrinthe, x, y, temp);
							parcours[x][y]=true;
							y++;
						} else {
							temp++;
							if(boucle==temp) {
								x = parcouru.get(index+1).x;
								y = parcouru.get(index+1).y;
								accessible = true;
								back = true;
							}
						}
						break;
					case 2:
						if(x>0 && !parcouru.contains(new Point(x-1, y))) {
							accessible = true;
							labyrinthe = openRoad(labyrinthe, x, y, temp);
							parcours[x][y]=true;
							x--;
						} else {
							temp++;
							if(boucle==temp) {
								x = parcouru.get(index+1).x;
								y = parcouru.get(index+1).y;
								accessible = true;
								back = true;
							}
						}
						break;
					case 3:
						if(y>0 && !parcouru.contains(new Point(x, y-1))) {
							accessible = true;
							labyrinthe = openRoad(labyrinthe, x, y, temp);
							parcours[x][y]=true;
							y--;
						} else {
							temp = 0;
							if(boucle==temp) {
								x = parcouru.get(index+1).x;
								y = parcouru.get(index+1).y;
								accessible = true;
								back = true;
							}
						}
						break;
				}
				
			}
			if(compteur == 6) {
				back = true;
				compteur = 0;
			}
			if(!back) {
				parcouru.add(0,new Point(x,y));
				index = 0;
			} else {
				if(parcouru.size()> index+7) {
					index+=6;
				} else {
					index++;
				}
				if(x==0 && y==0) {
					continuer = false;
				}
				back = false;
			}
			temp = rand.nextInt(4);
			boucle = temp;
		}
				
		//Création du writer
		FileWriter writer = new FileWriter(file);
		writer.write(Integer.toString(width)+"\n");
		writer.write(Integer.toString(width)+"\n");
		writer.write(Integer.toString(1)+"\n");
		writer.write(Integer.toString(1)+"\n");
		
		//Rédaction du labyrinthe
		for(int i=0; i<width; i++) {
			for(int j=0; j<width; j++) {
				if(labyrinthe[j][i]) {
					writer.write("X");
				} else {
					writer.write(" ");
				}
			}
			writer.write("\n");
		}
		
		//Fermeture du writer
		writer.close();
		return file;
	}

	private boolean[][] openRoad(boolean[][] labyrinthe, int x, int y, int aleat) {
		switch(aleat) {
			case 0:
				labyrinthe[3*(x+1)][3*y+1]=false;
				labyrinthe[3*(x+1)][3*y+2]=false;
				break;
			case 1:
				labyrinthe[3*x+1][3*(y+1)]=false;
				labyrinthe[3*x+2][3*(y+1)]=false;
				break;
			case 2:
				labyrinthe[3*x][3*y+1]=false;
				labyrinthe[3*x][3*y+2]=false;
				break;
			case 3:
				labyrinthe[3*x+1][3*y]=false;
				labyrinthe[3*x+2][3*y]=false;
				break;
		}
		return labyrinthe;
		
	}

	public void reInit(int level) {
		try {
			this.init(this.aleatLab(System.nanoTime(), level));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void init(File file) {
		Random r = new Random();
		InputStreamReader fr;
		try {
		    //Rajout de l'encodage (sinon bug avec ant sous Windows)
		    fr = new InputStreamReader(new FileInputStream(file), "UTF-8");
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
						if(line.charAt(j) == 'X') {
							goal = true;
							if(line.charAt(j) == 'X') {
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
						if(line.charAt(j) == 'X') {
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
