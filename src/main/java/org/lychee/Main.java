package org.lychee;

import org.lychee.gui.LycheeFrame;
import org.lychee.gui.helper.SwingHelper;
import org.lychee.zest.Command;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		SwingHelper.fixSwing();
		LycheeFrame frame = new LycheeFrame();
		frame.init();
	}
}