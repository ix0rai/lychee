package org.lychee.zest;

import java.awt.Graphics2D;

public class FillCommand extends Command {
	private Shape shape;

	public FillCommand(Shape shape) {
		this.shape = shape;
	}

	@Override
	public Shape execute(Graphics2D graphics) {
		return null;
	}
}
