package org.lychee.zest;

public class ZestParser {
	// take in file, convert to array list of commands
	private Command parseCommand(String line) {
		String commandName = line.split("\\(")[0];
		for (CommandType type : CommandType.values()) {
			if (type.name().equals(commandName)) {
				// todo
			}
		}

		return null;
	}
}
