package org.lychee.zest;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ZestParser {
	public static Result<Command, LineError> parseCommand(String line, int lineNumber) {
		try {
			String[] newLine = line.trim().split("\\(");
			String commandName = newLine[0];
			// todo make this better -- the substring removes the );
			String parameters = newLine[1].substring(0, newLine[1].length() - 2);
			String[] args = parameters.split(",");

				command = new EraseCommand(eraseStart, eraseEnd, eraseWidth);
				break;
			case "fill":
				String[] fillArgs = parameters.split(",");
				Coordinate fillStart = (Coordinate) parseArgument(fillArgs[0]);
				int fillWidth = (int) parseArgument(fillArgs[1]);
				int fillHeight = (int) parseArgument(fillArgs[2]);
				if (fillArgs.length == 4) {
					String color = (String) parseArgument(fillArgs[3]);
					command = new FillCommand(fillStart, fillWidth, fillHeight, color);
				} else {
					command = new FillCommand(fillStart, fillWidth, fillHeight);
				}
				break;
			case "circle":
				String[] circleArgs = parameters.split(",");
				Coordinate circleStart = (Coordinate) parseArgument(circleArgs[0]);
				int circleWidth = (int) parseArgument(circleArgs[1]);
				int circleHeight = (int) parseArgument(circleArgs[2]);
				if (circleArgs.length == 4) {
					String color = (String) parseArgument(circleArgs[3]);
					command = new CircleCommand(circleStart, circleWidth, circleHeight, color);
				} else {
					command = new CircleCommand(circleStart, circleWidth, circleHeight);
				}
				default ->
						Result.err(new LineError("command not found", new ParsingError("could not parse", null, lineNumber)));
			};
		} catch (Exception e) {
			return Result.err(new LineError("internal parsing error", new ParsingError("internal error reading line", null, lineNumber)));
		}
	}

	private static Map<String, Result<?, ParsingError>> parseArguments(List<Pair<String, Argument<?>>> arguments, String[] args, int lineNumber) {
		Map<String, Result<?, ParsingError>> results = new HashMap<>();
		int i = 0;

		for (Pair<String, Argument<?>> argument : arguments) {
			results.put(argument.a(), argument.b().parse(args[i].trim(), argument.a(), lineNumber));
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

		scnr.close();
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
