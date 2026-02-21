package org.lychee.zest;

public record LineError(String command, ParsingError error) {
	public String toString() {
		return "error parsing command '" + command + "':\n" + error;
	}
}
