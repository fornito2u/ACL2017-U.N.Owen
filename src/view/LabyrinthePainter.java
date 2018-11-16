package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import engine.Game;
import engine.GamePainter;
import model.Hero;
import model.Labyrinthe;
import model.Monstre;
import model.Personnage;

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
	public static final int PROPORTION_ECRAN=10;
	public static final int DECALAGE_ECRAN_X=20;
	public static final int DECALAGE_ECRAN_Y=40;

	private Hero hero;
	private Labyrinthe labyrinthe;
	private Game game;
	
	public LabyrinthePainter(Hero hero, Labyrinthe lab, Game game)
	{
		this.game = game;
		this.hero = hero;
		this.labyrinthe = lab;
		
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
					crayon.setColor(Color.orange);
					crayon.fillRect(i*10+22, j*10+42, 6, 6);
					this.labyrinthe.setGoalX(i*10);
					this.labyrinthe.setGoalY(j*10);
					crayon.setColor(Color.gray);
				}
			}
		}
		
		//Affichage du niveau
		crayon.drawString("Niveau "+Integer.toString(this.game.getLevel()), this.getWidth()-100, 25);
		
		//Affichage du joueur
		crayon.setColor(Color.blue);
		crayon.fillOval((this.hero.getX()*PROPORTION_ECRAN)+DECALAGE_ECRAN_X,(this.hero.getY()*PROPORTION_ECRAN)+DECALAGE_ECRAN_Y,Hero.getDiameter(),Hero.getDiameter());
		
		//Affichage des monstres
		crayon.setColor(Color.red);
		for(Monstre m : this.game.getMonstreList()) {
			crayon.fillOval((m.getX()*PROPORTION_ECRAN)+DECALAGE_ECRAN_X,(m.getY()*PROPORTION_ECRAN)+DECALAGE_ECRAN_Y,Monstre.getDiameter(),Monstre.getDiameter());
			//afficherVie(crayon,m,(m.getX()*PROPORTION_ECRAN)+DECALAGE_ECRAN_X,(m.getY()*PROPORTION_ECRAN)+DECALAGE_ECRAN_Y-10);
		}

		//Affichage de la vie du joueur
		afficherVie(crayon,this.getHero(),20,17);
	}

	public void afficherVie(Graphics2D crayon, Personnage p, int x, int y) {
		if (p != null && p.getPv() > 0) {
			crayon.setColor(Color.red);
			crayon.fillRect(x, y, p.getPv()*4, 5);
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
