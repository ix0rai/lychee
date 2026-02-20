package org.lychee.gui;

import org.lychee.gui.flex_grid.FlexGridLayout;
import org.lychee.zest.ZestParser;

import javax.swing.JEditorPane;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CodePanel extends LycheePanel {
	public static final int WIDTH = 400;
	public static final int HEIGHT = 500;

	private final JEditorPane editor = new JEditorPane();

	public CodePanel(DrawingPanel panel) {
		super(WIDTH, HEIGHT, WIDTH, HEIGHT, WIDTH, HEIGHT);
		this.setBackground(LycheeColors.DARK_RED);
		this.setLayout(new FlexGridLayout());

		this.editor.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.editor.setBackground(LycheeColors.DARK_RED);
		//this.editor.setFont();
		this.editor.setContentType("text/zest-sources");

		editor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				panel.setCommands(ZestParser.parseFromString(editor.getText()));
			}
		});

		this.add(editor);

	}
}
