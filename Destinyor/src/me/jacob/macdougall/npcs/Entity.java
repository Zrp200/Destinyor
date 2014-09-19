package me.jacob.macdougall.npcs;

import graphic.engine.screen.Art;
import graphic.engine.screen.Bitmap;
import graphic.engine.screen.Screen;

import java.util.HashMap;
import java.util.Map;

import me.jacob.macdougall.magic.Element;
import me.jacob.macdougall.magic.Spells;

public class Entity implements Cloneable {
	
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
	
	public int spawnX;
	public int spawnY;
	
	public Element Resistance;
	public String Condition;
	public Entities type;
	
	public boolean Focused = false;
	public boolean attacking = false;
	
	public String Gender;
	
	public static Map<String, Entity> entities = new HashMap<>();
	public Map<String, Spells> spells = new HashMap<>();
        
	public static boolean attackable = false;
	public static boolean battle = false;
	public boolean dead = false;
	public boolean appiedExp = false;
	
	public int X = 0, Y = 0, X1 = 0, Y1 = 0;
	public int w = 16, h = 16;
	protected int w1 = 32, h1 = 32;
	protected int w2 = 64, h2 = 64;
	protected int w3 = 72, h3 = 72;
	protected static int frameInterval = 250, fr = 0;
	
	protected long frameTimer = System.currentTimeMillis();
	
	protected String name;
	
	protected Bitmap[] frames;
	
	protected boolean removed = false;
	
	public int TA = 0;
	
	public boolean paused = true;
	
	public Entity(String name, String frame, String gender, int LVL, int EXP, int HP, int STR, int SKL, int SPD, int LUK, int DEF, int GOLD, Element Resistance, String Condition, int pos, int spawn){
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
		this.Condition = Condition;
		
		this.X = pos;
		this.Y = pos;
		this.X1 = spawn;
		this.Y1 = spawn;
		
		spells.put("Attack", new Spells("Attack", Element.get(Element.Physical), Str * 2, 1, 0, name));
		
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
	
	public static Entity[] getEntities() {
		Entity[] entity = new Entity[entities.size()];
		for(Entity e : entities.values()) {
			for(int i = 0; i < entities.size(); i++) {
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
	
	public static Entity newInstance(String key){
		try {
			return (Entity) entities.get(key).clone();
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
	
	public void SETXY(int x, int y){
		this.X = x;
		this.Y = y;
	}
	
	public int getLVL() {
		return LVL;
	}

	public void setLVL(int lVL) {
		LVL = lVL;
	}

	public int getExp() {
		return Exp;
	}

	public void setExp(int exp) {
		Exp = exp;
	}

	public int getGold() {
		return Gold;
	}

	public void setGold(int gold) {
		Gold = gold;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getStr() {
		return Str;
	}

	public void setStr(int str) {
		Str = str;
	}

	public int getWis() {
		return Wis;
	}

	public void setWis(int wis) {
		Wis = wis;
	}

	public int getSkl() {
		return Skl;
	}

	public void setSkl(int skl) {
		Skl = skl;
	}

	public int getSpd() {
		return Spd;
	}

	public void setSpd(int spd) {
		Spd = spd;
	}

	public int getLuk() {
		return Luk;
	}

	public void setLuk(int luk) {
		Luk = luk;
	}

	public int getDef() {
		return Def;
	}

	public void setDef(int def) {
		Def = def;
	}

	public int getVit() {
		return Vit;
	}

	public void setVit(int vit) {
		Vit = vit;
	}
        
        public Element getResistance() {
            return Resistance;
        }

	public String getCondition() {
		return Condition;
	}

	public void setCondition(String condition) {
		Condition = condition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getHp() {
		return HP;
	}

	public void setHp(int hp) {
		this.HP = hp;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		this.X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		this.Y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}
	
	public int getW1() {
		return w1;
	}

	public void setW1(int w1) {
		this.w1 = w1;
	}

	public int getH1() {
		return h1;
	}

	public void setH1(int h1) {
		this.h1 = h1;
	}

	public int getW2() {
		return w2;
	}

	public void setW2(int w2) {
		this.w2 = w2;
	}

	public int getH2() {
		return h2;
	}

	public void setH2(int h2) {
		this.h2 = h2;
	}

	public int getW3() {
		return w3;
	}

	public void setW3(int w3) {
		this.w3 = w3;
	}

	public int getH3() {
		return h3;
	}

	public void setH3(int h3) {
		this.h3 = h3;
	}
}
