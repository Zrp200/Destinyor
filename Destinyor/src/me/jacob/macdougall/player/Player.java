package me.jacob.macdougall.player;

import me.jacob.macdougall.DebugWriter;
import me.jacob.macdougall.Time;
import me.jacob.macdougall.enemies.Dummy;
import me.jacob.macdougall.enemies.Enemy;
import me.jacob.macdougall.graphics.GraphicsGetter;
import me.jacob.macdougall.items.Equipment;
import me.jacob.macdougall.items.Items;
import me.jacob.macdougall.magic.Element;
import me.jacob.macdougall.magic.Spells;
import me.jacob.macdougall.quests.Quest;
import graphic.engine.screen.Bitmap;
import graphic.engine.screen.GameFont;
import graphic.engine.screen.Screen;
import me.jacob.macdougall.battles.PlayerBattle;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Player extends Dummy {

	// Static means that all instances of the class will have the same value. Only values relating to movement should be static as only one character moves.
	public static Map<String, Player> players = new HashMap<>();
	public static Map<Integer, String> names = new HashMap<>();
	
	// The inventory should also be static as all players have access to it, same with gold.
	public static Map<Integer, Items> inventory = new HashMap<>();
	
	public Map<Integer, Spells> spells = new HashMap<>();
	
	private Map<String, Integer> status = new HashMap<>();

	public static int[] levelup = { 50, 100, 150, 200, 250, 300, 350, 400, 450, 500 };

	public Quest quest;
	
	public Random random = new Random();
	
	/**
	 * This hp is for the save files, because it won't be modified by easy difficultly and won't get taken down during battles
	 */
	public int HP; // This hp is for save files, because it won't be modified by easy difficultly, also used for leveling up

	public int accuracy = 100;
	public int chanceToMiss = 0;
	public int rand = 0;

	private int threat = 100;

	public static int temp = -1;
	public static String combatName = "";
	
	public Element[] Resistance;

	public static boolean Attackable = false;

	public boolean attacked = false;;

	// Map X and Y and Level
	public static int X = 6, Y = 7;
	public static int Level = 1;

	public boolean inParty = false;
	public boolean p1 = false, p2 = false, p3 = false, p4 = false;

	// Direction constants
	public static final int Direction_Up = 0, Direction_Down = 1,
			Direction_Left = 2, Direction_Right = 3;
	// Direction Controller
	public static int dir = Direction_Down;

	private Enemy target = null;

	public static final Bitmap[][] frames = SpriteChecker.frames;


	public static int AmountOFPlayers() {
		return players.size();
	}

	public Player(String name, String gender, int lvl, int exp, int hp, int str, int skl, int spd, int luk, int def, int wis, int gold, boolean inParty, Spells[] spells, Equipment[] items) {
		super(name, gender, lvl, exp, hp, str, skl, spd, luk, def, wis, gold, X, Y, spells, items);
		
		HP = hp;

		this.spells.put(0, Spells.Attack);

		if(spells != null) {
			for(int s = 0; s < spells.length; s++)
				this.spells.put(s + 1, spells[s]);
		}

		if(items != null) {
			for(int i = 0; i < items.length; i++) {
			}
		}

		this.spells.put(1, Spells.get("Fire Ball"));

		this.inParty = inParty;

		put("stat.level", getLvl());
		put("stat.exp", getExp());
		put("stat.hp", getHp());
		put("stat.str", getStr());
		put("stat.skl", getSkl());
		put("stat.spd", getSpd());
		put("stat.luk", getLuk());
		put("stat.def", getDef());
		put("stat.gold", getGold());
		names.put(Player.players.size(), name);
	}

	public static Player[] getPlayers() {
		int p = 0;
		for(Player player : players.values()) {
			if(player.inParty) {
				p++;
			}
			if(p > 4) {
				p = 4;
			}
		}
		Player[] player = new Player[4];
		for(int i = 0; i < players.size(); i++) {
			if(Player.players.get(names.get(i)).inParty) {
				player[i] = Player.players.get(names.get(i));
			}
		}
		return player;
	}

	public static Player[] getActualPlayers() {
		Player[] player = Player.getPlayers();
		Player[] players;
		int size = 0;
		for(int i = 0; i < player.length; i++) {
			if(player[i] == null) {
				size++;
			}
		}

		players = new Player[player.length - size];
		for(int i = 0; i < players.length; i++) {
			players[i] = player[i];
		}
		return players;
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
		//            if(Exp >= 50 * Lvl) {
		//                Lvl += 1;
		//                Str += 10;
		//                Def += 10;
		//                Hp = get("stat.hp") + 50;
		//                put("stat.hp", Hp);
		//                DebugWriter.println("Leveling Up: " + Name);
		//            }
	}

	public void render(Screen screen) {
		int x = 0, y = 0;
		x = (int) (Screen.getWidth() / 2);
		y = (int) (Screen.getHeight() / 2);

		if(frames[Move.dir].length <= Move.frame) {
			Move.frame = 0;
		}

		screen.render(frames[Move.dir][Move.frame], x, y);

	}

	public void renderUI(Screen screen, int co, int no) {
		// co = "character offset", no = "name offset";
		if(Move.frame >= frames[Direction_Down].length) {
			Move.frame = 0;
		}
		screen.render(frames[Direction_Down][Move.frame], 448, 32 + co);
		if(Time.frameTimer(10)) {
			Move.frame++;
		}
		GameFont.render(getName(), screen, (448 - 18), ((32 * 10) - 18) + no);

	}

	public void renderTime(Graphics g, int no) {
		int x = GraphicsGetter.getAbsoluteX(472);
		int y = GraphicsGetter.getAbsoluteY((302 - 2) + (no * 12));
		int width = GraphicsGetter.getWidth(turnTimer / 20);
		int width2 = GraphicsGetter.getWidth(turnTimer / 20);
		int height = GraphicsGetter.getHeight(6);
		g.drawRect(x, y, width, height);
		g.fillRect(x, y, width2, height);
	}

	public void attack(Enemy enemy) {
		int damage = 0;
		if(!miss(enemy)) {
			damage = random.nextInt(PlayerBattle.pAttack(this, enemy));
			if(damage < 1) {
				damage = 1;
			}
			enemy.setHp(enemy.getHp() - damage);
		}
		Player.combatName = this.getName();
		Player.temp = damage;
		turnTimer = 0;
		attacked = false;
	}

	public void useSpell(int spell, Enemy enemy) {
		if(!miss(enemy)) {
			enemy.setHp(-spells.get(spell).damage);
		}
	}
	
	// Spells will miss
	public boolean miss(Enemy enemy) {
		// ensures rand can't be negative, incase I add negative Luk modifers
		DebugWriter.removeln(getName() + ": Chance to miss was 1 out of " + rand);
		rand = (int) (getLuk() + (getLuk() * skillcheck(enemy)) * 1);
		DebugWriter.println(getName() + ": Chance to miss was 1 out of " + rand);
		chanceToMiss = random.nextInt(rand);
		if(chanceToMiss <= 0) {
			return true;
		} else {
			return false;
		}
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
		if(this.getSkl() > enemy.getSkl()) {
			crit = enemy.getSkl() % this.getSkl();
		}
		return crit;
	}

	public Equipment Equipment(int key) {
		if(this.equipped.get(key) == null) {
			return null;
		} else {
			return this.equipped.get(key);
		}
	}

	public void Equip(Equipment equip) {
		equipped.put(equipped.size(), equip);
		equip.equipped = true;
	}

	public static void putItInTheBag(Items item) {
		inventory.put(inventory.size(), item);
	}

	/**
	 * Returns a player with max TA
	 * @return
	 */
	public static Player getPlayerAttack() {
		Player[] players = Player.getActualPlayers();
		Player player;
		// Make sure it's in chronological order
		for(int i = 0; i < players.length; i++) {
			if(players[i].turnTimer >= players[i].limit) {
				player = players[i];
				return player;
			}
		}
		return null;
	}

	/**
	 * Retuns a the first player who can attack or who will be the first to attack
	 * @return
	 */
	public static Player getNextAttackPlayer() {
		if(getPlayerAttack() != null) {
			return getPlayerAttack();
		} else {
			Player player = null;

			if(getActualPlayers() != null) {
				player = getActualPlayers()[0];
			}

			for(int i = 0; i < getActualPlayers().length; i++) {
				if(player.getTurnTimer() > getActualPlayers()[i].getTurnTimer()) {
					player = getActualPlayers()[i];
					i = 0;
				}
			}
			return player;
		}
	}

	public int getThreat() {
		return (int) (threat + getStr() + getWis() + getSkl() - (getHp() * 0.10f));
	}

	public void setThreat(int threat) {
		this.threat = threat;
	}

	public void setTarget(Enemy enemy) {
		target = enemy;
	}

	public Enemy getTarget() {
		return target;
	}

}
