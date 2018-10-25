package start;

import controler.HeroController;
import engine.GameEngineGraphical;
import model.Hero;
import model.Jeu;
import view.HeroPainter;
import view.MazeGame;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		Jeu jeu = new Jeu();
		jeu.start();
	}

}
