package org.lychee;

import org.lychee.gui.LycheeFrame;
import org.lychee.gui.helper.SwingHelper;
import org.lychee.zest.ZestParser;

public class Main {
	public static void main(String[] args) {
		SwingHelper.fixSwing();
		LycheeFrame frame = new LycheeFrame();
		frame.init();
//		LycheeFrame frame = new LycheeFrame();
//		frame.init();
		ZestParser parse = new ZestParser();
		parse.parseFile("testing.txt");
	}
}