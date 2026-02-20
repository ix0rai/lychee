package org.lychee.gui;

public class LycheeButtonPanel extends LycheePanel {
	private static final int HEIGHT = 50;

	public LycheeButtonPanel(int width) {
		super(width, HEIGHT, width, HEIGHT, width, HEIGHT);
		this.setBackground(LycheeColors.PINK);
	}
}
