package org.lychee.zest;

public enum CommandType {
	LINE("line", LineCommand.class),
	ERASE("erase", EraseCommand.class),
	FILL("fill", FillCommand.class);

	private final String id;
	private final Class<Command> commandClass;

	CommandType(String id, Class<? extends Command> commandClass) {
		this.id = name();
		this.commandClass = null;
	}
}
