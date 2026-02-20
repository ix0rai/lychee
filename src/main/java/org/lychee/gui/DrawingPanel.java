package org.lychee.gui;

import org.lychee.zest.Command;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends LycheePanel {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;

	private List<Command> commands = new ArrayList<>();

	public DrawingPanel() {
		super(WIDTH, HEIGHT, WIDTH, HEIGHT, WIDTH, HEIGHT);
		this.setBackground(LycheeColors.OFF_WHITE);
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
		this.repaint();
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		for (Command command : commands) {
			if (command != null) {
				command.execute((Graphics2D) graphics);
			}
		}
	}
}
