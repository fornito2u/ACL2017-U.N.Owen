package start;

import controler.HeroController;
import engine.GameEngineGraphical;
import model.Hero;
import model.MazeGame;
import view.HeroPainter;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		// creation du jeu particulier et de son afficheur
		MazeGame jeu = new MazeGame("helpFilePacman.txt");
		jeu.start();
	}

}
