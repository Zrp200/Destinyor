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
	
	public static File CreateFolderAndReturn(String location) {
		File file = new File(location);
		if(!file.exists()) {
		file.mkdir();
		}
		return file;
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
				if(location.equals(DestinyorFiles.DestinyorSettings)) {
					location = DestinyorFiles.DestinyorFolder;
					settings = true;
				}
				if(location.equals(DestinyorFiles.DestinyorCharacters)) {
					location = DestinyorFiles.DestinyorFolder;
					saves = true;
				}
				if(location.equals(DestinyorFiles.DestinyorEnemies)) {
					location = DestinyorFiles.DestinyorFolder;
					enemies = true;
				}
				if(location.equals(DestinyorFiles.DestinyorSpells)) {
					location = DestinyorFiles.DestinyorFolder;
					spells = true;
				}
                                
                                if(location.equals(DestinyorFiles.DestinyorNpcs)) {
                                    location = DestinyorFiles.DestinyorFolder;
                                    dialouges = true;
                                }
				File dir = new File(location);
				dir.mkdirs();
				if(settings) {
					location = DestinyorFiles.DestinyorSettings;
				}
				if(saves) {
					location = DestinyorFiles.DestinyorCharacters;
				}
				if(enemies) {
					location = DestinyorFiles.DestinyorEnemies;
				}
				if(spells) {
					location = DestinyorFiles.DestinyorSpells;
				}
                if(dialouges) {
                	location = DestinyorFiles.DestinyorNpcs;
                }
                WriteToFiles(location);
			}
	}
	
	public static void ReadFromFiles(String location) {
		
		if(location.equals(DestinyorFiles.DestinyorSettings)) {
			Reader.ReadSettings(location);
		}
		
		if(location.equals(DestinyorFiles.DestinyorEnemies)) {
			Reader.ReadEnemies(location);
		}
		
		if(location.equals(DestinyorFiles.DestinyorCharacters)) {
			Reader.ReadPlayer(location);
		}
		
		if(location.equals(DestinyorFiles.DestinyorSpells)) {
			Reader.ReadSpells(location);
		}
                
                if(location.equals(DestinyorFiles.DestinyorNpcs)) {
                    Reader.ReadNpcs(location);
                }
	}
	
	public static void WriteToFiles(String location) {
		
		if(location.equals(DestinyorFiles.DestinyorSettings)) {
			Writer.writeSettingFile(location);
		}
		if(location.equals(DestinyorFiles.DestinyorCharacters)) {
			Writer.writeDefaultSaveFile(location);
		}
		
		if(location.equals(DestinyorFiles.DestinyorEnemies)) {
			Writer.WriteEnemyFile(location);
		}
		
		if(location.equals(DestinyorFiles.DestinyorSpells)) {
			Writer.WriteSpellFile(location);
		}
                
                if(location.equals(DestinyorFiles.DestinyorNpcs)) {
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