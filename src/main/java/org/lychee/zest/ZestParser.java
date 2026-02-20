package org.lychee.zest;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ZestParser {
	// take in file, convert to array list of commands
	private ArrayList<Command> commands;

	public ZestParser() {
		commands = new ArrayList<>();
	}

	public Command parseCommand(String line) {
		String[] newLine = line.trim().split("\\(");
		String commandName = newLine[0];
		String parameters = newLine[1];

		ArrayList<Integer> array = new ArrayList<>();
		Coordinate start;
		Coordinate end;
		int width;

		Command command = null;

		switch (commandName) {
			case "line":
				for (int i = 0; i < parameters.length(); i++) {
					if (Character.isDigit(parameters.charAt(i))) {
						int num = Integer.parseInt(parameters.substring(i, i+1));
						array.add(num);
					}
				}
				start = new Coordinate(array.get(0), array.get(1));
				end = new Coordinate(array.get(2), array.get(3));
				width = array.get(4);

				command = new LineCommand(start, end, width);
				break;
			case "erase":
				for (int i = 0; i < parameters.length(); i++) {
					if (Character.isDigit(parameters.charAt(i))) {
						int num = Integer.parseInt(parameters.substring(i, i+1));
						array.add(num);
					}
				}
				start = new Coordinate(array.get(0), array.get(1));
				end = new Coordinate(array.get(2), array.get(3));
				width = array.get(4);

				command = new EraseCommand(start, end, width);
				break;
			case "fill":
				break;
			case "circle":
				for (int i = 0; i < parameters.length(); i++) {
					if (Character.isDigit(parameters.charAt(i))) {
						int num = Integer.parseInt(parameters.substring(i, i+1));
						array.add(num);
					}
				}
				start = new Coordinate(array.get(0), array.get(1));
				end = new Coordinate(array.get(2), array.get(3));
				width = array.get(4);

				command = new CircleCommand(start, end, width);
				break;
			default:
				System.out.println("Something went wrong!");
				break;
		}
		return command;
	}

	public void parseFile(String filename) {
		try {
			Scanner scnr = new Scanner(new FileReader(filename));
			while (scnr.hasNextLine()) {
				String line = scnr.nextLine();
				if (line.contains(";")) {
					commands.add(parseCommand(line));
					System.out.println("Added!");
				}
			}
			System.out.println(commands);
			scnr.close();
		} catch(IOException io) {
			System.out.println("Something went wrong!");
		}
	}
}
