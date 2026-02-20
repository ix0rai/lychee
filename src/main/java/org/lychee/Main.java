package org.lychee;

import org.lychee.gui.LycheeFrame;
import org.lychee.gui.helper.SwingHelper;
import org.lychee.zest.Command;
import org.lychee.zest.ZestParser;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		SwingHelper.fixSwing();
		LycheeFrame frame = new LycheeFrame();
		frame.init();
//		LycheeFrame frame = new LycheeFrame();
//		frame.init();
		ZestParser parse = new ZestParser();
		List<Command> commands = parse.parseFile("testing.txt");
		frame.updateCommands(commands);
	}
}