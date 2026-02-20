package org.lychee.gui;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Dimension;

public abstract class LycheePanel extends JPanel {
	public static int BORDER_WEIGHT = 10;

	private final int minWidth;
	private final int minHeight;

	private final int preferredWidth;
	private final int preferredHeight;

	private final int maxWidth;
	private final int maxHeight;

	protected LycheePanel(int minWidth, int minHeight,
						  int preferredWidth, int preferredHeight,
						  int maxWidth, int maxHeight
	) {
		this(minWidth, minHeight, preferredWidth, preferredHeight, maxWidth, maxHeight, true);
	}

	protected LycheePanel(int minWidth, int minHeight,
			int preferredWidth, int preferredHeight,
			int maxWidth, int maxHeight, boolean border
	) {
		this.minWidth = minWidth;
		this.minHeight = minHeight;

		this.preferredWidth = preferredWidth;
		this.preferredHeight = preferredHeight;

		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;

		if (border) {
			this.setBorder(new LineBorder(LycheeColors.PINK, BORDER_WEIGHT));
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(this.preferredWidth, this.preferredHeight);
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(this.minWidth, this.minHeight);
	}

	@Override
	public Dimension getMaximumSize() {
		return new Dimension(this.maxWidth, this.maxHeight);
	}
}
