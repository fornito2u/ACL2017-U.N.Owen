package engine;

import java.util.ArrayList;

import model.Monstre;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         un jeu qui peut evoluer (avant de se terminer) sur un plateau width x
 *         height
 */
public interface Game {

	/**
	 * methode qui contient l'evolution du jeu en fonction de la commande
	 * 
	 * @param userCmd
	 *            commande utilisateur
	 */
	public void evolve(Cmd userCmd);

	/**
	 * @return true si et seulement si le jeu est fini
	 * @throws InterruptedException 
	 */
	public boolean isFinished() throws InterruptedException;

	public int getLevel();

	public ArrayList<Monstre> getMonstreList();

}
