package org.lychee.zest;

public record ParsingError(String message, String argName, int argNumber) {
	@Override
	public String toString() {
		return "\terror parsing argument '" + argName + "' (argument " + argNumber + "): \n\t" + message;
	}
}
