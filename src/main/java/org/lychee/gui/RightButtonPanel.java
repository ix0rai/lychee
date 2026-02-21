package org.lychee.gui;

import org.lychee.gui.flex_grid.FlexGridLayout;
import org.lychee.gui.flex_grid.constraints.FlexGridConstraints;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;

public class RightButtonPanel extends LycheeButtonPanel {
	private final JPanel leftButtons = new JPanel(new FlexGridLayout());

	public RightButtonPanel(LycheeFrame frame) {
		super(DrawingPanel.WIDTH, frame);
		this.setLayout(new BorderLayout());

		leftButtons.setOpaque(false);
		leftButtons.add(new LycheeButton("mouse:   0,   0"));
		leftButtons.add(new Spacer(30, 0));
		leftButtons.add(new LycheeButton("color: #FFFFFF"));
		this.add(leftButtons, BorderLayout.WEST);

		JPanel rightButtons = new JPanel(new FlexGridLayout());
		rightButtons.setOpaque(false);
		rightButtons.add(new JLabel(LycheeFrame.ICON));
		rightButtons.add(new LycheeButton("lychee."));
		this.add(rightButtons, BorderLayout.EAST);
	}

	public void setMouseProps(int x, int y, String hex) {
		FlexGridConstraints.Absolute constraints = FlexGridConstraints.createAbsolute();

		leftButtons.removeAll();
		LycheeButton button = new LycheeButton(String.format("mouse: %3d, %3d", x, y));
		leftButtons.add(button, constraints.fillOnlyX().incrementPriority());
		leftButtons.add(new Spacer(30, 0), constraints.nextColumn().fillOnlyX().incrementPriority());
		leftButtons.add(new LycheeButton(String.format("color: #%s", hex)), constraints.nextColumn().fillOnlyX().incrementPriority());
		this.revalidate();
	}
}
