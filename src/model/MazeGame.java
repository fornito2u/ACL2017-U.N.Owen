package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import controler.HeroController;
import engine.GameEngineGraphical;
import view.HeroPainter;
import engine.Cmd;
import engine.Game;
import model.Hero;

public class MazeGame implements Game {
	
	private Hero hero;
	private HeroPainter painter;
	private HeroController controller;
	private int limiteX;
	private int limiteY;
	
	public MazeGame(String source) 
	{
		this.hero = new Hero(this);
		this.controller = new HeroController();
		BufferedReader helpReader;
		this.painter = new HeroPainter(hero);
		this.limiteX = painter.getWidth();
		this.limiteY = painter.getHeight();
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			while ((ligne = helpReader.readLine()) != null) {
				System.out.println(ligne);
			}
			helpReader.close();
		} catch (IOException e) {
			System.out.println("Help not available");
		}
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
		System.out.println("Execute "+commande);
		if (commande.equals(Cmd.UP)) {
			this.painter.getHero().deplacer(0, -HeroPainter.UNITE_DEPLACEMENT);
		} else if (commande.equals(Cmd.DOWN)) {
			this.painter.getHero().deplacer(0,HeroPainter.UNITE_DEPLACEMENT);
		} else if (commande.equals(Cmd.LEFT)) {
			this.painter.getHero().deplacer(-HeroPainter.UNITE_DEPLACEMENT,0);
		} else if (commande.equals(Cmd.RIGHT)) {
			this.painter.getHero().deplacer(HeroPainter.UNITE_DEPLACEMENT,0);
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

}
