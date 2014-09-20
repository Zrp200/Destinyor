package me.jacob.macdougall.npcs;

import input.engine.keyboard.InputHandler;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.Keys;
import me.jacob.macdougall.cutscenes.Cutscene;
import me.jacob.macdougall.minimap.Minimap;
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
	private InputHandler input;
	
	public String[] dialouge;
	public String[] dialougePerm;
        public String sDialouge;
        
        
        public static String[][] names = {
            {"Bob", "Jim", "Scott", "Kevin", "Oscar", "Stanely", "Mark", "Dylan", "Ray", "Rei", "Ryan", "Sam", "Nick", "Steve", "Stanely",
                "Gary", "Neal", "Micheal", "Leo", "Triston"},
            {"Pam", "Angela", "Molly", "Amy", "Kelly", "Alessa", "Sam", "Sue", "Joyce", "Elizibeth"},
            {"Michael", "Geoff", "Jack", "Gavin", "Lewis", "Simon"}
        };
        
	public static Map<String, NPC> npcs = new HashMap<>();
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
	
	public void init(LevelMap lvlmap, InputHandler input) {
		if(lvlmap != null)
			this.map = lvlmap;
		if(input != null)
			this.input = input;
	}
	
	public static void RandomNpcs(int name, int gender, int x, int y, LevelMap map) {
		boolean made = false;
            if(map != null && map.getTileAt(x, y) != null && !map.getTileAt(x, y).isSolid() && npcs.size() < (512 * 512) / 32 && x != Player.X && y != Player.Y) {
            	for(NPC n : NPC.npcs.values()) {
            		if(n.name.equals(names[gender][name])) {
            			made = true;
            			break;
            		}
            	}
            if(!made)
           NPC.npcs.put(names[gender][name], new NPC(names[gender][name], "0,12", x, y, x + ":" + y, false));
            }
	}
        
	public void Speaking() {
		if(System.currentTimeMillis() >= t1 + 100 && !Cutscene.playing) {
			this.setDialougeWithCharacter();
			t1 = System.currentTimeMillis();
			if(Keys.Enter() && this.X == Player.X + Move.GetXDir() && this.Y == Player.Y + Move.GetYDir()) {
				Destinyor.Refresh = true;
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
		this.name = name;
		X = x;
		Y = y;
		XHolder = x;
		YHolder = y;
		//if(Dialouge.contains("p1.name"))
                sDialouge = Dialouge;
		this.dialouge = Dialouge.split("END");
                this.moving = moving;
                for(int i = 0; i < this.dialouge.length; i++) {
                    this.dialouge[i] = name + ":" + this.dialouge[i];
                }
                
                this.dialougePerm = this.dialouge;
        //System.out.println(this.name + ": " + this.toString());
		sFrame = frame;
		if(frame != null){
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
	
	public static NPC newInstance(String key){
		try {
			return (NPC) npcs.get(key).clone();
		} catch (Exception e) {
			System.err.println("Unable to clone " + key);
			return null;
		}
	}
	
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
			if(t == null) return false;
			if(t.isSolid()) return false;
			if(NPCCollision(x, y)) return false;
			else return true;
        } else {
        	return false;
        }
	}
	
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
	
	// Set events in text such as adding party members and entering combat
	public void setDialougeWithCharacter() {
		if(Player.getPlayers()[0] != null) {
			for(int i = 0; i < dialouge.length; i++) {
				if(dialouge[i].contains("p1.name")) {
					dialouge[i] = dialougePerm[i].replace("p1.name", Player.getPlayers()[0].Name);
				}
				if(dialouge[i].contains("p1.gender")) {
					// if there are no actual players this will throw an exception and crash the game, which is kinda the point
					String gender = "";
					try {
					gender = (Player.getActualPlayers()[0].Gender.equalsIgnoreCase("Male") ? "boy" : "girl");
					} catch(NullPointerException e) {
						e.printStackTrace();
						System.exit(-1);
					}
					dialouge[i] = dialougePerm[i].replace("p1.gender", gender);
				}
			}
		} else {
			for(int i = 0; i < dialouge.length; i++) {
				if(dialouge[i].contains("p1.name")) {
					dialouge[i] = dialougePerm[i].replace("p1.name", "Player1 not Initialized what is going on!");
				}
				if(dialouge[i].contains("p1.gender")) {
					//String gender = (Player.getActualPlayers()[0].Gender.equalsIgnoreCase("Male") ? "Boy" : "Girl");
					dialouge[i] = dialougePerm[i].replace("p1.gender", "you");
				}
			}
			
		}
			
		if(Player.getPlayers()[1] != null) {
			for(int i = 0; i < dialouge.length; i++) {
				if(dialouge[i].contains("p2.name")) {
					dialouge[i] = dialougePerm[i].replace("p2.name", Player.getPlayers()[1].Name);
				}
				if(dialouge[i].contains("p2.gender")) {
						if(Player.getActualPlayers() != null) {
					// if there are no actual players this will throw an exception and crash the game, which is kinda the point
						String gender = "";
						gender = (Player.getActualPlayers()[1].Gender.equalsIgnoreCase("Male") ? "boy" : "girl");
						dialouge[i] = dialougePerm[i].replace("p2.gender", gender);
					} else {
						dialouge[i] = dialougePerm[i].replace("p2.gender", "you");
					}
				}
			}
		} else {
			for(int i = 0; i < dialouge.length; i++) {
				if(dialouge[i].contains("p3.name")) {
					dialouge[i] = dialougePerm[i].replace("p2.name", "Hero");
				}
				if(dialouge[i].contains("p2.gender")) {
					//String gender = (Player.getActualPlayers()[0].Gender.equalsIgnoreCase("Male") ? "Boy" : "Girl");
					dialouge[i] = dialougePerm[i].replace("p2.gender", "you");
				}
			}
		}
		
		if(Player.getPlayers()[2] != null) {
			for(int i = 0; i < dialouge.length; i++) {
				if(dialouge[i].contains("p3.name")) {
					dialouge[i] = dialougePerm[i].replace("p3.name", Player.getPlayers()[2].Name);
				}
				if(dialouge[i].contains("p3.gender")) {
					if(Player.getActualPlayers() != null) {
				// if there are no actual players this will throw an exception and crash the game, which is kinda the point
						String gender = "";
						gender = (Player.getActualPlayers()[2].Gender.equalsIgnoreCase("Male") ? "boy" : "girl");
						dialouge[i] = dialougePerm[i].replace("p3.gender", gender);
					} else {
						dialouge[i] = dialougePerm[i].replace("p3.gender", "you");
					}
				}
			}
		} else {
			for(int i = 0; i < dialouge.length; i++) {
				if(dialouge[i].contains("p3.name")) {
					dialouge[i] = dialougePerm[i].replace("p3.name", "Hero");
				}
				if(dialouge[i].contains("p3.gender")) {
					//String gender = (Player.getActualPlayers()[0].Gender.equalsIgnoreCase("Male") ? "Boy" : "Girl");
					dialouge[i] = dialougePerm[i].replace("p3.gender", "you");
				}
			}
		}
		
		if(Player.getPlayers()[3] != null) {
			for(int i = 0; i < dialouge.length; i++) {
				if(dialouge[i].contains("p4.name")) {
					dialouge[i] = dialougePerm[i].replace("p4.name", Player.getPlayers()[3].Name);
				}
				if(dialouge[i].contains("p4.gender")) {
					if(Player.getActualPlayers() != null) {
				// if there are no actual players this will throw an exception and crash the game, which is kinda the point
						String gender = "";
						gender = (Player.getActualPlayers()[3].Gender.equalsIgnoreCase("Male") ? "boy" : "girl");
						dialouge[i] = dialougePerm[i].replace("p4.gender", gender);
					} else {
						dialouge[i] = dialougePerm[i].replace("p4.gender", "you");
					}
				}
			}
		} else {
			for(int i = 0; i < dialouge.length; i++) {
				if(dialouge[i].contains("p4.name")) {
					dialouge[i] = dialougePerm[i].replace("p4.name", "Hero");
				}
				if(dialouge[i].contains("p4.gender")) {
					//String gender = (Player.getActualPlayers()[0].Gender.equalsIgnoreCase("Male") ? "Boy" : "Girl");
					dialouge[i] = dialougePerm[i].replace("p4.gender", "you");
				}
			}
		}
	}
	
//	public boolean inMinimapRange() {
//		if(Player.X >= X - (62 * Minimap.getScale) && Player.X <= X + (62 * Minimap.getScale) && Player.Y >= Y - (45 * Minimap.getScale) && Player.Y <= Y + (45 * Minimap.getScale)) {
//			return true;
//		} else {
//			return false;
//		}
//	}
	
	
	
	public void tick() {
		time++;
		if(time % (ran + 60) == 0) {
			ran = random.nextInt(300);
			int XorY = random.nextInt(2);
			
			if(X >= XHolder + 2 && !collision(Player.X - 1, Player.Y)) {
				if(CanMove(X - 1, Y))
				X += -1; canMove = false; 
			} else if(X <= XHolder - 2 && !collision(Player.X + 1, Player.Y)) {
				if(CanMove(X + 1, Y))
				X += 1; canMove = false;
			}
			
			if(Y >= YHolder + 2 && !collision(Player.X, Player.Y + 1)) {
				if(CanMove(X, Y + 1))
				Y += -1; canMove = false; 
			} else if(Y <= YHolder - 2 && !collision(Player.X, Player.Y - 1)) {
				if(CanMove(X, Y - 1))
				Y += 1; canMove = false;
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
                if(frame >= frames.length) frame = 0;
	}
	
	public void render(Screen screen) {
			//Tile.tiles[map.tiles[X][Y]].render(screen, map.MapX_Pos + X * LevelMap.SIZE, map.MapY_Pos + Y * LevelMap.SIZE, this, null, 0);
		//screen.render(bitmap, x, y)
		screen.render(getFrames(frame), map.MapX_Pos + X * 32, map.MapY_Pos + Y * 32);
	}
	
	public void stopSpeaking() {
        this.speaking = false;
        Dialouge.setText(null, 0);
    }
	
	public void renderMinimap(Graphics g, int scale) {
		//if(this.inMinimapRange()) {
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
        
        
	
	
}
