package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import engine.GamePainter;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class HeroPainter implements GamePainter {

	/**
	 * la taille des cases
	 */

	protected static final int UNITE_DEPLACEMENT=10;
	protected static final int WIDTH = 100;
	protected static final int HEIGHT = 100;

	private int x;
	private int y;

	/**
	 * appelle constructeur parent
	 * 
	 * @param game
	 *            le jeutest a afficher
	 */
	public HeroPainter() {
		this.x=0;
		this.y=0;
	}

	public void deplacer(int x,int y) {
		if ((this.x+x>=0) && (this.x+x<MazeGame.LIMITE_X)) {
			this.x += x;
			System.out.println("X : "+this.x);
		}

		if ((this.y+y>=0) && (this.y+y<MazeGame.LIMITE_Y)) {
			this.y += y;
			System.out.println("Y : "+this.y);
		}
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(Color.blue);
		crayon.fillOval(x,y,10,10);
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
