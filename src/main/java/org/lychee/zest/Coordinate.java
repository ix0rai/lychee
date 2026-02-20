package org.lychee.zest;

import java.awt.event.MouseEvent;

public class Coordinate {
	private final int x;
	private final int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static Coordinate parse(String coordinateString) {
		// coordinates are represented by [x:y]
		// trim off []
		coordinateString = coordinateString.substring(1, coordinateString.length() - 1);

		String[] coords = coordinateString.split(":");
		int x = Integer.parseInt(coords[0]);
		int y = Integer.parseInt(coords[1]);
		return new Coordinate(x, y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String toString() {
		return "[" + x + ", " + y + "]";
	}
}
