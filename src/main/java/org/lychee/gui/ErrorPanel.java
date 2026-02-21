package org.lychee.gui;

public class ErrorPanel extends LycheePanel {
	public static final int WIDTH = CodePanel.WIDTH;
	public static final int HEIGHT = 200;

	private final LycheeFrame frame;

	public ErrorPanel(LycheeFrame frame) {
		super(WIDTH, HEIGHT, WIDTH, HEIGHT, WIDTH, HEIGHT, false);
		this.frame = frame;
		this.setBackground(LycheeColors.OFF_WHITE);
	}
}
