package start;

import java.io.IOException;

import controler.HeroController;
import engine.GameEngineGraphical;
import model.Hero;
import model.MazeGame;
import view.LabyrinthePainter;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		// creation du jeu particulier et de son afficheur
		MazeGame jeu = new MazeGame("helpFilePacman.txt");
		jeu.start();
	}

}
