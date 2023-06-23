/**
 * 
 */
package model;

import java.util.ArrayList;

import view.Board;

public class Bot extends Player {
	private ArrayList<Vector2d> hits;
	private ArrayList<Vector2d> misses;
	
	private int difficulty;
	
	public Bot(int difficultyIn) {
		name = "Bot";
		board = new Board(false);
		
		initShips();
		tileAmountLeft();
		
		difficulty = difficultyIn;
		
		hits = new ArrayList();
		misses = new ArrayList();
	}
	
	/**
	 * Default difficulty is 3
	 */
	public Bot() {
		this(3);
	}
	
	public void analyzeBoard(Player player) {
		Vector2d location = new Vector2d(0, 0);
		sleep(1000);
		
		if (hits.isEmpty() || difficulty == 1) {
			location = chooseRandomLocation();
			
			while (!isValid(location)) {
				location = chooseRandomLocation();
			}
		}
		else if (difficulty >= 4) {
			
		}
		else {
			int randomLocation = randomInteger(0, hits.size() - 1);
			
			do {
				randomLocation = randomInteger(0, hits.size() - 1);
				location = new Vector2d(hits.get(randomLocation).getCol(), hits.get(randomLocation).getRow());
				
				location.print();
				
				sleep(1000);
				
				int direction = randomInteger(0, 3);
				System.out.print(direction);
				
				// 0 up, 1 right, 2 down, 3 left
				switch (direction) {
				case 0: location.rowInc(); break;
				case 1: location.colInc(); break;
				case 2: location.rowDec(); break;
				case 3: location.colDec(); break;
				default: System.err.println("[Error]: Default case has been reached in analyzeBoard() method in the Bot class"); break;
				}
			} while (!isValid(location));
		}
		
		updateBoard(player, location);
	}
	
	public boolean isValid(Vector2d location) {
		if (location.getCol() < 0) { return false; }
		if (location.getRow() < 0) { return false; }
		if (location.getCol() > board.getCols() - 1) { return false; }
		if (location.getRow() > board.getRows() - 1) { return false; }
		if (!isVectorValid(misses, location)) { return false; }
		if (!isVectorValid(hits, location)) { return false; }
		
		return true;
	}
	
	public boolean isVectorValid(ArrayList<Vector2d> vectors, Vector2d location) {
		for (Vector2d vector : vectors) {
			if ((location.getCol() == vector.getCol()) && (location.getRow() == vector.getRow())) {
				return false;
			}
		}
		return true;
	}
	
	public boolean updateBoard(Player player, Vector2d location) {
		if (player.board.getContents()[location.getRow()][location.getCol()] == board.HIT_SYMBOL ||
				player.board.getContents()[location.getRow()][location.getCol()] == board.MISS_SYMBOL) {
			System.out.println("Already attacked this position");
			return false;
		}
		else {
			if (player.board.getContents()[location.getRow()][location.getCol()] == player.board.WATER_SYMBOL) {
				misses.add(location);
				player.board.miss(location.getRow(), location.getCol());
			}
			else if (player.board.getContents()[location.getRow()][location.getCol()] >= 'A' || player.board.getContents()[location.getRow()][location.getCol()] <= 'A' + player.ships.length - 1) {
				hits.add(location);
				player.board.hit(location.getRow(), location.getCol());
				player.ships[player.board.getContents()[location.getRow()][location.getCol()] - 'A'].decrementLength();
			}
		}
		return true;
	}
	
	public Vector2d chooseRandomLocation() {
		return new Vector2d(randomInteger(0, board.getCols() - 1), randomInteger(0, board.getRows() - 1));
	}
	
	public void displayHits() { displayArrayList("Bot's Hits", hits); }
	public void displayMisses() { displayArrayList("Bot's Misses", misses); }
	
	public void displayArrayList(String name, ArrayList<Vector2d> list) {
		System.out.println("ArrayList for: " + name);
		for (Vector2d location : list) {
			System.out.printf("<%d %d>\n", location.getCol(), location.getRow());
		}
		System.out.println();
	}
	
	// Getter
	public int getDifficulty() { return difficulty; }
	
	// Setter
	public void setDifficulty(int difficultyIn) { difficulty = difficultyIn; }
	
	// Utilities
	public int randomInteger(int lower, int upper) { return (int) (lower + (Math.random() * (upper + 1))); }
	public double randomDouble(double lower, double upper) { return (double) (lower + (Math.random() * (upper + 1))); }  
	public char randomChar(int lower, int upper) { return (char) randomInteger(lower, (upper + 1)); }
}
