package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import controller.Game;

public class FileSystem {
	private Scanner reader;
	private FileWriter writer;
	
	private Game game;
	
	private String directory = "/resources/";
	
	public FileSystem(Game gameIn) {
		game = gameIn;
	}
	
	public void save(Game gameIn) {
		try {
			writer = new FileWriter(new File(directory + gameIn.getPlayer().getName()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void load(Game gameIn) {
		try {
			reader = new Scanner(new File(directory + gameIn.getPlayer().getName()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
