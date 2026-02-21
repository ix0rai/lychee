package org.lychee.gui;

import org.lychee.gui.flex_grid.FlexGridLayout;
import org.lychee.zest.Command;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends LycheePanel {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;

	private final LycheeFrame frame;
	private List<Command> commands = new ArrayList<>();
	private final CanvasPanel canvas = new CanvasPanel();

	public DrawingPanel(LycheeFrame frame) {
		super(WIDTH, HEIGHT, WIDTH, HEIGHT, WIDTH, HEIGHT, false);
		this.frame = frame;

		this.setBackground(LycheeColors.PINK);
		this.setLayout(new FlexGridLayout());
		this.add(canvas);
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
			this.addMouseMotionListener(new MouseMotionListener() {
				@Override
				public void mouseDragged(MouseEvent e) {
					// no-op
				}

				@Override
				public void mouseMoved(MouseEvent e) {
					BufferedImage image = CanvasPanel.this.render();
					Color c = new Color(image.getRGB(e.getX(), e.getY()));
					frame.updateMouseProps(e.getX(), e.getY(), String.format("%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue()));
				}
			});
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

		public BufferedImage render() {
			BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			this.printAll(image.getGraphics());
			return image;
		}
	}

	public Image render() {
		return this.canvas.render();
	}
}
