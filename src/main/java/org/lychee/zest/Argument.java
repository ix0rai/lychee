package org.lychee.zest;

import com.google.common.collect.ImmutableMap;

import java.awt.Color;
import java.util.Map;

public class Argument<P> {
	public static final Argument<Integer> INT = new Argument<>("integer", (str, name, argNumber) -> {
		// 0, 2, 532, 312
		try {
			return Result.ok(Integer.parseInt(str));
		} catch (NumberFormatException e) {
			return Result.err(new ParsingError("invalid integer", name, argNumber));
		}
	});

	public static final Argument<String> STRING = new Argument<>("string", (str, name, argNumber) -> {
		// "string", "example string", "lalala"
		try {
			return Result.ok(str.substring(1, str.length() - 1));        // strip quotes
		} catch (StringIndexOutOfBoundsException e) {
			return Result.err(new ParsingError("bad string format", name, argNumber));
		}
	});

	public static final Argument<Coordinate> COORDINATE = new Argument<>("coordinate", (str, name, argNumber) -> {
		// [1:0], [111:111], [12121:12121]
		if (!str.startsWith("[") || !str.endsWith("]")) {
			return Result.err(new ParsingError("coordinate must be surrounded by square brackets", name, argNumber));
		}

		if (!str.contains(":")) {
			return Result.err(new ParsingError("coordinate must be represented by two ints separated by ':'", name, argNumber));
		}

		try {
			str = str.substring(1, str.length() - 1);
		} catch (StringIndexOutOfBoundsException e) {
			return Result.err(new ParsingError("bad coordinate format", name, argNumber));
		}

		String[] coords = str.split(":");
		int x;
		try {
			x = Integer.parseInt(coords[0]);
		} catch (NumberFormatException e) {
			return Result.err(new ParsingError("invalid x coordinate", name, argNumber));
		}

		int y;
		try {
			y = Integer.parseInt(coords[1]);
		} catch (NumberFormatException e) {
			return Result.err(new ParsingError("invalid y coordinate", name, argNumber));
		}

		return Result.ok(new Coordinate(x, y));
	});

	private static final Map<String, Result<Color, ParsingError>> COLORS = ImmutableMap.<String, Result<Color, ParsingError>>builder()
			.put("black", Result.ok(Color.BLACK))
			.put("white", Result.ok(Color.WHITE))
			.put("red", Result.ok(Color.RED))
			.put("orange", Result.ok(Color.ORANGE))
			.put("yellow", Result.ok(Color.YELLOW))
			.put("green", Result.ok(Color.GREEN))
			.put("blue", Result.ok(Color.BLUE))
			.put("purple", Result.ok(Color.MAGENTA))
			.put("pink", Result.ok(Color.PINK))
			.build();

	public static final Argument<Color> COLOR = new Argument<>("color", (str, name, argNumber) -> {
		// "blue", "#FFFFFF", "#000000"
		if (!str.startsWith("\"") || !str.endsWith("\"")) {
			return Result.err(new ParsingError("color must be surrounded by double quotes", name, argNumber));
		}

		try {
			str = str.substring(1, str.length() - 1); // strip quotes
			if (str.startsWith("#")) {
				return Result.ok(Color.decode(str));
			} else {
				return COLORS.getOrDefault(str, Result.err(new ParsingError("unknown color", name, argNumber)));
			}
		} catch (NumberFormatException e) {
			return Result.err(new ParsingError("invalid color format (error parsing color string)", name, argNumber));
		} catch (StringIndexOutOfBoundsException e) {
			return Result.err(new ParsingError("invalid color format (error parsing color hex)", name, argNumber));
		}
	});

	private final String name;
	private final Parser<P> parser;

	public Argument(String name, Parser<P> parser) {
		this.name = name;
		this.parser = parser;
	}

	public Result<P, ParsingError> parse(String value, String name, int argNumber) {
		return parser.parse(value, name, argNumber);
	}

	@FunctionalInterface
	public interface Parser<P> {
		Result<P, ParsingError> parse(String arg, String name, int argNumber);
	}
}
