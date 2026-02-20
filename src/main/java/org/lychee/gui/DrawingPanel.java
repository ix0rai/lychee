package org.lychee.gui;

import org.lychee.gui.flex_grid.FlexGridLayout;
import org.lychee.zest.Command;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends LycheePanel {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;

	private List<Command> commands = new ArrayList<>();

	public DrawingPanel() {
		super(WIDTH, HEIGHT, WIDTH, HEIGHT, WIDTH, HEIGHT, false);
		this.setBackground(LycheeColors.PINK);
		this.setLayout(new FlexGridLayout());
		this.add(new CanvasPanel());
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
		this.repaint();
	}

	private class CanvasPanel extends LycheePanel {
		private static final int WIDTH = DrawingPanel.WIDTH - LycheePanel.BORDER_WEIGHT * 2;
		private static final int HEIGHT = DrawingPanel.HEIGHT - LycheePanel.BORDER_WEIGHT * 2;

		protected CanvasPanel() {
			super(WIDTH, HEIGHT, WIDTH, HEIGHT, WIDTH, HEIGHT, false);
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
}
