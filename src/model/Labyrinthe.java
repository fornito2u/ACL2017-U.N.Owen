package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Labyrinthe {

	private boolean[][] murs;
	private int width;
	private int height;

	
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
					this.murs[j][i] = (line.charAt(j) == 'X');
				}
				i++;
				line =  br.readLine();
			}
			System.out.println(murs[2][4]);
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
