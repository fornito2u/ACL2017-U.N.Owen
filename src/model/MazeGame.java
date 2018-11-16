package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import controler.HeroController;
import engine.GameEngineGraphical;
import view.LabyrinthePainter;
import engine.Cmd;
import engine.Game;
import model.Hero;

public class MazeGame implements Game {
	
	public static final int DEFAULT_WIDTH=50;
	public static final int DEFAULT_HEIGHT=25;
	public static final int DEFAULT_NB_MONSTRE=10;
	
	private Hero hero;
	private ArrayList<Monstre> monstreList;
	private LabyrinthePainter painter;
	private HeroController controller;
	private Labyrinthe labyrinthe;
	private int limiteX;
	private int limiteY;
	private int level;
	
	public MazeGame(String labyrinthFilename) throws IOException {
		this.labyrinthe = new Labyrinthe(new File(labyrinthFilename));
		this.hero = new Hero(this);
		this.monstreList=new ArrayList<>();
		for(int i=0;i<DEFAULT_NB_MONSTRE;i++) {
			this.monstreList.add(new Monstre(this));
		}
		this.controller = new HeroController();
		this.painter = new LabyrinthePainter(hero, labyrinthe, this);
		this.limiteX = painter.getWidth()-20;
		this.limiteY = painter.getHeight()-30;
		this.setLevel(1);
	}
	
	public MazeGame(long seed) {
		this.labyrinthe = new Labyrinthe(DEFAULT_WIDTH,DEFAULT_HEIGHT,seed);
		this.hero = new Hero(this);
		this.monstreList=new ArrayList<>();
		for(int i=0;i<DEFAULT_NB_MONSTRE;i++) {
			this.monstreList.add(new Monstre(this));
		}
		this.controller = new HeroController();
		this.painter = new LabyrinthePainter(hero, labyrinthe, this);
		this.limiteX = painter.getWidth()-20;
		this.limiteY = painter.getHeight()-30;
	}

	public Hero getHero() {
		return hero;
	}

	public void start() 
	{	
		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(this, this.painter, this.controller);
		try {
			engine.run();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande
	 */
	@Override
	public void evolve(Cmd commande) {
		if (commande.equals(Cmd.UP)) {
			this.hero.deplacer(0, -1);
		} else if (commande.equals(Cmd.DOWN)) {
			this.hero.deplacer(0,1);
		} else if (commande.equals(Cmd.LEFT)) {
			this.hero.deplacer(-1,0);
		} else if (commande.equals(Cmd.RIGHT)) {
			this.hero.deplacer(1,0);
		} else if (commande.equals(Cmd.SPACE)) {
			for(Monstre m : monstreList) {
				if(m.getX() >= hero.getX() - 1 && m.getX() <= hero.getX() + 1 && m.getY() >= hero.getY() - 1 && m.getY() <= hero.getY() + 1)
				{
					this.hero.attaquer(m);
				}
			}
		}
		//deplacerMonstre();

	}
	
	/**
	 * Deplacer les monstres
	 * On donne en parametre les coordonnées du héro pour que les monstres s'en approchent
	 */
	
	public void deplacerMonstre() {
		for(Monstre m : monstreList) {
			m.deplacer(hero.getX(), hero.getY());
		}
	}


	/**
	 * verifier si le jeu est fini
	 * @throws InterruptedException 
	 */
	@Override
	public boolean isFinished() throws InterruptedException {
		if(this.hero.getX()*10 == this.labyrinthe.getGoalX() && this.hero.getY()*10 == this.labyrinthe.getGoalY()) {
			this.changeLevel();
			Thread.sleep(1000);
			return false;
			
		}
		return false;
	}

	public int getLimiteX() {
		return limiteX;
	}

	public void setLimiteX(int limiteX) {
		this.limiteX = limiteX;
	}

	public int getLimiteY() {
		return limiteY;
	}

	public void setLimiteY(int limiteY) {
		this.limiteY = limiteY;
	}
	
	public Labyrinthe getLabyrinthe() {
		return labyrinthe;
	}


	public void setLabyrinthe(Labyrinthe labyrinthe) {
		this.labyrinthe = labyrinthe;
	}
	
	public int getPosX() {
		return this.labyrinthe.getPosX();
	}
	
	public int getPosY() {
		return this.labyrinthe.getPosY();
	}

	public ArrayList<Monstre> getMonstreList() {
		return monstreList;
	}
	
	public void changeLevel() {
		this.setLevel(this.level + 1);
		this.hero.reInit();
		this.monstreReinit();
		this.tresorReinit();
	}

	private void tresorReinit() {
		this.labyrinthe.tresorReinit();
	}

	private void monstreReinit() {
		this.monstreList=new ArrayList<>();
		for(int i=0;i<DEFAULT_NB_MONSTRE;i++) {
			this.monstreList.add(new Monstre(this));
		}
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public ArrayList<Monstre> getMontreList() {
		return this.monstreList;
	}
}
