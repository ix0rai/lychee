package org.lychee.zest;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class CircleCommand extends Command {
    public static final String NAME = "circle";

    private final Coordinate start;
    private final int width;
    private final int height;
    private final Color color;

    public CircleCommand(Coordinate start, int width, int height, Color color) {
        this.start = start;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public static java.util.List<Pair<String, Argument<?>>> getArguments() {
        return List.of(
                new Pair<>("start", Argument.COORDINATE),
                new Pair<>("width", Argument.INT),
                new Pair<>("height", Argument.INT),
                new Pair<>("color", Argument.COLOR)
        );
    }

    public static Result<Command, LineError> build(Map<String, Result<?, ParsingError>> arguments, int line) {
        return buildChecked(NAME, line, arguments, args -> new CircleCommand(
                (Coordinate) args.get("start").unwrap(),
                (Integer) args.get("width").unwrap(),
                (Integer) args.get("height").unwrap(),
                (Color) args.get("color").unwrap()
        ));
    }

    @Override
    public void execute(Graphics2D graphics) {
        graphics.setColor(this.color);
        graphics.setStroke(new BasicStroke(width));
        graphics.drawOval(start.getX(), start.getY(), width, height);
    }

    public String toString() {
        return "circle: " + start + " | " + width + " | " + height + " | " + color;
    }
}
