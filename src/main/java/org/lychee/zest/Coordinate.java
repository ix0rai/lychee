package org.lychee.zest;

public class Coordinate {
	private int x;
	private int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "[" + x + ", " + y + "]";
	}
}
