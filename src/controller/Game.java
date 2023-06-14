package controller;

import java.util.Scanner;

import model.Bot;
import model.Player;
import model.Ship;

public class Game {
	private Scanner reader;
	
	private Player player;
	private Bot bot;
	
	private Ship[] ships;
	
	public Game() {
		reader = new Scanner(System.in);
		init();
	}
	
	public void init() {
		// Game initializing
		introduction();
		createShips();
		placeShips(player);
		placeShips(bot);
		
		// Game starting
		loop();
	}
	
	public void introduction() {
		System.out.println("Type a username: ");
		String username = reader.nextLine();
		
		player = new Player(username);
		bot = new Bot();
	}
	
	// Logic
	public void createShips() {
		ships = new Ship[6];
		ships[0] = new Ship("Carrier", 5);
		ships[1] = new Ship("Battleship", 4);
		ships[2] = new Ship("Submarine 1", 3);
		ships[3] = new Ship("Submarine 2", 3);
		ships[4] = new Ship("Destroyer 1", 2);
		ships[5] = new Ship("Destroyer 2", 2);
	}
	
	public void placeShips(Player player) {
		int shipsPlaced = 0;
		
		while (shipsPlaced < ships.length) {
			int orientation = randomInteger(0, 1);
			
			int rowPlaced = randomInteger(0, (orientation == 0) ? player.getBoard().getRows() - ships[shipsPlaced].getLength() : player.getBoard().getRows());
			int colPlaced = randomInteger(0, (orientation == 0) ? player.getBoard().getCols() : player.getBoard().getCols() - ships[shipsPlaced].getLength());
			
			if (!checkIntersection(player, ships[shipsPlaced], rowPlaced, colPlaced, orientation)) {
				// Vertical, Top Down, Row Changes
				if (orientation == 0) {
					for (int iterator = rowPlaced; iterator < rowPlaced + ships[shipsPlaced].getLength(); iterator++) {
						player.getBoard().placeShipTile(iterator, colPlaced);
					}
				}
				
				// Horizontal, Left to Right, Col Changes
				else if (orientation == 1) {
					for (int iterator = colPlaced; iterator < colPlaced + ships[shipsPlaced].getLength(); iterator++) {
						player.getBoard().placeShipTile(rowPlaced, iterator);
					}
				}
				shipsPlaced++;
			}
		}
	}
	
	public boolean checkIntersection(Player player, Ship ship, int rowPlaced, int colPlaced, int orientation) {
		// Vertical, Top Down, Row Changes
		if (orientation == 0) {
			for (int iterator = rowPlaced; iterator < rowPlaced + ship.getLength(); iterator++) {
				if (player.getBoard().getContents()[iterator][colPlaced] != player.getBoard().WATER_SYMBOL) {
					return true;
				}
			}
		}
		
		// Horizontal, Left to Right, Col Changes
		else if (orientation == 1) {
			for (int iterator = colPlaced; iterator < colPlaced + ship.getLength(); iterator++) {
				if (player.getBoard().getContents()[rowPlaced][iterator] != player.getBoard().WATER_SYMBOL) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void loop() {
		do {
			while (!hasPlayerLost() && !hasBotLost()) {
				// Player Turn
				int col = userInput("Enter the Column you wish to Target (X): ", "Out of range of the board", 0, bot.getBoard().getCols());
				int row = userInput("Enter the Row you wish to Target (Y): ", "Out of range of the board", 0, bot.getBoard().getRows());
				
				
				
				// Bot Turn
			}
			
			if (hasBotLost()) { System.out.println("You have won!"); }
			if (hasPlayerLost()) { System.out.println("You have lost."); }
			
		} while (askToPlayAgain());
	}
	
	public boolean hasPlayerLost() {
		return false;
	}
	
	public boolean hasBotLost() {
		return false;
	}
	
	public int userInput(String prompt, String errorMessage, int lower, int upper) {
		int value;
		System.out.println(prompt);
		do {
			value = reader.nextInt();
			if (value < lower || value > upper) { System.out.println(errorMessage); }
		} while (value < lower || value > upper);
		
		return value;
	}
	
	public boolean askToPlayAgain() {
		char answer;
		System.out.println("Would you like to play again? (Y/N)");
		do {
			answer = reader.nextLine().toUpperCase().charAt(0);
			if (answer != 'Y' && answer != 'N') { System.out.println("Error, Y or N are the only valid answers."); }
		} while (answer != 'Y' && answer != 'N');
		
		return (answer == 'Y');
	}
	
	// Utilities
	public int randomInteger(int lower, int upper) {
		return (int) (lower + (Math.random() * (upper + 1)));
	}
	
	public double randomDouble(double lower, double upper) {
		return (double) (lower + (Math.random() * (upper + 1)));
	}
	
	public char randomChar(int lower, int upper) {
		return (char) randomInteger(lower, (upper + 1));
	}
}
