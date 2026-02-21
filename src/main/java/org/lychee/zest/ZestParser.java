package org.lychee.zest;

import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

public class ZestParser {
	private static final ImmutableMap<String, BiFunction<String[], Integer, Result<Command, LineError>>> COMMANDS = ImmutableMap.<String, BiFunction<String[], Integer, Result<Command, LineError>>>builder()
			.put(LineCommand.NAME, (args, line) -> LineCommand.build(
					parseArguments(LineCommand.getArguments(), args), line
			))
			.put(CircleCommand.NAME, (args, line) -> CircleCommand.build(
					parseArguments(CircleCommand.getArguments(), args), line
			))
			.put(FillCommand.NAME, (args, line) -> FillCommand.build(
					parseArguments(FillCommand.getArguments(), args), line
			))
			.put(EraseCommand.NAME, (args, line) -> EraseCommand.build(
					parseArguments(EraseCommand.getArguments(), args), line
			))
			.build();

	public static Result<Command, LineError> parseCommand(String line, int lineNumber) {
		try {
			if (!line.endsWith(";")) {
				return Result.err(new LineError("line must end in a ;", lineNumber, null));
			} else if (!line.contains("(") || !line.contains(")")) {
				return Result.err(new LineError("command must have arguments enclosed by ()", lineNumber, null));
			}

			String commandName;
			String arguments;
			try {
				String[] splitByOpen = line.trim().split("\\(");
				commandName = splitByOpen[0].trim();
				String[] splitByClose = splitByOpen[1].trim().split("\\)");
				arguments = splitByClose[0].trim();
				String semicolon = splitByClose[1].trim();

				if (!semicolon.equals(";")) {
					return Result.err(new LineError("arguments must be immediately followed by a ;", lineNumber, null));
				}
			} catch (Exception e) {
				return Result.err(new LineError("malformed line (string splitting error)", lineNumber, null));
			}

			String[] args = arguments.split(",");

			var command = COMMANDS.get(commandName);
			if (command == null) {
				return Result.err(new LineError("unknown command ''" + commandName + "'", lineNumber, null));
			} else {
				return command.apply(args, lineNumber);
			}
		} catch (Exception e) {
			return Result.err(new LineError("internal parsing error", lineNumber, null));
		}
	}

	private static Map<String, Result<?, ParsingError>> parseArguments(List<Pair<String, Argument<?>>> arguments, String[] args) {
		Map<String, Result<?, ParsingError>> results = new HashMap<>();
		int i = 0;

		for (Pair<String, Argument<?>> argument : arguments) {
			results.put(argument.a(), argument.b().parse(args[i].trim(), argument.a(), i + 1));
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
			if (!line.startsWith("//")) {
				results.add(parseCommand(line, i));
			}

			i++;
		}

		scnr.close();
		return results;
	}
}

