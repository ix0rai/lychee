package org.lychee.zest.commands;

import org.lychee.zest.Command;
import org.lychee.zest.LineError;
import org.lychee.zest.Pair;
import org.lychee.zest.ParsingError;
import org.lychee.zest.Result;
import org.lychee.zest.arguments.Argument;

import java.awt.Graphics2D;
import java.util.List;
import java.util.Map;

public class BeginLayerCommand extends Command {
	public static final String NAME = "layer";

	private final int layer;
	public BeginLayerCommand(int layer) {
		this.layer = layer;
	}

	public int getLayer() {
		return layer;
	}

	public static java.util.List<Pair<String, Argument<?>>> getArguments() {
		return List.of(
				new Pair<>("number", Argument.INT)
		);
	}

	public static Result<Command, LineError> build(Map<String, Result<?, ParsingError>> arguments, int line) {
		return buildChecked(NAME, line, arguments, args -> new BeginLayerCommand(
				(Integer) args.get("number").unwrap()
		));
	}

	@Override
	public void execute(Graphics2D graphics) {
		// no-op
	}
}
