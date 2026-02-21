package org.lychee.zest;

import org.jspecify.annotations.Nullable;

import java.awt.*;
import java.util.Map;
import java.util.function.Function;

public abstract class Command {
	abstract public void execute(Graphics2D graphics);

	public static @Nullable LineError checkArguments(String name, int line, Map<String, Result<?, ParsingError>> arguments) {
		for (Result<?, ParsingError> result : arguments.values()) {
			if (result.isErr()) {
				return new LineError(name, line, result.unwrapErr());
			}
		}

		return null;
	}

	public static Result<Command, LineError> buildChecked(String name, int line, Map<String, Result<?, ParsingError>> arguments, Function<Map<String, Result<?, ParsingError>>, Command> builder) {
		var err = Command.checkArguments(name, line, arguments);

		if (err != null) {
			return Result.err(err);
		} else {
			return Result.ok(builder.apply(arguments));
		}
	}
}
