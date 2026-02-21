package org.lychee.zest;

public record LineError(String command, int lineNumber, ParsingError error) {
	@Override
	public String toString() {
		if (error != null) {
			return "error parsing arguments for command '" + command + "' on line " + lineNumber + ":\n" + error;
		} else {
			return "error reading line " + lineNumber + ":\n\t" + command;
		}
	}
}
