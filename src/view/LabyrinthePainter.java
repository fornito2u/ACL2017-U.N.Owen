package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import engine.GamePainter;
import model.Hero;
import model.Labyrinthe;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class LabyrinthePainter implements GamePainter {

	/**
	 * la taille des cases
	 */

	public static final int UNITE_DEPLACEMENT=5;
	protected static final int WIDTH = 800;
	protected static final int HEIGHT = 500;

	private Hero hero;
	private Labyrinthe labyrinthe;
	
	public LabyrinthePainter(Hero hero)
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
		//Affichage du labyrinthe
		crayon.setColor(Color.gray);
		for(int i=0; i<this.getWidth()/10; i++) {
			for(int j=0; j<this.getHeight()/10; j++) {
				if(!this.labyrinthe.open(i, j)) {
					crayon.fillRect(i*10, j*10, 10, 10);
				}
			}
		}
		
		//Affichage du joueur
		crayon.setColor(Color.blue);
		crayon.fillOval(this.hero.getX(),this.hero.getY(),Hero.getDiameter(),Hero.getDiameter());
		
		//Affichage de la vie du joueur
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


	public void add(Labyrinthe labyrinthe) {
		this.labyrinthe = labyrinthe;
	}

}
