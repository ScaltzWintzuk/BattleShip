package view;

public class Board {
	private boolean cheatsOn = false;
	private boolean isPlayer;
	
	public final char HIT_SYMBOL = 'X';
	public final char MISS_SYMBOL = 'O';
	public final char SHIP_SYMBOL = 'S';
	public final char WATER_SYMBOL = '*';
	
	private char[][] contents;
	
	public Board(int rows, int cols, boolean isPlayerIn) {
		contents = new char[rows][cols];
		isPlayer = isPlayerIn;
		
		defaultBoard();
	}
	
	public Board(boolean isPlayerIn) { this(10, 10, isPlayerIn); }
	
	// Methods
	public void displayBoard() {
		for (int row = 0; row < contents.length; row++) {
			for (int col = 0; col < contents[row].length; col++) {
				if (contents[row][col] >= 'A' && contents[row][col] <= 'F' && !cheatsOn && !isPlayer) {
					System.out.printf("%c ", WATER_SYMBOL);
				}
				else {
					System.out.printf("%c ", contents[row][col]);
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void defaultBoard() {
		for (int row = 0; row < contents.length; row++) {
			for (int col = 0; col < contents[row].length; col++) {
				contents[row][col] = WATER_SYMBOL;
			}
		}
	}
	
	public void updateTile(int row, int col, char symbol) {  contents[row][col] = symbol; }
	public void hit(int row, int col) { contents[row][col] = HIT_SYMBOL; }
	public void miss(int row, int col) { contents[row][col] = MISS_SYMBOL; }
	public void placeShipTile(int row, int col) { contents[row][col] = SHIP_SYMBOL; }
	
	// Getters
	public int getRows() { return contents.length; }
	public int getCols() { return contents[0].length; }
	public char[][] getContents() { return contents; }
}
