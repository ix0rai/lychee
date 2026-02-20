package org.lychee.zest;

import org.lychee.gui.LycheeColors;

import java.awt.*;

public class FillCommand extends Command {
	private final Coordinate start;
	private final int width;
	private final int height;

	public FillCommand(Coordinate start, int width, int height) {
		this.start = start;
		this.width = width;
		this.height = height;
	}

	@Override
	public Shape execute(Graphics2D graphics) {
		graphics.setColor(LycheeColors.RED);
		graphics.fillRect(start.getX(), start.getY(), width, height);
		return null;
	}

	public String toString() {
		return start + " | " + width + " | " + height;
	}
}
