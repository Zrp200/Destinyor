package me.jacob.macdougall.player;

import me.jacob.macdougall.DebugWriter;
import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.graphics.GraphicsGetter;
import me.jacob.macdougall.items.Equipment;
import me.jacob.macdougall.items.Items;
import me.jacob.macdougall.magic.Element;
import me.jacob.macdougall.magic.Spells;
import me.jacob.macdougall.npcs.Enemy;
import graphic.engine.screen.Art;
import graphic.engine.screen.Bitmap;
import graphic.engine.screen.GameFont;
import graphic.engine.screen.Screen;
import graphic.engine.window.Resolution;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Player {
	
	// Static means that all instances of the class will have the same value. Only values relating to movement should be static as only one character moves.
        // The inventory should also be static as all players have access to it, same with gold.
	public static Map<Integer, Items> inventory = new HashMap<>();
	private static Map<Integer, Equipment> Einventory = new HashMap<>();
	public Map<String, Equipment> equipped = new HashMap<>();
        public Map<Integer, Spells> spells = new HashMap<>();
	public static Map<Integer, String> names = new HashMap<>();
	public static Map<String, Player> players = new HashMap<>();
        private Map<String, Integer> status = new HashMap<>();
	
	public static int[] levelup = {
	50, 100, 150, 200, 250, 300, 350, 400, 450, 500
	};
	
	public static Equipment fakeitem = new Equipment("null", 0, 0, Element.get(Element.Dark), "null", null);
        
        //public Item(String name, int damage, int cost, String attribute, Element element, String type, boolean equipped, String user) {
     
	public Random random = new Random();
	
	public int TA = 0;
	private boolean paused = true;
	
	public int Lvl;
	public int Exp;
	public int Hp;
	public int Str;
	public int Skl;
	public int Spd;
	public int Luk;
	public int Def;
    public int Wis;
    public int accuracy = 100;
	public static int Gold;
	public int chanceToMiss = 0;
	public int rand = 0;
	//
	//public int temps[] = new int[10];
	public static int temp = 0;
	public static String combatName = "";
	
	public String Name;
	public String Gender;
	public Element[] Resistance;
	
	public static boolean Attackable = false;
	
	public boolean attacked = false;;
	
        // Map X and Y
	public static int X = 6, Y = 7;
	
	public boolean canAttack = true;
        public boolean inParty = true;
        public boolean p1 = false, p2 = false, p3 = false, p4 = false;
	
	// Direction constants
	public static final int Direction_Up = 0, Direction_Down = 1, Direction_Left = 2, Direction_Right = 3;
	// Direction Controller
	public static int dir = Direction_Down;
	
	public static final Bitmap[][] frames = 
		{
			{ Art.getSpritesheet()[14][15], Art.getSpritesheet()[15][15] }, // Up
			{ Art.getSpritesheet()[14][12], Art.getSpritesheet()[15][12] }, // Down
			{ Art.getSpritesheet()[14][13], Art.getSpritesheet()[15][13] }, // Left
			{ Art.getSpritesheet()[14][14], Art.getSpritesheet()[15][14] } // Right
		};
        
        public static int AmountOFPlayers() {
            return players.size();
        }
        
	public Player(String name, String gender, int lvl, int exp, int hp, int str, int skl, int spd, int luk, int def, int wis, int gold, int players, Spells[] spells, Equipment[] items) {
		
		this.Name = name;
		this.Gender = gender;
		
                this.spells.put(0, Spells.Attack);
                
                if(spells != null) {
                    for(int s = 0; s < spells.length; s++)
                        this.spells.put(s + 1, spells[s]);
                }
                
                if(items != null) {
                    for(int i = 0; i < items.length; i++) {
                        //this.equipped.put(items[i].name, items[i]);
                        //this.equipped.get(items[i].name).equipped = true;
                    }
                }
                
                this.spells.put(1, Spells.get("Fire Ball"));
                
		Lvl = lvl;
		Exp = exp;
		Hp = hp;
		Str = str;
		Skl = skl;
		Spd = spd;
		Luk = luk;
		Def = def;
                Wis = wis;
		Gold = gold;
                
                switch(players) {
                    case 0: p1 = true;
                        inParty = true;
                        break;
                    case 1: p2 = true;
                        inParty = true;
                        break;
                    case 2: p3 = true;
                        inParty = true;
                        break;
                    case 3: p4 = true;
                        inParty = true;
                        break;
                }
		
		if(Lvl == 0) {
			// Assume all variables to be 0
			Lvl = 1;
			Exp = 0;
			Hp = 100;
			Str = 10;
			Skl = 10;
			Spd = 10;
			Luk = 10;
			Def = 10;
                        Wis = 10;
			Gold = 0;
		}
		
		put("stat.level", Lvl);
		put("stat.exp", Exp);
		put("stat.hp", Hp);
		put("stat.str", Str);
		put("stat.skl", Skl);
		put("stat.spd", Spd);
		put("stat.luk", Luk);
		put("stat.def", Def);
		put("stat.gold", Gold);
                names.put(Player.players.size(), name);
	}
        
	public static Player[] getPlayers() {
		Player[] player = new Player[4];
                for(int i = 0; i < players.size(); i++) {
                    if(Player.players.get(names.get(i)).inParty) {
			player[i] = Player.players.get(names.get(i));
                    }
		}
		return player;
	}
	
	public static Player newInstance(String key){
		throw new RuntimeException("Their should never be 2 of the same players");
	}
	
	public static void addPlayer(String key, Player player) {
		players.put(key, player);
	}
	
	public static Player getPlayer(String key) {
		return players.get(key);
	}
	
	private void put(String key, int value) {
		status.put(key, value);
		if(status.get(key) < 0) {
			status.put(key, 0);
		}
	}
	
	public int get(String key) {
		return status.get(key) == null ? 0 : status.get(key);
	}
	
	public Spells getSpells(int i) {
		return spells.get(i);
	}

	public void levelUp() {
            if(Exp >= 50 * Lvl) {
                Lvl += 1;
                Str += 10;
                Def += 10;
                Hp = get("stat.hp") + 50;
                put("stat.hp", Hp);
                DebugWriter.println("Leveling Up: " + Name);
            }
	}

	public void pause() {
		paused = true;
	}

	public void play() {
		if(!paused)
			TA++;
		paused = false;
	}
	
	public void render(Screen screen, int x, int y) {
		
		x = 512 / 2;
		y = (768 / 2) / 2;
                
		screen.render(frames[Move.dir][Move.frame], x, y);
		
		if(Destinyor.Override) {
			x -= 1;
			screen.render(Art.getFont()[30][1], x, y);
			x += 24;
			screen.render(Art.getFont()[31][1], x, y);
			x += 8;
			screen.render(Art.getFont()[32][1], x, y);
			x -= 24 + 8;
			y += 24;
			screen.render(Art.getFont()[30][2], x, y);
			x += 24;
			screen.render(Art.getFont()[31][2], x, y);
			x += 8;
			screen.render(Art.getFont()[32][2], x, y);
			
			x -= 16;
			screen.render(Art.getFont()[34][2], x, y);
			x -= 8;
			screen.render(Art.getFont()[34][2], x, y);
			
			x -= 12;
			y -= 8;
			screen.render(Art.getFont()[33][1], x, y);
			y -= 8;
			screen.render(Art.getFont()[33][2], x, y);
			
			x += 29;
			y += 8;
			screen.render(Art.getFont()[33][1], x, y);
			y -= 8;
			screen.render(Art.getFont()[33][2], x, y);
			
			x -= 9;
			y -= 13;
			screen.render(Art.getFont()[34][2], x, y);
			x -= 8;
			screen.render(Art.getFont()[34][2], x, y);
		}
	}

	public void renderUI(Screen screen, int co, int no) {
		// co = "character offset", no = "name offset";
		screen.render(frames[2][0], 448, 32 + co);
		GameFont.render(Name, screen, (448 - 18), ((32 * 10) - 18) + no);
		//GameFont.render(String.valueOf(TA / 5), screen, 446 + 32, ((32 * 10) - 18) + no);
		
	}
	
	public void renderTime(Graphics g, int no) {
		int x = GraphicsGetter.getAbsoluteX(472);
		int y = GraphicsGetter.getAbsoluteY((302 - 2) + (no * 12));
		int width = GraphicsGetter.getWidth(500 / 20);
		int width2 = GraphicsGetter.getWidth(this.TA / 20);
		int height = GraphicsGetter.getHeight(6);
//		int x = 472 * (Resolution.width() / 512);
//		int y = 302 * (Resolution.width() / 384) + (no * 20);
		//int width = (this.TA / 10);
		//int height = 8 * (Resolution.width() / 384);
		//GameFont.render(String.valueOf(TA / 5), screen, 446 + 32, ((32 * 10) - 18) + no);
		g.drawRect(x, y, width, height);
		g.fillRect(x, y, width2, height);
		//g.drawRect(446 + 32, ((32*10) - 18) + no, 446 + 32 + 8, ((32*10)-18) + no);
	}
	
	public void attack(Enemy enemy) {
		int damage = 0;
		if(!miss(enemy)) {
		if(!this.equipped.isEmpty()) {
			for(Equipment equip : equipped.values()) {
				if(equip.isWeapon()) {
					if(canDamage(enemy, equip)) {
						damage = (int) (random.nextInt((damage())) + equip.damage + (damage() * skillcheck(enemy)));
						enemy.HP -= damage;
						temp = damage;
						attacked = true;
						//enemy.HP -= damage(enemy, equip);
					}
				}
			}
			
		}
		if(canDamage(enemy)) {
			damage = (int) (random.nextInt((damage())) + (damage() * skillcheck(enemy)));
			enemy.HP -= damage;
			temp = damage;
		}
		temp = damage;
		//temp = -1;
		}
		Player.combatName = this.Name;
		Player.temp = damage;
		//temp = -1;
		attacked = false;
	}
	
	public void useSpell(int spell, Enemy enemy) {
		if(!miss(enemy)) {
			enemy.HP -= spells.get(spell).damage;
		}
	}
	
	public boolean canDamage(Enemy enemy, Equipment weapon) {
		if(enemy.Def <= Str + weapon.damage && !this.attacked) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean canDamage(Enemy enemy) {
		if(enemy.Def <= Str && !this.attacked) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the damage in int format
	 * @param enemy that the player is attackign
	 * @param weapon that will be calculated into the damage
	 * @return
	 */
	public int damage(Enemy enemy, Equipment weapon) {
		int damage = 0;
		damage = Str + weapon.damage;
		damage += damage * skillcheck(enemy);
		return damage;
	}
	
	public int damage() {
		int damage = 0;
		damage = Str;
		return damage;
	}
	// Spells will miss
	public boolean miss(Enemy enemy) {
		// ensures rand can't be negative, incase I add negative Luk modifers
		DebugWriter.removeln(Name + ": Chance to miss was 1 out of " + rand);
		rand = (int) (Luk + (Luk * skillcheck(enemy)) * 1);
		DebugWriter.println(Name + ": Chance to miss was 1 out of " + rand);
		chanceToMiss = random.nextInt(rand);
		if(chanceToMiss <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public int damage(Enemy enemy) {
		int damage = 0;
		damage = Str;
		damage += damage * skillcheck(enemy);
		return damage;
	}
	
	public int defense() {
		return 0;
	}
	
	/**
	 * calculates the amount of bonus damage.
	 * It is based on
	 * the enemies skill level and the current attacking players skill level.
	 * The damage is multiplied by enemy.skl % player.skl
	 * @param enemy
	 * @return
	 */
	public float skillcheck(Enemy enemy) {
		int crit = 0;
		if(this.Skl > enemy.Skl) {
			crit = enemy.Skl % this.Skl;
		}
		
		
		
		return crit;
	}
	
	public int critical(Enemy enemy) {
		int crit = 0;
		crit = (int) Math.ceil(((damage() * skillcheck(enemy)) * (2 * Skl / Luk)));
		
		return crit;
	}
        
        public Equipment Equipment(String key) {
            if(this.equipped.get(key) == null) {
                    return fakeitem;
            } else {
                return this.equipped.get(key);
            }
        }
//        
        public void Equip(Equipment equip) {
        	//Equipment equipment = equip.newEquipmentInstance();
        	this.equipped.put(equip.name, equip);
        	equip.equipped = true;
            //this.equipped.put(Equipment.equipment.get(key).name, Equipment.equipment.get(key));
        }
//        
//        public void Equip(String key) {
//        	this.equipped.put(Einventory.get(key).name, Einventory.get(key));
//        	Einventory.get(key).equipped = true;
//            //this.equipped.put(Equipment.equipment.get(key).name, Equipment.equipment.get(key));
//        }
        
//        public void Equip(Items equip) {
//        	//Equipment equipment = Equipment.get(equip.name);
//        	//this.equipped.put(equip.name, equipment);
//        	//equip.equipped = true;
//            //this.equipped.put(Equipment.equipment.get(key).name, Equipment.equipment.get(key));
//        }
        
        public static void putItInTheBag(Items item) {
        	System.out.println(inventory.size());
            inventory.put(inventory.size(), item);
        }
        
//        public static void putItInTheBag(Equipment item) {
//            Einventory.put(Einventory.size() - 1, item);
//        }
        
        public static Items[] Inventory() {
        	Items[] inv = new Items[inventory.size() + Einventory.size()];
        	for(int i = 0; i < inventory.size() + Einventory.size(); i++) {
        		if(i < inventory.size()) {
        			inv[i] = inventory.get(i);
        		}
        		if(i >= inventory.size()) {
        			inv[i] = Einventory.get(i - inventory.size());
        		}
        	}
        	return inv;
        }

}
