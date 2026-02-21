package org.lychee.zest;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class FillCommand extends Command {
	private final Coordinate start;
	private final int width;
	private final int height;
	private final Color color;

	public FillCommand(Coordinate start, int width, int height, Color color) {
		super("fill");
		this.start = start;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	public static java.util.List<Pair<String, Argument<?>>> getArguments() {
		return List.of(
				new Pair<>("start", Argument.COORDINATE),
				new Pair<>("width", Argument.INT),
				new Pair<>("height", Argument.INT),
				new Pair<>("color", Argument.COLOR)
		);
	}

	public static Result<Command, LineError> build(Map<String, Result<?, ParsingError>> arguments) {
		return buildChecked("line", arguments, args -> new FillCommand(
				(Coordinate) args.get("start").unwrap(),
				(Integer) args.get("width").unwrap(),
				(Integer) args.get("height").unwrap(),
				(Color) args.get("color").unwrap()
		));
	}

	@Override
	public void execute(Graphics2D graphics) {
		graphics.setColor(color);
		graphics.setStroke(new BasicStroke(5));
		graphics.fillRoundRect(start.getX(), start.getY(), width, height, 1, 1);
	}

	public String toString() {
		return "fill: " + start + " | " + width + " | " + height + " | " + color;
	}
}
