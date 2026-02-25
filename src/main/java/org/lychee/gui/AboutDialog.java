package org.lychee.gui;

import org.lychee.gui.flex_grid.FlexGridLayout;
import org.lychee.gui.flex_grid.constraints.FlexGridConstraints;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class AboutDialog {
	public static void show(JFrame parent) {
		JDialog frame = new JDialog(parent, "lychee info", true);
		Container pane = frame.getContentPane();
		pane.setLayout(new FlexGridLayout());
		pane.setBackground(LycheeColors.RED);

		FlexGridConstraints.Absolute cb = FlexGridConstraints.createAbsolute().alignCenterX();

		JLabel title = new JLabel("lychee");
		title.setForeground(Color.WHITE);
		title.setFont(title.getFont().deriveFont(title.getFont().getSize2D() * 1.5f));

		JButton okButton = new LycheeButton("ok");
		okButton.addActionListener(_ -> frame.dispose());

		pane.add(new Spacer(300, 0), cb);
		pane.add(title, cb.pos(0, 0));
		pane.add(new Spacer(0, 10), cb.pos(0, 1));
		var desc = new JLabel("the new way to paint");
		desc.setForeground(Color.WHITE);
		pane.add(desc, cb.pos(0, 2));
		pane.add(new Spacer(0, 10), cb.pos(0, 3));
		var version = new JLabel("version 0.1.0");
		version.setForeground(Color.WHITE);
		pane.add(version, cb.pos(0, 4));
		pane.add(new Spacer(0, 10), cb.pos(0, 5));
		pane.add(createLink("https://github.com/ix0rai/lychee", () -> openUrl("https://github.com/ix0rai/lychee")), cb.pos(0, 6));
		pane.add(new Spacer(0, 10), cb.pos(0, 7));
		pane.add(okButton, cb.pos(0, 8));
		pane.add(new Spacer(0, 10), cb.pos(0, 9));

		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(parent);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

	public static JLabel createLink(String text, Runnable action) {
		JLabel link = new JLabel(text);
		link.setForeground(LycheeColors.PINK);
		link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		@SuppressWarnings("unchecked")
		Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) link.getFont().getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		link.setFont(link.getFont().deriveFont(attributes));
		link.addMouseListener(onMousePress(e -> action.run()));
		return link;
	}

	public static MouseListener onMousePress(Consumer<MouseEvent> op) {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				op.accept(e);
			}
		};
	}

	public static void openUrl(String url) {
		try {
			if (Objects.requireNonNull(Os.getOs()) == Os.LINUX) {
				new ProcessBuilder("/usr/bin/env", "xdg-open", url).start();
			} else {
				if (Desktop.isDesktopSupported()) {
					Desktop desktop = Desktop.getDesktop();
					desktop.browse(new URI(url));
				}
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		} catch (URISyntaxException ex) {
			throw new IllegalArgumentException(ex);
		}
	}
}
