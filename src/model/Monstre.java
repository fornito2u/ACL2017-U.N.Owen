package model;

public class Monstre extends Personnage {
	private MazeGame jeu;
	
	public Monstre()
	{
		super();
		this.x=15;
		this.y=15;
	}

	@Override
	public void deplacer(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attaquer(Personnage p) {
		p.setPv(p.getPv()-1);
	}
	
	
}
