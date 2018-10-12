package start;

import model.HeroPainter;
import engine.GameEngineGraphical;
import model.HeroController;
import model.MazeGame;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		HeroPainter painter = new HeroPainter();
		HeroController controller = new HeroController();

		// creation du jeu particulier et de son afficheur
		MazeGame game = new MazeGame("helpFilePacman.txt",painter);

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		engine.run();
	}

}
