package me.jacob.macdougall.files;

import graphic.engine.screen.Art;
import graphic.engine.screen.Bitmap;
import graphic.engine.screen.GameFont;
import graphic.engine.screen.Screen;
import graphic.engine.window.Resolution;

import input.engine.mouse.Mouse;

import java.util.ArrayList;
import java.util.List;

import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.graphics.UI;
import me.jacob.macdougall.gui.GUI_Objects;
import me.jacob.macdougall.gui.Menus;
import me.jacob.macdougall.gui.TextBox;
import me.jacob.macdougall.input.Keys;
import me.jacob.macdougall.world.Tile;

//import java.io.File;
//
//import me.jacob.macdougall.Destinyor;

public class Saves {
	
	public static List<Saves> saves = new ArrayList<>();
	
	public static Saves emptySave = new Saves("Create new save", -1, null);
	
	private int id = 0;
	private String name;
	private Bitmap image;
	private String filePath;
	private static int save = 0;
	public static int saveSelector = 0;
	private GUI_Objects saveslot;
	private TextBox namer = new TextBox(Resolution.width() / 4, Resolution.height() / 4, 120, 20, Art.getButtons()[0][0]);
	private boolean clicked = false;
	private static Saves[] displayedSaves = new Saves[3];
//	
	public Saves(String name, int id, Bitmap image) {
		this.name = name;
		this.image = image;
		this.id = id;
		filePath = "Save" + getID();
		saveslot = new GUI_Objects(0, id * (4 * Tile.SIZE), 16 * Tile.SIZE, 4 * Tile.SIZE);
	}
	
	public String getID() {
		if(id < 10) {
			return "00" + id;
		}
		if(id < 100) {
			return "0" + id;
		}
		return Integer.toString(id);
	}
	
	public static String getID(int id) {
		if(id < 10) {
			return "00" + id;
		}
		if(id < 100) {
			return "0" + id;
		}
		return Integer.toString(id);
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void render(Screen screen, int num) {
		if(image != null)
			screen.render(image, 7, (128 * num) + 7);
		
		if(!name.equals("Create new save"))
			GameFont.render("Name: " + name, screen, 196, (128 * num) + 32);
		else
			GameFont.render(name, screen, 196, (128 * num) + 32);
		if(!name.equals("Create new save"))
			GameFont.render("Save: " + filePath, screen, 196, (128 * num) + 64);
		//else
			//GameFont.render("Create new save", screen, 64, 48 * num);
	}
	
	public static void renderSaveScreen(Screen screen) {
		UI.renderSaves(screen);
		for(int i = 0; i < displayedSaves.length; i++) {
			if(displayedSaves[i] != null) {
				displayedSaves[i].render(screen, i);
				if(displayedSaves[i].namer.focused) {
					displayedSaves[i].namer.render(screen);
				}
			}
		}
//		if(!saves.isEmpty()) {
//			for(int i = save; i < saves.size(); i++) {
//				saves.get(i).render(screen);
//				if(saves.get(i).namer.focused) {
//					saves.get(i).namer.render(screen);
//				}
//			}
//		}
	}
	
	public void update(Mouse mouse) {
		//if(save) {
			if(saveslot.inBox(mouse.getPressed(Mouse.X), mouse.getPressed(Mouse.Y))) {
				namer.focused = true;
				clicked = true;
			}
			if(namer.focused) {
				namer.update(mouse);
			}
		//}
	}
	
	public static void update(Mouse mouse, Screen screen, Destinyor game) {
		if(Menus.menu == Menus.SAVES) {
			if(save >= 0 && save <= 999) {
				
				if(Keys.MoveDown()) {
					saveSelector++;
					if(saveSelector > 3) {
						saveSelector = 0;
						save++;
						if(save > 999) {
							save = 999;
						}
					}
				}
				if(Keys.MoveUp()) {
					saveSelector--;
					if(saveSelector < 0) {
						saveSelector = 3;
						save--;
						if(save < 0) {
							save = 0;
						}
					}
				}
				//Saves[] displayedSaves = new Saves[3];
				
				for(int i = 0; i < displayedSaves.length; i++) {
					displayedSaves[i] = get(save+i);
					displayedSaves[i].update(mouse);
					if(displayedSaves[i].clicked) {
						if(!displayedSaves[i].namer.focused) {
							if(displayedSaves[i].name.equals("Create new save")) {
								UI.REFRESH(screen);
								game.renderSave();
								String path = DestinyorFiles.DestinyorSaveFolder + DestinyorFiles.fileSplit + "Save" + getID(saves.size());
								FileLoader.CreateFolder(path);
								Screenshot screenshot = new Screenshot(screen, path);
								add(new Saves(displayedSaves[i].namer.input, saves.size(), Art.getAndConvert(screenshot.getPath(), 160, 128)));
							} else {
								
							}
						}
					}
				}
			}
		}
	}
	
	public static void add(Saves save) {
		if(saves.isEmpty()) {
			saves.add(save);
		} else if(saves.size() < 999) {
			saves.add(save);
		}
	}
	
	public static Saves get(int i) {
		if(!saves.isEmpty()) {
			if(i >= saves.size() || saves.get(i) == null) {
				return emptySave;
			} else {
				return saves.get(i);
			}
		}
		return emptySave;
	}
	
	public static void loadSaves() {
		System.out.println("Checking saves");
		if(!FileLoader.checkIfFolderIsEmpty(DestinyorFiles.DestinyorSaveFolder)) {
			String[] saveFiles = FileLoader.getFilesAndFolders(DestinyorFiles.DestinyorSaveFolder, "Save");
			if(saveFiles != null && saveFiles.length > 0) {
				for(int i = 0; i < saveFiles.length; i++) {
					System.out.println("Found save: " + saveFiles[i]);
					add(new Saves(saveFiles[i], i, Art.getAndConvert(DestinyorFiles.DestinyorSaveFolder + DestinyorFiles.fileSplit + saveFiles[i] + DestinyorFiles.fileSplit + "Icon.png", 160, 120)));
					System.out.println("Added save: " + saveFiles[i]);
				}
			}
		}
	}
	
	
}
