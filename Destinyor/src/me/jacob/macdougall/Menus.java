package me.jacob.macdougall;

import graphic.engine.screen.Art;
import graphic.engine.screen.GameFont;
import graphic.engine.screen.Screen;
import graphic.engine.window.Resolution;

import input.engine.keyboard.InputHandler;
import input.engine.mouse.Mouse;
import me.jacob.macdougall.audio.Sound;
import me.jacob.macdougall.graphics.Buttons;
import me.jacob.macdougall.graphics.Scrollbars;
import me.jacob.macdougall.player.Move;

import java.util.Map;
import java.util.HashMap;

public class Menus {
	
	public static final int NONE = -1, MAIN = 0, MENU = 1, OPTIONS = 2, BATTLES = 3;
	
	
	
	
	public int[] centre = {
			256, 192
	};
	
	public Map<Integer, Buttons[]> buttons = new HashMap<>();
	
	public String[][] names = {
			{ "Start", "Options", "Save", "Exit" },
			{ "Resume", "Options", "Save", "Exit" },
			//{ "Resolution" }
	};
	
	public String resolution = Resolution.width() + " * " + Resolution.height();
	
	public Map<Integer, Buttons> button = new HashMap<>();
	
	
	// -1 no menu currently open, 0 main, 1 esc, 2 options, 3 battles
	public static int menu = 0;
	
	
	private static int menuState = 0;
	public static boolean mainMenu = true;
	public static int getLastMenuState() {
		if(mainMenu)
			return MAIN;
		else
			return MENU;
	}
	
	public InputHandler input;
	public Destinyor game;
	public ResolutionButton rButton = new ResolutionButton(this);
	public Scrollbars rScroll = new Scrollbars(250, 32, 20, 10);
	public boolean isOn = true;
	public int buttonSelected = 0;
	
	private int[] pressed = new int[2];
	
	
	Menus(Destinyor game, InputHandler input) {
		this.game = game;
		this.input = input;
		
		Buttons[][] menus = new Buttons[names.length][];
		
		for(int i = 0; i < names.length; i++) {
			System.out.println(i);
			menus[i] = new Buttons[names[i].length];
			for(int j = 0; j < names[i].length; j++) {
				System.out.println(j);
				button.put(button.size(), new Buttons(names[i][j], 192, 128 + j * 30));
				menus[i][j] = button.get(button.size() - 1);
			}
			buttons.put(i, menus[i]);
		}
		
		button.put(button.size(), new Buttons("Resolution", 10, 32));
		
		button.put(button.size(), new Buttons(rButton.resolution, 10 + 120, 32));
		
		button.put(button.size(), new Buttons("Accept", 32, 32 + 120));
		
		button.put(button.size(), new Buttons("Display Mode: ", 512 - 250, 32));
		
		button.put(button.size(), new Buttons(Resolution.Fullscreen, 512 - 130, 32));
		
		Buttons[] buttons3 = {
				button.get(button.size() - 5), button.get(button.size() - 4), button.get(button.size() - 3), button.get(button.size() - 2), button.get(button.size() - 1)
		};
		
		buttons.put(buttons.size(), buttons3);
	}
	
	public void checkMenu(Move move) {
		if(Keys.Escape()) {
			menu = 1;
			Menus.movement(move);
		}
	}
	
	public void render(Screen screen) {
		
		if(menu > NONE) {
			int i = 0;
		for(Buttons b : buttons.get(menu)) {
			if(b != null) {
			b.render(screen);
			if(buttonSelected == i)
			screen.render(Art.getFont()[35][1], b.x - 10, b.y + b.getHeight() / 8);
			}
			i++;
		}
		}
		
		switch(menu) {
			case NONE: break;
			case MAIN: break;
			case MENU: break;
			case OPTIONS: 
				rButton.render(screen);
                rScroll.render(screen);
                break;
			case BATTLES: break;
		}
		if(rButton.mustRestart) {
			GameFont.render("You must restart the game in order for these changes to take affect", screen, 512 / 2 - 120,  368 / 2 - 20);
		}
		
	}
	public void mouseChecker(Mouse mouse, int width, int height, Move move) {
		if(menu == NONE) {
			return;
		}
		
		pressed = mouse.getPressed();
		
		if(pressed != null) {
			for(int i = 0; i < button.size(); i++) {
				if(button.get(i).inBox(pressed[0], pressed[1])) {
					switch(i) {
					case 0: isOn = false;
							mainMenu = false;
							menu = -1;
							mouse.resetMouseWheel();
							movement(move);
							break;
							
					case 1: menu = 2;
							break;
							
					case 2: break;
					
					case 3: 
						for(Sound sounds : Sound.sounds.values()) {
							sounds.stopSound();
							sounds.close();
						}
						System.exit(0);
						break;
					}
				}
			}
			if(menu == 2) {
				rButton.setRes(pressed[0], pressed[1]);
				rButton.setWindow(pressed[0], pressed[1]);
				rButton.setSelected(mouse);
				if(buttons.get(menu)[1].inBox(pressed[0], pressed[1]) && Time.getKeyTimer(10, false)) {
					rButton.rClick();
					Time.resetKeyTimer();
					Destinyor.Refresh = true;
				} else
				if(buttons.get(menu)[2].inBox(pressed[0], pressed[1])) {
					rButton.accept(game);
				} else
				if(buttons.get(menu)[4].inBox(pressed[0], pressed[1]) && Time.getKeyTimer(10, false)) {
					rButton.wClick();
					Time.resetKeyTimer();
				} else
				if(rScroll.inBox(pressed[0], pressed[1])) {
					rScroll.clicked();
				}
			}
		}
	}
	
	public void inputChecker() {
		if(Time.getKeyTimer(10, false) && menu != NONE) {
		if(Keys.MoveUp()) {
			buttonSelected -= 1;
			Time.resetKeyTimer();
		}
		if(Keys.MoveDown()) {
			buttonSelected += 1;
			Time.resetKeyTimer();
		}
		if(Keys.Enter()) {
			click();
			Time.resetKeyTimer();
		}
		}
		if(menu != NONE) {
			if(buttonSelected > buttons.get(menu).length - 1) {
				buttonSelected = 0;
			}
			if(buttonSelected < 0) {
				buttonSelected = buttons.get(menu).length - 1;
			}
		}
	}
	
	public void click() {
		switch(buttonSelected) {
		case 0: isOn = false;
				mainMenu = false;
				menu = NONE;
				break;
		case 1:	menu = OPTIONS;
				break;
		case 2: break;
		case 3: 
			for(Sound sounds : Sound.sounds.values()) {
				sounds.stopSound();
				sounds.close();
			}
			System.exit(0);
				break;
		}
		buttonSelected = 0;
	}
	
	public static void movement(Move move) {
		if(menu > NONE) {
			move.canMove = false;
		} else {
			move.canMove = true;
		}
	}
}
