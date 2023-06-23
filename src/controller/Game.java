package controller;

import java.util.Scanner;

import model.Bot;
import model.Player;
import model.Ship;

public class Game {
	private Scanner reader;
	
	private Player player;
	private Bot bot;
	
	public Game() {
		reader = new Scanner(System.in);
		init();
	}
	
	public void init() {
		// Game initializing
		introduction();
		placeShips(player);
		placeShips(bot);
		
		// Game starting
		loop();
	}
	
	public void introduction() {
		System.out.println("Type a username: ");
		String username = reader.nextLine();
		
		int difficulty = userInput("What difficulty do you want the bot to be? \n1 = Easy \n2 = Medium \n3 = Hard", "Error, please enter 1 through 3 for difficulty", 1, 3);
		
		player = new Player(username);
		bot = new Bot(difficulty);
	}
	
	// Logic
	
	public void placeShips(Player player) {
		int shipsPlaced = 0;
		
		Ship[] ships = player.getShips();
		
		while (shipsPlaced < ships.length) {
			int orientation = randomInteger(0, 1);
			
			int rowPlaced = randomInteger(0, (orientation == 0) ? player.getBoard().getRows() - ships[shipsPlaced].getLength() - 1 : player.getBoard().getRows() - 1);
			int colPlaced = randomInteger(0, (orientation == 0) ? player.getBoard().getCols() - 1 : player.getBoard().getCols() - ships[shipsPlaced].getLength() - 1);
			
			if (!checkIntersection(player, ships[shipsPlaced], rowPlaced, colPlaced, orientation)) {
				// Vertical, Top Down, Row Changes
				if (orientation == 0) {
					for (int iterator = rowPlaced; iterator < rowPlaced + ships[shipsPlaced].getLength(); iterator++) {
						player.getBoard().updateTile(iterator, colPlaced, (char) ('A' + shipsPlaced));
					}
				}
				
				// Horizontal, Left to Right, Col Changes
				else if (orientation == 1) {
					for (int iterator = colPlaced; iterator < colPlaced + ships[shipsPlaced].getLength(); iterator++) {
						player.getBoard().updateTile(rowPlaced, iterator, (char) ('A' + shipsPlaced));
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
				// Player's Turn
				bot.displayBoard();
				
				int col = userInput("Enter the Column you wish to Target (X): ", "Out of range of the board", 0, bot.getBoard().getCols() - 1);
				int row = userInput("Enter the Row you wish to Target (Y): ", "Out of range of the board", 0, bot.getBoard().getRows() - 1);
				
				if (bot.getBoard().getContents()[row][col] == bot.getBoard().MISS_SYMBOL || bot.getBoard().getContents()[row][col] == bot.getBoard().HIT_SYMBOL) {
					System.out.println(row + " : " + col + " has already been hit!");
				}
				else {
					if (bot.getBoard().getContents()[row][col] >= 'A' && bot.getBoard().getContents()[row][col] <= 'A' + bot.getShips().length - 1) {
						bot.getBoard().hit(row, col);
						bot.getShip(bot.getBoard().getContents()[row][col]).decrementLength();
					}
					
					else {
						bot.getBoard().miss(row, col);
					}
					
					// Bot's Turn
					bot.analyzeBoard(player);
					player.displayBoard();
				}
			}
			
			if (hasBotLost()) { System.out.println("You have won!"); }
			if (hasPlayerLost()) { System.out.println("You have lost."); }
			
		} while (askToPlayAgain());
	}
	
	public void displaySunkenShips() {
		
	}
	
	public boolean hasPlayerLost() { return player.tilesRemaining() <= 0; }
	public boolean hasBotLost() { return bot.tilesRemaining() <= 0; }
	
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
	
	// Utility Functions
	public int randomInteger(int lower, int upper) {
		return (int) (lower + (Math.random() * (upper + 1)));
	}
	
	public double randomDouble(double lower, double upper) {
		return (double) (lower + (Math.random() * (upper + 1)));
	}
	
	public char randomChar(int lower, int upper) {
		return (char) randomInteger(lower, (upper + 1));
	}
	
	// Getters
	public Player getPlayer() { return player; }
	public Bot getBot() { return bot; }
}
