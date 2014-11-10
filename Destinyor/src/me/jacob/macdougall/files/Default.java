package me.jacob.macdougall.files;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.jacob.macdougall.magic.Element;

public class Default {

	// This is a class to load up the default enemies in to the FileWriter
	private static final List<String[]> entities = new ArrayList<>();
	private static final List<String[]> enemies = new ArrayList<>();
	private static final List<String[]> spells = new ArrayList<>();
	private static final List<String[]> npcs = new ArrayList<>();
	private static final Map<String, String[]> npcDialouge = new HashMap<>();
	private static final List<String[]> items = new ArrayList<>();
	
	private static final String[] enemiesFormat = {
		"Name = ", "Frame = ", "Level = ",
		"Experience = ", "Health = ", "Strength = ",
		"Skill = ", "Speed = ", "Luck = ", "Defense = ",
		"Wisdom = ", "Gold = ", "Resistance = ", "Condition = ",
		"X&Y = "
	};
	
	private static final String[] itemsFormat = {
		"Name = ", "Damage = ", "Price = ", "Element = ", "Limb = ", "Spell = "
	};
	
	public static void setEntities(String[] stats) {
		entities.add(stats);
	}
	
	public static String[][] getEntities() {
		String[][] stats = new String[entities.size()][];
		for(int i = 0; i < entities.size(); i++) {
			stats[i] = entities.get(i);
		}
		
		return stats;
	}
	
	public static String[] getEntitiesFormat() {
		return null;
	}
	
	public static void setEnemy(String[] stats) {
		enemies.add(stats);
	}
	
	public static String[][] getEnemies() {
		String[][] stats = new String[enemies.size()][];
		for(int i = 0; i < enemies.size(); i++) {
			stats[i] = enemies.get(i);
		}
		
		return stats;
	}
	
	public static String[] getEnemiesFormat() {
		return enemiesFormat;
	}
	
	public static void setSpell(String[] stats) {
		spells.add(stats);
	}
	
	public static List<String[]> getSpell() {
		return spells;
	}
	
	public static void setNpcs(String[] stats) {
		npcs.add(stats);
	}
	
	public static List<String[]> getNpcs() {
		return npcs;
	}
	
	public static int getNpcLength() {
		return npcs.size();
	}
	
	public static void setDialouges(String key, String[] dialouge) {
		npcDialouge.put(key, dialouge);
	}

	public static String[] getDialouges(String key) {
		return npcDialouge.get(key);
	}

	public static int getDialougesLength() {
		return npcDialouge.size();
	}
	
	public static void setItems(String[] stats) {
		items.add(stats);
	}

	public static String[][] getItems() {
		String[][] stats = new String[items.size()][];
		for(int i = 0; i < items.size(); i++) {
			stats[i] = items.get(i);
		}
		
		return stats;
	}
	
	public static String[] getItemsFormat() {
		return itemsFormat;
	}

	public static void setSpells() {

		String[] FireBall = { "Fire Ball", "Fire", "20", "1", "20",
				"0" };

		setSpell(FireBall);

	}

	public static void setNpcs() {

		String[] Jacob = { "Jacob", "16, 13", "5", "5", "jacob.txt" };

		String[] JacobDialouge = { "Hello, welcome to our game!", "END",
				"Bonjour, bienvenue à notre jeu!" };

		String[] Izak = { "Izak", "16, 12", "9", "9", "izak.txt" };

		String[] IzakDialouge = { "Hello, welcome to my game!", "END",
				"Bonjour, bienvenue sur mon jeu!" };

		// String[] Tobi = {
		// "Tobi", "14, 8", "13", "13", "tobi.txt"
		// };
		//
		// String[] TobiDialouge = {
		// "Tobi is a good boy!", "END",
		// "I was the only npc that had collision at one point.", "END",
		// "Whack-a-Mole-No-Jutsu"
		// };

		String[][] stats = { Jacob, Izak };
		String[][] Dialouges = { JacobDialouge, IzakDialouge };
		for (String[] stat : stats) {
			setNpcs(stat);
		}
		for (int i = 0; i < Dialouges.length; i++) {
			setDialouges(stats[i][0], Dialouges[i]);
		}

	}

	public static void setPlayers() {
		// public Player(String name, String gender, int lvl, int exp, int hp,
		// int str, int skl, int spd, int luk, int def, int wis, int gold, int
		// players, Spells[] spells, Item[] items) {
		String[] Kyro = { "Kyro", "Male", "1", "0", "100", "10", "10", "10",
				"10", "6", "7", "0", "0", "Fire Ball", "Sword, Sheild" };

		String[] Karen = { "Karen", "Female", "1", "0", "100", "10", "10",
				"10", "10", "6", "7", "0", "0", "Fire Ball", "Sword, Sheild" };

		String[] Mark = { "Mark", "Male", "1", "0", "100", "10", "10", "10",
				"10", "6", "7", "0", "0", "Fire Ball", "Sword, Sheild" };

		String[] Leslie = { "Leslie", "Female", "1", "0", "100", "10", "10",
				"10", "10", "6", "7", "0", "0", "Fire Ball", "Sword, Sheild" };

		String[][] Stats = { Kyro, Karen, Mark, Leslie };

		for (int i = 0; i < Stats.length; i++) {
			//setPlayers(i, Stats[i]);
		}
	}

