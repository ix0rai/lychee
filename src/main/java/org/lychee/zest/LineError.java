package org.lychee.zest;

public record LineError(String command, ParsingError error) {
}
