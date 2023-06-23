package model;

import view.Board;

public class Player {
	protected String name;
	protected Board board;
	
	protected int tilesLeft;
	
	protected Ship[] ships;
	
	public Player(String nameIn) {
		name = nameIn;
		board = new Board(true);
		
		initShips();
		tileAmountLeft();
	}
	
	public Player() {
		this("Player");
	}
	
	public void initShips() {
		ships = new Ship[6];
		ships[0] = new Ship("Carrier", 5);
		ships[1] = new Ship("Battleship", 4);
		ships[2] = new Ship("Submarine 1", 3);
		ships[3] = new Ship("Submarine 2", 3);
		ships[4] = new Ship("Destroyer 1", 2);
		ships[5] = new Ship("Destroyer 2", 2);
	}
	
	public int tileAmountLeft() {
		tilesLeft = 0;
		for (int i = 0; i < ships.length; i++) {
			tilesLeft += ships[i].getLength();
		}
		
		return tilesLeft;
	}
	
	// Methods
	public void displayBoard() {
		System.out.printf("%s's board:\n", name);
		board.displayBoard();
	}
	
	public void sleep(int value) {
		try {
			Thread.sleep(value);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// Getters
	public String getName() { return name; }
	public Board getBoard() { return board; }
	public Ship[] getShips() { return ships; }
	public Ship getShip(int index) { return ships[index]; }
	
	public int tilesRemaining() {
		int tilesLeft = 0;
		for (Ship s : ships) { tilesLeft += s.getLength(); }
		return tilesLeft;
	}
	
	// Setters
	public void setName(String nameIn) { name = nameIn; }
	public void hit() { tilesLeft = (tilesLeft <= 0) ? 0 : tilesLeft - 1; }
}
