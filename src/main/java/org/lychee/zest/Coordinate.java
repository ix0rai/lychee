package org.lychee.zest;

public class Coordinate {
	private int x;
	private int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static Coordinate parse(String coordinateString) {
		// coordinates are represented by [x, y]
		// trim off []
		coordinateString = coordinateString.substring(1, coordinateString.length() - 1);

		String[] coords = coordinateString.split(":");
		int x = Integer.parseInt(coords[0]);
		int y = Integer.parseInt(coords[1]);
		return new Coordinate(x, y);
	}

	public String toString() {
		return "[" + x + ", " + y + "]";
	}
}
