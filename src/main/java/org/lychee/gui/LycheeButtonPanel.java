package org.lychee.gui;

public class LycheeButtonPanel extends LycheePanel {
	private static final int HEIGHT = 50;
	protected final LycheeFrame root;

	public LycheeButtonPanel(int width, LycheeFrame root) {
		super(width, HEIGHT, width, HEIGHT, width, HEIGHT);
		this.setBackground(LycheeColors.PINK);
		this.root = root;
	}
}
