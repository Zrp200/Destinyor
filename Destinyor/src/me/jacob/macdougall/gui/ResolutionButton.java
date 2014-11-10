package me.jacob.macdougall.gui;

import input.engine.mouse.Mouse;

import java.util.HashMap;
import java.util.Map;

import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.Time;
import me.jacob.macdougall.input.Keys;
import graphic.engine.screen.Screen;
import graphic.engine.window.Resolution;

public class ResolutionButton {

	public Map<String, Buttons> buttons = new HashMap<>();

	public Menus menu;

	public String resolution = Resolution.width() + " * " + Resolution.height();
	public String Window = Resolution.Fullscreen;
	public final String DefaultWindow = Resolution.Fullscreen;
	public int width = Resolution.width();
	public int height = Resolution.height();

	int selected = Resolution.getResolutionInt();

	public boolean rOn = false;
	public boolean wOn = false;

	private int amount = 3;

	public boolean mustRestart = false;

	public ResolutionButton(Menus menu) {
		for(int i = 0; i < Resolution.Resolutions.length; i++) {
			buttons.put(Resolution.Resolutions[i], new Buttons(Resolution.Resolutions[i], 130, 0));
		}
		buttons.put("Borderless Window", new Buttons("Borderless Window", 382, 32 - 21));
		buttons.put("Fullscreen", new Buttons("Fullscreen", 382, 32));
		buttons.put("Window", new Buttons("Window", 382, 32 + 21));
		this.menu = menu;
	}

	public void render(Screen screen) {
		if(rOn) {
			for(int i = 0; i < selected(amount).length; i++) {
				Buttons button = buttons.get(Resolution.Resolutions[selected(amount)[i]]);
				button.y = (32 - 21) + (21 * (i));
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
		if(rOn && Time.getKeyTimer(10, false)) {
			Destinyor.Refresh();
			for(int i = 0; i < selected(amount).length; i++) {
				Buttons button = buttons.get(Resolution.Resolutions[selected(amount)[i]]);
				if(button.inBox(x, y)) {
					resolution = Resolution.Resolutions[selected(amount)[i]];
					width = Resolution.Width[selected(amount)[i]];
					height = Resolution.Height[selected(amount)[i]];
					menu.buttons.get(2)[1].setName(resolution);
					Time.resetKeyTimer();
					selected = Resolution.getResolutionInt(resolution);
					rOn = false;
				}
			}

		}
	}

	public void setWindow(int x, int y) {
		if(wOn && Time.getKeyTimer(5, false)) {
			Destinyor.Refresh();
			if(buttons.get("Window").inBox(x, y)) {
				Window = "Window";
				menu.buttons.get(2)[4].setName(Window);
				Time.resetKeyTimer();
				wOn = false;
			}
			if(buttons.get("Borderless Window").inBox(x, y)) {
				Window = "Borderless Window";
				menu.buttons.get(2)[4].setName(Window);
				Time.resetKeyTimer();
				wOn = false;
			}
			if(buttons.get("Fullscreen").inBox(x, y)) {
				Window = "Fullscreen";
				menu.buttons.get(2)[4].setName(Window);
				Time.resetKeyTimer();
				wOn = false;
			}
		}
	}

	public void setSelected(Mouse mouse) {
		if(rOn) {
			if(!mouse.getMouseWheelMoved()) {
				if(Time.getKeyTimer(10, false)) {
					if(Keys.MoveDown()) {
						selected++;
						Time.resetKeyTimer();
					}
					if(Keys.MoveUp()) {
						selected--;
						Time.resetKeyTimer();
					}
				}
			} else {
				selected = mouse.getMouseWheel(0, Resolution.Resolutions.length - 1, Resolution.getResolutionInt());
				mouse.setMouseWheelMoved(false);
			}
		}
	}

	public void accept(Destinyor game) {
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
		Destinyor.Refresh();
		Menus.menu = Menus.getLastMenuState();
	}

	private int[] selected(int amount) {
		if(selected <= -1) {
			selected = Resolution.Resolutions.length - 1;
		}
		if(selected >= Resolution.Resolutions.length) {
			selected = 0;
		}
		int[] selectedButtons = new int[amount];
		for(int i = -1; i < amount - 1; i++) {
			if(selected + i >= 0) {
				if(selected + i <= Resolution.Resolutions.length - 1) {
					selectedButtons[i + 1] = selected + i;
				} else {
					selectedButtons[i + 1] = 0;
				}
			} else {
				selectedButtons[i + 1] = Resolution.Resolutions.length - 1;
			}
		}

		return selectedButtons;
	}

}
