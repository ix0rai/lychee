package org.lychee.zest;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class LineCommand extends Command {
	private static final String NAME = "line";

	private final Coordinate start;
	private final Coordinate end;
	private final int width;
	private final Color color;

	public LineCommand(Coordinate start, Coordinate end, int width, Color color) {
		this.start = start;
		this.end = end;
		this.width = width;
		this.color = color;
	}

	@Override
	public void execute(Graphics2D graphics) {
		graphics.setColor(color);
		graphics.setStroke(new BasicStroke(width));
		graphics.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
	}

	public static List<Pair<String, Argument<?>>> getArguments() {
		return List.of(
				new Pair<>("start", Argument.COORDINATE),
				new Pair<>("end", Argument.COORDINATE),
				new Pair<>("width", Argument.INT),
				new Pair<>("color", Argument.COLOR)
		);
	}

	public static Result<Command, LineError> build(Map<String, Result<?, ParsingError>> arguments) {
    	return buildChecked(NAME, arguments, args -> new LineCommand(
				(Coordinate) args.get("start").unwrap(),
				(Coordinate) args.get("end").unwrap(),
				(Integer) args.get("width").unwrap(),
				(Color) args.get("color").unwrap()
		));
	}

	public String toString() {
		return "line: " + start + " | " + end + " | " + width + " | " + color;
	}
}
