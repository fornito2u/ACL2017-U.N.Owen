package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import engine.GamePainter;
import model.Hero;
import model.Labyrinthe;
import model.Monstre;

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

	private Hero hero;
	private Labyrinthe labyrinthe;
	private ArrayList<Monstre> monstreList;
	
	public LabyrinthePainter(Hero hero, Labyrinthe lab,ArrayList<Monstre> monstres)
	{
		this.hero = hero;
		this.labyrinthe = lab;
		this.monstreList=monstres;
		
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
		for(int i=0; i<(this.getWidth()-40)/10; i++) {
			for(int j=0; j<(this.getHeight()-60)/10; j++) {
				if(!this.labyrinthe.open(i, j)) {
					crayon.fillRect(i*10+20, j*10+40, 10, 10);
				}
				if(this.labyrinthe.isGoal(i, j)) {
					crayon.setColor(Color.blue);
					crayon.fillOval(i*10+20, j*10+40, 10, 10);
					crayon.setColor(Color.gray);
				}
			}
		}
		
		//Affichage du joueur
		crayon.setColor(Color.blue);
		crayon.fillOval(this.hero.getX(),this.hero.getY(),Hero.getDiameter(),Hero.getDiameter());
		
		//Affichage des monstres
		crayon.setColor(Color.red);
		for(Monstre m : monstreList) {
			crayon.fillOval(m.getX(),m.getY(),Monstre.getDiameter(),Monstre.getDiameter());
		}
		
		
		//Affichage de la vie du joueur
		if(this.hero.getPv()>0)
		{
			crayon.setColor(Color.red);
			crayon.fillRect(20, 17, this.hero.getPv()*10, 5);
		}
		
		
	}

	@Override
	public int getWidth() {
		return this.labyrinthe.getWidth()*10+40;
	}

	@Override
	public int getHeight() {
		return this.labyrinthe.getHeight()*10+60;
	}
}
