package me.jacob.macdougall.files;

import graphic.engine.window.Resolution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.player.Player;
import me.jacob.macdougall.quests.Quest;


public class Writer {
	
	private static BufferedWriter bw;
	
	public static void writeSettingFile(String location) {
		
		String widthSetting = "Width = " + Resolution.width();
		String heightSetting = "Height = " + Resolution.height();
		String fullscreen = "Window Mode = " + Resolution.Fullscreen;
		
		//BufferedWriter bw;
		
		try {
			bw = new BufferedWriter(new FileWriter(location));
		
			bw.write(widthSetting);
			bw.newLine();
			bw.write(heightSetting);
			bw.newLine();
			bw.write(fullscreen);
		
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeCutscene(String location) {
		try {
		bw = new BufferedWriter(new FileWriter(location + Destinyor.fileSplit + "default.txt"));
		
		bw.write("X = 7, Y = 7, Level = 1:");
		bw.newLine();
		bw.write("Jacob: left, left, left, down, down, jacob.txt, jacob.txt, up, up, right, right, right:");
		bw.newLine();
		bw.write("Izak: right, up, up, izak.txt, izak.txt, down, down, left:");
		//bw.newLine();
		
		bw.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeNpcs(String location) {
		try {
		bw = new BufferedWriter(new FileWriter(location));
		
                String[] Stats = { "Name = ", "Frames = ", "X = ", "Y = ", "Dialouge Location = "};
                
                String Format = "NPCs";
                
			bw.write(Format + " #Note for Modding: Copy and paste the npc characteristics than alter it, also always leave a space between npcs");
			
			for(int i = 0; i < Default.getNpcLength(); i++) {
				String[] stats = Default.getNpcs(i);
                                bw.newLine();
				for(int j = 0; j < stats.length; j++) {
				bw.newLine();
				bw.write(Stats[j]);
				bw.write(stats[j]);
                                if(stats[j].contains(".txt")) {
                                	writeDialouge(Destinyor.DestinyorDialougesFolder + Destinyor.fileSplit + stats[j], stats[0]);
                                    //if(Destinyor.home.contains("\\"))
                                       // writeDialouge(Destinyor.DestinyorDialougesFolder + "\\" + stats[j], stats[0]);
                                    //else
                                       // writeDialouge(Destinyor.DestinyorDialougesFolder + "//" + stats[j], stats[0]);
                                }
				}
			}
//		NPC.npcs.put("Jacob", new NPC("Jacob", "0,12", 5, 5, 0, "Hello I'm Jacob and thank you for playing the game.", true));
//		NPC.npcs.put("Izak", new NPC("Izak", "0,12", 9, 9, 0, "Hello I'm Izak and thanks for playing my game.", true));
//		NPC.npcs.put("Kenpachi", new NPC("Kenpachi", "0,12", 13, 13, 0, "HAHAHAHAHAHAHA! END A fight I love a good fight!", false));
//		NPC.npcs.put("Zabuza", new NPC("Zabuza", "0,12", 17, 17, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Itachi", new NPC("Itachi", "0,12", 21, 21, 0, "You lack hatred!", false));
//		NPC.npcs.put("Yamamoto", new NPC("Yamamoto", "0,12", 25, 25, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Jim", new NPC("Jim", "0,12", 29, 29, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Pam", new NPC("Pam", "0,12", 33, 33, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Scott", new NPC("Scott", "0,12", 37, 37, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Kevin", new NPC("Kevin", "0,12", 41, 41, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Oscar", new NPC("Oscar", "0,12", 45, 45, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Angela", new NPC("Angela", "0,12", 49, 49, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Stanely", new NPC("Stanely", "0,12", 53, 53, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Tobi", new NPC("Tobi", "0,12", 57, 57, 0, "I'm a good boy END I was the only npc to have collision at one point",false));
//		NPC.npcs.put("Bob", new NPC("Bob", "0,12", 20, 20, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Mark", new NPC("Mark", "0,12", 24, 24, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Dylan", new NPC("Dylan", "0,12", 69, 69, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Ray", new NPC("Ray", "0,12", 73, 73, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Molly", new NPC("Molly", "0,12", 77, 77, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Amy", new NPC("Amy", "0,12", 81, 81, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Rei", new NPC("Rei", "0,12", 85, 85, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Kelly", new NPC("Kelly", "0,12", 89, 89, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Ryan", new NPC("Ryan", "0,12", 93, 93, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Alessa", new NPC("Alessa", "0,12", 97, 97, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Sam", new NPC("Sam", "0,12", 101, 101, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Nick", new NPC("Nick", "0,12", 105, 105, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Cosmo", new NPC("Cosmo", "2,15", 109, 109, 0, "BLUE IS A COLOUR! END Hey I found a nickel!", false));
//		NPC.npcs.put("Wanda", new NPC("Wanda", "1,15", 113, 113, 0, "Hello I'm Wanda and I'll be your new fairy god-parent!", false));
//		NPC.npcs.put("John", new NPC("John", "1,15", 117, 117, 117, "117", false));
//		NPC.npcs.put("Steve", new NPC("Steve", "0,12", 121, 121, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Michael", new NPC("Michael", "0,12", 125, 125, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Stanely", new NPC("Stanely", "0,12", 129, 129, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Geoff", new NPC("Geoff", "0,12", 133, 133, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Micheal", new NPC("Micheal", "0,12", 137, 137, 0, "SHUT THE FUCK UP GAVIN!!", false));
//		NPC.npcs.put("Jack", new NPC("Jack", "0,12", 141, 141, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Gavin", new NPC("Gavin", "0,12", 145, 145, 0, "Hello END Bonjour", false));
//		NPC.npcs.put("Lewis", new NPC("Lewis", "0,12", 149, 149, 0, "Hello and welcome to the yogpod!", false));
//		NPC.npcs.put("Simon", new NPC("Simon", "0,12", 153, 153, 0, "Diggy diggy!", false));
//		NPC.npcs.put("Gary", new NPC("Gary", "0,12", 157, 157, 0, "Hello END Bonjour"));
//		NPC.npcs.put("Neal", new NPC("Neal", "0,12", 161, 161, 0, "Hello END Bonjour"));
//		NPC.npcs.put("Leo", new NPC("Leo", "0,12", 165, 165, 0, "Hello END Bonjour"));
//		NPC.npcs.put("Lucy", new NPC("Lucy", "0,12", 169, 169, 0, "Hello END Bonjour"));
//		NPC.npcs.put("Nise", new NPC("Nise", "0,12", 173, 173, 0, "Hello END Bonjour"));
//		NPC.npcs.put("Sue", new NPC("Sue", "0,12", 177, 177, 0, "Hello END Bonjour"));
//		NPC.npcs.put("Joyce", new NPC("Joyce", "0,12", 181, 181, 0, "Hello END Bonjour"));
//		NPC.npcs.put("Elizibeth", new NPC("Elizibeth", "0,12", 185, 185, 0, "Hello END Bonjour"));
//		NPC.npcs.put("Mikhaila", new NPC("Mikhaila", "0,12", 189, 189, 0, "Hello END Bonjour"));
//		NPC.npcs.put("Sydnie", new NPC("Sydnie", "0,12", 193, 193, 0, "Hello END Bonjour"));
//		NPC.npcs.put("Caryn", new NPC("Caryn", "0,12", 197, 197, 0, "Hello END Bonjour"));
//		NPC.npcs.put("Mortimer", new NPC("Mortimer", "0,12", 201, 201, 0, "Hello END Bonjour"));
//		NPC.npcs.put("Aretha", new NPC("Aretha", "0,12", 205, 205, 0, "Hello END Bonjour"));
//		NPC.npcs.put("Hiram", new NPC("Hiram", "0,12", 209, 209, 0, "Hello END Bonjour"));
//		NPC.npcs.put("Emmett", new NPC("Emmett", "0,12", 213, 213, 0, "Hello END Bonjour"));
//		NPC.npcs.put("Triston", new NPC("Triston", "0,12", 217, 217, 0, "Hello END Bonjour"));
		bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
		public static void writeDialouge(String location, String name) {
			BufferedWriter bw;
			try {
	            bw = new BufferedWriter(new FileWriter(location));
	            
	            String[] stats = Default.getDialouges(name);
	            
	            for(int i = 0; i < stats.length; i++) {
	            	bw.write(stats[i]);
	            	bw.newLine();
	            }
	            bw.close();
	            
			} catch (IOException e) {
                e.printStackTrace();
            }
		}
		//public static void writeDialouge
        
//        public static void writeDialouge(String location, String name) {
//            System.out.println("Writing Dialouge of: " + location);
//            try {
//            bw = new BufferedWriter(new FileWriter(location));
//            
//            
//            for(int i = 0; i < Default.getDialougesLength(); i++) {
//                String[] stats = Default.getDialouges(name);
//                System.out.println(Default.getDialouges(name)[i]);
//                for(int j = 0; j < stats.length; j++) {
//                    bw.write(stats[j]);
//                    bw.newLine();
//                }
//            bw.close();
//            }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        
        public static void writeDefaultSaveFile(String location) {
        	int players = 4;
        	String player1Name = "Player Name = ";
    		String player1Gender = "Player Gender = ";
    		String player1Level = "Player Level = " + 1;
    		String player1Exp = "Player Experience = " + 0;
    		String player1Hp = "Player Health = " + 100;
    		String player1Str = "Player Strength = " + 10;
    		String player1Skl = "Player Skill = " + 10;
    		String player1Spd = "Player Speed = " + 10;
    		String player1Luk = "Player Luck = " + 10;
    		String player1Def = "Player Defense = " + 10;
            String player1Wis = "Player Wisdom = " + 10;
    		String player1Gold = "Player Gold = " + 0;
    		String player1Resistance = "Player Resistances = ";
    		
    		String name; //= "";
    		String gender; //= "";
    		
    		String player1Spells = "Player Spells = ";
    		String player1XY = "X & Y = " + Player.X + "," + Player.Y;
    		try {
    			
    			bw = new BufferedWriter(new FileWriter(location));  
                            bw.write("Players");
                            bw.newLine();
                            bw.write("Level = 1");
                            bw.newLine();
                            bw.write(player1XY);
    			for(int i = 0; i < players; i++) {
                                //Player p;
                                switch(i) {
                                    case 0: 
                                        name = "Kyro";
                                        gender = "Male";
                                        break;
                                    case 1: 
                                        name = "Karen";
                                        gender = "Female";
                                        break;
                                    case 2:
                                        name = "Mark";
                                        gender = "Male";
                                        break;
                                    case 3: 
                                        name = "Lesie";
                                        gender = "Female";
                                        break;
                                        
                                    default:
                                        name = "SEND HELP";
                                        gender = "HELP";
                                }
			bw.newLine();
			bw.newLine();
			bw.write(player1Name + name);
			bw.newLine();
			bw.write(player1Gender + gender);
			bw.newLine();
			bw.write(player1Level);
			bw.newLine();
			bw.write(player1Exp);
			bw.newLine();
			bw.write(player1Hp);
			bw.newLine();
			bw.write(player1Str);
			bw.newLine();
			bw.write(player1Skl);
			bw.newLine();
			bw.write(player1Spd);
			bw.newLine();
			bw.write(player1Luk);
			bw.newLine();
			bw.write(player1Def);
			bw.newLine();
			bw.write(player1Wis);
			bw.newLine();
			bw.write(player1Gold);
			bw.newLine();
			bw.write(player1Resistance);
			bw.newLine();
			bw.write(player1Spells);
			bw.newLine();
			bw.write("inParty = true");
			//bw.newLine();
			
			            }
			
			bw.close();
			
			} catch (IOException e) {
			e.printStackTrace();
			}
        }
	
	public static void writeSaveFile(String location) {
            
            int players = 4;
		String player1Name = "Player Name = ";
		String player1Gender = "Player Gender = ";
		String player1Level = "Player Level = " + 1;
		String player1Exp = "Player Experience = " + 0;
		String player1Hp = "Player Health = " + 100;
		String player1Str = "Player Strength = " + 10;
		String player1Skl = "Player Skill = " + 10;
		String player1Spd = "Player Speed = " + 10;
		String player1Luk = "Player Luck = " + 10;
		String player1Def = "Player Defense = " + 10;
                String player1Wis = "Player Wisdom = " + 10;
		String player1Gold = "Player Gold = " + 0;
		String player1Resistance = "Player Resistances = ";
                
                int level = 1;
                int exp = 0;
                int hp = 100;
                int str = 10;
                int skl = 10;
                int spd = 10;
                int luk = 10;
                int def = 10;
                int wis = 10;
                int gold = Player.Gold;
                //String resist;
		
                String name = "Kyro";
                String gender = "Male";
		
		String player1Spells = "Player Spells = ";
		String player1XY = "Player X & Y = " + Player.X + "," + Player.Y;
		
		try {
			
			bw = new BufferedWriter(new FileWriter(location));  
                        bw.write("Players");
			for(int i = 0; i < players; i++) {
                            Player p;
                            switch(i) {
                                case 0: 
                                    name = "Kyro";
                                    gender = "Male";
                                    p = Player.getPlayer(Player.names.get(i));
                                    level = p.Lvl;
                                    exp = p.Exp;
                                    hp = p.Hp;
                                    str = p.Str;
                                    skl = p.Skl;
                                    spd = p.Spd;
                                    luk = p.Luk;
                                    def = p.Def;
                                    wis = p.Wis;
                                    break;
                                case 1: 
                                    name = "Karen";
                                    gender = "Female";
                                    p = Player.getPlayer(Player.names.get(i));
                                    level = p.Lvl;
                                    exp = p.Exp;
                                    hp = p.Hp;
                                    str = p.Str;
                                    skl = p.Skl;
                                    spd = p.Spd;
                                    luk = p.Luk;
                                    def = p.Def;
                                    wis = p.Wis;
                                    break;
                                case 2:
                                    name = "Mark";
                                    gender = "Male";
                                    p = Player.getPlayer(Player.names.get(i));
                                    level = p.Lvl;
                                    exp = p.Exp;
                                    hp = p.Hp;
                                    str = p.Str;
                                    skl = p.Skl;
                                    spd = p.Spd;
                                    luk = p.Luk;
                                    def = p.Def;
                                    wis = p.Wis;
                                    break;
                                case 3: 
                                    name = "Lesie";
                                    gender = "Female";
                                    p = Player.getPlayer(Player.names.get(i));
                                    level = p.Lvl;
                                    exp = p.Exp;
                                    hp = p.Hp;
                                    str = p.Str;
                                    skl = p.Skl;
                                    spd = p.Spd;
                                    luk = p.Luk;
                                    def = p.Def;
                                    wis = p.Wis;
                                    break;
                                
                            }
                        bw.newLine();
                        bw.newLine();
			bw.write(player1Name + name);
			bw.newLine();
			bw.write(player1Gender + gender);
			bw.newLine();
			bw.write(player1Level + level);
			bw.newLine();
			bw.write(player1Exp + exp);
			bw.newLine();
			bw.write(player1Hp + hp);
			bw.newLine();
			bw.write(player1Str + str);
			bw.newLine();
			bw.write(player1Skl + skl);
			bw.newLine();
			bw.write(player1Spd + spd);
			bw.newLine();
			bw.write(player1Luk + luk);
			bw.newLine();
			bw.write(player1Def + def);
			bw.newLine();
                        bw.write(player1Wis + wis);
                        bw.newLine();
			bw.write(player1Gold + gold);
			bw.newLine();
			bw.write(player1Resistance);
			bw.newLine();
			bw.write(player1Spells);
			bw.newLine();
			bw.write(player1XY);
			
                        }
			
			bw.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void WriteEnemyFile(String location) {
		
		String[] Format = {
			"Enemies", "{", "}"
		};
		
		String[] Stats = {
		 "Name = ","Frame = ", "Gender = ", "Level = ", "Experience = ", "Health = ", "Strength = ", "Skill = ", "Speed = ", "Luck = ", "Defense = ", "Gold = ", "Resistance = ", "Condition = ", "X&Y = "
		};
		
		//BufferedWriter bw;
		
		try {
			
			bw = new BufferedWriter(new FileWriter(location));
			
			bw.write(Format[0] + " #Note for Modding: Copy and paste the enemy characteristics than alter it, also always leave a space between enemies");
			//bw.newLine();
			
			
			for(int i = 0; i < Default.getStatsLength(); i++) {
				String[] stats = Default.getStats(i);
                                bw.newLine();
				for(int j = 0; j < stats.length; j++) {
					//if(stats[j].equals(stats[0])) {
						
						//bw.newLine();
						//bw.write(Default.eKey[i]);
					//}
				bw.newLine();
				bw.write(Stats[j]);
				bw.write(stats[j]);
				}
			}
		
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void WriteSpellFile(String location) {
		String[] Format = {
				"Enemies", "{", "}"
		};
		
		//BufferedWriter bw;
		
		try {
			bw = new BufferedWriter(new FileWriter(location));
			
			bw.write(Format[0]);
			bw.newLine();
			bw.newLine();
			
			String[] Spells = Default.getSpell(0);
			for(int j = 0; j < Spells.length; j++) {
			bw.write(Spells[j]);
			bw.newLine();
			}
			
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
        
        public static void WriteItemFile(String location) {
            BufferedWriter bw;
            try {
            bw = new BufferedWriter(new FileWriter(location));
             //public Item(String name, int damage, int cost, String attribute, Element element, String type, boolean equipped, String user) {
            bw.write("Items");
            bw.newLine();
            //bw.newLine();
            for(int i = 0; i < Default.getItemLength(); i++) {
                bw.newLine();
                String[] Item = Default.getItems(i);
                for(int j = 0; j < Item.length; j++) {
                    bw.write(Item[j]);
                    bw.newLine();
                }
            }
            
            bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        public static void WriteQuests(String location) {
            try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(location));
            
            for(int i = 0; i < Quest.quests.size(); i++) {
                for(int j = 0; j < Quest.quests.get(i).bosses.length; j++)
                    bw.write(Quest.quests.get(i).bosses[j].name);
                for(int j = 0; j < Quest.quests.get(i).npcs.length; j++)
                    bw.write(Quest.quests.get(i).npcs[j].name);
                for(int j = 0; j < Quest.quests.get(i).rewards.length; j++)
                    bw.write(Quest.quests.get(i).rewards[j].name);
            }
            bw.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
}
