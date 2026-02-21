package org.lychee.gui;

import org.lychee.gui.flex_grid.FlexGridLayout;
import org.lychee.zest.ZestParser;
import org.quiltmc.syntaxpain.LineNumbersRuler;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.file.Files;
import java.nio.file.Path;

public class CodePanel extends LycheePanel {
	public static final int WIDTH = 400;
	public static final int HEIGHT = 500;

	private final JEditorPane editor;
	private final DrawingPanel drawingPanel;

	public CodePanel(DrawingPanel panel, JEditorPane editor) {
		super(WIDTH, HEIGHT, WIDTH, HEIGHT, WIDTH, HEIGHT);
		this.setBackground(LycheeColors.DARK_RED);
		this.setLayout(new FlexGridLayout());

		this.drawingPanel = panel;
		JScrollPane scrollPane = new JScrollPane(editor);
		this.editor = editor;
		this.editor.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.editor.setBackground(LycheeColors.DARK_RED);
		this.editor.setCaretColor(LycheeColors.WHITE);
		this.editor.setContentType("text/zest-sources");

		final LineNumbersRuler ruler = LineNumbersRuler.install(new LineNumbersRuler(
				this.editor, LycheeColors.WHITE, 0
		));
		ruler.setForeground(LycheeColors.RED);
		ruler.setBackground(LycheeColors.DARK_RED);
		ruler.setFont(this.editor.getFont());

		editor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				reloadCode();
			}
		});

		editor.addPropertyChangeListener(_ -> reloadCode());

		this.add(scrollPane);

		try {
			this.editor.setText(Files.readString(Path.of("lychee_example.zst")));
		} catch (Exception ignored) {

		}
	}

	public void reloadCode() {
		drawingPanel.setCommands(ZestParser.parseFromString(editor.getText()));
	}

	public void setCode(String code) {
		this.editor.setText(code);
		this.reloadCode();
	}

	public String getCode() {
		return this.editor.getText();
	}
}
