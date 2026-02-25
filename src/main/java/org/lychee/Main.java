package org.lychee;

import org.lychee.gui.LycheeFrame;
import org.lychee.gui.helper.SwingHelper;

public class Main {
	public static void main(String[] args) {
		SwingHelper.fixSwing();
		LycheeFrame frame = new LycheeFrame();
		frame.init();
	}
}