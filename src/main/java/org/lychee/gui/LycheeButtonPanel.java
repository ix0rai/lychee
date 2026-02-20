package org.lychee.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class LycheeButtonPanel extends LycheePanel {
	private static final int HEIGHT = 50;

	public LycheeButtonPanel(int width) {
		super(width, HEIGHT, width, HEIGHT, width, HEIGHT);
		this.setLayout(new BorderLayout());

		//this.setBackground(LycheeColors.PINK);
		JPanel leftButtons = new JPanel();
		leftButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
		leftButtons.setBackground(LycheeColors.OFF_WHITE);
		leftButtons.setOpaque(false);

		JPanel rightButtons = new JPanel();
		rightButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
		leftButtons.setBackground(LycheeColors.OFF_WHITE);
		leftButtons.setOpaque(false);

		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		leftButtons.add(new LycheeButton("one"));
		leftButtons.add(new LycheeButton("two"));
		rightButtons.add(new LycheeButton("three"));

		this.add(leftButtons, BorderLayout.WEST);
		this.add(rightButtons, BorderLayout.EAST);
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		Graphics2D g2d = (Graphics2D) graphics;
		//g2d.setColor(LycheeColors.OFF_WHITE);
		//g2d.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
	}
}
