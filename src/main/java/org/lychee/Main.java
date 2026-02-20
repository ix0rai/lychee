package org.lychee;

import org.lychee.gui.LycheeFrame;
import org.lychee.zest.ZestParser;

public class Main {
	public static void main(String[] args) {
//		LycheeFrame frame = new LycheeFrame();
//		frame.init();
		ZestParser parse = new ZestParser();
		parse.parseFile("testing.txt");
	}
}