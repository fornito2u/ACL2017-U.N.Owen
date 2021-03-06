package controler;

import java.awt.event.KeyEvent;

import engine.Cmd;
import engine.GameController;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * controleur de type KeyListener
 * 
 */
public class HeroController implements GameController {

	/**
	 * commande en cours
	 */
	private Cmd commandeEnCours;
	
	/**
	 * construction du controleur par defaut le controleur n'a pas de commande
	 */
	public HeroController() {
		this.commandeEnCours = Cmd.IDLE;
		isUP=false;
		isLEFT=false;
		isRIGHT=false;
		isDOWN=false;
	}

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 * 
	 * @return commande faite par le joueur
	 */
	public Cmd getCommand() {
		return this.commandeEnCours;
	}

	//Boolean vrai si la touche a été appuyé et si elle n'a pas encore été relaché
	private boolean isUP;
	private boolean isLEFT;
	private boolean isRIGHT;
	private boolean isDOWN;

	@Override
	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
			// si on appuie sur 'q',commande joueur est gauche
			//case Integer.toKeyEvent.VK_UP:
			case KeyEvent.VK_UP:
			case KeyEvent.VK_Z:
				this.commandeEnCours = Cmd.UP;
				isUP = true;
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				this.commandeEnCours = Cmd.DOWN;
				isDOWN = true;
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_Q:
				this.commandeEnCours = Cmd.LEFT;
				isLEFT = true;
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				this.commandeEnCours = Cmd.RIGHT;
				isRIGHT = true;
				break;
			case KeyEvent.VK_SPACE:
				this.commandeEnCours = Cmd.SPACE;
				break;
		}
	}


	@Override
	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			// si on appuie sur 'q',commande joueur est gauche
			//case Integer.toKeyEvent.VK_UP:
			case KeyEvent.VK_UP:
			case KeyEvent.VK_Z:
				isUP=false;
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				isDOWN=false;
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_Q:
				isLEFT=false;
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				isRIGHT=false;
				break;
		}

		//Si toutes les touches sont relachés
		if (!isUP && !isDOWN && !isLEFT && !isRIGHT) {
			this.commandeEnCours = Cmd.IDLE;
		}
	}

	@Override
	/**
	 * ne fait rien
	 */
	public void keyTyped(KeyEvent e) {


	}

}
