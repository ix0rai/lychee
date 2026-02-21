package org.lychee.zest;

import org.lychee.gui.LycheeColors;

import java.awt.*;

public class CircleCommand extends Command {
    private final Coordinate start;
    private final int width;
    private final int height;
    private final String color;


    public CircleCommand(Coordinate start, int width, int height) {
        this.start = start;
        this.width = width;
        this.height = height;
        color = null;
    }

    public CircleCommand(Coordinate start, int width, int height, String color) {
        this.start = start;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public Shape execute(Graphics2D graphics) {
        if (color != null && color.startsWith("#")) {
            graphics.setColor(changeColor(true, color));
        } else if (color != null) {
            graphics.setColor(changeColor(false, color));
        }
        graphics.setStroke(new BasicStroke(width));
        graphics.drawOval(start.getX(), start.getY(), width, height);
        return null;
    }

    public String toString() {
        return "circle: " + start + " | " + width + " | " + height + " | " + color;
    }
}
