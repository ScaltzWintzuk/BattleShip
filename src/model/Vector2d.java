package model;

public class Vector2d {
	private int x, y;
	
	public Vector2d(int xIn, int yIn) {
		x = xIn;
		y = yIn;
	}
	
	public Vector2d() { this(0, 0); }
	
	// Methods
	public void setVector(int xIn, int yIn) {
		x = xIn;
		y = yIn;
	}
	
	public void print() { System.out.printf("<%d, %d>\n", x, y); }
	public void rowInc() { y++; }
	public void rowDec() { y--; }
	public void colInc() { x++; }
	public void colDec() { y--; }
	
	// Getters
	public int xPos() { return x; }
	public int yPos() { return y; }
	public int getRow() { return y; }
	public int getCol() { return x; }
	
	// Setters
	public void updateX(int xIn) { x = xIn; }
	public void updateY(int yIn) { y = yIn; }
	public void setRow(int rowIn) { updateY(rowIn); }
	public void setCol(int colIn ) { updateX(colIn); }
}
