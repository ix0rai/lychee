package org.lychee.zest;

import org.lychee.gui.LycheeColors;

import java.awt.*;

public class FillCommand extends Command {
	private final Coordinate start;
	private final Coordinate end;

	public FillCommand(Coordinate start, Coordinate end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public Shape execute(Graphics2D graphics) {
		graphics.setColor(LycheeColors.RED);
		graphics.fillRect(start.getX(), start.getY(), end.getX(), end.getY());
		System.out.println("executed");
		return null;
	}
}
