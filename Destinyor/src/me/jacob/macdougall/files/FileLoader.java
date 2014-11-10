package me.jacob.macdougall.files;

import java.io.*;
import java.util.List;

public class FileLoader {

	// Allows the coder to only do Fileloader.CreateFiles, ReadFiles, WriteFiles in one method

	public static void CreateFolder(String location) {
		File file = new File(location);
		if(!file.exists()) {
			file.mkdir();
		}
	}

	/**
	 * Creates the folder if it needs to be created and returns the folder, if the folder already exists it just returns the folder.
	 * @param location
	 * @return
	 */
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
			String path;

			path = location;
			location = DestinyorFiles.DestinyorFolder;
			File dir = new File(location);
			dir.mkdirs();

			location = path;
			WriteToFiles(location);
		}
	}
	
	public static void CreateDefaultFile(String location, String name, String[][] STATS, String[] format) {
		File file = new File(location);
		if(!file.exists()) {
			
			file = CreateFolderAndReturn(DestinyorFiles.DestinyorFolder);
			
			Writer.WriteDefault(location, name, STATS, format);
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

		if(location.equals(DestinyorFiles.DestinyorItems)) {
			Reader.ReadItems(location);
		}
	}

	public static void WriteToFiles(String location) {

		if(location.equals(DestinyorFiles.DestinyorSettings)) {
			Writer.writeSettingFile(location);
		}
		if(location.equals(DestinyorFiles.DestinyorCharacters)) {
			Writer.writeDefaultCharacterFile(location);
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

	public static List<String> PKeys() {
		return Reader.PlayerKeys();
	}
}