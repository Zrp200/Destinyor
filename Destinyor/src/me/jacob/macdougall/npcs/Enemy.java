package me.jacob.macdougall.npcs;

import graphic.engine.screen.Art;
import graphic.engine.screen.Bitmap;
import graphic.engine.screen.Screen;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import me.jacob.macdougall.items.Equipment;
import me.jacob.macdougall.items.Items;
import me.jacob.macdougall.magic.Element;
import me.jacob.macdougall.magic.Spells;

public class Enemy implements Cloneable {
	
	public int LVL;
	public int Exp;
	public int Gold;
	public int HP;
	public int Str;
	public int Wis;
	public int Skl;
	public int Spd;
	public int Luk;
	public int Def;
	public int Vit;
	
	public int limit;
	
	public Element Resistance;
	public String Condition;
	
	public boolean Focused = false;
	public boolean attacking = false;
	
	public String Gender; // Old code from when this class used to be for players and npcs as well. To lazy to change now. May be useful for sound effects.
	
	public static Map<String, Enemy> enemies = new HashMap<>();
	public Map<Integer, Spells> spells = new HashMap<>();
	public Map<Integer, Spells> equipment = new HashMap<>();
        
	public static boolean attackable = false;
	public static boolean battle = false;
	public boolean dead = false;
	
	public int X = 0, Y = 0, X1 = 0, Y1 = 0;
	public int w, h;
	protected static int frameInterval = 250, fr = 0;
	
	protected long frameTimer = System.currentTimeMillis();
	
	protected String name;
	
	protected Bitmap[] frames;
	
	protected boolean removed = false;
	
	public int TA = 0;
	
	private Items[] item;
	
	public boolean paused = true;
	
	public Enemy(String name, String frame, String gender, int LVL, int EXP, int HP, int STR, int SKL, int SPD, int LUK, int DEF, int GOLD, Element Resistance, Spells[] spells, int pos, int spawn){
		this.name = name;
		this.Gender = gender;
		this.LVL = LVL;
		this.Exp = EXP;
		this.HP = HP;
		this.Str = STR;
		this.Skl = SKL;
		this.Spd = SPD;
		this.Luk = LUK;
		this.Def = DEF;
		this.Gold = GOLD;
		this.spells.put(0, Spells.Attack);
                if(spells != null)
                    for(int s = 0; s < spells.length; s++)
                        this.spells.put(s + 1, spells[s]);
		
		this.X = pos;
		this.Y = pos;
		this.X1 = spawn;
		this.Y1 = spawn;
                
		//this.spells.put("Attack", new Spells("Attack", Element.get(Element.Physical), Str * 2, 1, 0, name));
		
		if(frame != null){
			if(!frame.equals("")) {
				// Change all this code to add more frames (useful for later)
				// For every frame you add, add one example with code
				int[] framez = new int[2];
//				int[] framez = new int[3];
				
				framez[0] = Integer.parseInt(frame.split(",")[0]); // X coordinate on spritesheet
				framez[1] = Integer.parseInt(frame.split(",")[1]); // Y coordinate on spritesheet
				// Not Applicable for changing to add more frames
				
				this.frames = new Bitmap[2];
				this.frames[0] = Art.getSpritesheet()[framez[0]][framez[1]]; // Get First Frame
				this.frames[1] = Art.getSpritesheet()[framez[0] + 1][framez[1]]; // Move over one to get next frame
//				this.frames[2] = Art.spritesheet[framez[0]][framez[1] + 1]; // Move down one to get next frame
			}
		}	
		
		//Spells.spells.put(name, new Spells(name, type, damage, targets, cost));
	}
	
	public static Enemy[] getEntities() {
		Enemy[] entity = new Enemy[enemies.size()];
		for(Enemy e : enemies.values()) {
			for(int i = 0; i < enemies.size(); i++) {
				entity[i] = e;
			}
		}
		return entity;
	}
	
	public void play() {
		if(!paused)
			TA += 1;
		paused = false;
	}
	
	public void pause() {
		paused = true;
	}
	
	public static Enemy newInstance(String key){
		try {
			return (Enemy) enemies.get(key).clone();
		} catch (CloneNotSupportedException e) {
			System.err.println("Unable to clone " + key);
			return null;
		}
	}
	
	public void SetPos(int x, int y) {
		this.X = x;
		this.Y = y;
	}
	
	public void render(Screen screen){
		screen.render(frames[fr], X, Y);
	}
	
	public void tick(){
		if(System.currentTimeMillis() - frameTimer > 2 * frameInterval) {
			frameTimer = System.currentTimeMillis();
		}
		if(System.currentTimeMillis() - frameTimer > frameInterval && frames != null){
			if(fr >= frames.length - 1){
				fr = 0;
			} else {
				fr++;
			}
			
			frameTimer += frameInterval;
		}
	}
        
	public String getName() {
		return name;
	}
	
	public Items death() {
		Items items = null;
		if(item != null) {
		Random random = new Random();
		int rand = random.nextInt(item.length);
		items = item[rand];
		}
		return items;
	}
	
	public Equipment weapons() {
		return null;
	};
	
	public Equipment armour() {
		return null;
	};
}