	public static void setEnemies() {

		String[] GSlimeStats = {
				// Name, Co-ord, Gender, Lvl, Exp, Hp, Str, Skl, Spd, Luk, Def,
				// Gold, Resistance, Condition, null for now, X & Y
				"Green Slime", "12,12", "1", "1", "10", "1", "1", "1", "1",
				"1", "1", "5", "null", "null", "1, 25, 1, 25" };

		String[] RSlimeStats = { "Red Slime", "12,13", "2", "5", "20", "5",
				"1", "2", "1", "3", "1", "10", "Fire", "null", "1, 50, 26, 50" };

		String[] BSlimeStats = { "Blue Slime", "12,14", "3", "10", "50", "9",
				"3", "6", "5", "7", "1", "15", "Water, Ice", "null", "1, 75, 51, 75" };

		String[] GGoblinStats = { "Green Goblin", "16,8", "1", "10", "200",
				"7", "2", "3", "1", "7", "1", "1000", "null", "null", "1, 25, 1, 25" };

		String[] RGoblinStats = { "Fire Goblin", "16,10", "5", "100", "1000",
				"70", "20", "30", "10", "70", "10", "10000", "Fire",
				"Fire Ball", "1, 50, 26, 50" };

		String[] BGoblinStats = { "Water Goblin", "16,11", "10", "1000",
				"10000", "700", "200", "300", "100", "700", "100", "10000",
				"Ice, Water", "null", "1, 75, 51, 75" };

		String[] PUndeadStats = { "Undead Priest", "12,9", "100", "10000",
				"100000", "7000", "2000", "3000", "1000", "7000", "10000",
				"100000", "Ice, Dark, Posion", "Fire Ball", "0, 0, 0, 0" };
		
//		String[] PUndeadStats = { "Undead Priest", "12,9", "100", "10000",
//				"100000", "7000", "2000", "3000", "1000", "7000", "10000",
//				"100000", "Ice, Dark, Posion", "Fire Ball", "0, 0" };

		/*
		 * Name = Undead Knight Frame = 12,11 Gender = Female Level = 10
		 * Experience = 10000 Health = 10000 Strength = 700 Skill = 200 Speed =
		 * 300 Luck = 100 Defense = 700 Gold = 10000 Resistance = Physical,
		 * Dark, Posion Spells = Heal
		 */

		String[][] stats = { GSlimeStats, RSlimeStats, BSlimeStats,
				GGoblinStats, RGoblinStats, BGoblinStats, PUndeadStats };

		for (String[] stat : stats) {
			setEnemy(stat);
		}

	}

	public static void SetItems() {
		// public Item(String name, int damage, int cost, Element element,
		// String type, Spells spelleffect)
		String[] Sword = { "Sword", "1", "1", Element.Physical, "Right Hand",
				"null" };

		String[] Sheild = { "Shield", "1", "1", Element.Physical, "Left Hand",
				"null", "" };

		String[] Pauldrens = {
				"Pauldrens", "1", "1", Element.Physical, "Shoulders", "null"
		};
		
		String[] Chestguard = { "Chest Plate", "1", "1", Element.Physical,
				"Upper Torso", "null" };

		String[] LegGuards = { "Leg Plate", "1", "1", Element.Physical,
				"Legs", "null" };

		String[] Boots = { "Boots", "1", "1", Element.Physical, "Feet",
				"null" };

		String[] Gloves = { "Gloves", "1", "1", Element.Physical, "Hands",
				"null" };

		String[][] stats = { Sword, Sheild, Pauldrens, Chestguard, LegGuards, Boots,
				Gloves };

		for (String[] stat : stats) {
			setItems(stat);
		}

	}
	
	public static void SetEntities() {
		final String[] Humanoid = {
				"Humanoid {",
				"\tHead, Neck, Upper Torso, Shoulders, Arms,",
				"\tLower Torso, Hands, Legs, Feet",
				"}"
		};
		
		final String[] Angelic = {
				"Angelic {",
				"\tHead, Neck, Upper Torso, Shoulders, Wings,",
				"\tArms, Lower Torso, Hands, Legs, Feet",
				"}"
		};
		
		final String[] Slime = {
				"Slime {",
				"\tHead",
				"}"
		};
		
		final String[][] stats = {
				Humanoid, Angelic, Slime
		};
		
		for(String[] stat : stats) {
			setEntities(stat);
		}
		
	}

}
