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
		bw = new BufferedWriter(new FileWriter(location + DestinyorFiles.fileSplit + "default.txt"));
		
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
                                	writeDialouge(DestinyorFiles.DestinyorDialougesFolder + DestinyorFiles.fileSplit + stats[j], stats[0]);
                                }
				}
			}
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
		 "Name = ","Frame = ", "Level = ", "Experience = ", "Health = ", "Strength = ", "Skill = ", "Speed = ", "Luck = ", "Defense = ", "Wisdom = ", "Gold = ", "Resistance = ", "Condition = ", "X&Y = "
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
