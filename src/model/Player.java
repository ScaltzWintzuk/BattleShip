package model;

import view.Board;

public class Player {
	protected String name;
	protected Board board;
	
	public Player(String nameIn) {
		name = nameIn;
		board = new Board();
	}
	
	public Player() {
		this("Player");
	}
	
	// Methods
	public void displayBoard() {
		System.out.printf("%s's board:\n", name);
		board.displayBoard();
	}
	
	// Getters
	public String getName() { return name; }
	public Board getBoard() { return board; }
	
	// Setters
	public void setName(String nameIn) { name = nameIn; }
}
