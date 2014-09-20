package me.jacob.macdougall.files;

import java.util.HashMap;
import java.util.Map;

import me.jacob.macdougall.magic.Element;

public class Default {
	
	// This is a class to load up the default enemies in to the FileWriter
	private static final Map<Integer, String[]> players = new HashMap<>();
	private static final Map<Integer, String[]> enemyStat = new HashMap<>();
	private static final Map<Integer, String[]> spells = new HashMap<>();
	private static final Map<Integer, String[]> npcs = new HashMap<>();
        private static final Map<String, String[]> npcDialouge = new HashMap<>();
        private static final Map<Integer, String[]> items = new HashMap<>();
	
//	public static String[] eKey = {
//			"GreenSlime", "RedSlime", "BlueSlime", "GreenGoblin", "RedGoblin", "BlueGoblin", "UndeadPriest"	
//	};
//	
//	public static String[] nKey = {
//		"The Doctor", "Jacob", "Izak", "Tobi"
//	};
//	
//	public static String[] sKey = {
//		"FireBall"
//	};
        
        public static void setItems(int key, String[] stats) {
            items.put(key, stats);
        }
        
        public static String[] getItems(int key) {
            return items.get(key);
        }
        
        public static int getItemLength() {
            return items.size();
        }
	
	public static String[] getStats(int key) {
		return enemyStat.get(key);
	}
        
        public static void setNpcs(int key, String[] stats) {
            npcs.put(key, stats);
        }
        
