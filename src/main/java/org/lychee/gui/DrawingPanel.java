package org.lychee.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class DrawingPanel extends LycheePanel {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;

	public DrawingPanel() {
		super(WIDTH, HEIGHT, WIDTH, HEIGHT, WIDTH, HEIGHT);
		this.setBackground(LycheeColors.OFF_WHITE);
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
	}
}
