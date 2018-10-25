package model;

import controler.HeroController;
import engine.GameEngineGraphical;
import view.HeroPainter;
import view.MazeGame;

public class Jeu {
	
	private Hero hero;
	private HeroPainter painter;
	private HeroController controller;
	private MazeGame game;
	
	public Jeu() 
	{
		this.hero = new Hero(this);
		this.painter = new HeroPainter(hero);
		this.controller = new HeroController();
	}
	
	
	public MazeGame getGame() {
		return game;
	}


	public void setGame(MazeGame game) {
		this.game = game;
	}


	public void start() 
	{	
		// creation du jeu particulier et de son afficheur
		this.game = new MazeGame("helpFilePacman.txt",painter);
	
		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(this.game, painter, controller);
		try {
			engine.run();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
