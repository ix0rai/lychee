package org.lychee.zest;

import java.awt.Color;
import java.util.function.Function;

public class Argument<P> {
	public static final Argument<Integer> INT = new Argument<>("integer", (str, line) -> {
		// 0, 2, 532, 312
		try {
			return Result.ok(Integer.parseInt(str));
		} catch (NumberFormatException e) {
			return Result.err(new ParsingError("invalid integer", line));
		}
	});

	public static final Argument<String> STRING = new Argument<>("string", (str, line) -> {
		// "string", "example string", "lalala"
		// todo handle errors
		return Result.ok(str.substring(1, str.length() - 1)); 		// strip quotes
	});

	public static final Argument<Coordinate> COORDINATE = new Argument<>("coordinate", (str, line) -> {
		// [1:0], [111:111], [12121:12121]
		str = str.substring(1, str.length() - 1);

		String[] coords = str.split(":");
		int x;
		try {
			x = Integer.parseInt(coords[0]);
		} catch (NumberFormatException e) {
			return Result.err(new ParsingError("invalid x coordinate", line));
		}

		int y;
		try {
			y = Integer.parseInt(coords[1]);
		} catch (NumberFormatException e) {
			return Result.err(new ParsingError("invalid y coordinate", line));
		}

		return Result.ok(new Coordinate(x, y));
	});

	public static final Argument<Color> COLOR = new Argument<>("color", (str, line) -> {
		// "blue", "#FFFFFF", "#000000"
		str = str.substring(1, str.length() - 1); // strip quotes
		if (str.startsWith("#")) {
			str = str.substring(1);
			// todo handle errors here
			return Result.ok(Color.decode(str));
		} else {
			return switch (str) {
				case "black" -> Result.ok(Color.BLACK);
				case "white" -> Result.ok(Color.WHITE);
				case "red" -> Result.ok(Color.RED);
				case "orange" -> Result.ok(Color.ORANGE);
				case "yellow" -> Result.ok(Color.YELLOW);
				case "green" -> Result.ok(Color.GREEN);
				case "blue" -> Result.ok(Color.BLUE);
				case "purple" -> Result.ok(Color.MAGENTA);
				case "pink" -> Result.ok(Color.PINK);
				default -> Result.err(new ParsingError("color not found", line));
			};
		}
	});

	private final String name;
	private final Parser<P> parser;

	public Argument(String name, Parser<P> parser) {
		this.name = name;
		this.parser = parser;
	}

	public Result<P, ParsingError> parse(String value, int line) {
		return parser.parse(value, line);
	}

	@FunctionalInterface
	public interface Parser<P> {
		Result<P, ParsingError> parse(String arg, int line);
	}
}
