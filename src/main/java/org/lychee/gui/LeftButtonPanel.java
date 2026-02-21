package org.lychee.gui;

import org.jspecify.annotations.NonNull;
import org.lychee.gui.flex_grid.FlexGridLayout;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.filechooser.FileFilter;

public class LeftButtonPanel extends LycheeButtonPanel {
	public LeftButtonPanel(LycheeFrame frame) {
		super(CodePanel.WIDTH, frame);
		this.setLayout(new BorderLayout());

		JButton importButton = createImportButton();
		JButton clearButton = new LycheeButton("clear code");
		clearButton.addActionListener(_ -> frame.setCode(""));

		JButton exportImageButton = createExportButton(frame);

		JPanel leftButtons = new JPanel(new FlexGridLayout());
		leftButtons.setOpaque(false);
		leftButtons.add(importButton);
		leftButtons.add(new Spacer(20, 0));
		leftButtons.add(clearButton);
		this.add(leftButtons, BorderLayout.WEST);

		JPanel rightButtons = new JPanel(new FlexGridLayout());
		rightButtons.setOpaque(false);
		rightButtons.add(exportImageButton);
		this.add(rightButtons, BorderLayout.EAST);
	}

	private @NonNull JButton createExportButton(LycheeFrame frame) {
		JButton exportImageButton = new JButton("export image");
		exportImageButton.addActionListener(_ -> {
			Image image = frame.render();
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setDialogType(JFileChooser.OPEN_DIALOG);
			chooser.setFileFilter(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return pathname.isDirectory() || pathname.getName().endsWith(".png");
				}

				@Override
				public String getDescription() {
					return "png images";
				}
			});

			if (chooser.showOpenDialog(root) == JFileChooser.APPROVE_OPTION) {
				File selectedFile = chooser.getSelectedFile();
				try {
					ImageIO.write((RenderedImage) image, "png", selectedFile);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		});
		return exportImageButton;
	}

	private @NonNull JButton createImportButton() {
		JButton importButton = new LycheeButton("import code");
		importButton.addActionListener(_ -> {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setDialogType(JFileChooser.OPEN_DIALOG);
			chooser.setFileFilter(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return pathname.isDirectory() || pathname.getName().endsWith(".zst");
				}

				@Override
				public String getDescription() {
					return "zest files";
				}
			});

			if (chooser.showOpenDialog(root) == JFileChooser.APPROVE_OPTION) {
				File selectedFile = chooser.getSelectedFile();
				try {
					String text = Files.readString(selectedFile.toPath());
					LeftButtonPanel.this.root.setCode(text);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		});
		return importButton;
	}
}
