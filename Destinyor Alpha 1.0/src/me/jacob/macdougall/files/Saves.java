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
		File file = new File(Destinyor.DestinyorFolder + Destinyor.fileSplit + "Save" + id + ".destinyor");
		Writer.writeSaveFile(file.getAbsolutePath());
	}
	
	public void getSaves() {
		File saves = new File(Destinyor.DestinyorSaves);
		while(saves.exists()) {
			saves = new File(Destinyor.DestinyorFolder + Destinyor.fileSplit + "Save" + no + ".destinyor");
			no++;
		}
	}
	
	
}
