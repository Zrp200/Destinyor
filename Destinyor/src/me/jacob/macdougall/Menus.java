package me.jacob.macdougall;

import graphic.engine.screen.GameFont;
import graphic.engine.screen.Screen;
import graphic.engine.window.Resolution;

import input.engine.keyboard.InputHandler;
import input.engine.mouse.Mouse;
import me.jacob.macdougall.audio.Sound;
import me.jacob.macdougall.graphics.Buttons;
import me.jacob.macdougall.player.Move;

import java.util.Map;
import java.util.HashMap;

public class Menus {
	
	public static final int CLOSED = -1, MAIN = 0, MENU = 1, OPTIONS = 2, BATTLES = 3;
	
	
	
	
	public int[] centre = {
			256, 192
	};
	
	public Map<Integer, Buttons[]> buttons = new HashMap<>();
	
	public String[] names = {
			"Start", "Options", "Save", "Exit", "Resolution"
	};
	
	public String resolution = Resolution.width() + " * " + Resolution.height();
	
	
	//public Enum<?> hi;
	
//	public Enum<?> menu = {
//		0, 1, 2	
//	};
	
	public Map<Integer, Buttons> button = new HashMap<>();
	// 1024 is double 240
	// 1152 is double + 30 270
	// 1280 is double + 60 or triple - 60 300
	// 1600 is triple + 15	375
	// 1920 is quadruple +  30 480
	
	
	// -1 no menu currently open, 0 main, 1 esc, 2 options, 3 battles
	
	public static int menu = 0;
	//public static int buttonsX = (Resolution.width() / Options.ResX[Resolution.getScaleX()] * Options.ResY[Resolution.getScaleY()]) / 2;
	public InputHandler input;
	public Destinyor game;
	public ResolutionButton rButton = new ResolutionButton(this);
	public boolean isOn = true;
	//public float xDivide = (float) (Resolution.width() / 512);
	//public float xDivide = (float) 512 / Resolution.width();
	//public float divide = 0.1953125f;
	//private Mouse mouse;
	
	private int[] pressed = new int[2];
	
	
	Menus(Destinyor game, InputHandler input) {
		this.game = game;
		this.input = input;
		for(int i = 0; i < 4; i++) {
			button.put(i, new Buttons(names[i], 192, 128 + i * 30));
		}
		Buttons[] buttons1 = {
			button.get(0), button.get(1), button.get(2), button.get(3)	
		};
		
		buttons.put(0, buttons1);
		
		button.put(4, new Buttons(names[4], 10, 32));
		button.put(5, new Buttons(rButton.resolution, 10 + 120, 32));
		
		button.put(6, new Buttons("Accept", 32, 32 + 120));
		
		button.put(7, new Buttons("Display Mode: ", 512 - 250, 32));
		button.put(8, new Buttons(Resolution.Fullscreen, 512 - 130, 32));
		
		
		Buttons[] buttons2 = {
				button.get(4), button.get(5), button.get(6), button.get(7), button.get(8)
		};
		
		buttons.put(2, buttons2);
		
//		button.put(6, new Buttons("Attack", 256, 384));
//		button.put(7, new Buttons("Spells", 256, 384 - 8));
//		
//		Buttons[] commands = {
//				button.get(6), button.get(7)
//		};
//		
//		buttons.put(3, commands);
//		for(int i = 0; i < button.size(); i++) {
//		}
		
		//this.mouse = mouse;
	}
	
	public void checkMenu() {
		if(Keys.Escape()) {
				menu = 1;
		}
	}
	
	public void render(Screen screen) {
		switch(menu) {
			case CLOSED: break;
			case MAIN: 
                            //for(int i = 0; i < buttons.get(menu).length; i++) {
                            //buttons.get(menu)[i].render(screen);
                            for(Buttons b : buttons.get(menu))
				b.render(screen);
                            //}
                            break;
			case MENU:  for(Buttons b : buttons.get(menu))
                                        b.render(screen);
                                    break;
			case OPTIONS:   for(Buttons b : buttons.get(menu))
                                            b.render(screen);
                                        rButton.render(screen);
					break;
			case BATTLES: for(Buttons b : buttons.get(menu))
                                        b.render(screen);
                                    break;
		}
		if(rButton.mustRestart) {
			GameFont.render("You must restart the game in order for these changes to take affect", screen, 512 / 2 - 120,  368 / 2 - 20);
		}
		
	}
	public void mouseChecker(Mouse mouse, int width, int height) {
		if(menu == CLOSED) {
			return;
		}
		
		pressed = mouse.getPressed();
//		for(int i = 0; i < buttons.size(); i++) {
//			if(buttons.get(i).inBox(mouse.mouseX, mouse.mouseY)) {
//				System.out.println("Button: " + i + " is working");
//			}
//		}
		
		if(pressed != null) {
			for(int i = 0; i < button.size(); i++) {
				if(button.get(i).inBox(pressed[0], pressed[1])) {
					switch(i) {
					case 0: isOn = false;
							menu = -1;
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
				if(buttons.get(2)[1].inBox(pressed[0], pressed[1]) && Time.getKeyTimer(10, true)) {
					rButton.rClick();
				}
				if(buttons.get(2)[2].inBox(pressed[0], pressed[1])) {
					rButton.accept(game);
				}
				if(buttons.get(2)[4].inBox(pressed[0], pressed[1]) && Time.getKeyTimer(10, true)) {
					rButton.wClick();
				}
			}
		}
		
		//int x = mouse.mouseX;
		//System.out.println(x);
		
	}
	
	public static void movement(Move move) {
		if(menu > CLOSED) {
			move.canMove = false;
		} else {
			move.canMove = true;
		}
	}
}
