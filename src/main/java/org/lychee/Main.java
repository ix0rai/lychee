package org.lychee;

import org.lychee.gui.LycheeFrame;
import org.lychee.gui.helper.SwingHelper;
import org.lychee.zest.Argument;
import org.lychee.zest.Command;
import org.lychee.zest.ZestParser;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		SwingHelper.fixSwing();
		LycheeFrame frame = new LycheeFrame();
		frame.init();
		ZestParser parse = new ZestParser();
		var results = parse.parseFile("testing.zst");
		ArrayList<Command> commands = new ArrayList<>();
		for (var result : results) {
			if (result.isOk()) {
				commands.add(result.unwrap());
			} else {
				System.out.println(result.unwrapErr());
			}
		}
		frame.updateCommands(commands);

		System.out.println(Argument.COLOR.parse("123", 3).unwrapErr());
	}
}