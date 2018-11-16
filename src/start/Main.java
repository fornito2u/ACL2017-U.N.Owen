package start;


import java.io.IOException;

import model.MazeGame;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		//Labyrinthe particulier et de son afficheur
		MazeGame jeu = new MazeGame();
		//MazeGame jeu = new MazeGame(6);
		//Labyrinthe au hasard
		//MazeGame jeu = new MazeGame(System.nanoTime());
		
		jeu.start();
	}

}
