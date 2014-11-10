package me.jacob.macdougall;

public class GameVariables {

	private static int FPSLimit = 60;

	public static int FramesPerSecond = 0;
	public static int UpdatesPerSecond = 0;

	private static Difficultly d;
	private static Render render;

	// private static Shadows shadow;

	public static boolean isFpsLimit() {
		return (FPSLimit > 1);
	}

	public static int getFPSLimit() {
		return FPSLimit;
	}

	public static void setFPSLimit(int fps) {
		FPSLimit = fps;
	}

	public static enum Difficultly {
		EASY, NORMAL, HARD
	}

	public static enum Render {
		MENU, OPTIONS, PAUSE, LEVEL, MAP, INVENTORY, EQUIPMENT, SPELLBOOK, CHARACTERS
	}

	public static Difficultly getDifficultly() {
		return d;
	}

	public static void setDifficultly(Difficultly diff) {
		d = diff;
	}

	/**
	 * 
	 * @param diff
	 *            0 = easy, 1 = normal, 2 = hard
	 */
	public static void setDifficultly(int diff) {
		switch (diff) {
			case 0:
				d = Difficultly.EASY;
				break;
			case 1:
				d = Difficultly.NORMAL;
				break;
			case 2:
				d = Difficultly.HARD;
				break;
			default:
				d = Difficultly.NORMAL;
				break;
		}
	}

	public Render getRender() {
		return render;
	}

	public static void setRender(Render renderType) {
		render = renderType;
	}
}
