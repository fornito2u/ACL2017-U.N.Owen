package model;

public abstract class Personnage {
	
	
	protected static final int BORDURE_ECRAN=70;

	
	protected int x;
	protected int y;
	protected int pv;
	
	
	public Personnage() {
		this.pv = 20;
	}

	abstract public void deplacer(int x, int y);
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	abstract public void attaquer(Personnage p);

	protected abstract int getPoints();

	
}
