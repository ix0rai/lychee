package org.lychee.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class CodePanel extends LycheePanel {
	public static final int WIDTH = 300;
	public static final int HEIGHT = 500;

	public CodePanel() {
		super(WIDTH, HEIGHT, WIDTH, HEIGHT, WIDTH, HEIGHT);
		this.setBackground(LycheeColors.DARK_RED);
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
	}
}
