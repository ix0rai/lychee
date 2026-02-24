package org.lychee.zest.commands;

import org.lychee.zest.arguments.Argument;
import org.lychee.zest.Command;
import org.lychee.zest.arguments.Coordinate;
import org.lychee.zest.LineError;
import org.lychee.zest.Pair;
import org.lychee.zest.ParsingError;
import org.lychee.zest.Result;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class EraseCommand extends Command {
	public static final String NAME = "erase";
	private final Coordinate start;
	private final Coordinate end;
	private final int width;

	public EraseCommand(Coordinate start, Coordinate end, int width) {
		this.start = start;
		this.end = end;
		this.width = width;
	}

	@Override
	public void execute(Graphics2D graphics) {
		graphics.setColor(graphics.getBackground());
		graphics.setStroke(new BasicStroke(width));
		graphics.drawLine(start.x(), start.y(), end.x(), end.y());
	}

	public static java.util.List<Pair<String, Argument<?>>> getArguments() {
		return List.of(
				new Pair<>("start", Argument.COORDINATE),
				new Pair<>("end", Argument.COORDINATE),
				new Pair<>("width", Argument.INT)
		);
	}

	public static Result<Command, LineError> build(Map<String, Result<?, ParsingError>> arguments, int lineNumber) {
		return buildChecked(NAME, lineNumber, arguments, args -> new EraseCommand(
				(Coordinate) args.get("start").unwrap(),
				(Coordinate) args.get("end").unwrap(),
				(Integer) args.get("width").unwrap()
		));
	}
}
