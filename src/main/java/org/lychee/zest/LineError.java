package org.lychee.zest;

import org.jspecify.annotations.NonNull;

public record LineError(String command, int lineNumber, ParsingError error) {
	@Override
	@NonNull
	public String toString() {
		if (error != null) {
			return "error parsing arguments for command '" + command + "' on line " + lineNumber + ":\n" + error;
		} else {
			return "error reading line " + lineNumber + ":\n\t" + command;
		}
	}
}
