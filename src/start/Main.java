package start;

import controler.HeroController;
import engine.GameEngineGraphical;
import model.Hero;
import view.HeroPainter;
import view.MazeGame;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		Hero hero = new Hero();
		HeroPainter painter = new HeroPainter(hero);
		HeroController controller = new HeroController();

		// creation du jeu particulier et de son afficheur
		MazeGame game = new MazeGame("helpFilePacman.txt",painter);

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		engine.run();
	}

}
