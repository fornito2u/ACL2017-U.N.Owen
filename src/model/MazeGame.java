package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import engine.Cmd;
import engine.Game;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class MazeGame implements Game {

	private HeroPainter heroPainter;

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public MazeGame(String source, HeroPainter hero) {
		BufferedReader helpReader;
		heroPainter=hero;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			while ((ligne = helpReader.readLine()) != null) {
				System.out.println(ligne);
			}
			helpReader.close();
		} catch (IOException e) {
			System.out.println("Help not available");
		}
	}

	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande
	 */
	@Override
	public void evolve(Cmd commande) {
		System.out.println("Execute "+commande);
		if (commande.equals(Cmd.UP)) {
			heroPainter.deplacer(0, -HeroPainter.UNITE_DEPLACEMENT);
		} else if (commande.equals(Cmd.DOWN)) {
			heroPainter.deplacer(0,HeroPainter.UNITE_DEPLACEMENT);
		} else if (commande.equals(Cmd.LEFT)) {
			heroPainter.deplacer(-HeroPainter.UNITE_DEPLACEMENT,0);
		} else if (commande.equals(Cmd.RIGHT)) {
			heroPainter.deplacer(HeroPainter.UNITE_DEPLACEMENT,0);
		}
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		// le jeu n'est jamais fini
		return false;
	}

}
