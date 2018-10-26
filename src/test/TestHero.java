package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.Test;

import model.Hero;
import model.MazeGame;

public class TestHero {
	
	@Test
	public void testDeplacerXRight() {
		MazeGame mg=new MazeGame("",154235);
		Hero hero=mg.getHero();
		int initialX=hero.getX();
		int initialY=hero.getY();
		hero.deplacer(3, 0);
		assertEquals(initialX+3, hero.getX());
		assertEquals(initialY, hero.getY());
	}
	
	@Test
	public void testDeplacerYRight() {
		MazeGame mg=new MazeGame("",154235);
		Hero hero=mg.getHero();
		int initialX=hero.getX();
		int initialY=hero.getY();
		hero.deplacer(0, -10);
		assertEquals(initialX, hero.getX());
		assertEquals(initialY-10, hero.getY());
	}
	
	@Test
	public void testDeplacerBoundary() {
		MazeGame mg=new MazeGame("",154235);
		Hero hero=mg.getHero();
		int initialX=hero.getX();
		int initialY=hero.getY();
		hero.deplacer(-999, -999);
		assertEquals(initialX, hero.getX());
		assertEquals(initialY, hero.getY());
	}
}
