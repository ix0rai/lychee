package org.lychee.zest;

import java.awt.Graphics2D;

public abstract class Command {
	abstract public Shape execute(Graphics2D graphics);
}
