package org.lychee.zest;

import java.awt.Graphics2D;

public class CircleCommand extends Command {
    private final Coordinate start;
    private final Coordinate end;
    private final int width;

    public CircleCommand(Coordinate start, Coordinate end, int width) {
        this.start = start;
        this.end = end;
        this.width = width;
    }

    @Override
    public Shape execute(Graphics2D graphics) {
        return null;
    }

    public String toString() {
        return start + " | " + end + " | " + width;
    }
}
