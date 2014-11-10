package me.jacob.macdougall.graphics;

import graphic.engine.screen.Art;
import graphic.engine.screen.Screen;
import me.jacob.macdougall.world.*;

public class UI {

	public static final int Map = 0, Fight = 1, Minimap = 2, Inventory = 3,
			Equipment = 4, Spellbook = 5, Player1 = 6, Player2 = 7,
			Player3 = 8, Player4 = 9;

	// 0 map, 1, fight, 2, minimap, 3 inv, 4 equip, 5 spellbook, 6 Kryo equip, 7 Karen equip, 8 Player 3 Equip,  8 Player 4 Equip
	public static int menu = 0;

	public static boolean biggerUI = true;

	private static final int BorderWidth = 16, BorderHeight = 12;
	private static final int GroundWidth = 16, GroundHeight = 12;

	public static void renderSpritesheet(Screen screen) {
		for(int i = 0; i < Art.getSpritesheet().length; i++) {
			for(int j = 0; j < Art.getSpritesheet()[i].length; j++) {
				screen.render(Art.getSpritesheet()[i][j], i * Tile.SIZE, j * Tile.SIZE);
			}
		}
	}

	public static void render(Screen screen) {
		Ground(screen, GroundWidth, GroundHeight);

		Border(screen, BorderWidth, BorderHeight);

		BorderEnemyText(screen, BorderWidth, BorderHeight);
	}

	public static void renderInventory(Screen screen) {
		REFRESH(screen);
		BorderMenu(screen, BorderWidth, BorderHeight);
	}

	public static void TextBox(Screen screen) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 2; j++) {
				screen.render(Art.getSpritesheet()[0][0], (i + 4) * Tile.SIZE, (j + 9) * Tile.SIZE);
			}
		}
	}

	public static void renderEquipment(Screen screen) {
		REFRESH(screen);
		BorderMenu(screen, BorderWidth, BorderHeight);
		BorderP1(screen, BorderWidth, BorderHeight - 4);
		BorderP2(screen, BorderWidth, BorderHeight - 4);
		BorderP3(screen, BorderWidth, BorderHeight - 4);
	}

	private static void BorderMenu(Screen screen, int w, int h) {
		BorderX(screen, w, h);
		BorderY(screen, w, h);
		BorderTextBottom(screen, w, h);

	}

	private static void BorderP1(Screen screen, int w, int h) {
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				screen.render(Art.getSpritesheet()[19][3], 3 * Tile.SIZE, (j + 0) * Tile.SIZE);

			}
		}
	}

	private static void BorderP2(Screen screen, int w, int h) {
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				screen.render(Art.getSpritesheet()[18][3], 8 * Tile.SIZE, (j + 0) * Tile.SIZE);
			}
		}
	}

	private static void BorderP3(Screen screen, int w, int h) {
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				screen.render(Art.getSpritesheet()[18][3], 12 * Tile.SIZE, (j + 0) * Tile.SIZE);
			}
		}
	}

	@SuppressWarnings("unused")
	private static void test(Screen screen, int w, int h) {
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				screen.render(Art.getSpritesheet()[0][0], (i + 0) * Tile.SIZE, 5 * Tile.SIZE);
			}
		}
	}

	private static void Border(Screen screen, int w, int h) {
		BorderX(screen, w, h);
		BorderY(screen, w, h);
		BorderTextTop(screen, w, h);
	}

	private static void BorderTextBottom(Screen screen, int w, int h) {
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				screen.render(Art.getSpritesheet()[17][3], (i + 0) * Tile.SIZE, 16 * Tile.SIZE);
			}
		}
	}

	private static void BorderX(Screen screen, int w, int h) {
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				//Tile.tiles[Tile.tile]
				//Shadows shadow = new Shadows(Art.getSpritesheet()[17][3]);
				//tileX.render(screen, (i + 0) * Tile.SIZE, (11 * Tile.SIZE) + 12, Shadows.DOWN);
				screen.render(Art.getSpritesheet()[17][3], (i + 0) * Tile.SIZE, (11 * Tile.SIZE) + 12);
				//screen.render(Art.getSpritesheet()[16][3], (i + 0) * Tile.SIZE, 8 * Tile.SIZE);
				//shadow = new Shadows(Art.getSpritesheet()[16][3]);
				screen.render(Art.getSpritesheet()[16][3], (i + 0) * Tile.SIZE, (-1 * Tile.SIZE) + 6);
			}
		}
	}

	private static void BorderY(Screen screen, int w, int h) {
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				screen.render(Art.getSpritesheet()[18][3], (0 * Tile.SIZE), ((j + 0) * Tile.SIZE));
				screen.render(Art.getSpritesheet()[19][3], (15 * Tile.SIZE) - 3, (j + 0) * Tile.SIZE);
				//screen.render(Art.getSpritesheet()[19][3], 12 * Tile.SIZE, (j + 9) * Tile.SIZE);
			}
		}
	}

	private static void BorderTextTop(Screen screen, int w, int h) {
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				screen.render(Art.getSpritesheet()[17][3], (i + 0) * Tile.SIZE, 9 * Tile.SIZE);
			}
		}
	}

	private static void BorderEnemyText(Screen screen, int w, int h) {
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				screen.render(Art.getSpritesheet()[18][3], 5 * Tile.SIZE, (j + 9) * Tile.SIZE);
			}
		}
	}

	private static void Ground(Screen screen, int w, int h) {
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				screen.render(Art.getSpritesheet()[3][0], (i + 0) * Tile.SIZE, (j + 0) * Tile.SIZE);
			}
		}
	}

	public static void REFRESH(Screen screen) {
		for(int i = 0; i < LevelMap.FloorWidth; i++) {
			for(int j = 0; j < LevelMap.FloorHeight; j++) {
				screen.render(Art.getSpritesheet()[0][7], (i + 0) * Tile.SIZE, (j + 0) * Tile.SIZE);
			}
		}
	}

}