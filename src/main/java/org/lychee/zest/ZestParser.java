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
		// todo make this better -- the substring removes the );
		String parameters = newLine[1].substring(0, newLine[1].length() - 2);

		ArrayList<Integer> array = new ArrayList<>();

		Command command = null;

		switch (commandName) {
			case "line":
				String[] args = parameters.split(",");
				Coordinate lineStart = (Coordinate) parseArgument(args[0]);
				Coordinate lineEnd = (Coordinate) parseArgument(args[1]);
				int lineWidth = (int) parseArgument(args[2]);

				command = new LineCommand(lineStart, lineEnd, lineWidth);
				break;
			case "erase":
				String[] eraseArgs = parameters.split(",");
				Coordinate eraseStart = (Coordinate) parseArgument(eraseArgs[0]);
				Coordinate eraseEnd = (Coordinate) parseArgument(eraseArgs[1]);
				int eraseWidth = (int) parseArgument(eraseArgs[2]);

				command = new EraseCommand(eraseStart, eraseEnd, eraseWidth);
				break;
			case "fill":
				String[] fillArgs = parameters.split(",");
				Coordinate fillStart = (Coordinate) parseArgument(fillArgs[0]);
				Coordinate fillEnd = (Coordinate) parseArgument(fillArgs[1]);

				command = new FillCommand(fillStart, fillEnd);
			case "circle":
				String[] circleArgs = parameters.split(",");
				Coordinate circleStart = (Coordinate) parseArgument(circleArgs[0]);
				Coordinate circleEnd = (Coordinate) parseArgument(circleArgs[1]);
				int circleWidth = (int) parseArgument(circleArgs[2]);

				command = new CircleCommand(circleStart, circleEnd, circleWidth);
				break;
			default:
				System.out.println("Something went wrong!");
				break;
		}
		return command;
	}

	public Object parseArgument(String arg) {
		arg = arg.trim();
		if (arg.startsWith("[") && arg.endsWith("]")) {
			return Coordinate.parse(arg);
		} else if (arg.startsWith("\"") && arg.endsWith("\"")) {
			return parseString(arg);
		} else {
			// assume int
			return Integer.parseInt(arg);
		}
	}

	public String parseString(String arg) {
		return arg.substring(1, arg.length() - 1);
	}

	public ArrayList<Command> parseFile(String filename) {
		try {
			Scanner scnr = new Scanner(new FileReader(filename));
			while (scnr.hasNextLine()) {
				String line = scnr.nextLine();
				if (line.endsWith(";")) {
					commands.add(parseCommand(line));
					System.out.println("Added!");
				}
			}
			System.out.println(commands);
			scnr.close();
		} catch(IOException io) {
			System.out.println("Something went wrong!");
		}
		return commands;
	}
}
