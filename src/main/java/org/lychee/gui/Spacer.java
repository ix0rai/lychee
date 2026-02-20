package org.lychee.gui;

public class Spacer extends LycheePanel {
	public Spacer(int width, int height) {
		super(width, height, width, height, width, height);
		this.setOpaque(false);
	}
}
