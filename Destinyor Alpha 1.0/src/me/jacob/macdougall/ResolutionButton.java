package me.jacob.macdougall;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import me.jacob.macdougall.graphics.Buttons;
import graphic.engine.screen.GameFont;
import graphic.engine.screen.Screen;
import graphic.engine.window.Resolution;

public class ResolutionButton {
	
	public Map<String, Buttons> buttons = new HashMap<>();
	
	//public GameStorage buttons = new GameStorage();
	public Menus menu;
	
	public String resolution = Resolution.width() + " * " + Resolution.height();
	public String Window = Resolution.Fullscreen;
	public final String DefaultWindow = Resolution.Fullscreen;
	public int width = Resolution.width();
	public int height = Resolution.height();
	
	public boolean rOn = false;
	public boolean wOn = false;
	
	public boolean mustRestart = false;
	
	public ResolutionButton(Menus menu) {
		for(int i = 0; i < Resolution.Resolutions.length; i++) {
			buttons.put(Resolution.Resolutions[i], new Buttons(Resolution.Resolutions[i], 32, 21 * i));
		}
		buttons.put("Borderless Window", new Buttons("Borderless Window", 512 - 250, 0));
		buttons.put("Fullscreen", new Buttons("Fullscreen", 512 - 250, 21));
		buttons.put("Window", new Buttons("Window", 512 - 250, 42));
		this.menu = menu;
	}
	
	public void render(Screen screen) {
		if(rOn) {
			for(int i = 0; i < Resolution.Resolutions.length; i++) {
				Buttons button = buttons.get(Resolution.Resolutions[i]);
				button.render(screen);
			}
		}
		if(wOn) {
			buttons.get("Borderless Window").render(screen);
			buttons.get("Window").render(screen);
			buttons.get("Fullscreen").render(screen);
		}
	}
	
	public void rClick() {
		if(rOn) {
			rOn = false;
		} else {
			rOn = true;
		}
	}
	
	public void wClick() {
		if(wOn) {
			wOn = false;
		} else {
			wOn = true;
		}
	}
	
	public void setRes(int x, int y) {
		if(rOn && Time.getKeyTimer(5, true)) {
		Destinyor.Refresh = true;
		for(int i = 0; i < Resolution.Resolutions.length; i++) {
			Buttons button = buttons.get(Resolution.Resolutions[i]);
			if(button.inBox(x, y)) {
				resolution = Resolution.Resolutions[i];
				width = Resolution.Width[i];
				height = Resolution.Height[i];
				menu.buttons.get(2)[1].setName(resolution);
				rOn = false;
			}
		}
		}
	}
	
	public void setWindow(int x, int y) {
		if(wOn && Time.getKeyTimer(5, true)) {
			Destinyor.Refresh = true;
			if(buttons.get("Window").inBox(x, y)) {
				Window = "Window";
				menu.buttons.get(2)[4].setName(Window);
				wOn = false;
			}
			if(buttons.get("Borderless Window").inBox(x, y)) {
				Window = "Borderless Window";
				menu.buttons.get(2)[4].setName(Window);
				wOn = false;
			}
			if(buttons.get("Fullscreen").inBox(x, y)) {
				Window = "Fullscreen";
				menu.buttons.get(2)[4].setName(Window);
				wOn = false;
			}
//				Buttons button = buttons.get(Resolution.Resolutions[i]);
//				if(button.inBox(x, y)) {
//					resolution = Resolution.Resolutions[i];
//					width = Resolution.Width[i];
//					height = Resolution.Height[i];
//					menu.buttons.get(2)[1].setName(resolution);
//				}
//			}
		}
	}
	
	public void accept(Destinyor game) {
		Destinyor.Refresh = true;
		Resolution.setWidth(width);
		Resolution.setHeight(height);
		game.setSize(width, height);
		game.setLocation(game.getLocation());
		game.frame.setSize(width, height);
		game.frame.setLocation(game.frame.getLocation());
		Resolution.Fullscreen = Window;
		if(!Window.equalsIgnoreCase(DefaultWindow)) {
			Destinyor.setSettings();
			mustRestart = true;
		}
//		switch(Window) {
//        case "Fullscreen": game.frame.setUndecorated(true);
//            game.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//            break;
//        case "Borderless Window": game.frame.setUndecorated(true);
//            break;
//    }
		Destinyor.Refresh = true;
		//game.frame.setL
		menu.menu = 0;
	}
	
}
