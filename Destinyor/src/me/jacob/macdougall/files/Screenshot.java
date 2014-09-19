package me.jacob.macdougall.files;

import java.io.File;
import java.io.IOException;

import graphic.engine.screen.Screen;

import javax.imageio.ImageIO;

public class Screenshot {
	
	private int id = 0;
	
	public Screenshot(Screen screen) {
		File image = new File("Screenshot" + id + ".png");
		try {
			ImageIO.write(screen.image, "PNG", image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id++;
	}
	
	
}
