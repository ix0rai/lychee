package org.lychee.zest;

import java.awt.Graphics2D;

public class FillCommand extends Command {
	private final Color color;

	public FillCommand(Color color) {
		this.color = color;
	}

	@Override
	public Shape execute(Graphics2D graphics) {
		return null;
	}
}
