package org.lychee.zest;

import com.google.common.collect.ImmutableMap;
import org.lychee.zest.arguments.Argument;
import org.lychee.zest.commands.BeginLayerCommand;
import org.lychee.zest.commands.CircleCommand;
import org.lychee.zest.commands.EndLayerCommand;
import org.lychee.zest.commands.EraseCommand;
import org.lychee.zest.commands.FillCommand;
import org.lychee.zest.commands.LineCommand;

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
			.put(BeginLayerCommand.NAME, (args, line) -> BeginLayerCommand.build(
					parseArguments(BeginLayerCommand.getArguments(), args), line
			))
			.put(EndLayerCommand.NAME, (_, _) -> Result.ok(new EndLayerCommand()))
			.build();

	public static Result<Command, LineError> parseCommand(String line, int lineNumber) {
		lineNumber++;

		try {
			line = line.trim();
			boolean layerOpen = line.startsWith("layer");
			boolean layerClose = line.startsWith("}");
			boolean layer = layerOpen || layerClose;

			if (!layer && !line.endsWith(";")) {
				return Result.err(new LineError("line must end in a ;", lineNumber, null));
			} else if (!layerClose && (!line.contains("(") || !line.contains(")"))) {
				return Result.err(new LineError("command must have arguments enclosed by ()", lineNumber, null));
			} else if (layerOpen && !line.endsWith("{")) {
				return Result.err(new LineError("layer start must end with {", lineNumber, null));
			} else if (layerClose && !line.equals("}")) {
				return Result.err(new LineError("layer end must be a single }", lineNumber, null));
			}

			String commandName;
			String arguments;
			try {
				String[] splitByOpen = line.trim().split("\\(");
				commandName = splitByOpen[0].trim();
				String[] splitByClose = splitByOpen[1].trim().split("\\)");
				arguments = splitByClose[0].trim();
				String semicolon = splitByClose[1].trim();

				if (!layer && !semicolon.equals(";")) {
					return Result.err(new LineError("arguments must be immediately followed by a ;", lineNumber, null));
				} else if (layer && !semicolon.equals("{")) {
					return Result.err(new LineError("layer definition must be immediately followed by a {", lineNumber, null));
				}
			} catch (Exception e) {
				if (!layerClose) {
					return Result.err(new LineError("malformed line (string splitting error)", lineNumber, null));
				} else {
					arguments = "";
					commandName = EndLayerCommand.NAME;
				}
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

	public static Map<Integer, List<Result<Command, LineError>>> parseFromString(String code) {
		ArrayList<Result<Command, LineError>> results = new ArrayList<>();

		Scanner scnr = new Scanner(code);
		int i = 0;
		while (scnr.hasNextLine()) {
			String line = scnr.nextLine();
			if (!line.startsWith("//") && !line.isBlank()) {
				results.add(parseCommand(line, i));
			}

			i++;
		}

		scnr.close();

		Map<Integer, List<Result<Command, LineError>>> layeredResults = new HashMap<>();
		int currentLayer = 0;
		for (Result<Command, LineError> result : results) {
			if (result.isOk()) {
				Command command = result.unwrap();
				if (command instanceof BeginLayerCommand beginLayerCommand) {
					currentLayer = beginLayerCommand.getLayer();
				} else if (command instanceof EndLayerCommand) {
					currentLayer = 0;
				} else {
					layeredResults.computeIfAbsent(currentLayer, _ -> new ArrayList<>()).add(result);
				}
			} else {
				layeredResults.computeIfAbsent(currentLayer, _ -> new ArrayList<>()).add(result);
			}
		}

		return layeredResults;
	}
}

