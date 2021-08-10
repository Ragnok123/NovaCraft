package net.novatech.novacraft.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.novatech.novacraft.NovaCraft;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.addIcon("icons/icon256.png", Files.FileType.Internal);
		config.addIcon("icons/icon128.png", Files.FileType.Internal);
		config.foregroundFPS = 60;
        config.title = "NovaCraft";
		config.width = 960;
		config.height = 540;

		boolean touch = false;
		for (String anArg : arg) {
			if (anArg.equals("--touch")) touch = true;
		}
        new LwjglApplication(new NovaCraft(System.getProperty("user.home") + "/.novacraft", touch), config);
	}
}
