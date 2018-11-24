package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import controler.HeroController;
import engine.GameEngineGraphical;
import view.LabyrinthePainter;
import engine.Cmd;
import engine.Game;

public class MazeGame implements Game {
	
	public static final int DEFAULT_WIDTH=50;
	public static final int DEFAULT_HEIGHT=25;
	public static final int DEFAULT_NB_MONSTRE=10;


	//Gestion des cooldowns grâce aux cycles
	//Un cycle est égal à 20ms (voir GameEngineGraphical - run() - Thread.sleep)
	private int cycle;

	//Le cooldown en nombre de cycles
	private static int HEROS_MOVE_COOLDOWN=3; // 60ms
	private static int MONSTER_MOVE_COOLDOWN=8; //160ms
	private static int FANTOM_MOVE_COOLDOWN=10; //200ms
	private static int HEROS_ATTACK_COOLDOWN=3; //60ms
	private static int MONSTER_ATTACK_COOLDOWN=8; //160ms
	private static int FANTOM_ATTACK_COOLDOWN=10; //200ms
	private static int NB_FANTOM = 0;

	private boolean herosCanMove;
	private boolean monsterCanMove;
	private boolean fantomCanMove;
	private boolean herosCanAttack;
	private boolean monsterCanAttack;
	private boolean fantomCanAttack;

	private Hero hero;
	private ArrayList<Monstre> monstreList;
	private LabyrinthePainter painter;
	private HeroController controller;
	private Labyrinthe labyrinthe;
	private int limiteX;
	private int limiteY;
	private int level;
	
	public MazeGame() throws IOException {
		this.cycle=0;
		this.herosCanMove=true;
		this.monsterCanMove=true;
		this.herosCanAttack=true;
		this.monsterCanAttack=true;
		this.labyrinthe = new Labyrinthe();
		this.hero = new Hero(this);
		this.monstreList=new ArrayList<>();
		for(int j = 0; j < NB_FANTOM; ++j) {
			this.monstreList.add(new Fantom(this));
		}
		for(int i=0;i<DEFAULT_NB_MONSTRE-NB_FANTOM;i++) {
			this.monstreList.add(new Monstre(this));
		}
		this.controller = new HeroController();
		this.painter = new LabyrinthePainter(hero, labyrinthe, this);
		this.limiteX = painter.getWidth()-20;
		this.limiteY = painter.getHeight()-30;
		this.setLevel(1);
	}
	
	public MazeGame(long seed) {
		this.cycle=0;
		this.herosCanMove=true;
		this.monsterCanMove=true;
		this.herosCanAttack=true;
		this.monsterCanAttack=true;
		this.labyrinthe = new Labyrinthe(DEFAULT_WIDTH,DEFAULT_HEIGHT,seed);
		this.hero = new Hero(this);
		this.monstreList=new ArrayList<>();
		for(int j = 0; j < NB_FANTOM; ++j) {
			this.monstreList.add(new Fantom(this));
		}
		for(int i=0;i<DEFAULT_NB_MONSTRE-NB_FANTOM;i++) {
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


	private void newCycle() {
		cycle++;
		if (cycle%HEROS_MOVE_COOLDOWN == 0) {
			herosCanMove=true;
		}
		if (cycle%MONSTER_MOVE_COOLDOWN == 0) {
			monsterCanMove=true;
		}
		if (cycle%FANTOM_MOVE_COOLDOWN == 0) {
			fantomCanMove=true;
		}
		if (cycle%HEROS_ATTACK_COOLDOWN == 0) {
			herosCanAttack=true;
		}
		if (cycle%MONSTER_ATTACK_COOLDOWN == 0) {
			monsterCanAttack=true;
		}
		if (cycle%FANTOM_ATTACK_COOLDOWN == 0) {
			fantomCanAttack=true;
		}
	}

	private void heroTryToMove(int x, int y) {
		if (herosCanMove) {
			this.hero.deplacer(x, y);
			herosCanMove=false;
		}
	}

	private void monstersTryToMove() {
		if (monsterCanMove) {
			deplacerMonstre();
			monsterCanMove=false;
		}
		if (fantomCanMove) {
			deplacerFantom();
			fantomCanMove=false;
		}
	}


	private void heroTryToAttack() {
		if (herosCanAttack) {
			for (Monstre m : monstreList) {
				if (m.getX() >= hero.getX() - 1 && m.getX() <= hero.getX() + 1 && m.getY() >= hero.getY() - 1 && m.getY() <= hero.getY() + 1) {
					this.hero.attaquer(m);
				}
			}
			herosCanAttack=false;
		}
	}


	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande
	 */
	@Override
	public void evolve(Cmd commande) {
		newCycle();
		if (commande.equals(Cmd.UP)) {
			heroTryToMove(0,-1);
		} else if (commande.equals(Cmd.DOWN)) {
			heroTryToMove(0,1);
		} else if (commande.equals(Cmd.LEFT)) {
			heroTryToMove(-1,0);
		} else if (commande.equals(Cmd.RIGHT)) {
			heroTryToMove(1,0);
		} else if (commande.equals(Cmd.SPACE)) {
			heroTryToAttack();
		}
		monstersTryToMove();
		supprimerMonstre();
	}
	
	/**
	 * Deplacer les monstres
	 * On donne en parametre les coordonnées du héro pour que les monstres s'en approchent
	 */
	
	public void deplacerMonstre() {
		for(Monstre m : monstreList) {
			if (!m.isFantom()) {
				m.deplacer(hero.getX(), hero.getY());
			}
		}
	}
	public void deplacerFantom() {
		for(Monstre m : monstreList) {
			if (m.isFantom()) {
				m.deplacer(hero.getX(), hero.getY());
			}
		}
	}


	/**
	 * verifier si le jeu est fini
	 * @throws InterruptedException 
	 */
	@Override
	public boolean isFinished() throws InterruptedException {
		if(this.hero.getX()*20 == this.labyrinthe.getGoalX() && this.hero.getY()*20 == this.labyrinthe.getGoalY()) {
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

	public void changeLevel() {
		this.setLevel(this.level + 1);
		this.hero.reInit();
		this.monstreReinit();
		this.labyrinthe.reInit(this.level);
	}

	private void monstreReinit() {
		this.monstreList=new ArrayList<>();
		for(int j = 0; j < NB_FANTOM; ++j) {
			this.monstreList.add(new Fantom(this));
		}
		for(int i=0;i<DEFAULT_NB_MONSTRE-NB_FANTOM;i++) {
			this.monstreList.add(new Monstre(this));
		}
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	
	public ArrayList<Monstre> getMonstreList() {
		return monstreList;
	}

	public void supprimerMonstre() {
		for(int i=0;i<monstreList.size();i++){
			if(monstreList.get(i).getPv()<=0){
				monstreList.remove(i);
			}
		}
	}
	
}
