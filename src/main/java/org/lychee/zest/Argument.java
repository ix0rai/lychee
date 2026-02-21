package org.lychee.zest;

import com.google.common.collect.ImmutableMap;

import java.awt.Color;
import java.util.Map;

public class Argument<P> {
	public static final Argument<Integer> INT = new Argument<>("integer", (str, name, line) -> {
		// 0, 2, 532, 312
		try {
			return Result.ok(Integer.parseInt(str));
		} catch (NumberFormatException e) {
			return Result.err(new ParsingError("invalid integer", name, line));
		}
	});

	public static final Argument<String> STRING = new Argument<>("string", (str, name, line) -> {
		// "string", "example string", "lalala"
		// todo handle errors
		try {
			return Result.ok(str.substring(1, str.length() - 1));        // strip quotes
		} catch (StringIndexOutOfBoundsException e) {
			return Result.err(new ParsingError("bad string format", name, line));
		}
	});

	public static final Argument<Coordinate> COORDINATE = new Argument<>("coordinate", (str, name, line) -> {
		// [1:0], [111:111], [12121:12121]
		try {
			str = str.substring(1, str.length() - 1);
		} catch (StringIndexOutOfBoundsException e) {
			return Result.err(new ParsingError("bad coordinate format", name, line));
		}

		String[] coords = str.split(":");
		int x;
		try {
			x = Integer.parseInt(coords[0]);
		} catch (NumberFormatException e) {
			return Result.err(new ParsingError("invalid x coordinate", name, line));
		}

		int y;
		try {
			y = Integer.parseInt(coords[1]);
		} catch (NumberFormatException e) {
			return Result.err(new ParsingError("invalid y coordinate", name, line));
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

	public static final Argument<Color> COLOR = new Argument<>("color", (str, name, line) -> {
		// "blue", "#FFFFFF", "#000000"
		try {
			str = str.substring(1, str.length() - 1); // strip quotes
			if (str.startsWith("#")) {
				str = str.substring(1);
				return Result.ok(Color.decode(str));
			} else {
				return COLORS.getOrDefault(str, Result.err(new ParsingError("unknown color", name, line)));
			}
		} catch (NumberFormatException | StringIndexOutOfBoundsException e) {
			return Result.err(new ParsingError("invalid color format", name, line));
		}
	});

	private final String name;
	private final Parser<P> parser;

	public Argument(String name, Parser<P> parser) {
		this.name = name;
		this.parser = parser;
	}

	public Result<P, ParsingError> parse(String value, String name, int line) {
		return parser.parse(value, name, line);
	}

	@FunctionalInterface
	public interface Parser<P> {
		Result<P, ParsingError> parse(String arg, String name, int line);
	}
}
