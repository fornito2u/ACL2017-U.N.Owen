package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import controler.HeroController;
import engine.GameEngineGraphical;
import view.LabyrinthePainter;
import engine.Cmd;
import engine.Game;
import model.Hero;

public class MazeGame implements Game {
	
	private Hero hero;
	private LabyrinthePainter painter;
	private HeroController controller;
	private Labyrinthe labyrinthe;
	private int limiteX;
	private int limiteY;
	
	public MazeGame(String source) throws IOException 
	{
		this.labyrinthe = new Labyrinthe(new File("labyrinthe.txt"));
		this.hero = new Hero(this);
		this.controller = new HeroController();
		this.painter = new LabyrinthePainter(hero, labyrinthe);
		this.limiteX = painter.getWidth()-20;
		this.limiteY = painter.getHeight()-30;
		BufferedReader helpReader;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			while ((ligne = helpReader.readLine()) != null) {
				System.out.println(ligne);
			}
			helpReader.close();
		} catch (IOException e) {
			//System.out.println("Help not available");
		}
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
			this.painter.getHero().deplacer(0, -LabyrinthePainter.UNITE_DEPLACEMENT);
		} else if (commande.equals(Cmd.DOWN)) {
			this.painter.getHero().deplacer(0,LabyrinthePainter.UNITE_DEPLACEMENT);
		} else if (commande.equals(Cmd.LEFT)) {
			this.painter.getHero().deplacer(-LabyrinthePainter.UNITE_DEPLACEMENT,0);
		} else if (commande.equals(Cmd.RIGHT)) {
			this.painter.getHero().deplacer(LabyrinthePainter.UNITE_DEPLACEMENT,0);
		}
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		// le jeu n'est jamais fini
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


}
