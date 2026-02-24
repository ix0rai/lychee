package org.lychee.zest.commands;

import org.lychee.gui.helper.SwingHelper;
import org.lychee.zest.Command;
import org.lychee.zest.LineError;
import org.lychee.zest.Pair;
import org.lychee.zest.ParsingError;
import org.lychee.zest.Result;
import org.lychee.zest.arguments.Argument;
import org.lychee.zest.arguments.Coordinate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Map;

public class TextCommand extends Command {
	public static final String NAME = "text";

	private final Coordinate start;
	private final int fontSize;
	private final String text;
	private final Color color;

	public TextCommand(Coordinate start, int width, String text, Color color) {
		this.start = start;
		this.fontSize = width;
		this.text = text;
		this.color = color;
	}

	public static java.util.List<Pair<String, Argument<?>>> getArguments() {
		return List.of(
				new Pair<>("start", Argument.COORDINATE),
				new Pair<>("font size", Argument.INT),
				new Pair<>("text", Argument.STRING),
				new Pair<>("color", Argument.COLOR)
		);
	}

	public static Result<Command, LineError> build(Map<String, Result<?, ParsingError>> arguments, int line) {
		return buildChecked(NAME, line, arguments, args -> new TextCommand(
				(Coordinate) args.get("start").unwrap(),
				(Integer) args.get("font size").unwrap(),
				(String) args.get("text").unwrap(),
				(Color) args.get("color").unwrap()
		));
	}

	@Override
	public void execute(Graphics2D graphics) {
		graphics.setColor(this.color);
		graphics.setFont(SwingHelper.JBMONO.deriveFont(Font.ITALIC, this.fontSize));
		SwingHelper.fix(graphics);
		graphics.drawString(this.text, start.x(), start.y());
	}
}
