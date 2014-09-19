package me.jacob.macdougall.files;

import java.io.*;
import java.util.List;

import me.jacob.macdougall.Destinyor;

public class FileLoader {
	
	// Allows the coder to only do Fileloader.CreateFiles, ReadFiles, WriteFiles in one method
	
	public static void CreateFolder(String location) {
		File file = new File(location);
		if(!file.exists()) {
		file.mkdir();
		}
	}
	
	public static void CreateFile(String location) {
			File file = new File(location);
			if(!file.exists()) {
				//WriteToFiles(location);
			//} else {
				boolean settings = false;
				boolean saves = false;
				boolean enemies = false;
				boolean spells = false;
                boolean dialouges = false;
				if(location.equals(Destinyor.DestinyorSettings)) {
					location = Destinyor.DestinyorFolder;
					settings = true;
				}
				if(location.equals(Destinyor.DestinyorSaves)) {
					location = Destinyor.DestinyorFolder;
					saves = true;
				}
				if(location.equals(Destinyor.DestinyorEnemies)) {
					location = Destinyor.DestinyorFolder;
					enemies = true;
				}
				if(location.equals(Destinyor.DestinyorSpells)) {
					location = Destinyor.DestinyorFolder;
					spells = true;
				}
                                
                                if(location.equals(Destinyor.DestinyorNpcs)) {
                                    location = Destinyor.DestinyorFolder;
                                    dialouges = true;
                                }
				File dir = new File(location);
				dir.mkdirs();
				if(settings) {
					location = Destinyor.DestinyorSettings;
				}
				if(saves) {
					location = Destinyor.DestinyorSaves;
				}
				if(enemies) {
					location = Destinyor.DestinyorEnemies;
				}
				if(spells) {
					location = Destinyor.DestinyorSpells;
				}
                if(dialouges) {
                	location = Destinyor.DestinyorNpcs;
                }
                WriteToFiles(location);
			}
	}
	
	public static void ReadFromFiles(String location) {
		
		if(location.equals(Destinyor.DestinyorSettings)) {
			Reader.ReadSettings(location);
		}
		
		if(location.equals(Destinyor.DestinyorEnemies)) {
			Reader.ReadEnemies(location);
		}
		
		if(location.equals(Destinyor.DestinyorSaves)) {
			Reader.ReadPlayer(location);
		}
		
		if(location.equals(Destinyor.DestinyorSpells)) {
			Reader.ReadSpells(location);
		}
                
                if(location.equals(Destinyor.DestinyorNpcs)) {
                    Reader.ReadNpcs(location);
                }
	}
	
	public static void WriteToFiles(String location) {
		
		if(location.equals(Destinyor.DestinyorSettings)) {
			Writer.writeSettingFile(location);
		}
		if(location.equals(Destinyor.DestinyorSaves)) {
			Writer.writeDefaultSaveFile(location);
		}
		
		if(location.equals(Destinyor.DestinyorEnemies)) {
			Writer.WriteEnemyFile(location);
		}
		
		if(location.equals(Destinyor.DestinyorSpells)) {
			Writer.WriteSpellFile(location);
		}
                
                if(location.equals(Destinyor.DestinyorNpcs)) {
                    Writer.writeNpcs(location);
                }
	}
        
        public static String readCutscenes(String location) {
            return Reader.ReadCutscenes(location);
        }
        
        public static String readDialouges(String location) {
            return Reader.ReadDialouge(location);
        }
	
	public static boolean Override() {
		return Reader.Override;
	}
	
	public static boolean Debug() {
		return Reader.Debug;
	}

	public static List<String> EKeys() {
		return Reader.keys();
	}
	
	public static List<String> SpellKeys() {
		return Reader.spellKeys();
	}
        
        public static List<String> PKeys() {
            return Reader.PlayerKeys();
        }
    }