package org.lychee.zest.commands;

import org.lychee.zest.Command;
import org.lychee.zest.LineError;
import org.lychee.zest.Pair;
import org.lychee.zest.ParsingError;
import org.lychee.zest.Result;
import org.lychee.zest.arguments.Argument;
import org.lychee.zest.arguments.Coordinate;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class LineCommand extends Command {
	public static final String NAME = "line";

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
		graphics.drawLine(start.x(), start.y(), end.x(), end.y());
	}

	public static List<Pair<String, Argument<?>>> getArguments() {
		return List.of(
				new Pair<>("start", Argument.COORDINATE),
				new Pair<>("end", Argument.COORDINATE),
				new Pair<>("width", Argument.INT),
				new Pair<>("color", Argument.COLOR)
		);
	}

	public static Result<Command, LineError> build(Map<String, Result<?, ParsingError>> arguments, int lineNumber) {
    	return buildChecked(NAME, lineNumber, arguments, args -> new LineCommand(
				(Coordinate) args.get("start").unwrap(),
				(Coordinate) args.get("end").unwrap(),
				(Integer) args.get("width").unwrap(),
				(Color) args.get("color").unwrap()
		));
	}
}
