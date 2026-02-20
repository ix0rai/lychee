package org.lychee.gui.helper;


import javax.imageio.ImageIO;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.InputStream;

import static java.awt.RenderingHints.*;

public class SwingHelper {
	public static void fixSwing() {
		try (InputStream is = SwingHelper.class.getResourceAsStream("/jbmono.ttf")) {
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);

			UIManager.put("Label.font", font);
			UIManager.put("Button.font", font);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// enable a bunch of nice things that are off by default for legacy compat
		// use hardware accel if possible
		System.setProperty("sun.java2d.opengl", "true");
		// do not use DirectX, it's buggy. software is better if OGL support is missing
		System.setProperty("sun.java2d.d3d", "false");
		System.setProperty("sun.java2d.noddraw", "true");
		// force font antialiasing
		System.setProperty("awt.useSystemAAFontSettings", "on");
		System.setProperty("swing.aatext", "true");
		System.setProperty("swing.useSystemFontSettings", "true");
		// disable Metal's abuse of bold fonts
		System.setProperty("swing.boldMetal", "false");
		// always create native windows for popup menus (allows animations to play, etc)
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		// no ImageIO, I don't want you to write tons of tiny files to the disk, to be quite honest
		ImageIO.setUseCache(false);
	}

	public static void fix(Graphics g) {
		if (g instanceof Graphics2D g2d) fix(g2d);
	}

	public static void fix(Graphics2D g2d) {
		g2d.setRenderingHint(KEY_RENDERING, VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(KEY_STROKE_CONTROL, VALUE_STROKE_PURE);
		g2d.setRenderingHint(KEY_COLOR_RENDERING, VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(KEY_INTERPOLATION, VALUE_INTERPOLATION_BICUBIC);
		g2d.setRenderingHint(KEY_ALPHA_INTERPOLATION, VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(KEY_DITHERING, VALUE_DITHER_DISABLE);
	}
}
