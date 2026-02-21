package org.lychee.zest;

public record ParsingError(String message, String argName, int lineNumber) {
	@Override
	public String toString() {
		return "\terror parsing argument '" + argName + "' at line " + lineNumber + ": \n\t" + message;
	}
}
