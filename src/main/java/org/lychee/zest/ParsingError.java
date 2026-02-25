package org.lychee.zest;

import org.jspecify.annotations.NonNull;

public record ParsingError(String message, String argName, int argNumber) {
	@Override
	@NonNull
	public String toString() {
		return "\terror parsing argument '" + argName + "' (argument " + argNumber + "): \n\t" + message;
	}
}
