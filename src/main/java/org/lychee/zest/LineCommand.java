package org.lychee.zest;

import org.lychee.gui.LycheeColors;

import java.awt.*;

public class LineCommand extends Command {
	private final Coordinate start;
	private final Coordinate end;
	private final int width;
	private final String color;

	public LineCommand(Coordinate start, Coordinate end, int width) {
		this.start = start;
		this.end = end;
		this.width = width;
		color = null;
	}

	public LineCommand(Coordinate start, Coordinate end, int width, String color) {
		this.start = start;
		this.end = end;
		this.width = width;
		this.color = color;
	}

	@Override
	public Shape execute(Graphics2D graphics) {
		if (color != null && color.startsWith("#")) {
			graphics.setColor(changeColor(true, color));
		} else if (color != null) {
			graphics.setColor(changeColor(false, color));
		}
		graphics.setStroke(new BasicStroke(width));
		graphics.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
		return null;
	}

	public String toString() {
		return "line: " + start + " | " + end + " | " + width + " | " + color;
	}
}
