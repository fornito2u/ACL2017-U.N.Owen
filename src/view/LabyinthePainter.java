package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import engine.GamePainter;
import model.Hero;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class LabyinthePainter implements GamePainter {

	/**
	 * la taille des cases
	 */

	public static final int UNITE_DEPLACEMENT=5;
	protected static final int WIDTH = 800;
	protected static final int HEIGHT = 500;

	private Hero hero;
	
	public LabyinthePainter(Hero hero)
	{
		this.hero = hero;
	}


	public Hero getHero() {
		return hero;
	}


	public void setHero(Hero hero) {
		this.hero = hero;
	}


	public static int getUniteDeplacement() {
		return UNITE_DEPLACEMENT;
	}


	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(Color.blue);
		crayon.fillOval(this.hero.getX(),this.hero.getY(),10,10);
		if(this.hero.getPv()>0)
		{
			crayon.setColor(Color.red);
			crayon.fillRect(10, 10, this.hero.getPv()*10, 5);
		}
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

}
