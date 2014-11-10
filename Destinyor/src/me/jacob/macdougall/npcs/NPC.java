package me.jacob.macdougall.npcs;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.Time;
import me.jacob.macdougall.cutscenes.Cutscene;
import me.jacob.macdougall.input.Keys;
import me.jacob.macdougall.player.Move;
import me.jacob.macdougall.player.Player;
import me.jacob.macdougall.world.Houses;
import me.jacob.macdougall.world.LevelMap;
import me.jacob.macdougall.world.Tile;
import graphic.engine.screen.Art;
import graphic.engine.screen.Bitmap;
import graphic.engine.screen.Dialouge;
import graphic.engine.screen.Screen;

public class NPC {

	private LevelMap map;

	public String[] dialouge;
	private String[] dialougePerm;

	public static Map<Integer, NPC> npcs = new HashMap<>();
	private int ID = 0;
	private static int NO = -1;
	private int n = -1;

	private Random random = new Random();
	private int ran = random.nextInt(300);

	protected long t = System.currentTimeMillis();
	private long t1 = System.currentTimeMillis();

	public String name = "";
	public String sFrame;
	public boolean render = true;
	protected int time = 0;
	protected int X = 0;
	protected int Y = 0;
	private int XHolder = X, YHolder = Y;
	public int dialougeLocation;
	public boolean inRange;
	public boolean canMove = true;
	public boolean speaking = false;
	public boolean moving = true;
	public byte opinion = 100;
	public boolean cutscene = false;
	public Houses home;

	public int frame = 0;
	public int gold = 0;

	protected Bitmap[] frames;

	public Bitmap getFrames(int i) {
		return frames[i];
	}

	public LevelMap getMap() {
		return this.map;
	}

	public void init(LevelMap lvlmap) {
		if(lvlmap != null && map == null)
			this.map = lvlmap;
	}

	public void Speaking() {
		if(System.currentTimeMillis() >= t1 + 100 && !Cutscene.playing) {
			//Keywords.setDialougeWithCharacter();
			t1 = System.currentTimeMillis();
			if(Keys.Enter() && this.X == Player.X + Move.GetXDir() && this.Y == Player.Y + Move.GetYDir() && Time.getKeyTimer(10, false)) {
				Destinyor.Refresh();
				this.n++;
				this.speaking = true;
				this.dialougeLocation = n;
				this.canMove = false;
				if(dialougeLocation == dialouge.length) {
					this.dialougeLocation = 0;
					this.n = -1;
					this.stopSpeaking();
					canMove = true;
				}
				dialouge[dialougeLocation] = Keywords.setKeyWords(dialougePerm[dialougeLocation]); // Keeps updating the speech incase the play changes characters.
				Time.resetKeyTimer();
			}
		}
		if(!collision(Player.X - 1, Player.Y)) {
			if(!collision(Player.X + 1, Player.Y)) {
				if(!collision(Player.X, Player.Y - 1)) {
					if(!collision(Player.X, Player.Y + 1)) {
						speaking = false;
						canMove = true;
					}
				}
			}
		}
	}

	public NPC(String name, String frame, int x, int y, String Dialouge, boolean moving) {
		NO++;
		ID = NO;
		this.name = name;
		X = x;
		Y = y;
		XHolder = x;
		YHolder = y;
		this.dialouge = Dialouge.split("END");
		this.moving = moving;
		for(int i = 0; i < this.dialouge.length; i++) {
			this.dialouge[i] = name + ":" + this.dialouge[i];
		}

		this.dialougePerm = this.dialouge;
		sFrame = frame;
		if(frame != null) {
			//if(!frame.equals("")) {
			// Change all this code to add more frames (useful for later)
			// For every frame you add, add one example with code
			int[] framez = new int[2];
			//				int[] framez = new int[3];

			framez[0] = Integer.parseInt(frame.split(",")[0].trim()); // X coordinate on spritesheet
			framez[1] = Integer.parseInt(frame.split(",")[1].trim()); // Y coordinate on spritesheet
			// Not Applicable for changing to add more frames

			this.frames = new Bitmap[2];
			this.frames[0] = Art.getSpritesheet()[framez[0]][framez[1]]; // Get First Frame
			this.frames[1] = Art.getSpritesheet()[framez[0] + 1][framez[1]]; // Move over one to get next frame
			//				this.frames[2] = Art.spritesheet[framez[0]][framez[1] + 1]; // Move down one to get next frame
			//}
		}
	}

