package org.lychee.gui;

import org.lychee.gui.flex_grid.FlexGridLayout;
import org.lychee.gui.flex_grid.constraints.FlexGridConstraints;
import org.lychee.zest.Command;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.Image;
import java.io.File;
import java.util.List;

public class LycheeFrame extends JFrame {
	public static Image ICON_IMAGE;
	public static Icon ICON;

	static {
		try {
			ICON_IMAGE = ImageIO.read(new File("lychee.png")); // todo fragile image loading
			ICON = new ImageIcon(ICON_IMAGE);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static int HEIGHT = 600;
	public static int WIDTH = 1000;

	private final JEditorPane editorPane = new JEditorPane();
	private final DrawingPanel drawingPanel = new DrawingPanel(this);
	private final CodePanel codePanel = new CodePanel(drawingPanel, editorPane);
	private final RightButtonPanel rightButtonPanel = new  RightButtonPanel(this);

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

		this.add(new LeftButtonPanel(this), buttonPanelConstraints.pos(0, 0));
		this.add(rightButtonPanel, buttonPanelConstraints.pos(1, 9));

		contentPane.setBackground(LycheeColors.PINK);

		this.setVisible(true);
		this.setIconImage(ICON_IMAGE);
	}

	public void setCode(String code) {
		this.codePanel.setCode(code);
	}

	public String getCode() {
		return this.codePanel.getCode();
	}

	public void updateCommands(List<Command> commands) {
		this.drawingPanel.setCommands(commands);
	}

	public Image render() {
		return this.drawingPanel.render();
	}

	public void updateMouseProps(int x, int y, String hex) {
		rightButtonPanel.setMouseProps(x, y, hex);
	}
}
