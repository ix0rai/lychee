package org.lychee.zest;

import java.awt.*;

public abstract class Command {
	abstract public Shape execute(Graphics2D graphics);

	public Color changeColor(boolean hex, String color) {
		if (hex) {
			Color newColor = Color.decode(color);
			return newColor;
		} else {
			return switch (color) {
				case "black" -> Color.BLACK;
				case "white" -> Color.WHITE;
				case "red" -> Color.RED;
				case "orange" -> Color.ORANGE;
				case "yellow" -> Color.YELLOW;
				case "green" -> Color.GREEN;
				case "blue" -> Color.BLUE;
				case "purple" -> Color.MAGENTA;
				case "pink" -> Color.PINK;
				default -> Color.BLACK;
			};
		}
	}
}
