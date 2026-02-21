package org.lychee.zest;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ZestParser {
	public static Result<Command, LineError> parseCommand(String line, int lineNumber) {
		String[] newLine = line.trim().split("\\(");
		String commandName = newLine[0];
		// todo make this better -- the substring removes the );
		String parameters = newLine[1].substring(0, newLine[1].length() - 2);
		String[] args = parameters.split(",");

		return switch (commandName) {
			case "line" -> {
				Map<String, Result<?, ParsingError>> arguments = parseArguments(LineCommand.getArguments(), args, lineNumber);
				yield LineCommand.build(arguments);
			}
			default -> Result.err(new LineError("command not found", new ParsingError("could not parse", lineNumber)));
		};
	}

	private static Map<String, Result<?, ParsingError>> parseArguments(Map<String, Argument<?>> arguments, String[] args, int lineNumber) {
		Map<String, Result<?, ParsingError>> results = new HashMap<>();
		int i = 0;

		for (Map.Entry<String, Argument<?>> argument : arguments.entrySet()) {
			results.put(argument.getKey(), argument.getValue().parse(args[i], lineNumber));
			i++;
		}

		return results;
	}

	public static ArrayList<Result<Command, LineError>> parseFromString(String code) {
		ArrayList<Result<Command, LineError>> results = new ArrayList<>();

		Scanner scnr = new Scanner(code);
		int i = 0;
		while (scnr.hasNextLine()) {
			String line = scnr.nextLine();
			if (line.endsWith(";")) {
				results.add(parseCommand(line, i));
			}

			i++;
		}
		System.out.println(results);
		scnr.close();

		System.out.println(results);
		return results;
	}

	public static ArrayList<Result<Command, LineError>> parseFile(String filename) {
		ArrayList<Result<Command, LineError>> results;

		try {
			results = parseFromString(Files.readString(Path.of(filename)));
		} catch (IOException io) {
			throw new RuntimeException(io);
		}

		return results;
	}
}
