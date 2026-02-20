package org.lychee.zest;

import org.lychee.gui.LycheeColors;

import java.awt.Graphics2D;

public class LineCommand extends Command {
	private final Coordinate start;
	private final Coordinate end;
	private final int width;

	public LineCommand(Coordinate start, Coordinate end, int width) {
		this.start = start;
		this.end = end;
		this.width = width;
	}

	@Override
	public Shape execute(Graphics2D graphics) {
		graphics.setColor(LycheeColors.RED);
		graphics.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
		return null;
	}

	public String toString() {
		return start + " | " + end + " | " + width;
	}
}
