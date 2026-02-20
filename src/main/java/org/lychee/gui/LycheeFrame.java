package org.lychee.gui;

import org.lychee.gui.flex_grid.FlexGridLayout;
import org.lychee.gui.flex_grid.constraints.FlexGridConstraints;
import org.lychee.zest.Command;

import javax.swing.JFrame;
import java.awt.Container;
import java.util.List;

public class LycheeFrame extends JFrame {
	public static int HEIGHT = 600;
	public static int WIDTH = 1400;

	private final CodePanel codePanel = new CodePanel();
	private final DrawingPanel drawingPanel = new DrawingPanel();

	public void init() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Lychee GUI");

		Container contentPane = this.getContentPane();

		this.setLayout(new FlexGridLayout());
		this.setSize(WIDTH, HEIGHT);

		FlexGridConstraints.Absolute mainPanelConstraints = FlexGridConstraints.createAbsolute().extent(1, 9);

		this.add(codePanel, mainPanelConstraints.pos(0, 1));
		this.add(drawingPanel, mainPanelConstraints.pos(1, 0));

		FlexGridConstraints.Absolute buttonPanelConstraints = FlexGridConstraints.createAbsolute().extent(1, 1);

		this.add(new LeftButtonPanel(), buttonPanelConstraints.pos(0, 0));
		this.add(new RightButtonPanel(), buttonPanelConstraints.pos(1, 9));

		contentPane.setBackground(LycheeColors.PINK);

		this.setVisible(true);
		//this.setIconImage(null) // todo pretty icon
	}

	public void updateCommands(List<Command> commands) {
		this.drawingPanel.setCommands(commands);
	}
}
