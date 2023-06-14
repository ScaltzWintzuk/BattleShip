package model;

import view.Board;

public class Bot extends Player {
	private int botLevel = 3;
	
	public Bot() {
		super("Bot");
	}
	
	public void analyzeBoard(Board board) {
		
	}
	
	// Getter
	public int getBotLevel() { return botLevel; }
	
	// Setter
	public void setBotLevel(int level) { botLevel = level; }
}