        public static String[] getNpcs(int key) {
            return npcs.get(key);
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
        
	public static void setStats(int key, String[] stats) {
		enemyStat.put(key, stats);
	}
	
	public static int getStatsLength() {
		return enemyStat.size();
	}
	
	public static String[] getSpell(int key) {
		return spells.get(key);
	}
	
	public static void setSpell(int key, String[] stats) {
		spells.put(key, stats);
	}
	
	public static int getSpellsLength() {
		return spells.size();
	}
	
	public static int getNpcLength() {
		return npcs.size();
	}
        
        public static void setPlayers(int key, String[] player) {
            players.put(key, player);
        }
        
        public static String[] getPlayers(int key) {
            return players.get(key);
        }
	
	public static void setSpells() {
		
		String[] FireBall = {
				"FireBall", "Fire Ball", "Fire", "20", "1", "20", "0"
		};
		
		setSpell(0, FireBall);
		
	}
	
	public static void setNpcs() {
//		String[] Doctor = {
//				"The Doctor", "0, 12", "1", "1", "Hello I am a test NPC called \"The Doctor\" nice to meet you"
//		};
                
//                String[] nullDialouge = {
//                    "null"
//                };
                
        String[] Jacob = {
        		"Jacob", "16, 13", "5", "5", "jacob.txt"
        };
         
        String[] JacobDialouge = {
            "Hello, welcome to our game!", "END", "Bonjour, bienvenue à notre jeu!"
        };
        
        String[] Izak = {
        		"Izak", "16, 12", "9", "9", "izak.txt"
        };
        
        String[] IzakDialouge = {
            "Hello, welcome to my game!", "END", "Bonjour, bienvenue sur mon jeu!"
        };
                
//        String[] Tobi = {
//        		"Tobi", "14, 8", "13", "13", "tobi.txt"
//        };
//        
//        String[] TobiDialouge = {
//            "Tobi is a good boy!", "END", "I was the only npc that had collision at one point.", "END", "Whack-a-Mole-No-Jutsu"
//        };
                
                String[][] Stats = {Jacob, Izak};
                String[][] Dialouges = {JacobDialouge, IzakDialouge};
        for(int i = 0; i < Stats.length; i++) {
        	setNpcs(i, Stats[i]);
		}
        for(int i = 0; i < Dialouges.length; i++) {
            setDialouges(Stats[i][0], Dialouges[i]);
        }
		
	}
	
        public static void setPlayers() {
            //public Player(String name, String gender, int lvl, int exp, int hp, int str, int skl, int spd, int luk, int def, int wis, int gold, int players, Spells[] spells, Item[] items) {
            String[] Kyro = {
                "Kyro", "Male", "1", "0", "100", "10", "10", "10", "10", "6", "7", "0", "0", "Fire Ball", "Sword, Sheild"
            };
            
            String[] Karen = {
                "Karen", "Female", "1", "0", "100", "10", "10", "10", "10", "6", "7", "0", "0", "Fire Ball", "Sword, Sheild"
            };
            
            String[] Mark = {
                "Mark", "Male", "1", "0", "100", "10", "10", "10", "10", "6", "7", "0", "0", "Fire Ball", "Sword, Sheild"
            };
            
            String[] Leslie = {
                "Leslie", "Female", "1", "0", "100", "10", "10", "10", "10", "6", "7", "0", "0", "Fire Ball", "Sword, Sheild"
            };
            
            String[][] Stats = {
                Kyro, Karen, Mark, Leslie
            };
            
            for(int i = 0; i < Stats.length; i++) {
		setPlayers(i, Stats[i]);
            }
        }
        
	public static void setEnemies() {
		
		//Entity enemy = new Entity(name, frame, gender, LVL, EXP, HP, STR, SKL, SPD, LUK, DEF, GOLD, Resistance, Condition);
		
		String[] GSlimeStats = {
			// Name, 		Co-ord, Gender, Lvl, Exp,  Hp,  Str, Skl, Spd, Luk, Def, Gold, Resistance, Condition, null for now
			"Green Slime", "12,12", "null", "1", "1", "10", "1", "1", "1", "1", "1", "5", "null", "null", "1, 25"
		};
		
		String[] RSlimeStats = {
			"Red Slime", "12,13", "null", "2", "5", "20", "5", "1", "2", "1", "3", "10", "Fire", "null", "26, 50"
		};
		
		String[] BSlimeStats = {
			"Blue Slime", "12,14", "null", "3", "10", "50", "9", "3", "6", "5", "7", "15", "Water, Ice", "null", "51, 75"
		};
			
		String[] GGoblinStats = {
			"Green Goblin", "16,8", "Male", "1", "10", "200", "7", "2", "3", "1", "7", "1000", "null", "null", "25, 25"
		};
		
		String[] RGoblinStats = {
			"Fire Goblin", "16,10", "Male", "5", "100", "1000", "70", "20", "30", "10", "70", "10000", "Fire", "Fire Ball", "25, 25"
		};
			
		String[] BGoblinStats = {
			"Water Goblin", "16,11", "Female", "10", "1000", "10000", "700", "200", "300", "100", "700", "10000", "Ice, Water", "null", "25, 25"
		};
		
		String[] PUndeadStats = {
			"Undead Priest", "12,9", "Male", "100", "10000", "100000", "7000", "2000", "3000", "1000", "7000", "100000", "Ice, Dark, Posion", "Fire Ball", "0, 0"
		};
		
		/*
		 * 	Name = Undead Knight
		 *	Frame = 12,11
		 *	Gender = Female
		 *	Level = 10
		 *	Experience = 10000
		 *	Health = 10000
		 *	Strength = 700
		 *	Skill = 200
		 *	Speed = 300
		 *	Luck = 100
		 *	Defense = 700
		 *	Gold = 10000
		 *	Resistance = Physical, Dark, Posion
		 *	Spells = Heal
		 */
		
		String[][] Stats = {
				GSlimeStats, RSlimeStats, BSlimeStats, GGoblinStats, RGoblinStats, BGoblinStats, PUndeadStats
		};
		
		for(int i = 0; i < Stats.length; i++) {
		setStats(i, Stats[i]);
		}
		
	}
        
        public static void SetItems() {
            //public Item(String name, int damage, int cost, Element element, String type, Spells spelleffect)
            String[] Sword = {
                "Sword", "1", "1", Element.Physical, "Weapon", "null" 
            };
            
            String[] Sheild = {
                "Sheild", "1", "1", Element.Physical, "Armour", "null"
            };
            
            String[] Chestguard = {
                "Chest Plate", "1", "1", Element.Physical, "Armour", "null"
            };
            
            String[] LegGuards = {
                "Leg Plate", "1", "1", Element.Physical, "Armour", "null"
            };
            
            String[] Boots = {
                "Boots", "1", "1", Element.Physical, "Armour", "null"
            };
            
            String[] Gloves = {
                "Gloves", "1", "1", Element.Physical, "Armour", "null"
            };
            
            String[][] Stats = {
				Sword, Sheild, Chestguard, LegGuards, Boots, Gloves
		};
            
            for(int i = 0; i < Stats.length; i++) {
		setItems(i, Stats[i]);
            }
            
        }
	
}
