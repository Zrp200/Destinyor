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
import me.jacob.macdougall.player.Player;

public class Enemy extends Dummy implements Cloneable {
	
//	public int LVL;
//	public int Exp;
//	public int Gold;
//	public int HP;
//	public int Str;
//	public int Wis;
//	public int Skl;
//	public int Spd;
//	public int Luk;
//	public int Def;
//	public int Vit;
	
	public int limit;
	
	public Element Resistance;
	public String Condition;
	
	public boolean Focused = false;
	public boolean attacking = false;
	
	public static Map<String, Enemy> enemies = new HashMap<>();
	public static Map<Integer, String> names = new HashMap<>();
        
	public static boolean attackable = false;
	public static boolean battle = false;
	
	public int X = 0, Y = 0, X1 = 0, Y1 = 0;
	public int w, h;
	protected static int frameInterval = 250, fr = 0;
	
	protected long frameTimer = System.currentTimeMillis();
	
	//public String name;
	
	protected Bitmap[] frames;
	
	protected boolean removed = false;
	
	public int TA = 0;
	
	private Items[] item;
	
	public boolean paused = true;
	
	public int damage = 0;
	
	private static Random randomEnemy = new Random();
	
	// For later, involing setting commands before TA and having them excuted after the TA
	public boolean[] targeted = {
			// p1,    p2,    p3,    p4
			false, false, false, false
	};
	
	/**
	 * 
	 * @param name
	 * @param frame
	 * @param lvl
	 * @param exp
	 * @param hp
	 * @param str
	 * @param skl
	 * @param spd
	 * @param luk
	 * @param def
	 * @param wis
	 * @param gold
	 * @param Resistance
	 * @param spells
	 * @param pos
	 * @param spawn
	 */
	public Enemy(String name, String frame, int lvl, int exp, int hp, int str, int skl, int spd, int luk, int def, int wis, int gold, Element Resistance, Spells[] spells, int pos, int spawn){
		super(name, null, lvl, exp, hp, str, skl, spd, luk, def, wis, gold, 0, 0, spells, null);
		names.put(names.size(), name);
		this.spells.put(0, Spells.Attack);
                if(spells != null)
                    for(int s = 0; s < spells.length; s++)
                        this.spells.put(s + 1, spells[s]);
		
		this.X = pos;
		this.Y = pos;
		this.X1 = spawn;
		this.Y1 = spawn;
		
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
	
//	public void SetPos(int x, int y) {
//		this.X = x;
//		this.Y = y;
//	}
	
	public void render(Screen screen){
		screen.render(frames[fr], x, y);
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
	
	public int attack(Player player) {
		if(damage == 0) {
		Random random = new Random();
		if(str - player.getDef() <= 0) {
			
			damage = random.nextInt(2);
			//players[eTargets].Hp -= random;
			//tempEDamageHolder = random;
			//Battles.
			//random = randomGen.nextInt(2);
			TA = 0;
			return damage;
		} else {
			damage = random.nextInt((str - player.getDef()));
			//players[eTargets].Hp -= (Damage - players[eTargets].Def);
			TA = 0;
		}
		}
		return damage;
	}
	
	public static Enemy getRandomEntity() {
		
		int rand = randomEnemy.nextInt(getEntities().length);
		//System.out.println(rand);
		//random = null;
		return Enemy.newInstance(names.get(rand));
		//return enemies.get(names.get(rand));
	}
	
//	public Equipment weapons() {
//		return null;
//	};
//	
//	public Equipment armour() {
//		return null;
//	};
	
	
}
