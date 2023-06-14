package model;

public class Ship {
	private String name;
	private int length;
	
	public Ship(String nameIn, int lengthIn) {
		name = nameIn;
		length = lengthIn;
	}
	
	// Getters
	public String getName() { return name; }
	public int getLength() { return length; }
	
	// Setters
	public void setName(String nameIn) { name = nameIn; }
}
