package org.lychee.gui;

import com.google.common.graph.Graph;
import org.lychee.gui.flex_grid.FlexGridLayout;
import org.lychee.zest.Command;
import org.lychee.zest.LineError;
import org.lychee.zest.Result;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrawingPanel extends LycheePanel {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;

	private final LycheeFrame frame;
	private List<Command> commands = new ArrayList<>();
	private List<LineError> errors = new ArrayList<>();

	private final JEditorPane editorPane = new JEditorPane();
	private final JScrollPane scrollPane = new JScrollPane(editorPane);
	private final CanvasPanel canvas = new CanvasPanel();

	public DrawingPanel(LycheeFrame frame) {
		super(WIDTH, HEIGHT, WIDTH, HEIGHT, WIDTH, HEIGHT, false);
		this.frame = frame;

		this.setBackground(LycheeColors.PINK);
		this.setLayout(new FlexGridLayout());

		this.editorPane.setBackground(LycheeColors.WHITE);
		this.editorPane.setEditable(false);
		this.editorPane.setForeground(LycheeColors.RED);
		this.editorPane.setPreferredSize(new Dimension(CanvasPanel.WIDTH, CanvasPanel.HEIGHT));
	}

	public void setCommands(Map<Integer, List<Result<Command, LineError>>> commands) {
		this.errors = commands.values().stream().flatMap(Collection::stream).filter(Result::isErr).map(Result::unwrapErr).toList();

		this.removeAll();
		if (this.errors.isEmpty()) {
			ArrayList<List<Result<Command, LineError>>> orderedCommands = new ArrayList<>();

			int lowestLayer = Integer.MAX_VALUE;
			int prevLowest = Integer.MIN_VALUE;
			while (orderedCommands.size() != commands.size()) {
				for (Map.Entry<Integer, List<Result<Command, LineError>>> entry : commands.entrySet()) {
					if (entry.getKey() < lowestLayer && entry.getKey() > prevLowest) {
						lowestLayer = entry.getKey();
					}
				}

				orderedCommands.add(commands.get(lowestLayer));
				prevLowest = lowestLayer;
				lowestLayer = Integer.MAX_VALUE;
			}

			this.commands = orderedCommands.stream().flatMap(Collection::stream).map(Result::unwrap).toList();
			this.add(canvas);
		} else {
			StringBuilder sb = new StringBuilder();
			for (LineError error : errors) {
				sb.append(error.toString()).append("\n");
			}
			this.editorPane.setText(sb.toString());
			this.add(scrollPane);
		}

		this.revalidate();
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

			if (errors.isEmpty()) {
				for (Command command : commands) {
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
