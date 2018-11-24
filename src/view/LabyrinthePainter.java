package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

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

	public static final int UNITE_DEPLACEMENT=3;
	public static final int PROPORTION_ECRAN=20;
	public static final int DECALAGE_ECRAN_X=20;
	public static final int DECALAGE_ECRAN_Y=40;

	private Hero hero;
	private Labyrinthe labyrinthe;
	private Game game;
	private int compteurVortex = 0;
	
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
		Image mur= new ImageIcon("images/mur.png").getImage();
		Image sol= new ImageIcon("images/sol.png").getImage();
		Image tresor= new ImageIcon("images/tresor.png").getImage();
		Image vortex= new ImageIcon("images/vortex.png").getImage();
		for(int i=0; i<(this.getWidth()-40)/20; i++) {
			for(int j=0; j<(this.getHeight()-60)/20; j++) {
				if(!this.labyrinthe.open(i, j)) {
					crayon.drawImage(mur, i*20+20, j*20+40, null);
				} else {
					crayon.drawImage(sol, i*20+20, j*20+40, null);
				}
				if(this.labyrinthe.isGoal(i, j)) {
					this.labyrinthe.setGoalX(i*20);
					this.labyrinthe.setGoalY(j*20);
					crayon.drawImage(tresor, i*20+22, j*20+42, null);
				}
				if(this.labyrinthe.isVortex(i, j)) {
					this.labyrinthe.setVortexX(i*20);
					this.labyrinthe.setVortexY(j*20);
					crayon.drawImage(vortex, 
							i*20+22, 
							j*20+42,
							i*20+22+20, 
							j*20+42+20,
							compteurVortex/10*30,0,
							compteurVortex/10*30+30,30,
							null);
					if(compteurVortex == 140) {
						compteurVortex=0;
					} else {
						compteurVortex++;
					}
				}
			}
		}
		
		//Affichage du niveau
		crayon.setColor(Color.gray);
		crayon.drawString("Niveau "+Integer.toString(this.game.getLevel()), this.getWidth()-100, 25);
		
		//Affichage des points
		crayon.setColor(Color.gray);
		crayon.drawString(Integer.toString(this.hero.getPoints())+" points", this.getWidth()/2, 25);
		
		//Affichage du joueur
		Image hero= new ImageIcon("images/hero.png").getImage();
		crayon.drawImage(hero, 
				(this.hero.getX()*PROPORTION_ECRAN)+DECALAGE_ECRAN_X,
				(this.hero.getY()*PROPORTION_ECRAN)+DECALAGE_ECRAN_Y-10,
				(this.hero.getX()*PROPORTION_ECRAN)+DECALAGE_ECRAN_X+30,
				(this.hero.getY()*PROPORTION_ECRAN)+DECALAGE_ECRAN_Y+20,
				this.hero.getCompteur()*30,this.hero.getDirection()*30,
				this.hero.getCompteur()*30+30,this.hero.getDirection()*30+30,
				null);
		//Affichage des monstres
		Image monstre= new ImageIcon("images/monstre.png").getImage();
		Image fantom= new ImageIcon("images/fantom.png").getImage();
		
		for(Monstre m : this.game.getMonstreList()) {	
			//afficherVie(crayon,m,(m.getX()*PROPORTION_ECRAN)+DECALAGE_ECRAN_X,(m.getY()*PROPORTION_ECRAN)+DECALAGE_ECRAN_Y-10);
			if (m.isFantom()) {
				crayon.drawImage(fantom, 
						(m.getX()*PROPORTION_ECRAN)+DECALAGE_ECRAN_X,
						(m.getY()*PROPORTION_ECRAN)+DECALAGE_ECRAN_Y-10,
						(m.getX()*PROPORTION_ECRAN)+DECALAGE_ECRAN_X+30,
						(m.getY()*PROPORTION_ECRAN)+DECALAGE_ECRAN_Y+20,
						m.getCompteur()*30,m.getDirection()*30,
						m.getCompteur()*30+30,m.getDirection()*30+30,
						null);
			} else {
				crayon.drawImage(monstre, 
						(m.getX()*PROPORTION_ECRAN)+DECALAGE_ECRAN_X,
						(m.getY()*PROPORTION_ECRAN)+DECALAGE_ECRAN_Y-10,
						(m.getX()*PROPORTION_ECRAN)+DECALAGE_ECRAN_X+30,
						(m.getY()*PROPORTION_ECRAN)+DECALAGE_ECRAN_Y+20,
						m.getCompteur()*30,m.getDirection()*30,
						m.getCompteur()*30+30,m.getDirection()*30+30,
						null);
			}
		}

		crayon.setColor(Color.red);
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
		return this.labyrinthe.getWidth()*20+40;
	}

	@Override
	public int getHeight() {
		return this.labyrinthe.getHeight()*20+60;
	}
}
