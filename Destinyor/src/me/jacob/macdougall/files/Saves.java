package me.jacob.macdougall.files;

import java.io.File;

import me.jacob.macdougall.Destinyor;

public class Saves {
	
	private int id = -1;
	private static int no = 0;
	
	//public static final String DestinyorSaves = DestinyorFolder + fileSplit + "Save" + ".destinyor";
	
	public Saves(int id) {
		this.id = id;
		no++;
	}
	
	public void Save() {
		File file = new File(DestinyorFiles.DestinyorFolder + DestinyorFiles.fileSplit + "Save" + id + ".destinyor");
		Writer.writeSaveFile(file.getAbsolutePath());
	}
	
	public void getSaves() {
		File saves = new File(DestinyorFiles.DestinyorCharacters);
		while(saves.exists()) {
			saves = new File(DestinyorFiles.DestinyorFolder + DestinyorFiles.fileSplit + "Save" + no + ".destinyor");
			no++;
		}
	}
	
	
}