	public NPC(boolean random, String name, String frame, int x, int y, String Dialouge, boolean moving) {
		if(random) {
			this.name = name;
			X = x;
			Y = y;
			XHolder = x;
			YHolder = y;
			this.dialouge = Dialouge.split("END");
			this.moving = moving;
			for(int i = 0; i < this.dialouge.length; i++) {
				this.dialouge[i] = name + ":" + this.dialouge[i];
			}

			this.dialougePerm = this.dialouge;
			sFrame = frame;
			if(frame != null) {
				// Change all this code to add more frames (useful for later)
				// For every frame you add, add one example with code
				int[] framez = new int[2];
				//					int[] framez = new int[3];

				framez[0] = Integer.parseInt(frame.split(",")[0].trim()); // X coordinate on spritesheet
				framez[1] = Integer.parseInt(frame.split(",")[1].trim()); // Y coordinate on spritesheet
				// Not Applicable for changing to add more frames

				this.frames = new Bitmap[2];
				this.frames[0] = Art.getSpritesheet()[framez[0]][framez[1]]; // Get First Frame
				this.frames[1] = Art.getSpritesheet()[framez[0] + 1][framez[1]]; // Move over one to get next frame
				//					this.frames[2] = Art.spritesheet[framez[0]][framez[1] + 1]; // Move down one to get next frame
				//}
			}

		}
	}

//	public static NPC newInstance(String key) {
//		try {
//			return (NPC) npcs.get(key).clone();
//		} catch (Exception e) {
//			System.err.println("Unable to clone " + key);
//			return null;
//		}
//	}

	public boolean NPCCollision(int x, int y) {
		for(NPC n : NPC.npcs.values()) {
			if(n.X == x && n.Y == y) {
				return true;
			}
		}
		return false;
	}

	public boolean CanMove(int x, int y) {
		if(!moving) {
			Tile t = map.getTileAt(x, y);
			if(t == null)
				return false;
			if(t.isSolid())
				return false;
			if(NPCCollision(x, y))
				return false;
			else
				return true;
		} else {
			return false;
		}
	}

	/**
	 * if the x is equal to npc.X or y is equal to npc.Y return true, else return false
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean collision(int x, int y) {
		if(x == X && y == Y) {
			return true;
		} else {
			return false;
		}
	}

	public boolean inRange() {
		if(this.cutscene) {
			return true;
		} else {
			if(Player.X >= X - 8 && Player.X <= X + 8 && Player.Y >= Y - 6 && Player.Y <= Y + 6) {
				return true;
			} else {
				return false;
			}
		}
	}

	public void tick() {
		time++;
		if(time % (ran + 60) == 0) {
			ran = random.nextInt(300);
			int XorY = random.nextInt(2);

			if(X >= XHolder + 2 && !collision(Player.X - 1, Player.Y)) {
				if(CanMove(X - 1, Y))
					X += -1;
				canMove = false;
			} else if(X <= XHolder - 2 && !collision(Player.X + 1, Player.Y)) {
				if(CanMove(X + 1, Y))
					X += 1;
				canMove = false;
			}

			if(Y >= YHolder + 2 && !collision(Player.X, Player.Y + 1)) {
				if(CanMove(X, Y + 1))
					Y += -1;
				canMove = false;
			} else if(Y <= YHolder - 2 && !collision(Player.X, Player.Y - 1)) {
				if(CanMove(X, Y - 1))
					Y += 1;
				canMove = false;
			}

			int move = random.nextInt(3) - 1;

			if(XorY == 0) {
				if(X < XHolder + 2 && X > XHolder - 2 && canMove && !collision(Player.X - move, Player.Y)) {
					if(CanMove(X + move, Y)) {
						X += move;
					}
				}
			}
			if(XorY == 1) {
				if(Y < YHolder + 2 && Y > YHolder - 2 && canMove && !collision(Player.X, Player.Y - move)) {
					move = random.nextInt(3) - 1;
					if(CanMove(X, Y + move)) {
						Y += move;
					}
				}
			}
		}
		if(System.currentTimeMillis() - t > 400 && inRange()) {
			frame++;
			t = System.currentTimeMillis();
		}
		if(frame >= frames.length)
			frame = 0;
	}

	public void render(Screen screen) {
		screen.render(getFrames(frame), map.MapX_Pos + X * 32, map.MapY_Pos + Y * 32);
	}

	public void stopSpeaking() {
		this.speaking = false;
		Dialouge.setText(null, 0);
	}

	public void renderMinimap(Graphics g, int scale) {
		g.drawRect(this.X * scale, this.Y * scale, scale, scale);
		g.fillRect(this.X * scale, this.Y * scale, scale, scale);
		//}
	}

	public void setX(int i) {
		X += i;
	}

	public void setY(int j) {
		Y += j;
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	/**
	 * Will return an NPC with the same name
	 * @param name the NPC's name that it is looking for.
	 * @param NAME If this value is greater than 0, it will return an npc after it's name has been passed that many times
	 * @return
	 */
	public static NPC getNpc(String name, int NAME) {
		if(!(NAME <= 0)) {
			NAME = NAME - 1;
		} else {
			NAME = 0;
		}
		int names = 0;
		for(int i = 0; i < npcs.size(); i++) {
			if(NPC.getNpc(i).name.equals(name)) {
				if(NAME == names) {
					return NPC.getNpc(i);
				} else {
					names++;
				}
			}
		}
		return null;
	}

	public static NPC getNpc(int ID) {
		return npcs.get(ID);
	}

	/**
	 * Returns the NPC's ID
	 * @return NPC's ID
	 */
	public int getID() {
		return ID;
	}

}
